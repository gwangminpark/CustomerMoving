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

import insung.moving.customer.adapter.recyclerview.BaseRecyclerViewAdapter;
import insung.moving.customer.adapter.*;
import insung.moving.customer.R;
import insung.moving.customer.databinding.ActivityStartAddressDialogBinding;
import insung.moving.customer.model.DongArrayItem;
import insung.moving.customer.model.GunguArrayItem;
import insung.moving.customer.model.HeightArrayItem;
import insung.moving.customer.model.StartAddressItem;
import insung.moving.customer.service.RecvPacket;
import insung.moving.customer.service.SendPacket;
import insung.moving.customer.service.SocketService;
import insung.moving.customer.service.resultInterface.GetMapAddrInterface;
import insung.moving.customer.temp.DEFINE;
import insung.moving.customer.temp.PROTOCOL;

import java.util.ArrayList;

public class StartAddressDialogActivity extends BaseActivity {

    public static final String INTENT_FILTER = DEFINE.INTENT_HEAD + "MAIN";
    private ActivityStartAddressDialogBinding binding;
    private AddressItemAdapter addressItemAdapter;

    private StartAddressItem startAddressItem; // 시도 군구 동 저장할 객체

    private ArrayList<String> sidoItems;    // 군구 이름
    private ArrayList<String> sidoCodes;    // 군구 코드


    GunguArrayItem gunguArrayItem; // 군구 리스트 저장 객체
    private ArrayList<String> gunguItems;    // 군구 이름
    private ArrayList<String> gunguCodes;    // 군구 코드

    DongArrayItem dongArrayItem; // 동 리스트 저장 객체
    private ArrayList<String> dongItems;    // 동 이름
    private ArrayList<String> dongCodes;    // 동 코드

    HeightArrayItem heightArrayItem; // 층 리스트 저장 객체
    private ArrayList<String> heightItems;    // 층 이름
    private ArrayList<String> heightCodes;    // 층  코드

    private String CURRENT_TYPE = "1"; // 기본 데이터 시도로 저장

