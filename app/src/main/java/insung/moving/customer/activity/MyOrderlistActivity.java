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

    private static ArrayList<String> order_items;    //  리스트

    private static MyorderListActivityBinding binding;
    private static NotittleToolbarBinding notittleToolbarBinding;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyOrderlistAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.myorder_list_activity );
        notittleToolbarBinding = DataBindingUtil.bind( binding.notittleToolbar.getRoot() );
        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );

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
        mRecyclerView.setItemAnimator( new DefaultItemAnimator() );
        mAdapter = new MyOrderlistAdapter( items );
        mRecyclerView.setAdapter( mAdapter );

    }

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
