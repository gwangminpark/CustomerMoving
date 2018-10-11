package insung.moving.customer.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;


import insung.moving.customer.R;
import insung.moving.customer.app.MyApplication;
import insung.moving.customer.databinding.ActivityMainBinding;
import insung.moving.customer.databinding.CommonNavigationBinding;
import insung.moving.customer.databinding.CommonToolbarBinding;


import insung.moving.customer.service.RecvPacket;

import insung.moving.customer.service.resultInterface.InsertDorderForCustCInterface;
import insung.moving.customer.util.BackPressCloseHandler;


import java.util.ArrayList;


public class MainActivity extends BaseActivity {
    //  리스트선언
    private static final int REQUEST_MOVINGTYPE_DIALOG = 1; //종류선택
    private static final int REQUEST_MOVINGDAY_DIALOG = 2; //날짜선택
    private static final int REQUEST_STARTADDRESS_DIALOG = 3;//시작주소
    private static final int REQUEST_FINISHADDRESS_DIALOG = 4;//도착주소

    public static final String INTENT_FILTER = MyApplication.INTENT_HEAD + "MAIN";

    public static int MAIN_INPUT_CHECK = 0;//메인 input기능 유효성 체크를 위한 변수
    public static int movingtype_check = 0;//이사구분 (0:선택안함 1:가정이사, 2:사무실이사, 3:원룸이사,4:공장이사,5:제주도이사,6:해외이사)
    public static String movingday_data = ""; //선택한 날짜를 저장하는 변수 ex) 2018-07-11
    public static ArrayList<String> movingstart_data; //출발할 주소를 저장하는 ArrayList  ex) {"대구","남구","대명동"}
    public static ArrayList<String> movingfinish_data; //도착될 주소를 저장하는 ArrayList  ex) {"대구","서구","비산동"}
    public static String movingname_data = ""; //이름을 저장하는 변수
    public static String movingphone_data = ""; //휴대폰 번호를 저장하는 변수

    private ActivityMainBinding binding;
    private CommonToolbarBinding commonToolbarBinding;
    private CommonNavigationBinding commonNavigationBinding;
    private BackPressCloseHandler backPressCloseHandler;

