package insung.moving.customer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import insung.moving.customer.activity.MainActivity;
import insung.moving.customer.app.MyApplication;
import insung.moving.customer.service.resultInterface.BaseInterface;
import insung.moving.customer.service.resultInterface.GetDorderForCustInterface;
import insung.moving.customer.service.resultInterface.GetMapAddrInterface;
import insung.moving.customer.service.resultInterface.GetVersionCustInterface;
import insung.moving.customer.service.resultInterface.InsertDorderForCustCInterface;
import insung.moving.customer.service.resultInterface.Pst_PingInterface;
import insung.moving.customer.temp.PROTOCOL;
import insung.moving.customer.util.Util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketService extends Service implements Runnable{

	private final int HANDLER_INTERNET_CLOSE 		= 1001;
	private final int HANDLER_FOREGROUND_MAIN 		= 1002;

	private final int HANDLER_NETWORK_ERROR		= 1003;
	private final int HANDLER_NETWORK_OK		= 1004;

	private final int HANDLER_DATA_ERROR		= 1005;
	private final int HANDLER_DATA_OK			= 1006;

	private final int HANDLER_LOADING			= 1007;

	private final int ACTIVITY_FOREGROUND_TIME		= 1200000;

	private Socket socket = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;

	private Thread recvThread = null;

	private boolean bServerConnect = false;

	private Message successMessage;
	private Message errorMessage;
	private BaseInterface baseInterface;
	private boolean dataSyncController = false;

	private final IBinder mBinder = new SocketServiceBinder() {};

	public class SocketServiceBinder extends Binder {
		public SocketService getService()
		{
			return SocketService.this; //현재 서비스를 반환.
		}
	}

	private void dataReceive(RecvPacket recvPacket, BaseInterface baseInterface) {
		if (recvPacket.ERROR != PROTOCOL.PE_OK) {
			if (baseInterface != null) {
				try {
					baseInterface.fail(recvPacket.COMMAND);
				} catch (Exception e) {

				}
			}
			return;
		}

		if (baseInterface != null) {
			baseInterface.success(recvPacket);
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		StartThread();
		return mBinder;
	}

	public void InterfaceDataSend(SendPacket Packet, BaseInterface baseInterface){

		switch (Packet.GetMessageType()) {
			// 지금은 프로토콜로 메세지를 지정해놨음
			case PROTOCOL.GET_DORDER_FOR_CUST:
				getDorderForCustInterface=(GetDorderForCustInterface) baseInterface;
				break;
			case PROTOCOL.GET_MAP_ADDR:
				getMapAddrInterface= (GetMapAddrInterface) baseInterface;
				break;
			case PROTOCOL.INSERT_DORDER_FOR_CUST_C:
				insertDorderForCustCInterface=(InsertDorderForCustCInterface) baseInterface;
				break;
			case PROTOCOL.GET_VERSION_CUST:
				getVersionCustInterface=(GetVersionCustInterface) baseInterface;
				break;
			case PROTOCOL.PST_PING:
				pst_pingInterface=(Pst_PingInterface) baseInterface;
		}
		Send(Packet);
	}
	public void StartThread(){
		StartService(false);
	}

	public void StopThread(){
		StopService();
	}

	private GetDorderForCustInterface getDorderForCustInterface;
	private GetMapAddrInterface getMapAddrInterface;
	private GetVersionCustInterface getVersionCustInterface;
	private InsertDorderForCustCInterface insertDorderForCustCInterface;
	private Pst_PingInterface pst_pingInterface;



	@Override
	public void onCreate() {
		super.onCreate();
	}

	int nReConnectCount = 0;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if( msg.what == HANDLER_INTERNET_CLOSE ) {
				StartService(true);
			}else if(msg.what == PROTOCOL.HANDLER_MESSAGE_LOGIN_RESTART) {

				if ( nReConnectCount >= 10 )	{
					++nReConnectCount;				//SocketConnect에서 예외발생이 10회이상일때 안보내주기 위해서 값을 추가한다.
					bServerConnect = false;
					Toast.makeText( SocketService.this, "프로그램 종료 후 인터넷을 확인 후 연결해주세요.", Toast.LENGTH_LONG).show();
				} else {
					++nReConnectCount;
					Toast.makeText( SocketService.this, "재접속 카운트" + nReConnectCount + "회", Toast.LENGTH_SHORT).show();
				}
			}else if( msg.what == HANDLER_FOREGROUND_MAIN ) {
//				setPoregroundWindow();
				handler.sendEmptyMessageDelayed(HANDLER_FOREGROUND_MAIN, ACTIVITY_FOREGROUND_TIME);
			}else if( msg.what == HANDLER_NETWORK_ERROR ) {
//				setPoregroundWindow();
				bServerConnect = false;

				Intent intent = new Intent( MainActivity.INTENT_FILTER);
				intent.putExtra( MyApplication.networkIntentValue, false);
				sendBroadcast(intent);

			}else if( msg.what == HANDLER_NETWORK_OK ) {

				Intent intent = new Intent( MainActivity.INTENT_FILTER);
				intent.putExtra( MyApplication.networkIntentValue, true);
				sendBroadcast(intent);

			}else if( msg.what == HANDLER_LOADING ) {

				Intent intent = new Intent( MainActivity.INTENT_FILTER);
				intent.putExtra("loading", msg.arg1);
				sendBroadcast(intent);

			}else if( msg.what == HANDLER_DATA_OK ) {

				RecvPacket recvPacket = (RecvPacket)msg.obj;
	/*			baseInterface.success(recvPacket);
				baseInterface = null;*/


				// 추후 버전 체크 추가 시 여기 꼭 확인할 것
				switch (recvPacket.SUB_TYPE) {
					case PROTOCOL.GET_DORDER_FOR_CUST:
						dataReceive( recvPacket,getDorderForCustInterface);
						break;
					case PROTOCOL.GET_MAP_ADDR:
						dataReceive( recvPacket,getMapAddrInterface );
						break;
					case PROTOCOL.INSERT_DORDER_FOR_CUST_C:
						dataReceive( recvPacket, insertDorderForCustCInterface );
						break;
					case PROTOCOL.GET_VERSION_CUST:
						dataReceive( recvPacket,getVersionCustInterface);
						break;
					case PROTOCOL.PST_PING:
						dataReceive( recvPacket, pst_pingInterface);
						break;

				}
				//	dataReceive( recvPacket,getDorderForCustInterface);

			}else if( msg.what == HANDLER_DATA_ERROR ) {

				Exception e = (Exception)msg.obj;
				if(baseInterface !=null){

					baseInterface = null;
				}
			}
		}
	};

	/**
	 * 뷰의 위치가 화면 안에 있게 하기 위해서 검사하고 수정한다.
	 */
	public SocketService() {}
	public void StartService() {

		bServerConnect = true;
		recvThread = null;
		recvThread = new Thread(this);
		recvThread.start();
	}


	public void StartService(boolean bReStart)
	{
//		handler.removeMessages(HANDLER_INTERNET_CLOSE);

		if( bReStart == false ) {
			bServerConnect = true;
		}

		recvThread = null;
		recvThread = new Thread(this);
		recvThread.start();
	}

	public void StopService()
	{
//		handler.removeMessages(HANDLER_INTERNET_CLOSE);

		bServerConnect = false;

	}

	public void run() {
		if (SocketConnect() == true) {
			while (true) {
				try {
//					handler.removeMessages(HANDLER_INTERNET_CLOSE);
//					if( bServerConnect == true )
//						handler.sendEmptyMessageDelayed(HANDLER_INTERNET_CLOSE, 5000);

					byte HeaderData[] = new byte[28];
					if (in.read(HeaderData, 0, 28) == -1) {
						// 스트림의 끝까지 갔을때 -1을 리턴, 값을 받게 됬을때 데이터는 받아도 스트림은 끝나지 않음
						// 데이터가 올때까지 무한 대기 코드ㅇㅇ
						break;
					}
//
////
////
					RecvPacket rPacket = new RecvPacket();
					boolean bHeaderCheck = HeaderParsing(HeaderData, rPacket);

					if (bHeaderCheck == true) {
						int RecvSize = 0, nTotalRecv = 0;

						if (rPacket.PACKET_SIZE > 28)    //28 = 헤더 사이즈
						{
							byte Command[] = new byte[rPacket.PACKET_SIZE - 28];
							while (nTotalRecv < (rPacket.PACKET_SIZE - 28)) {
								RecvSize = in.read(Command, nTotalRecv, Command.length - nTotalRecv);
								if (RecvSize == -1) {
									break;
								}
								nTotalRecv += RecvSize;
							}

							if (RecvSize == -1) {
								break;
							}

							rPacket.COMMAND = new String(Command, 0, nTotalRecv, "ksc5601");
						}
						dataSyncController = false;

						SendSuccessMessage(rPacket);

//						baseInterface.success(rPacket);
//						baseInterface = null;
//						ActivityMessage(rPacket);
					}
				} catch (Exception e) {
					dataSyncController = false;

//					if(baseInterface !=null){
//						baseInterface.fail(e);
//						baseInterface = null;
//					}
					SendErrorMessage(e);

					Log.d("INSUNG", "INSUNG RECV = " + e.getMessage());
//					if(!Connectivity.isConnected(SocketService.this)){
					bServerConnect = false;
					handler.sendEmptyMessageDelayed(HANDLER_NETWORK_ERROR, 3000);
//					}


					break;
				}
			}
		}
	}

	public boolean SocketConnect() {
		nReConnectCount = 0;

		while(bServerConnect) {
			SocketDisConnect();

			try {
				SocketAddress socketAddr = new InetSocketAddress( MyApplication.SERVER_IP, MyApplication.SERVER_PORT);
				socket = new Socket();
				socket.connect(socketAddr, 3000);

				//핑

				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());

				handler.sendEmptyMessage(HANDLER_NETWORK_OK);

				return true;
			} catch (Exception e) {
				try {
					Thread.sleep( 3000 );
				} catch (Exception a) {
				}

				bServerConnect = false;

				handler.sendEmptyMessage( HANDLER_NETWORK_ERROR );
			}
		}

		return false;
	}

	public void SocketDisConnect() {
		try {
			if( out != null ) {	out.close(); out = null; }
			if( in != null ) { in.close(); in = null; }
			if( socket != null ) { socket.close(); socket = null; }
		} catch (Exception e) {
			Log.d("INSUNG","INSUNG DISCONNECT = " + e.getMessage());
		}
	}

	synchronized public void Send(SendPacket sPacket)	{
		if( out == null ) return;

		try {
			out.write(sPacket.GetData(), 0, sPacket.GetPacketSize());
			out.flush();

		} catch (Exception e) {
//			bServerConnect = false;
//			handler.sendEmptyMessageDelayed(HANDLER_NETWORK_ERROR,3000);
			Log.d("INSUNG","INSUNG SEND = " + e.getMessage());
		}


	}
	private void SendSuccessMessage(RecvPacket recvPacket){
		successMessage = Message.obtain();
		successMessage.what = HANDLER_DATA_OK;
		successMessage.obj = recvPacket;
		handler.sendMessage(successMessage);
	}
	private void SendErrorMessage(Exception e){
		errorMessage = Message.obtain();
		errorMessage.what = HANDLER_DATA_ERROR;
		errorMessage.obj = e;
		handler.sendMessage(errorMessage);
	}

	public boolean HeaderParsing(byte Data[], RecvPacket rPacket) {
		int Header[] = new int[6];

		byte Temp[] = new byte[4];
		Temp[0] = Data[0];
		Temp[1] = Data[1];
		Temp[2] = Data[2];
		Temp[3] = Data[3];

		String HEAD = new String(Temp);
		if(HEAD.equals("SISD") == false) return false;
		rPacket.HEAD = HEAD;
		int Index = 0;
		for(int i=4;i<28;i+=4) {
			Header[Index] = (int)Data[i];
			Header[Index] += ((int)Data[i+1]) << 8;
			Header[Index] += ((int)Data[i+2]) << 16;
			Header[Index] += ((int)Data[i+3]) << 24;
			Header[Index] = Util.htonl(Header[Index]);
			++Index;
		}

		rPacket.PACKET_SIZE = Header[0];
		rPacket.TYPE = Header[1];
		rPacket.SUB_TYPE = Header[2];
		rPacket.ERROR = Header[3];
		rPacket.NROW = Header[4];
		rPacket.MSG_TYPE = Header[5];

		return true;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//리무브 메세지
		super.onDestroy();
	}
}