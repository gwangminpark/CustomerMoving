package insung.moving.customer.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.ibm.icu.text.Transliterator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import insung.moving.customer.app.MyApplication;
import insung.moving.customer.R;
import insung.moving.customer.databinding.CommonToolbarBinding;
import insung.moving.customer.service.NetworkPresenter;
import insung.moving.customer.service.SendPacket;
import insung.moving.customer.service.SocketService;
import insung.moving.customer.temp.DEFINE;
import insung.moving.customer.temp.PROTOCOL;
import insung.moving.customer.util.KeyHandleUtil;
import insung.moving.customer.util.ProgressDialogManager;


public class BaseActivity extends AppCompatActivity {
    protected MyApplication myApplication;
    protected ProgressDialog progressDialog;
    public boolean bound;
    public SocketService service;
    public AlertDialog.Builder builder;
    public NetworkPresenter networkPresenter;
    public AlertDialog networkAlertDialog;
    public String myNumber;
    //나의 휴대폰번호를 임시로 저장시켜놓는 변수

    //    public MainThreadHandler<BaseActivity> mHandler = new MainThreadHandler<>(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );

//        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy( policy );

        networkPresenter = new NetworkPresenter( this );
        myApplication = (MyApplication) getApplicationContext();

        if (!bound) {
            bindService(
                    new Intent( BaseActivity.this, SocketService.class ), connection,
                    Context.BIND_AUTO_CREATE );
        }

    }
    protected void setBindService(ServiceConnection getServiceConnection){
        if (!bound) {
            bindService(
                    new Intent(BaseActivity.this, SocketService.class), getServiceConnection,
                    Context.BIND_AUTO_CREATE);
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder iservice) {
            SocketService.SocketServiceBinder binder = (SocketService.SocketServiceBinder) iservice;
            service = binder.getService();
            bound = true;
            networkPresenter.service = service;
        }

        public void onServiceDisconnected(ComponentName className) {
            service = null;
            bound = false;
        }
    };

    protected void showProgressDialog(String title, String msg) {
        progressDialog = ProgressDialogManager.showSingle( this, progressDialog, title, msg );
        progressDialog.setCanceledOnTouchOutside( true );
    }
    public void showNetworkProgressDialog(String title, String msg) {
        progressDialog = ProgressDialogManager.showSingle(this, progressDialog, title, msg);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
                    KeyHandleUtil.doubleBackServiceFinish(BaseActivity.this, service);
                    return false;
                }
                return false;
            }
        });
    }
    protected void dismissProgressDialog() {
        ProgressDialogManager.dismiss( progressDialog );
    }

    @Override
    protected void onDestroy() {

               try {
//                   if (bound) {
//                       bound = false;
//                       unbindService( connection );
//                   }

                if (service != null) {
                    service.StopThread();

                    if (bound) {
                        bound = false;
                        unbindService( connection );
                    }
                    Intent intent = new Intent( BaseActivity.this, SocketService.class );
                    this.stopService( intent );
                }

        } catch (Exception e) {
        }
        super.onDestroy();
    }

    protected void showToast(String msg) {
        Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_SHORT ).show();
    }

    protected void initCommonActionBar(CommonToolbarBinding commonToolbarBinding, String title) {
        setSupportActionBar( commonToolbarBinding.toolbar );
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled( true );
        actionBar.setHomeAsUpIndicator( R.drawable.ic_common_back );
        actionBar.setDisplayShowTitleEnabled( false );
        commonToolbarBinding.toolbarTitle.setText( title );
    }

    public void startApp() {
        showToast( "설정-권한 허용 또는 안드로이드 버전 업데이트를 해주세요..." );
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void checkVerify() {


            if (ContextCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                // 권한체크 후 허용

                // 이 권한을 필요한 이유를 설명해야하는가?
                if (ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.CALL_PHONE )) {

                    // 다이어로그같은것을 띄워서 사용자에게 해당 권한이 필요한 이유에 대해 설명

                    // 해당 설명이 끝난뒤 requestPermissions()함수를 호출하여 권한허가를 요청해야 합니다
                    ActivityCompat.requestPermissions( this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1 );
                } else {
                    ActivityCompat.requestPermissions( this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1 );

                    // 필요한 권한과 요청 코드를 넣어서 권한허가요청에 대한 결과를 받아야 합니다

                }
            }
            //최초 앱다운후 실행시 권한체크 완료후 앱 실행했을시
            TelephonyManager mgr = (TelephonyManager) getSystemService( Context.TELEPHONY_SERVICE );
            try {


            } catch (Exception e) {
                //이미 위에 if(line:154)문 에서 권한 체크여부를 체크하므로 catch로 빠질일은 없음
                //Toast.makeText( this, "비정상적인 접근", Toast.LENGTH_SHORT ).show();
                //finish();
            }

        }



    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {

            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한체크 허용도


                } else {
                    //권한체크 거부시 강제종료
                    Toast.makeText(this, "전화걸기 사용 이 불가합니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    public class SocketRecv extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals( DEFINE.NETWORK_INTENT_FILTER) == true) {

                int networkStateValue = intent.getIntExtra(DEFINE.networkIntentValue, 0);

                if(networkStateValue == DEFINE.HANDLER_NETWORK_OK){
                    // 네트워크 성공 시 핸들러 리무브
                    dismissProgressDialog();
                    if(networkAlertDialog != null){
                        networkAlertDialog.dismiss();
                    }

                }else if(networkStateValue == DEFINE.HANDLER_NETWORK_LOADING){
                    // 네트워크 성공 시 핸들러 리무브
                    showNetworkProgressDialog("","서버와 연결중입니다.");

                }else if(networkStateValue == DEFINE.HANDLER_NETWORK_RESTART){
                    // 네트워크 재시작 시 로그인 데이터 추가


                }else if(networkStateValue == DEFINE.HANDLER_NETWORK_ERROR){
                    dismissProgressDialog();
                    // 네트워크 실패

                    if (networkAlertDialog != null && networkAlertDialog.isShowing()){
                        return;
                    }
                    builder = new AlertDialog.Builder(BaseActivity.this);
                    builder.setTitle("네트워크 에러");
                    builder.setCancelable(false);
                    builder.setMessage("통신이 원할하지 않습니다 재시도해주세요.");
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.setPositiveButton("연결", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP){
                                KeyHandleUtil.doubleBackFinish(BaseActivity.this);
                                return false;
                            }
                            return false;
                        }
                    });
                    networkAlertDialog = builder.create();
                    networkAlertDialog.show();

                    networkAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            showNetworkProgressDialog("","서버와 연결중입니다.");
                            service.StartService();

                        }
                    });
                }else if(networkStateValue == DEFINE.HANDLER_NETWORK_CLOSE){
                    // 네트워크 성공 시 핸들러 리무브
//                    showNetworkProgressDialog("","서버와 연결중입니다.");
                    builder = new AlertDialog.Builder(BaseActivity.this);
                    builder.setTitle("네트워크 에러");
                    builder.setCancelable(false);
                    builder.setMessage("통신이 원할하지 않습니다 재시도해주세요.");
                    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    networkAlertDialog = builder.create();
                    networkAlertDialog.show();

                }

            }
        }
    }

}
