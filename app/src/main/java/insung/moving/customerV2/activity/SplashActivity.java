package insung.moving.customerV2.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;


import com.crashlytics.android.Crashlytics;

import java.util.Timer;
import java.util.TimerTask;

import insung.moving.customerV2.app.MyApplication;
import insung.moving.customerV2.service.resultInterface.Pst_PingInterface;
import io.fabric.sdk.android.Fabric;
import insung.moving.customerV2.R;
import insung.moving.customerV2.service.RecvPacket;
import insung.moving.customerV2.service.resultInterface.GetVersionCustInterface;
import insung.moving.customerV2.util.KeyHandleUtil;


public class SplashActivity extends BaseActivity {

    public static final String INTENT_FILTER = MyApplication.INTENT_HEAD + "MAIN";

    public static Timer timer = new Timer();
    private static float server_version; //마켓(서버)에 올라가있는 앱 버전//
    private static float app_version; //현재 앱 버전
    private static TimerTask second;
    private static SocketRecv Splashreceiver;
    public static Context context;

    AlertDialog.Builder builder;
    AlertDialog networkAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        Fabric.with( this, new Crashlytics() );
        setContentView( R.layout.activity_splash );

        context = this;
        Splashreceiver = new SocketRecv();
        this.registerReceiver( Splashreceiver, new IntentFilter( INTENT_FILTER ) );

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (Splashreceiver != null)
            unregisterReceiver( Splashreceiver );
    }

    public void onBackPressed() {
        KeyHandleUtil.doubleBackServiceFinish( SplashActivity.this, service );
    }

    private void DialogUpdate() {
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        dlg.setMessage( "이사킹을 사용하기 위해서는 업데이트를 해야합니다" ).setCancelable( false );
        dlg.setPositiveButton( "업데이트",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=insung.moving.customer" ) );
                        startActivity( intent );
                        finish();
                    }
                } );
        dlg.setNegativeButton( "취소",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                } );
        AlertDialog alert = dlg.create();
        alert.setTitle( "업데이트" );
        alert.setIcon( R.mipmap.ic_launcher );
        alert.show();
    }

    private void DialogCheck() {
        AlertDialog.Builder dlg = new AlertDialog.Builder( this );
        dlg.setMessage( "서버에서 점검중입니다. 잠시 후에 이용해주세요." ).setCancelable( false );
        dlg.setPositiveButton( "확인",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        finish();
                    }
                } );

        AlertDialog alert = dlg.create();
        alert.setTitle( "점검중.." );
        alert.setIcon( R.mipmap.ic_launcher );
        alert.show();
    }

    public class MyRecevier extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (intent.getAction().equals( "com.android.vending.INSTALL_REFERRER" )) {

                    //아래와 같이 referrer 정보를 얻을 수 있습니다
                    Log.d( "MyReceiver", "referrer " + intent.getStringExtra( "referrer" ) );
                }
            }
        }
    }

    public void pingstart() {

        second = new TimerTask() {

            @Override
            public void run() {
                Log.i( "핑쓰레드", "1" );
                ping();

            }
        };
        timer.schedule( second, 0, 120000 );
    }

    public void ping() {

        networkPresenter.PST_PING( new Pst_PingInterface() {
            @Override
            public void success() {
            }

            @Override
            public void success(RecvPacket packet) {

            }

            @Override
            public void fail(String t) {

            }
        } );
    }

    public void cancle() {
        timer.cancel();
    }

    public void version_check() {

        networkPresenter.GetVersionCust( new GetVersionCustInterface() {
            @Override
            public void success(String version) {

                Log.i( "Version11121", version );
                try {
                    server_version = Float.parseFloat( version );
                    //서버에서 가져오는 앱버전
                    app_version = Float.parseFloat( getPackageManager().getPackageInfo( getPackageName(), 0 ).versionName );
                    //클라앱버전

                    if (server_version > app_version) {
                        //서버버전이 앱버전보다 높으면 마켓업데이트 창으로 보냄
                        DialogUpdate();
                    } else if (server_version < app_version) {
                        //앱버전이 서버버전보다 높으면 서버 점검중
                        DialogCheck();
                    } else if (server_version == app_version) {
                        new Handler().postDelayed( new Runnable() {

                            @Override
                            public void run() {
                                startActivity( new Intent( SplashActivity.this,
                                        MainActivity.class ) );
                                finish();
                            }
                        }, 1000 );

                    }

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void success(RecvPacket packet) {

            }

            @Override
            public void fail(String t) {

            }
        } );

    }

    public class SocketRecv extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals( INTENT_FILTER ) == true) {

                if (intent.getBooleanExtra( MyApplication.networkIntentValue, false )) {
                    // 네트워크 성공 시 핸들러 리무브
                    pingstart();
                    version_check();
                    //버전체크
                } else {
                    // 네트워크 실패
                    if (networkAlertDialog != null && networkAlertDialog.isShowing()) {
                        return;
                    }
                    builder = new AlertDialog.Builder( SplashActivity.this );
                    builder.setTitle( "네트워크 에러" );
                    builder.setMessage( "통신이 원할하지 않습니다 재시도해주세요." );
                    builder.setPositiveButton( "연결", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    } );
                    networkAlertDialog = builder.create();
                    networkAlertDialog.show();

                    networkAlertDialog.setOnDismissListener( new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            showProgressDialog( "", "Loading" );
                            service.StartService( false );
                        }
                    } );
                }
            }
        }
    }
}
