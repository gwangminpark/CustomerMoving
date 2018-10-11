package insung.moving.customer.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import insung.moving.customer.adapter.AddressItemAdapter;
import insung.moving.customer.adapter.BaseRecyclerViewAdapter;
import insung.moving.customer.R;
import insung.moving.customer.app.MyApplication;
import insung.moving.customer.databinding.ActivityFinishAddressDialogBinding;
import insung.moving.customer.model.DongArrayItem;
import insung.moving.customer.model.GunguArrayItem;
import insung.moving.customer.model.StartAddressItem;
import insung.moving.customer.service.RecvPacket;
import insung.moving.customer.service.SendPacket;
import insung.moving.customer.service.SocketService;
import insung.moving.customer.service.resultInterface.GetMapAddrInterface;
import insung.moving.customer.service.Protocol;

import java.util.ArrayList;

public class FinishAddressDialogActivity extends BaseActivity {

    public static final String INTENT_FILTER = MyApplication.INTENT_HEAD + "MAIN";
    private static ActivityFinishAddressDialogBinding binding;
    private static AddressItemAdapter addressItemAdapter;
    private static StartAddressItem startAddressItem; // 시도 군구 동 저장할 객체

    private static ArrayList<String> sidoItems;    // 시도 이름
    private static ArrayList<String> sidoCodes;    // 시도 코드

    private static GunguArrayItem gunguArrayItem; // 군구 리스트 저장 객체
    private static ArrayList<String> gunguItems;    // 군구 이름
    private static ArrayList<String> gunguCodes;    // 군구 코드

    private static DongArrayItem dongArrayItem; // 동 리스트 저장 객체
    private static ArrayList<String> dongItems;    // 동 이름
    private static ArrayList<String> dongCodes;    // 동 코드
    private static String CURRENT_TYPE_F = "1"; // 기본 데이터 시도로 저장

    private static String ADDRESS_SIDO_TYPE = "1";
    private static String ADDRESS_GUNGU_TYPE = "3";
    private static String ADDRESS_DONG_TYPE = "5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_finish_address_dialog );

        binding.recyclerViewAddress.setHasFixedSize( true );
        binding.recyclerViewAddress.setLayoutManager( new GridLayoutManager( FinishAddressDialogActivity.this, 4, GridLayoutManager.VERTICAL, false ) );
        startAddressItem = new StartAddressItem();

        gunguArrayItem = new GunguArrayItem();
        gunguItems = new ArrayList<>();
        gunguCodes = new ArrayList<>();

        dongArrayItem = new DongArrayItem();
        dongItems = new ArrayList<>();
        dongCodes = new ArrayList<>();

        sidoItems = new ArrayList<>();
        sidoCodes = new ArrayList<>();

        sidoItems = myApplication.sidoArrayItem.getSidoItems();
        sidoCodes = myApplication.sidoArrayItem.getSidoCodes();

        Intent intent = getIntent();
        String type = intent.getStringExtra( "finish" );

        if (!type.equals( "도착주소" )) {
            //최초  입력아닐시
            new Handler().postDelayed( new Runnable() {

                @Override
                public void run() {
                    address_change();
                    //최초 클릭이 아닐시 이전 클릭정보(마지막 동정보)가 남아있음
                }
            }, 200 );
            SharedPreferences prefs = getSharedPreferences( "PrefName_finish", MODE_PRIVATE );
            binding.tittle1.setText( prefs.getString( "tittle1", "" ) );
            binding.tittle2.setText( prefs.getString( "tittle2", "" ) );
            binding.tittle3.setText( prefs.getString( "tittle3", "" ) );
            binding.tittle1.setVisibility( View.VISIBLE );
            binding.tittle2.setBackgroundResource( R.color.title_blue );
            binding.tittle3.setBackgroundResource( R.color.title_blue );
            setBindService( serviceConnection );
        }

        if (startAddressItem.getStartAddressName() != null) {
            SharedPreferences prefs = getSharedPreferences( "Address", MODE_PRIVATE );
            binding.addressTittle.setText( prefs.getString( "finish_address", startAddressItem.getStartAddressName() ) );
        }

