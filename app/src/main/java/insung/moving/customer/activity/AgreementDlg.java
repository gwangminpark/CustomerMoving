package insung.moving.customer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import insung.moving.customer.R;

public class AgreementDlg extends BaseActivity {
    
    SharedPreferences.Editor sp_edit;
    SharedPreferences sp;
//    private boolean bound;
// 	private ISocketAidl service;
// 	private SocketRecv receiver;
// 	private final String INTENT_FILTER = DEFINE.INTENT_HEAD + "AgreementDlg";
// 	private Context mApplicationContext;
//
// 	private ServiceConnection connection = new ServiceConnection() {
//		 public void onServiceConnected(ComponentName className, IBinder iservice) {
//			 service = ISocketAidl.Stub.asInterface(iservice);
//			 bound = true;
//
////			 PST_SELECT_RIDER_CLAUSE_SEND();
//		 }
//
//		 public void onServiceDisconnected(ComponentName className) {
//			service = null;
//			bound = false;
//		 }
//	};
	 
//	public class SocketRecv extends BroadcastReceiver {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			if(intent.getAction().equals(INTENT_FILTER) == true)
//			{
//				Bundle incoming = intent.getExtras();
//				RecvPacket rPacket = incoming.getParcelable("DATA");
//				switch(rPacket.SUB_TYPE)
//				{
//				case PROTOCOL.PST_SELECT_RIDER_CLAUSE:
//					PST_SELECT_RIDER_CLAUSE_RECV(rPacket);
//					break;
//				}
//			}
//		}
//	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView( R.layout.agreementdlg);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		
//		if(getParent() == null)
//			mApplicationContext = AgreementDlg.this;
//		else
//			mApplicationContext = getParent();
		
		Intent intent = getIntent();
		setResult(RESULT_CANCELED, intent);
		
        
		TextView tvAgreement1 = (TextView)findViewById(R.id.tvAgreement1);
		tvAgreement1.setText(Html.fromHtml("<u>" + tvAgreement1.getText() + "</u>"));
		
		tvAgreement1.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	Intent intent = new Intent(AgreementDlg.this, AgreementDetail.class);
        		intent.putExtra("INDEX", 1);
        		startActivity(intent);
            }
        });
		TextView tvAgreement2 = (TextView)findViewById(R.id.tvAgreement2);
        tvAgreement2.setText(Html.fromHtml("<u>" + tvAgreement2.getText() + "</u>"));
        
        tvAgreement2.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	Intent intent = new Intent(AgreementDlg.this, AgreementDetail.class);
        		intent.putExtra("INDEX", 2);
        		startActivity(intent);
            }
        });
        TextView tvAgreement3 = (TextView)findViewById(R.id.tvAgreement3);
        tvAgreement3.setText(Html.fromHtml("<u>" + tvAgreement3.getText() + "</u>"));
        
        tvAgreement3.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	Intent intent = new Intent(AgreementDlg.this, AgreementDetail.class);
        		intent.putExtra("INDEX", 3);
        		startActivity(intent);
            }
        });

        TextView tvAppAgreement = (TextView)findViewById(R.id.tvAppAgreement);
        tvAppAgreement.setText(Html.fromHtml("<u>" + tvAppAgreement.getText() + "</u>"));

        tvAppAgreement.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(AgreementDlg.this, AgreementDetail.class);
                intent.putExtra("INDEX", 4);
                startActivity(intent);
            }
        });




        Button btnAgreement = (Button)findViewById(R.id.btnAgreement);
        btnAgreement.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "009428874"));
                startActivity(intent);
            	finish();


            }
        });
        
//        if(!bound) {
//			if(DATA.group_type.compareTo("2") == 0)
//				getApplicationContext().bindService(new Intent(mApplicationContext, SocketService.class), connection, Context.BIND_AUTO_CREATE);
//			else
//				getApplicationContext().bindService(new Intent(mApplicationContext, SocketService2.class), connection, Context.BIND_AUTO_CREATE);
//		}
//
//		receiver = new SocketRecv();
//        this.registerReceiver(receiver, new IntentFilter(INTENT_FILTER));
	}
	
//	public void PST_SELECT_RIDER_CLAUSE_SEND() {
//		try
//		{
//			SendPacket sPacket = new SendPacket();
//			sPacket.AddType(PROTOCOL.PT_REQUEST, PROTOCOL.PST_SELECT_RIDER_CLAUSE);
//			sPacket.Commit();
//			service.DataSend( sPacket, INTENT_FILTER);
//		}
//		catch(Exception e)
//		{
//		}
//	}
//
//	public void PST_SELECT_RIDER_CLAUSE_RECV(RecvPacket rPacket) {
//		String[] Data = rPacket.COMMAND.split("\30");
//
//		CheckBox agreement1 = (CheckBox)findViewById(R.id.ckAgreement1);
//        CheckBox agreement2 = (CheckBox)findViewById(R.id.ckAgreement2);
//        CheckBox agreement3 = (CheckBox)findViewById(R.id.ckAgreement3);
////        agreement1.setChecked(true);
////        agreement2.setChecked(true);
////        agreement3.setChecked(true);
//        if(Data[0].compareTo("Y") == 0)
//            agreement1.setChecked(true);
//        if(Data[1].compareTo("Y") == 0)
//            agreement2.setChecked(true);
//        if(Data[2].compareTo("Y") == 0)
//            agreement3.setChecked(true);
//	}
}
