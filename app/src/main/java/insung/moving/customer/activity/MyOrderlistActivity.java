package insung.moving.customer.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import insung.moving.customer.adapter.MyOrderlistAdapter;
import insung.moving.customer.R;

import insung.moving.customer.databinding.CommonNavigationBinding;
import insung.moving.customer.databinding.MyorderListActivityBinding;
import insung.moving.customer.databinding.NotittleToolbarBinding;
import insung.moving.customer.model.OrderlistData;


/**
 * Created by user on 2018-07-11.
 */

public class MyOrderlistActivity extends BaseActivity {

    private ArrayList<String> order_items;    //  리스트

    private MyorderListActivityBinding binding;
    private NotittleToolbarBinding notittleToolbarBinding;
    private CommonNavigationBinding commonNavigationBinding;

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    MyOrderlistAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.myorder_list_activity );
        notittleToolbarBinding = DataBindingUtil.bind( binding.notittleToolbar.getRoot() );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
//        commonNavigationBinding = DataBindingUtil.bind(binding.commonNavigation.getRoot());

//       initNavigation();
        // initActionBar();

        order_items = new ArrayList<>();

        setSupportActionBar( notittleToolbarBinding.toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayShowTitleEnabled( false );

        mRecyclerView = binding.recyclerview;
        mLayoutManager = new LinearLayoutManager( this );
        mLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );

        Intent i = getIntent();
        ArrayList<String> myorder_list = (ArrayList<String>) i.getSerializableExtra( "listdata" );
        // MainActivity에서 넘긴 객체를 받음

        ArrayList<OrderlistData> items = new ArrayList();
        //생성

        for (int z = 0; z < myorder_list.size() / 5; z++) {
            //데이터가 5개씩 한 리스트 이므로 /5를 해줌

            //items 에 넣어줌 리사이클러뷰 표시
            items.add( new OrderlistData( myorder_list.get( (z * 5) + 0 ),         //신청일자
                    myorder_list.get( (z * 5) + 1 ),         //이사종류
                    myorder_list.get( (z * 5) + 2 ),         //이사날짜
                    myorder_list.get( (z * 5) + 3 ),         //출발지
                    myorder_list.get( (z * 5) + 4 ) ) );       //도착지
        }

        // LinearLayout으로 설정
        mRecyclerView.setLayoutManager( mLayoutManager );
        // Animation Defualt 설정
        mRecyclerView.setItemAnimator( new DefaultItemAnimator() );
        // Decoration 설정
        // mRecyclerView.addItemDecoration(new RecyclerViewDecoration(this, RecyclerViewDecoration.VERTICAL_LIST));
        // Adapter 생성
        mAdapter = new MyOrderlistAdapter( items );
        mRecyclerView.setAdapter( mAdapter );


    }

    private void initNavigation() {
        // 토글 아이콘

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, binding.drawerLayout, notittleToolbarBinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        toggle.getDrawerArrowDrawable().setColor( getResources().getColor( R.color.actionbar_text ) );
        binding.drawerLayout.setDrawerListener( toggle );

        toggle.syncState();

        commonNavigationBinding.linearRequest.setOnClickListener( navigationClickListener );
        commonNavigationBinding.linearCheck.setOnClickListener( navigationClickListener );
        commonNavigationBinding.linearMoving.setOnClickListener( navigationClickListener );
        commonNavigationBinding.linearPolicy.setOnClickListener( navigationClickListener );

    }

    private View.OnClickListener navigationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.linearRequest:
                    finish();
                    Intent intent = new Intent(
                            getApplicationContext(), // 현재 화면의 제어권자
                            MainActivity.class ); // 다음 넘어갈 클래스 지정
                    startActivity( intent ); // 다음 화면으로 넘어간다
                    break;

                case R.id.linearCheck:
                    showProgressDialog( "", "조회중입니다.\n잠시만 기다려 주세요." );
                    // 이사 체크리스트
                    break;

                case R.id.linearMoving:
                    break;

                case R.id.linearPolicy:
                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
        //뒤로가기 버튼
        order_items.clear();
        this.finish();
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
}
