package insung.moving.customer.service;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import insung.moving.customer.app.MyApplication;
import insung.moving.customer.service.resultInterface.GetDorderForCustInterface;
import insung.moving.customer.service.resultInterface.GetMapAddrInterface;
import insung.moving.customer.service.resultInterface.GetVersionCustInterface;
import insung.moving.customer.service.resultInterface.InsertDorderForCustCInterface;
import insung.moving.customer.service.resultInterface.Pst_PingInterface;

/**
 * Created by Administrator on 2018-07-04.
 */

public class NetworkPresenter implements NetworkPresenterInterface {
    // 사용 할 전체 메서드의 구현 클래스

    /**
     * 전역 데이터를 사용 할 함수는 해당 클래스에서 패킷을 작성하도록 하자
     * ex) ccCode, mCode 같은 액티비티와 관련 없는 데이터들..GPS 값 전송 같은
     * <p>
     * 액티비티에서 전송 할 데이터가 많은 경우 해당 클래스에서 처리 하기가 힘들어서 이렇게 변경함
     * 섞여있는 데이터는 알아서 하도록 하자
     **/

    private ArrayList<String> order_items;
    private Context context;
    private MyApplication myApplication;
    public SocketService service;

    public NetworkPresenter(Context context) {
        this.context = context;
        myApplication = (MyApplication) this.context.getApplicationContext();

    }


    @Override
    public void InsertDorderForCust(String movingtype_check, String movingday_data, String movingname_data, String movingphone_data, ArrayList<String> movingstart_data, ArrayList<String> movingfinish_data, final InsertDorderForCustCInterface anInterface) {
        //등록

        SendPacket sPacket = new SendPacket();
        sPacket.AddType( Protocol.PT_REQUEST, Protocol.INSERT_DORDER_FOR_CUST_C );
        sPacket.AddMessageType( Protocol.INSERT_DORDER_FOR_CUST_C );
        sPacket.AddString( "KING" );
        sPacket.AddString( "8" );
        sPacket.AddString( "39" );   //콜센타 코드 CCODE
        sPacket.AddString( "0" );   //소속업체코드 (1차)
        sPacket.AddString( "0" );    //
        sPacket.AddString( "14" );       //오더구분
        sPacket.AddString( movingtype_check );    //이사타입
        sPacket.AddString( "1" );         //이사종류 구분 설명
        sPacket.AddString( movingday_data );      //이사날짜
        sPacket.AddString( movingname_data );      //이름
        sPacket.AddString( movingphone_data );     //휴대폰번호
        sPacket.AddString( (String) movingstart_data.get( 0 ) );      //시도
        sPacket.AddString( (String) movingstart_data.get( 1 ) );        //군구
        sPacket.AddString( (String) movingstart_data.get( 2 ) );        //동
        sPacket.AddString( "1" );
        sPacket.AddString( "" );
        sPacket.AddString( "" );
        sPacket.AddString( "" );
        sPacket.AddString( (String) movingfinish_data.get( 0 ) );        //시도
        sPacket.AddString( (String) movingfinish_data.get( 1 ) );          //군구
        sPacket.AddString( (String) movingfinish_data.get( 2 ) );          //동
        sPacket.AddString( "1" );
        sPacket.AddString( "" );
        sPacket.AddString( "" );
        sPacket.AddString( "" );
        sPacket.AddRowDelimiter();
        sPacket.Commit();

        service.InterfaceDataSend( sPacket, new InsertDorderForCustCInterface() {
            @Override
            public void success(String insertcheck) {
            }
            @Override
            public void success(RecvPacket packet) {
                anInterface.success( "등록완료" );
            }
            @Override
            public void fail(String t) {
                anInterface.fail( t );
            }
        } );
    }

