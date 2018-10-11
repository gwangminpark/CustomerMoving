package insung.moving.customer.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import insung.moving.customer.app.MyApplication;
import insung.moving.customer.service.NetworkPresenter;
import insung.moving.customer.service.SocketService;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy( policy );

        networkPresenter = new NetworkPresenter( this );
        myApplication = (MyApplication) getApplicationContext();

        if (!bound) {
            bindService( new Intent( BaseActivity.this, SocketService.class ), connection, Context.BIND_AUTO_CREATE );
        }
    }

    @Override
    protected void onDestroy() {
        try {
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

    protected void setBindService(ServiceConnection getServiceConnection) {
        if (!bound) {
            bindService( new Intent( BaseActivity.this, SocketService.class ), getServiceConnection, Context.BIND_AUTO_CREATE );
        }
    }

    protected void showProgressDialog(String title, String msg) {
        progressDialog = ProgressDialogManager.showSingle( this, progressDialog, title, msg );
        progressDialog.setCanceledOnTouchOutside( true );
    }

    public void showNetworkProgressDialog(String title, String msg) {
        progressDialog = ProgressDialogManager.showSingle( this, progressDialog, title, msg );
        progressDialog.setCanceledOnTouchOutside( false );
        progressDialog.setOnKeyListener( new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    KeyHandleUtil.doubleBackServiceFinish( BaseActivity.this, service );
                    return false;
                }
                return false;
            }
        } );
    }

    protected void dismissProgressDialog() {
        ProgressDialogManager.dismiss( progressDialog );
    }

    protected void showToast(String msg) {
        Toast.makeText( getApplicationContext(), msg, Toast.LENGTH_SHORT ).show();
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

    public class SocketRecv extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals( MyApplication.NETWORK_INTENT_FILTER ) == true) {
                int networkStateValue = intent.getIntExtra( MyApplication.networkIntentValue, 0 );

                if (networkStateValue == MyApplication.HANDLER_NETWORK_OK) {
                    // 네트워크 성공 시 핸들러 리무브
                    dismissProgressDialog();
                    if (networkAlertDialog != null) {
                        networkAlertDialog.dismiss();
                    }
                } else if (networkStateValue == MyApplication.HANDLER_NETWORK_LOADING) {
                    // 네트워크 성공 시 핸들러 리무브
                    showNetworkProgressDialog( "", "서버와 연결중입니다." );

                } else if (networkStateValue == MyApplication.HANDLER_NETWORK_RESTART) {
                    // 네트워크 재시작 시 로그인 데이터 추가

                } else if (networkStateValue == MyApplication.HANDLER_NETWORK_ERROR) {
                    dismissProgressDialog();
                    // 네트워크 실패

                    if (networkAlertDialog != null && networkAlertDialog.isShowing()) {
                        return;
                    }
                    builder = new AlertDialog.Builder( BaseActivity.this );
                    builder.setTitle( "네트워크 에러" );
                    builder.setCancelable( false );
                    builder.setMessage( "통신이 원할하지 않습니다 재시도해주세요." );
                    builder.setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    } );
                    builder.setPositiveButton( "연결", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    } );
                    builder.setOnKeyListener( new DialogInterface.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                                KeyHandleUtil.doubleBackFinish( BaseActivity.this );
                                return false;
                            }
                            return false;
                        }
                    } );
                    networkAlertDialog = builder.create();
                    networkAlertDialog.show();
                    networkAlertDialog.setOnDismissListener( new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            showNetworkProgressDialog( "", "서버와 연결중입니다." );
                            service.StartService();
                        }
                    } );
                } else if (networkStateValue == MyApplication.HANDLER_NETWORK_CLOSE) {
                    // 네트워크 성공 시 핸들러 리무브
//                    showNetworkProgressDialog("","서버와 연결중입니다.");
                    builder = new AlertDialog.Builder( BaseActivity.this );
                    builder.setTitle( "네트워크 에러" );
                    builder.setCancelable( false );
                    builder.setMessage( "통신이 원할하지 않습니다 재시도해주세요." );
                    builder.setNegativeButton( "취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    } );
                    networkAlertDialog = builder.create();
                    networkAlertDialog.show();

                }
            }
        }
    }
}
