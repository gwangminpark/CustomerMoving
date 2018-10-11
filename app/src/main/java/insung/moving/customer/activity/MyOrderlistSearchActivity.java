package insung.moving.customer.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import insung.moving.customer.R;
import insung.moving.customer.app.MyApplication;
import insung.moving.customer.model.OrderlistData;
import insung.moving.customer.service.RecvPacket;

import insung.moving.customer.databinding.ActivityMyorderSearchBinding;
import insung.moving.customer.databinding.NotittleToolbarBinding;
import insung.moving.customer.service.resultInterface.GetDorderForCustInterface;

/**
 * Created by user on 2018-07-26.
 */

public class MyOrderlistSearchActivity extends BaseActivity {
    public static final String INTENT_FILTER = MyApplication.INTENT_HEAD + "MAIN";

    private static ArrayList<String> order_items;

    private ActivityMyorderSearchBinding binding;
    private NotittleToolbarBinding notittleToolbarBinding;

    private SocketRecv Orderlistreceiver;
    private AlertDialog.Builder builder;
    private AlertDialog networkAlertDialog;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_myorder_search );
        notittleToolbarBinding = DataBindingUtil.bind( binding.notittleToolbar.getRoot() );
        notittleToolbarBinding.toolbarTitle.setText( "나의 신청내역" );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

        setSupportActionBar( notittleToolbarBinding.toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayShowTitleEnabled( false );

        Orderlistreceiver = new SocketRecv();
        this.registerReceiver( Orderlistreceiver, new IntentFilter( INTENT_FILTER ) );
        order_items = new ArrayList<>();


        binding.phoneEdit.addTextChangedListener( new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.phoneEdit.getText().length() == 4) {
                    binding.phoneEdit2.requestFocus();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        } );

        binding.searchbt.setOnClickListener( new View.OnClickListener() {
            //조회 클릭시
            @Override
            public void onClick(View view) {
                if (binding.nameEdit.getText().toString().getBytes().length <= 0) {
                    showToast( "이름을 입력해주세요" );
                } else if (binding.phoneEdit.getText().toString().getBytes().length <= 3 || binding.phoneEdit2.getText().toString().getBytes().length <= 3) {
                    showToast( "번호를 입력(체크)해주세요" );
                } else {
                    order_list_view();
                }
            }
        } );

    }

    @Override
    public void onBackPressed() {
        //뒤로가기 버튼
        order_items.clear();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (Orderlistreceiver != null){
            unregisterReceiver( Orderlistreceiver );
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                this.finish();
                return true;
            }
        }
        return super.onOptionsItemSelected( item );
    }

    public void order_list_view() {

        String Phone = "010" + binding.phoneEdit.getText().toString() + binding.phoneEdit2.getText().toString();
        showProgressDialog( "", "" );
        Log.i( "공백제거전", binding.nameEdit.getText().toString() );
        Log.i( "공백제거후", binding.nameEdit.getText().toString().replace( " ", "" ) );
        networkPresenter.GetDorderForCust( binding.nameEdit.getText().toString().replace( " ", "" ), Phone, new GetDorderForCustInterface() {
            @Override
            public void success(ArrayList<String> orderlistData) {
                dismissProgressDialog();
                for (int i = 0; i < orderlistData.size(); i++) {
                    Log.i( "finish!!", orderlistData.get( i ) );
                }
                Intent intent = new Intent(
                        getApplicationContext(), //
                        MyOrderlistActivity.class );
                intent.putExtra( "listdata", orderlistData );
                startActivity( intent );

            }

            @Override
            public void success(RecvPacket packet) {

            }

            @Override
            public void fail(String t) {
                showToast( "등록정보가 없습니다 " );
                dismissProgressDialog();
            }
        } );
    }

    public class SocketRecv extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals( INTENT_FILTER ) == true) {

                if (intent.getBooleanExtra(MyApplication.networkIntentValue, false )) {
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

                    builder = new AlertDialog.Builder( MyOrderlistSearchActivity.this );
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