    @Override
    public void GetDorderForCust(String name, String phone, final GetDorderForCustInterface anInterface) {
        //리스트 가져오기
        SendPacket sPacket = new SendPacket();
        sPacket.AddType( Protocol.PT_REQUEST, Protocol.GET_DORDER_FOR_CUST );
        sPacket.AddMessageType( Protocol.GET_DORDER_FOR_CUST );
        sPacket.AddString( "KING" );
        sPacket.AddString( "8" );
        sPacket.AddString( "39" );
        sPacket.AddString( "0" );
        sPacket.AddString( "0" );
        sPacket.AddString( phone );
        sPacket.AddString( name );
        sPacket.AddRowDelimiter();
        sPacket.Commit();


        service.InterfaceDataSend( sPacket, new GetDorderForCustInterface() {
            @Override
            public void success(ArrayList<String> orderlistData) {

            }

            @Override
            public void success(RecvPacket packet) {
                try {
                    String[] recvData = packet.COMMAND.split( MyApplication.ROW_DELIMITER );


                    //showToast("조회 성공");

                    order_items = new ArrayList<>();
                    for (int i = 0; i < recvData.length; i++) {
                        Log.i( "recvDataa", recvData[i] );

                        String[] sData = recvData[i].split( MyApplication.DELIMITER );
                        //반복문 돌면서 sData의 []값에 해당되는 데이터가 order_items에 담김

                        order_items.add( sData[10] );  //주문일자
                        order_items.add( sData[13] );  //이사종류
                        order_items.add( sData[16] );  //이사일자
                        order_items.add( sData[23] + " " + sData[24] + " " + sData[25] );    // 시군    //구     //동
                        order_items.add( sData[28] + " " + sData[29] + " " + sData[30] );    // 시군    //구     //동
                        // order_items에 필요데이터만 담음

                    }
                    anInterface.success( order_items );

                } catch (Exception e) {
                    anInterface.fail( "조회실패" );
                }
            }

            @Override
            public void fail(String t) {
/*                order_items = new ArrayList<>();
                order_items.clear();
                order_items.add("사용자없음");
                anInterface.success(order_items);*/
                anInterface.fail( t );
                //변경
            }

        } );
    }

    @Override
    public void GetMapAddr(SendPacket sendPacket, final GetMapAddrInterface anInterface) {
        service.InterfaceDataSend( sendPacket, anInterface );
    }

    @Override
    public void GetVersionCust(final GetVersionCustInterface anInterface) {
        //버전조회
        SendPacket sPacket = new SendPacket();
        sPacket.AddType( Protocol.PT_REQUEST, Protocol.GET_VERSION_CUST );
        sPacket.AddMessageType( Protocol.GET_VERSION_CUST );
        sPacket.Commit();

        service.InterfaceDataSend( sPacket, new GetVersionCustInterface() {
            @Override
            public void success(String version) {

            }

            @Override
            public void success(RecvPacket packet) {
                try {
                    String[] recvData = packet.COMMAND.split( MyApplication.DELIMITER );

                    for (int i = 0; i < recvData.length; i++) {
                        Log.i( "recvDataa", recvData[i] );

                    }
                    anInterface.success( String.valueOf( recvData[0] ) );

                } catch (Exception e) {

                }
            }

            ////
            @Override
            public void fail(String t) {
                anInterface.fail( t );
            }

        } );

    }

    @Override
    public void PST_PING(final Pst_PingInterface anInterface) {
        SendPacket sPacket = new SendPacket();
        sPacket.AddType( Protocol.PT_REQUEST, Protocol.PST_PING );
        sPacket.AddMessageType( Protocol.PST_PING );
        sPacket.Commit();

        service.InterfaceDataSend( sPacket, new Pst_PingInterface() {
            @Override
            public void success() {

            }

            @Override
            public void success(RecvPacket packet) {
                try {
                    String[] recvData = packet.COMMAND.split( MyApplication.DELIMITER );


                } catch (Exception e) {

                }
            }

            ////
            @Override
            public void fail(String t) {
                anInterface.fail( t );
            }

        } );
    }


}