        // 리싸이클러 뷰 어뎁터 및 아이템 추가
        initRecyclerView( sidoItems );
        binding.cancelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty( binding.tittle1.getText() )) {
                    finish();
                } else if (TextUtils.isEmpty( binding.tittle1.getText() ) == false && TextUtils.isEmpty( binding.tittle2.getText() )) {
                    Toast.makeText( FinishAddressDialogActivity.this, "군/구를 선택해주세요", Toast.LENGTH_SHORT ).show();
                } else if (TextUtils.isEmpty( binding.tittle1.getText() ) == false && TextUtils.isEmpty( binding.tittle2.getText() ) == false && TextUtils.isEmpty( binding.tittle3.getText() )) {
                    Toast.makeText( FinishAddressDialogActivity.this, "동을 선택해주세요", Toast.LENGTH_SHORT ).show();
                } else if (TextUtils.isEmpty( binding.tittle1.getText() ) == false && TextUtils.isEmpty( binding.tittle2.getText() ) == false && TextUtils.isEmpty( binding.tittle3.getText() ) == false) {
                    finish();
                }

                // finish();
            }
        } );
        binding.okButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );

        binding.tittle1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tittle2.setText( "" );
                binding.tittle2.setBackgroundResource( R.color.white );
                binding.tittle3.setText( "" );
                binding.tittle3.setBackgroundResource( R.color.white );
                startAddressItem.setGunguName( "" );
                startAddressItem.setGunguCode( "" );
                startAddressItem.setDongCode( "" );
                startAddressItem.setDongName( "" );
                initRecyclerView( myApplication.sidoArrayItem.getSidoItems() );

                CURRENT_TYPE_F = ADDRESS_SIDO_TYPE;
            }
        } );
        binding.tittle2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendPacket sendPacket = null;
                SharedPreferences prefs = getSharedPreferences( "PrefName_finish", MODE_PRIVATE );
                binding.tittle3.setText( "" );
                binding.tittle3.setBackgroundResource( R.color.white );

                startAddressItem.setDongName( "" );
                startAddressItem.setDongCode( "" );
                startAddressItem.setSidoName( prefs.getString( "SidoName", "" ) );
                startAddressItem.setSidoCode( prefs.getString( "SidoCode", "" ) );


                sendPacket = GET_MAP_ADDR_SEND( ADDRESS_GUNGU_TYPE, startAddressItem.getSidoCode() );
                //   initRecyclerView(gunguArrayItem.getGunguItems());
                // initRecyclerView(myApplication.sidoArrayItem.getSidoItems());
                networkPresenter.GetMapAddr( sendPacket, new GetMapAddrInterface() {
                    @Override
                    public void success(RecvPacket packet) {
                        try {

                            final String[] recvData = packet.COMMAND.split( MyApplication.DELIMITER );
                            if (CURRENT_TYPE_F == ADDRESS_SIDO_TYPE) {

                                gunguCodes.clear();
                                gunguItems.clear();
                                for (int i = 0; i < recvData.length; i++) {
                                    // 시도, 코드 객체 담아줌
                                    gunguCodes.add( recvData[i++] );
                                    gunguItems.add( recvData[i] );

                                }
                                gunguArrayItem.setGunguItems( gunguItems );
                                gunguArrayItem.setGunguCodes( gunguCodes );

                                initRecyclerView( gunguItems );

                                CURRENT_TYPE_F = ADDRESS_GUNGU_TYPE;
                            } else if (CURRENT_TYPE_F == ADDRESS_GUNGU_TYPE) {

                                dongCodes.clear();
                                dongItems.clear();
                                for (int i = 0; i < recvData.length; i++) {
                                    // 시도, 코드 객체 담아줌
                                    dongCodes.add( recvData[i++] );
                                    dongItems.add( recvData[i] );

                                }
                                dongArrayItem.setDongItems( dongItems );
                                dongArrayItem.setDongCodes( dongCodes );

                                initRecyclerView( dongItems );

                                CURRENT_TYPE_F = ADDRESS_DONG_TYPE;
                            }
                        } catch (Exception e) {
                        }
                    }
                    @Override
                    public void fail(String t) {
                    }
                } );
                CURRENT_TYPE_F = ADDRESS_SIDO_TYPE;
            }
        } );

    }


    BaseRecyclerViewAdapter.OnItemClickListener addressOnItemClickListener = new BaseRecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            SendPacket sendPacket = null;

            if (CURRENT_TYPE_F == ADDRESS_SIDO_TYPE) {
                Log.i( "오류", String.valueOf( 0 ) );
                // 클릭 시 시도가 없을 경우 시도에 넣는다
                startAddressItem.setSidoName( myApplication.sidoArrayItem.getSidoItems().get( position ) );
                startAddressItem.setSidoCode( myApplication.sidoArrayItem.getSidoCodes().get( position ) );
                sendPacket = GET_MAP_ADDR_SEND( ADDRESS_GUNGU_TYPE, startAddressItem.getSidoCode() );
                binding.tittle1.setVisibility( View.VISIBLE );
                binding.tittle1.setText( startAddressItem.getSidoName() );

                SharedPreferences pref = getSharedPreferences( "PrefName_finish", MODE_PRIVATE );
                SharedPreferences.Editor editor = pref.edit();
                editor.putString( "tittle1", String.valueOf( binding.tittle1.getText() ) );
                editor.putString( "Start_position1", String.valueOf( position ) );
                editor.putString( "SidoName", myApplication.sidoArrayItem.getSidoItems().get( position ) );
                editor.putString( "SidoCode", myApplication.sidoArrayItem.getSidoCodes().get( position ) );
                editor.commit();

            } else if (CURRENT_TYPE_F == ADDRESS_GUNGU_TYPE) {
                Log.i( "오류", String.valueOf( 1 ) );
                // 클릭 시 군구가 없을 경우 군구에 넣는다.
                startAddressItem.setGunguName( gunguArrayItem.getGunguItems().get( position ) );
                startAddressItem.setGunguCode( gunguArrayItem.getGunguCodes().get( position ) );
                sendPacket = GET_MAP_ADDR_SEND( ADDRESS_DONG_TYPE, startAddressItem.getGunguCode() );
                binding.tittle2.setText( startAddressItem.getGunguName() );
                binding.tittle2.setBackgroundResource( R.color.title_blue );

                SharedPreferences pref = getSharedPreferences( "PrefName_finish", MODE_PRIVATE );
                SharedPreferences.Editor editor = pref.edit();
                editor.putString( "tittle2", String.valueOf( binding.tittle2.getText() ) );
                editor.putString( "GunguName", gunguArrayItem.getGunguItems().get( position ) );
                editor.putString( "GunguCode", gunguArrayItem.getGunguCodes().get( position ) );
                editor.commit();

            } else if (CURRENT_TYPE_F == ADDRESS_DONG_TYPE) {
                Log.i( "오류", String.valueOf( 2 ) );
                // 클릭 시 동이 없을 경우 동에 넣는다.
                startAddressItem.setDongName( dongArrayItem.getDongItems().get( position ) );
                binding.tittle3.setText( startAddressItem.getDongName() );
                binding.tittle3.setBackgroundResource( R.color.title_blue );

                sendPacket = GET_MAP_ADDR_SEND( ADDRESS_DONG_TYPE, startAddressItem.getGunguCode() );
                SharedPreferences pref = getSharedPreferences( "PrefName_finish", MODE_PRIVATE );
                SharedPreferences.Editor editors = pref.edit();
                editors.putString( "tittle3", String.valueOf( binding.tittle3.getText() ) );
                editors.commit();

                if (startAddressItem.getGunguName().equals( "세종시" )) {
                    startAddressItem.setGunguName( "" );
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra( "address_si", startAddressItem.getSidoName() );
                resultIntent.putExtra( "address_gu", startAddressItem.getGunguName() );
                resultIntent.putExtra( "address_dong", startAddressItem.getDongName() );
                setResult( RESULT_OK, resultIntent );

                binding.addressTittle.setText( startAddressItem.getStartAddressName() );

                SharedPreferences prefs = getSharedPreferences( "Address", MODE_PRIVATE );
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString( "finish_address", startAddressItem.getStartAddressName() );
                editor.commit();
                finish();


            } else {
                startAddressItem.setDongName( dongArrayItem.getDongItems().get( position ) );
                startAddressItem.setDongCode( dongArrayItem.getDongCodes().get( position ) );
            }

            binding.addressTittle.setText( startAddressItem.getStartAddressName() );

            networkPresenter.GetMapAddr( sendPacket, new GetMapAddrInterface() {
                @Override
                public void success(RecvPacket packet) {
                    try {

                        final String[] recvData = packet.COMMAND.split( MyApplication.DELIMITER );
                        Log.i( "CUR RSS", CURRENT_TYPE_F + "--" + ADDRESS_SIDO_TYPE );
                        if (CURRENT_TYPE_F == ADDRESS_SIDO_TYPE) {

                            gunguCodes.clear();
                            gunguItems.clear();
                            for (int i = 0; i < recvData.length; i++) {
                                // 시도, 코드 객체 담아줌
                                gunguCodes.add( recvData[i++] );
                                gunguItems.add( recvData[i] );

                            }
                            gunguArrayItem.setGunguItems( gunguItems );
                            gunguArrayItem.setGunguCodes( gunguCodes );

                            initRecyclerView( gunguItems );

                            CURRENT_TYPE_F = ADDRESS_GUNGU_TYPE;
                        } else if (CURRENT_TYPE_F == ADDRESS_GUNGU_TYPE) {

                            dongCodes.clear();
                            dongItems.clear();
                            for (int i = 0; i < recvData.length; i++) {
                                // 시도, 코드 객체 담아줌
                                dongCodes.add( recvData[i++] );
                                dongItems.add( recvData[i] );

                            }
                            dongArrayItem.setDongItems( dongItems );
                            dongArrayItem.setDongCodes( dongCodes );

                            initRecyclerView( dongItems );

                            CURRENT_TYPE_F = ADDRESS_DONG_TYPE;
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void fail(String t) {

                }
            } );
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        startAddressItem = null;

        gunguArrayItem = null;
        gunguItems = null;
        gunguCodes = null;

        dongArrayItem = null;
        dongItems = null;
        dongCodes = null;

    }

    public ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder iservice) {
            SocketService.SocketServiceBinder binder = (SocketService.SocketServiceBinder) iservice;
            service = binder.getService();
            bound = true;
            networkPresenter.service = service;
            address_change();
        }

        public void onServiceDisconnected(ComponentName className) {
            service = null;
            bound = false;
        }
    };

    public SendPacket GET_MAP_ADDR_SEND(String addressType, String addressCode) {
        // 군구 호출 메서드
        SendPacket sPacket = new SendPacket();

        addressCode = addressCode.replace( MyApplication.ROW_DELIMITER, "" );
        //ROW_DELEMITER이 붙어서 addressCode가 넘어오므로 제거해줌

        try {
            sPacket.AddType( Protocol.PT_REQUEST, Protocol.GET_MAP_ADDR );
            sPacket.AddMessageType( Protocol.GET_MAP_ADDR );
            sPacket.AddString( addressType );
            sPacket.AddString( addressCode );
            sPacket.AddRowDelimiter();
            sPacket.Commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sPacket;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initRecyclerView(ArrayList<String> adrressArrayList) {
        addressItemAdapter = new AddressItemAdapter( FinishAddressDialogActivity.this );
        addressItemAdapter.addItems( adrressArrayList );
        binding.recyclerViewAddress.setAdapter( addressItemAdapter );
        addressItemAdapter.setOnItemClickListener( addressOnItemClickListener );


    }

    public void address_change() {
        //시작주소 수정시 실행 메소드
        SharedPreferences prefs = getSharedPreferences( "PrefName_finish", MODE_PRIVATE );

        SendPacket sendPacket = null;
        startAddressItem.setDongCode( "" );
        startAddressItem.setDongName( "" );
        startAddressItem.setGunguName( prefs.getString( "GunguName", "" ) );
        startAddressItem.setGunguCode( prefs.getString( "GunguCode", "" ) );
        startAddressItem.setSidoName( prefs.getString( "SidoName", "" ) );
        startAddressItem.setSidoCode( prefs.getString( "SidoCode", "" ) );

        sendPacket = GET_MAP_ADDR_SEND( ADDRESS_DONG_TYPE, startAddressItem.getGunguCode() );

        networkPresenter.GetMapAddr( sendPacket, new GetMapAddrInterface() {
            @Override
            public void success(RecvPacket packet) {
                Log.i( "늦음", "0" );
                try {
                    final String[] recvData = packet.COMMAND.split( MyApplication.DELIMITER );

                    dongCodes.clear();
                    dongItems.clear();
                    for (int i = 0; i < recvData.length; i++) {
                        // 동 객체 담아줌
                        dongCodes.add( recvData[i++] );
                        dongItems.add( recvData[i] );

                    }
                    dongArrayItem.setDongItems( dongItems );
                    dongArrayItem.setDongCodes( dongCodes );
                    Log.i( "늦음", "1" );
                    initRecyclerView( dongItems );
                    //동 표시
                    CURRENT_TYPE_F = ADDRESS_DONG_TYPE;

                } catch (Exception e) {
                    Log.i( "늦음", "2" );
                }
            }

            @Override
            public void fail(String t) {
                Log.i( "늦음", "3" );
            }
        } );
        CURRENT_TYPE_F = ADDRESS_GUNGU_TYPE;

    }
}