    private SocketRecv receiver;
    private AlertDialog.Builder builder;
    private AlertDialog networkAlertDialog;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );
        commonToolbarBinding = DataBindingUtil.bind( binding.commonToolbar.getRoot() );
        commonNavigationBinding = DataBindingUtil.bind( binding.commonNavigation.getRoot() );
        backPressCloseHandler = new BackPressCloseHandler( this );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        initNavigation();
        initActionBar();

        receiver = new SocketRecv();
        this.registerReceiver( receiver, new IntentFilter( INTENT_FILTER ) );

        SharedPreferences prefs = getSharedPreferences( "Address", MODE_PRIVATE );
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

        binding.callbt.setOnClickListener( new View.OnClickListener() {
            //전화버튼 클릭시
            @Override
            public void onClick(View view) {
                TedPermission.with( MainActivity.this )
                        .setPermissionListener( new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse( "tel:" + "16662428" ) );
                                startActivity( intent );
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                            }

                        } )
                        .setDeniedMessage( getString( R.string.ted_permission_denied_message ) )
                        .setGotoSettingButtonText( getString( R.string.ted_permission_go_to_setting_button_text ) )
                        .setPermissions( android.Manifest.permission.CALL_PHONE ).check();
            }
        } );

        binding.movingtypeBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //다이얼로그 호출
                Intent intent = new Intent( MainActivity.this, MovingTypeCheckActivity.class );
                intent.putExtra( "type", binding.movingType.getText() );
                startActivityForResult( intent, REQUEST_MOVINGTYPE_DIALOG );

            }
        } );
        binding.movingdayBtn.setOnClickListener( new View.OnClickListener() {
            //이사종류 클릭시
            @Override
            public void onClick(View view) {
                if (MAIN_INPUT_CHECK == 0) {
                    //이사종류를 선택 안했을시
                    Intent intent = new Intent( MainActivity.this, MovingTypeCheckActivity.class );
                    intent.putExtra( "type", binding.movingType.getText() );
                    startActivityForResult( intent, REQUEST_MOVINGTYPE_DIALOG );
                    //  showToast( "이사종류를 선택해주세요" );
                } else {
                    //이사종류를 선택 했을시 (MAIN_INPUT_CHECK==1일때)
                    //추후에 이전단계 변경도 가능해야하므로 else 로 처리
                    Intent intent = new Intent( MainActivity.this, MovingDayDialogActivity.class );
                    startActivityForResult( intent, REQUEST_MOVINGDAY_DIALOG );
                }
            }
        } );
        binding.movingstartBtn.setOnClickListener( new View.OnClickListener() {
            //이사날짜 클릭시
            @Override
            public void onClick(View view) {
                if (MAIN_INPUT_CHECK == 0) {
                    Intent intent = new Intent( MainActivity.this, MovingTypeCheckActivity.class );
                    intent.putExtra( "type", binding.movingType.getText() );
                    startActivityForResult( intent, REQUEST_MOVINGTYPE_DIALOG );
                    // showToast( "이사종류를 선택해주세요" );
                } else if (MAIN_INPUT_CHECK == 1) {
                    //  showToast( "이사날짜를 선택해주세요" );
                    Intent intent = new Intent( MainActivity.this, MovingDayDialogActivity.class );
                    startActivityForResult( intent, REQUEST_MOVINGDAY_DIALOG );
                } else {
                    new Handler().postDelayed( new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent( MainActivity.this, StartAddressDialogActivity.class );
                            intent.putExtra( "start", binding.startday.getText() );
                            startActivityForResult( intent, REQUEST_STARTADDRESS_DIALOG );
                        }
                    }, 500 );
                }
            }
        } );
        binding.movingfinishBtn.setOnClickListener( new View.OnClickListener() {
            //출발주소 클릭시
            @Override
            public void onClick(View view) {
                if (MAIN_INPUT_CHECK == 0) {
                    Intent intent = new Intent( MainActivity.this, MovingTypeCheckActivity.class );
                    intent.putExtra( "type", binding.movingType.getText() );
                    startActivityForResult( intent, REQUEST_MOVINGTYPE_DIALOG );
                    //showToast( "이사종류를 선택해주세요" );
                } else if (MAIN_INPUT_CHECK == 1) {
                    Intent intent = new Intent( MainActivity.this, MovingDayDialogActivity.class );
                    startActivityForResult( intent, REQUEST_MOVINGDAY_DIALOG );
                    // showToast( "이사날짜를 선택해주세요" );
                } else if (MAIN_INPUT_CHECK == 2) {
                    new Handler().postDelayed( new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent( MainActivity.this, StartAddressDialogActivity.class );
                            intent.putExtra( "start", binding.startday.getText() );
                            startActivityForResult( intent, REQUEST_STARTADDRESS_DIALOG );
                        }
                    }, 500 );
                    //showToast( "출발주소를 선택해주세요 " );
                } else {
                    new Handler().postDelayed( new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent( MainActivity.this, FinishAddressDialogActivity.class );
                            intent.putExtra( "finish", binding.finishday.getText() );
                            startActivityForResult( intent, REQUEST_FINISHADDRESS_DIALOG );
                        }
                    }, 500 );

                }
            }
        } );
        binding.movingphoneBtn.setOnClickListener( new View.OnClickListener() {
            //도착주소 클릭시
            @Override
            public void onClick(View view) {
                if (MAIN_INPUT_CHECK == 0) {
                    Intent intent = new Intent( MainActivity.this, MovingTypeCheckActivity.class );
                    intent.putExtra( "type", binding.movingType.getText() );
                    startActivityForResult( intent, REQUEST_MOVINGTYPE_DIALOG );
                    // showToast( "이사종류를 선택해주세요" );
                } else if (MAIN_INPUT_CHECK == 1) {
                    Intent intent = new Intent( MainActivity.this, MovingDayDialogActivity.class );
                    startActivityForResult( intent, REQUEST_MOVINGDAY_DIALOG );
                    //   showToast( "이사날짜를 선택해주세요" );
                } else if (MAIN_INPUT_CHECK == 2) {
                    new Handler().postDelayed( new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent( MainActivity.this, StartAddressDialogActivity.class );
                            intent.putExtra( "start", binding.startday.getText() );
                            startActivityForResult( intent, REQUEST_STARTADDRESS_DIALOG );
                        }
                    }, 500 );
                    //showToast( "출발주소를 선택해주세요 " );
                } else if (MAIN_INPUT_CHECK == 3) {
                    new Handler().postDelayed( new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent( MainActivity.this, FinishAddressDialogActivity.class );
                            intent.putExtra( "finish", binding.finishday.getText() );
                            startActivityForResult( intent, REQUEST_FINISHADDRESS_DIALOG );
                        }
                    }, 500 );
                    // showToast( "도착주소를 선택해주세요" );
                } else {
                    PhoneDialogActivity phoneDialogActivity = new PhoneDialogActivity( MainActivity.this );
                    phoneDialogActivity.call_phonedlg();
                    //다이얼로그 호출
                }

            }
        } );
        binding.searchBt.setOnClickListener( new View.OnClickListener() {
            //바로문의하기 버튼 클릭시
            @Override
            public void onClick(View view) {
                if (MAIN_INPUT_CHECK == 0) {
                    Intent intent = new Intent( MainActivity.this, MovingTypeCheckActivity.class );
                    intent.putExtra( "type", binding.movingType.getText() );
                    startActivityForResult( intent, REQUEST_MOVINGTYPE_DIALOG );
                    //  showToast( "이사종류를 선택해주세요" );
                } else if (MAIN_INPUT_CHECK == 1) {
                    Intent intent = new Intent( MainActivity.this, MovingDayDialogActivity.class );
                    startActivityForResult( intent, REQUEST_MOVINGDAY_DIALOG );
                    //  showToast( "이사날짜를 선택해주세요" );
                } else if (MAIN_INPUT_CHECK == 2) {
                    new Handler().postDelayed( new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent( MainActivity.this, StartAddressDialogActivity.class );
                            intent.putExtra( "start", binding.startday.getText() );
                            startActivityForResult( intent, REQUEST_STARTADDRESS_DIALOG );
                        }
                    }, 500 );
                    // showToast( "출발주소를 선택해주세요 " );
                } else if (MAIN_INPUT_CHECK == 3) {
                    new Handler().postDelayed( new Runnable() {

                        @Override
                        public void run() {
                            Intent intent = new Intent( MainActivity.this, FinishAddressDialogActivity.class );
                            intent.putExtra( "finish", binding.finishday.getText() );
                            startActivityForResult( intent, REQUEST_FINISHADDRESS_DIALOG );
                        }
                    }, 500 );
                    // showToast( "도착주소를 선택해주세요" );
                } else if (MAIN_INPUT_CHECK == 4) {
                    PhoneDialogActivity phoneDialogActivity = new PhoneDialogActivity( MainActivity.this );
                    phoneDialogActivity.call_phonedlg();
                    //   showToast( "연락처를 입력해주세요" );
                    Log.i( "input2", String.valueOf( MAIN_INPUT_CHECK ) );
                } else {
                    showProgressDialog( "", "등록중입니다.\n잠시만 기다려 주세요." );
                    Log.i( "input2", String.valueOf( MAIN_INPUT_CHECK ) );
                    moving_order_insert();
                    // insert 실행
                }
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i( "log33", "onResume: " );
        binding.drawerLayout.closeDrawer( GravityCompat.START );
    }

    @Override
    protected void onRestart() {
        //MAIN_INPUT_CHECK = 0;
        movingname_data = "";
        movingphone_data = "";
        movingtype_check = 1;
        //관련 변수 초기화
        // TODO Auto-generated method stub
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        try {
            if (receiver != null){
                unregisterReceiver( receiver );
            }
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.drawerLayout.openDrawer( GravityCompat.START );
                return true;
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen( GravityCompat.START )) {
            binding.drawerLayout.closeDrawer( GravityCompat.START );
        } else {
            backPressCloseHandler.onBackPressed();
            //super.onBackPressed();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        switch (requestCode) {
            case REQUEST_MOVINGTYPE_DIALOG:
                //이사 종류 선택 Activity 에서 돌아올때
                if (resultCode == RESULT_OK) {

                    if (data.getStringExtra( "result" ).equals( "이사종류" )) {
                        //최초입력일때
                        MAIN_INPUT_CHECK = 1;
                        animate();
                        binding.movingType.setText( "가정이사" );
                    }

                    switch (movingtype_check) {
                        case 1:
                            binding.movingType.setText( "가정이사" );
                            break;

                        case 2:
                            binding.movingType.setText( "사무실이사" );
                            break;

                        case 3:
                            binding.movingType.setText( "원룸이사" );
                            break;

                        case 4:
                            binding.movingType.setText( "공장이사" );
                            break;

                        case 5:
                            binding.movingType.setText( "제주도이사" );
                            break;

                        case 6:
                            binding.movingType.setText( "해외이사" );
                            break;
                    }
                }
                break;

            case REQUEST_MOVINGDAY_DIALOG:
                //이사 날짜 선택 Activity 에서 돌아올때
                if (resultCode == RESULT_OK) {
                    if (binding.day.getText().equals( "이사날짜" )) {
                        //최초 한번 입력시에만 애니메이션,메인 체크변수 바꿔줌
                        animate2();
                        MainActivity.MAIN_INPUT_CHECK = 2;
                    }
                    binding.dayLeftCircle.setBackgroundResource( R.drawable.check_1 );
                    movingday_data = data.getStringExtra( "result" );
                    //변수받아서 movingday_data에저장
                    binding.day.setText( movingday_data );
                    //메인에 보이는 Text바꿔줌
                }
                break;

            case REQUEST_STARTADDRESS_DIALOG:
                //출발주소 선택 에서 돌아올때
                if (resultCode == RESULT_OK) {
                    if (binding.startday.getText().equals( "출발주소" )) {
                        //최초 한번 입력시에만 애니메이션,메인 체크변수 바꿔줌
                        animate3();
                        MAIN_INPUT_CHECK = 3;
                    }
                    binding.startLeftCircle.setBackgroundResource( R.drawable.check_1 );
                    movingstart_data = new ArrayList<String>();
                    //시 군구 동 주소를 담기위한 ArrayList 생성
                    movingstart_data.add( 0, data.getStringExtra( "address_si" ) );
                    movingstart_data.add( 1, data.getStringExtra( "address_gu" ) );
                    movingstart_data.add( 2, data.getStringExtra( "address_dong" ) );
                    //ArrayList에 시 군구 동 담아줌

                    binding.startday.setText( data.getStringExtra( "address_dong" ) );
                    //메인에 보여주는 text
                }
                break;

            case REQUEST_FINISHADDRESS_DIALOG:
                //도착주소 선택 에서 돌아올때
                if (resultCode == RESULT_OK) {
                    if (binding.finishday.getText().equals( "도착주소" )) {
                        //최초 한번 입력시에만 애니메이션,메인 체크변수 바꿔줌
                        animate4();
                        MainActivity.MAIN_INPUT_CHECK = 4;
                    }

                    binding.finishLeftCircle.setBackgroundResource( R.drawable.check_1 );
                    movingfinish_data = new ArrayList<String>();
                    //시군 구 동 주소를 담기위한 ArrayList 생성
                    movingfinish_data.add( 0, data.getStringExtra( "address_si" ) );
                    movingfinish_data.add( 1, data.getStringExtra( "address_gu" ) );
                    movingfinish_data.add( 2, data.getStringExtra( "address_dong" ) );
                    //ArrayList에 시 군구 동 담아줌

                    binding.finishday.setText( data.getStringExtra( "address_dong" ) );
                    //메인에 보여주는 text
                }
                break;

        }
    }

    private void initNavigation() {
        // 토글 아이콘
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, binding.drawerLayout, commonToolbarBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        toggle.getDrawerArrowDrawable().setColor( getResources().getColor( R.color.actionbar_text ) );
        binding.drawerLayout.setDrawerListener( toggle );
        toggle.syncState();

        commonNavigationBinding.linearRequest.setOnClickListener( navigationClickListener );
        commonNavigationBinding.linearCheck.setOnClickListener( navigationClickListener );
        commonNavigationBinding.linearMoving.setOnClickListener( navigationClickListener );
        commonNavigationBinding.linearPolicy.setOnClickListener( navigationClickListener );
        commonNavigationBinding.infoPolicy.setOnClickListener( navigationClickListener );
    }

    private void initActionBar() {
        setSupportActionBar( commonToolbarBinding.toolbar );
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled( false );
    }

    private View.OnClickListener navigationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.linearRequest:
                    //나의 신청내역 클릭시
                    Intent intent = new Intent( MainActivity.this, MyOrderlistSearchActivity.class );
                    startActivity( intent );
                    break;

                case R.id.linearCheck:
                    //이사체크리스트 클릭시
                    intent = new Intent( MainActivity.this, MovingCheckListActivity.class );
                    startActivity( intent );
                    break;

                case R.id.linearMoving:
                    //이사날찾기 클릭시
                    intent = new Intent( MainActivity.this, SearchMovingDayActivity.class );
                    startActivity( intent );
                    break;

                case R.id.linearPolicy:
                    //약관클릭시
                    intent = new Intent( MainActivity.this, ClauseActivity.class );
                    startActivity( intent );
                    break;

                case R.id.infoPolicy:
                    //개인정보처리 방침 클릭시//
                    intent = new Intent( MainActivity.this, PrivacyActivity.class );
                    startActivity( intent );
                    break;
            }
        }
    };

    public void moving_order_insert() {
        Log.i( "movingtype_check", String.valueOf( movingtype_check ) );
        //소켓서버로 보냄 insert
        networkPresenter.InsertDorderForCust( String.valueOf( movingtype_check ), movingday_data, movingname_data, movingphone_data, movingstart_data, movingfinish_data, new InsertDorderForCustCInterface() {
            @Override
            public void success(String insertcheck) {
                //등록 성공시
                MainActivity.this.recreate();
                dismissProgressDialog();
                showToast( "신청완료" );
                MAIN_INPUT_CHECK = 0;
                movingname_data = "";
                movingphone_data = "";
                movingtype_check = 1;
                //  onRestart();
            }

            @Override
            public void success(RecvPacket packet) {

            }

            @Override
            public void fail(String t) {
                dismissProgressDialog();
                showToast( "등록에 실패하였습니다." );
            }
        } );
    }

    public void animate() {
        // 이사종류-> 이사날짜로 내려가는 에니메이션
        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.translate_anim );
        ImageView car = (ImageView) findViewById( R.id.car );
        car.startAnimation( anim );
    }

    public void animate2() {
        // 이사날짜-> 출발주소로 내려가는 에니메이션
        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.translate_anim2 );
        ImageView car = (ImageView) findViewById( R.id.car );
        car.startAnimation( anim );
    }

    public void animate3() {
        // 출발주소-> 도착주소로 내려가는 에니메이션
        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.translate_anim3 );
        ImageView car = (ImageView) findViewById( R.id.car );
        car.startAnimation( anim );
    }

    public void animate4() {
        // 도착주소-> 연락처로 내려가는 에니메이션
        Animation anim = AnimationUtils.loadAnimation( getApplicationContext(),R.anim.translate_anim4 );
        ImageView car = (ImageView) findViewById( R.id.car );
        car.startAnimation( anim );
    }

    public class SocketRecv extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals( INTENT_FILTER ) == true) {
                if (intent.getBooleanExtra( MyApplication.networkIntentValue, false )) {
                    // 네트워크 성공 시 핸들러 리무브
                    dismissProgressDialog();
                    if (networkAlertDialog != null) {
                        networkAlertDialog.dismiss();
                    }
                } else {
                    // 네트워크 실패
                    if (networkAlertDialog != null && networkAlertDialog.isShowing()) {
                        return;
                    }
                    builder = new AlertDialog.Builder( MainActivity.this );
                    builder.setTitle( "네트워크 에러" );
                    builder.setMessage( "통신이 원할하지 않습니다 재시도해주세요!!." );
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