    private String ADDRESS_SIDO_TYPE = "1";
    private String ADDRESS_GUNGU_TYPE = "3";
    private String ADDRESS_DONG_TYPE = "5";
    private String ADDRESS_HEIGHT_TYPE= "7";

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
            Log.i("늦음","ss");
            address_change();
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.i("늦음","nn");
            service = null;
            bound = false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start_address_dialog);

        binding.recyclerViewAddress.setHasFixedSize(true);
        binding.recyclerViewAddress.setLayoutManager(new GridLayoutManager( StartAddressDialogActivity.this, 4,GridLayoutManager.VERTICAL, false));


        startAddressItem = new StartAddressItem();
        gunguArrayItem = new GunguArrayItem();
        gunguItems = new ArrayList<>();
        gunguCodes = new ArrayList<>();

        dongArrayItem = new DongArrayItem();
        dongItems = new ArrayList<>();
        dongCodes = new ArrayList<>();

        sidoItems = new ArrayList<>();
        sidoCodes = new ArrayList<>();

        heightArrayItem = new HeightArrayItem();


        heightItems= new ArrayList<>( );
        heightCodes = new ArrayList<>( );

        sidoItems = myApplication.sidoArrayItem.getSidoItems();
        sidoCodes =  myApplication.sidoArrayItem.getSidoCodes();

        Intent intent = getIntent();
        String type = intent.getStringExtra("start");

        if(!type.equals( "출발주소")) {
            //최초  입력아닐시
            new Handler().postDelayed( new Runnable() {

                @Override
                public void run() {
                    address_change();
                    //최초 클릭이 아닐시 이전 클릭정보(마지막 동정보 )가 남아있음
                }
            } ,200);

            // address_change();
            SharedPreferences prefs = getSharedPreferences( "PrefName_start", MODE_PRIVATE );
            binding.tittle1.setText( prefs.getString( "tittle1", "" ) );
            binding.tittle2.setText( prefs.getString( "tittle2", "" ) );
            binding.tittle3.setText( prefs.getString( "tittle3", "" ) );
            binding.tittle1.setVisibility( View.VISIBLE );
            binding.tittle2.setBackgroundResource( R.color.title_blue );
            binding.tittle3.setBackgroundResource( R.color.title_blue );
            // startAddressItem.setDongName(dongArrayItem.getDongItems().get(position));

            setBindService(serviceConnection);
        }




        if(startAddressItem.getStartAddressName()!=null){
            SharedPreferences prefs = getSharedPreferences( "Address", MODE_PRIVATE );
            binding.addressTittle.setText( prefs.getString( "start_address", startAddressItem.getStartAddressName() ) );
        }


        // 리싸이클러 뷰 어뎁터 및 아이템 추가
        initRecyclerView(sidoItems);
        binding.cancelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("주소소", String.valueOf( binding.tittle1.getText() ) );
                Log.i("주소소", String.valueOf( binding.tittle2.getText() ) );
                Log.i("주소소", String.valueOf( binding.tittle3.getText() ) );
                if (TextUtils.isEmpty(binding.tittle1.getText())){
                    finish();
                }else if(TextUtils.isEmpty(binding.tittle1.getText())==false&&TextUtils.isEmpty(binding.tittle2.getText())){
                    Toast.makeText(StartAddressDialogActivity.this, "군/구를 선택해주세요", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(binding.tittle1.getText())==false&&TextUtils.isEmpty(binding.tittle2.getText())==false&&TextUtils.isEmpty( binding.tittle3.getText())){
                    Toast.makeText(StartAddressDialogActivity.this, "동을 선택해주세요", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(binding.tittle1.getText())==false&&TextUtils.isEmpty(binding.tittle2.getText())==false&&TextUtils.isEmpty( binding.tittle3.getText())==false){
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
                binding.tittle2.setText("");
                binding.tittle2.setBackgroundResource( R.color.white );
                binding.tittle3.setText("");
                binding.tittle3.setBackgroundResource( R.color.white );
                startAddressItem.setGunguName("");
                startAddressItem.setGunguCode("");
                startAddressItem.setDongCode( "" );
                startAddressItem.setDongName("");
                initRecyclerView(myApplication.sidoArrayItem.getSidoItems());

                CURRENT_TYPE = ADDRESS_SIDO_TYPE;
            }
        } );
        binding.tittle2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SendPacket sendPacket = null;
                SharedPreferences prefs = getSharedPreferences( "PrefName_start", MODE_PRIVATE );
                binding.tittle3.setText("");
                binding.tittle3.setBackgroundResource( R.color.white );

                startAddressItem.setDongName("");
                startAddressItem.setDongCode("");
                startAddressItem.setSidoName(prefs.getString( "SidoName", "" ));
                startAddressItem.setSidoCode(prefs.getString( "SidoCode", "" ));


                sendPacket = GET_MAP_ADDR_SEND(ADDRESS_GUNGU_TYPE, startAddressItem.getSidoCode());
                //   initRecyclerView(gunguArrayItem.getGunguItems());
                // initRecyclerView(myApplication.sidoArrayItem.getSidoItems());
                networkPresenter.GetMapAddr(sendPacket, new GetMapAddrInterface() {
                    @Override
                    public void success(RecvPacket packet) {
                        try {

                            final String[] recvData = packet.COMMAND.split( DEFINE.DELIMITER);
                            if(CURRENT_TYPE == ADDRESS_SIDO_TYPE){

                                gunguCodes.clear();
                                gunguItems.clear();
                                for (int i = 0; i < recvData.length; i++) {
                                    // 시도, 코드 객체 담아줌
                                    gunguCodes.add(recvData[i++]);
                                    gunguItems.add(recvData[i]);

                                }
                                gunguArrayItem.setGunguItems(gunguItems);
                                gunguArrayItem.setGunguCodes(gunguCodes);

                                initRecyclerView(gunguItems);

                                CURRENT_TYPE = ADDRESS_GUNGU_TYPE;
                            }else if(CURRENT_TYPE == ADDRESS_GUNGU_TYPE){

                                dongCodes.clear();
                                dongItems.clear();
                                for (int i = 0; i < recvData.length; i++) {
                                    // 시도, 코드 객체 담아줌
                                    dongCodes.add(recvData[i++]);
                                    dongItems.add(recvData[i]);

                                }
                                dongArrayItem.setDongItems(dongItems);
                                dongArrayItem.setDongCodes(dongCodes);

                                initRecyclerView(dongItems);

                                CURRENT_TYPE = ADDRESS_DONG_TYPE;
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void fail(String t) {

                    }

                });
                CURRENT_TYPE = ADDRESS_SIDO_TYPE;
            }
        } );

    }


    BaseRecyclerViewAdapter.OnItemClickListener addressOnItemClickListener = new BaseRecyclerViewAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            SendPacket sendPacket = null;

            if(CURRENT_TYPE == ADDRESS_SIDO_TYPE){

                // 클릭 시 시도가 없을 경우 시도에 넣는다
                startAddressItem.setSidoName(myApplication.sidoArrayItem.getSidoItems().get(position));
                startAddressItem.setSidoCode(myApplication.sidoArrayItem.getSidoCodes().get(position));
                sendPacket = GET_MAP_ADDR_SEND(ADDRESS_GUNGU_TYPE, startAddressItem.getSidoCode());
                binding.tittle1.setVisibility( View.VISIBLE );
                binding.tittle1.setText(startAddressItem.getSidoName());

                SharedPreferences pref = getSharedPreferences("PrefName_start", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("tittle1", String.valueOf( binding.tittle1.getText() ) );
                editor.putString("Start_position1", String.valueOf( position ) );
                editor.putString("SidoName",myApplication.sidoArrayItem.getSidoItems().get(position));
                editor.putString("SidoCode",myApplication.sidoArrayItem.getSidoCodes().get(position));
                editor.commit();
            }else if(CURRENT_TYPE == ADDRESS_GUNGU_TYPE){

                // 클릭 시 군구가 없을 경우 군구에 넣는다.
                if(gunguArrayItem.getGunguItems().get(position)==null){

                }
                startAddressItem.setGunguName(gunguArrayItem.getGunguItems().get(position));
                startAddressItem.setGunguCode(gunguArrayItem.getGunguCodes().get(position));
                sendPacket = GET_MAP_ADDR_SEND(ADDRESS_DONG_TYPE, startAddressItem.getGunguCode());
                binding.tittle2.setText(startAddressItem.getGunguName());
                binding.tittle2.setBackgroundResource(R.color.title_blue );

                SharedPreferences pref = getSharedPreferences("PrefName_start", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("tittle2", String.valueOf( binding.tittle2.getText() ) );
                editor.putString("GunguName",gunguArrayItem.getGunguItems().get(position));
                editor.putString("GunguCode",gunguArrayItem.getGunguCodes().get(position));
                editor.commit();
            }else if(CURRENT_TYPE == ADDRESS_DONG_TYPE){

                // 클릭 시 동이 없을 경우 동에 넣는다.
                startAddressItem.setDongName(dongArrayItem.getDongItems().get(position));
                binding.tittle3.setText(startAddressItem.getDongName());
                binding.tittle3.setBackgroundResource(R.color.title_blue );

                sendPacket = GET_MAP_ADDR_SEND(ADDRESS_DONG_TYPE, startAddressItem.getGunguCode());

                SharedPreferences pref = getSharedPreferences("PrefName_start", MODE_PRIVATE);
                SharedPreferences.Editor editors = pref.edit();
                editors.putString("tittle3", String.valueOf( binding.tittle3.getText() ) );
                editors.commit();
                //동 클릭했을시
                //층수 데이터는 소켓통신 안해도 되므로 바로 리싸이클러뷰에 뿌려줌
                //밑에 주석풀면 층수 표시됨
/*                for (int i = 0; i < DATA.height.length; i++) {
                    // 시도, 코드 객체 담아줌
                    heightCodes.add( String.valueOf(i));
                    heightItems.add(DATA.height[i]+"층");
                }
                heightArrayItem.setHeightItems( heightItems );
                heightArrayItem.setHeightCodes( heightCodes );
                initRecyclerView(heightItems);

                CURRENT_TYPE = ADDRESS_HEIGHT_TYPE;*/


                // MainActivity.MAIN_INPUT_CHECK=3;
                //메인 인풋체크변수 변경
                if(startAddressItem.getGunguName().equals("세종시")){
                    startAddressItem.setGunguName("");
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("address_si",startAddressItem.getSidoName());
                resultIntent.putExtra("address_gu",startAddressItem.getGunguName());
                resultIntent.putExtra("address_dong",startAddressItem.getDongName());
                setResult(RESULT_OK,resultIntent);




                binding.addressTittle.setText(startAddressItem.getStartAddressName());

                SharedPreferences prefs = getSharedPreferences( "Address", MODE_PRIVATE );
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("start_address",startAddressItem.getStartAddressName());
                editor.commit();

                finish();

            }else if(CURRENT_TYPE==ADDRESS_HEIGHT_TYPE){
                //층 클릭했을시

                Log.i("퍼지션1", String.valueOf( position ) );

                startAddressItem.setHeightName(myApplication.heightArrayItem.getHeightItems().get(position));
                binding.addressTittle.setText(startAddressItem.getStartAddressName()+"-"+(position+1)+"층");

            }

            else {

                startAddressItem.setDongName(dongArrayItem.getDongItems().get(position));
                startAddressItem.setDongCode(dongArrayItem.getDongCodes().get(position));
            }

            binding.addressTittle.setText(startAddressItem.getStartAddressName());

            networkPresenter.GetMapAddr(sendPacket, new GetMapAddrInterface() {
                @Override
                public void success(RecvPacket packet) {
                    try {

                        final String[] recvData = packet.COMMAND.split( DEFINE.DELIMITER);
                        if(CURRENT_TYPE == ADDRESS_SIDO_TYPE){

                            gunguCodes.clear();
                            gunguItems.clear();
                            for (int i = 0; i < recvData.length; i++) {
                                // 시도, 코드 객체 담아줌
                                gunguCodes.add(recvData[i++]);
                                gunguItems.add(recvData[i]);

                            }
                            gunguArrayItem.setGunguItems(gunguItems);
                            gunguArrayItem.setGunguCodes(gunguCodes);

                            initRecyclerView(gunguItems);

                            CURRENT_TYPE = ADDRESS_GUNGU_TYPE;
                        }else if(CURRENT_TYPE == ADDRESS_GUNGU_TYPE){

                            dongCodes.clear();
                            dongItems.clear();
                            for (int i = 0; i < recvData.length; i++) {
                                // 시도, 코드 객체 담아줌
                                dongCodes.add(recvData[i++]);
                                dongItems.add(recvData[i]);

                            }
                            dongArrayItem.setDongItems(dongItems);
                            dongArrayItem.setDongCodes(dongCodes);

                            initRecyclerView(dongItems);

                            CURRENT_TYPE = ADDRESS_DONG_TYPE;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void fail(String t) {

                }

            });
        }
    };


    public SendPacket GET_MAP_ADDR_SEND(String addressType, String addressCode){
        // 군구 호출 메서드
        SendPacket sPacket = new SendPacket();

        addressCode=addressCode.replace(DEFINE.ROW_DELIMITER,"" );
        //ROW_DELEMITER이 붙어서 addressCode가 넘어오므로 제거해줌

        try	{
            sPacket.AddType( PROTOCOL.PT_REQUEST, PROTOCOL.GET_MAP_ADDR);
            sPacket.AddMessageType(PROTOCOL.GET_MAP_ADDR);
            sPacket.AddString(addressType);
            sPacket.AddString(addressCode);
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
       /* if(CURRENT_TYPE == ADDRESS_SIDO_TYPE){
            finish();
        }else if(CURRENT_TYPE == ADDRESS_GUNGU_TYPE){
            startAddressItem.setGunguName("");
            startAddressItem.setGunguCode("");

            initRecyclerView(myApplication.sidoArrayItem.getSidoItems());

            CURRENT_TYPE = ADDRESS_SIDO_TYPE;

        }else if(CURRENT_TYPE == ADDRESS_DONG_TYPE){
            startAddressItem.setDongName("");
            startAddressItem.setDongCode("");

            initRecyclerView(gunguArrayItem.getGunguItems());

            CURRENT_TYPE = ADDRESS_GUNGU_TYPE;
        }else if(CURRENT_TYPE==ADDRESS_HEIGHT_TYPE){
            startAddressItem.setHeightName("");

            initRecyclerView(dongArrayItem.getDongItems());

            heightItems.clear();
            //뒤로가기 눌렀을때 층 아이템 클리어해줌
            CURRENT_TYPE=ADDRESS_DONG_TYPE;
        }
        binding.addressTittle.setText(startAddressItem.getStartAddressName());*/
    }
    private void initRecyclerView(ArrayList<String> adrressArrayList){
        addressItemAdapter = new AddressItemAdapter( StartAddressDialogActivity.this);
        addressItemAdapter.addItems(adrressArrayList);
        binding.recyclerViewAddress.setAdapter(addressItemAdapter);
        addressItemAdapter.setOnItemClickListener(addressOnItemClickListener);


    }
    public void address_change(){
        //시작주소 수정시 실행 메소드
        SharedPreferences prefs = getSharedPreferences( "PrefName_start", MODE_PRIVATE );

        SendPacket sendPacket = null;
        startAddressItem.setDongCode( "" );
        startAddressItem.setDongName("");
        startAddressItem.setGunguName(prefs.getString( "GunguName", "" ));
        startAddressItem.setGunguCode(prefs.getString( "GunguCode", "" ));
        startAddressItem.setSidoName(prefs.getString( "SidoName", "" ));
        startAddressItem.setSidoCode(prefs.getString( "SidoCode", "" ));


        sendPacket = GET_MAP_ADDR_SEND(ADDRESS_DONG_TYPE, startAddressItem.getGunguCode());
        //   initRecyclerView(gunguArrayItem.getGunguItems());
        // initRecyclerView(myApplication.sidoArrayItem.getSidoItems());


        networkPresenter.GetMapAddr(sendPacket, new GetMapAddrInterface() {
            @Override
            public void success(RecvPacket packet) {
                Log.i("늦음","0");
                try {
                    final String[] recvData = packet.COMMAND.split( DEFINE.DELIMITER);

                    dongCodes.clear();
                    dongItems.clear();
                    for (int i = 0; i < recvData.length; i++) {
                        // 동 객체 담아줌
                        dongCodes.add(recvData[i++]);
                        dongItems.add(recvData[i]);

                    }
                    dongArrayItem.setDongItems(dongItems);
                    dongArrayItem.setDongCodes(dongCodes);
                    Log.i("늦음","1");
                    initRecyclerView(dongItems);
                    //동 표시
                    CURRENT_TYPE = ADDRESS_DONG_TYPE;

                }catch (Exception e){
                    Log.i("늦음","2");
                }
            }

            @Override
            public void fail(String t) {

            }

        });
        CURRENT_TYPE = ADDRESS_GUNGU_TYPE;

    }
}
