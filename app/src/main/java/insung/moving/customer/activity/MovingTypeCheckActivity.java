package insung.moving.customer.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import insung.moving.customer.R;
import insung.moving.customer.databinding.ActivityMovingTypeDialogBinding;


/**
 * Created by user on 2018-06-14.
 */

public class MovingTypeCheckActivity extends BaseActivity {

    private ActivityMovingTypeDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_moving_type_dialog );

        Intent intent = getIntent();
        String type = intent.getStringExtra( "type" );

        if (type.equals( "이사종류" )) {
            //최초입력일시
            MainActivity.movingtype_check = 1;
        }

        final Button okButton = (Button) findViewById( R.id.okButton );
        final Button cancelButton = (Button) findViewById( R.id.cancelButton );

        final RelativeLayout click1 = (RelativeLayout) findViewById( R.id.click1 );
        final RelativeLayout click2 = (RelativeLayout) findViewById( R.id.click2 );
        final RelativeLayout click3 = (RelativeLayout) findViewById( R.id.click3 );
        final RelativeLayout click4 = (RelativeLayout) findViewById( R.id.click4 );
        final RelativeLayout click5 = (RelativeLayout) findViewById( R.id.click5 );
        final RelativeLayout click6 = (RelativeLayout) findViewById( R.id.click6 );
        //클릭 레이아웃

        final RelativeLayout checkview1 = (RelativeLayout) findViewById( R.id.checkview1 );
        final RelativeLayout checkview2 = (RelativeLayout) findViewById( R.id.checkview2 );
        final RelativeLayout checkview3 = (RelativeLayout) findViewById( R.id.checkview3 );
        final RelativeLayout checkview4 = (RelativeLayout) findViewById( R.id.checkview4 );
        final RelativeLayout checkview5 = (RelativeLayout) findViewById( R.id.checkview5 );
        final RelativeLayout checkview6 = (RelativeLayout) findViewById( R.id.checkview6 );

        final ImageView checkview1_1 = (ImageView) findViewById( R.id.checkview1_1 );
        final ImageView checkview2_2 = (ImageView) findViewById( R.id.checkview2_2 );
        final ImageView checkview3_3 = (ImageView) findViewById( R.id.checkview3_3 );
        final ImageView checkview4_4 = (ImageView) findViewById( R.id.checkview4_4 );
        final ImageView checkview5_5 = (ImageView) findViewById( R.id.checkview5_5 );
        final ImageView checkview6_6 = (ImageView) findViewById( R.id.checkview6_6 );
        // 집 이미지 형광,검은색 교체 ex) 1_1 붙은건 형광색 이미지

        if (MainActivity.movingtype_check != 0) {
            checkview1.setBackgroundResource( R.drawable.house_01 );
            switch (MainActivity.movingtype_check) {
                case 1:
                    checkview1_1.setVisibility( View.VISIBLE );
                    checkview1.setBackgroundResource( R.drawable.house_01_1 );
                    break;
                case 2:
                    checkview2_2.setVisibility( View.VISIBLE );
                    checkview2.setBackgroundResource( R.drawable.house_02_1 );
                    break;
                case 3:
                    checkview3_3.setVisibility( View.VISIBLE );
                    checkview3.setBackgroundResource( R.drawable.house_03_1 );
                    break;
                case 4:
                    checkview4_4.setVisibility( View.VISIBLE );
                    checkview4.setBackgroundResource( R.drawable.house_04_1 );
                    break;
                case 5:
                    checkview5_5.setVisibility( View.VISIBLE );
                    checkview5.setBackgroundResource( R.drawable.house_05_1 );
                    break;
                case 6:
                    checkview6_6.setVisibility( View.VISIBLE );
                    checkview6.setBackgroundResource( R.drawable.house_06_1 );
                    break;
            }
        }

        click1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkview1_1.getVisibility() == View.GONE) {
                    //가정이사 클릭시
                    checkview1_1.setVisibility( View.VISIBLE );
                    checkview1.setBackgroundResource( R.drawable.house_01_1 );
                    checkview2.setBackgroundResource( R.drawable.house_02 );
                    checkview3.setBackgroundResource( R.drawable.house_03 );
                    checkview4.setBackgroundResource( R.drawable.house_04 );
                    checkview5.setBackgroundResource( R.drawable.house_05 );
                    checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview2_2.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 1;
                    //메인에서 선언한 type 체크를 위한 static 변수
                } else {
                    checkview1_1.setVisibility( View.GONE );
                    checkview1.setBackgroundResource( R.drawable.house_01 );

                    MainActivity.movingtype_check = 0;
                    //메인에서 선언한 type 체크를 위한 static 변수
                }
            }
        } );

        click2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview2_2.getVisibility() == View.GONE) {
                    //사무실이사 클릭시
                    checkview2_2.setVisibility( View.VISIBLE );
                    checkview2.setBackgroundResource( R.drawable.house_02_1 );
                    checkview1.setBackgroundResource( R.drawable.house_01 );
                    checkview3.setBackgroundResource( R.drawable.house_03 );
                    checkview4.setBackgroundResource( R.drawable.house_04 );
                    checkview5.setBackgroundResource( R.drawable.house_05 );
                    checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 2;
                    //메인에서 선언한 type 체크를 위한 static 변수

                } else {
                    checkview2_2.setVisibility( View.GONE );
                    checkview2.setBackgroundResource( R.drawable.house_02 );
                    MainActivity.movingtype_check = 0;
                    //메인에서 선언한 type 체크를 위한 static 변수

                }

            }
        } );
        click3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview3_3.getVisibility() == View.GONE) {
                    checkview3_3.setVisibility( View.VISIBLE );
                    checkview3.setBackgroundResource( R.drawable.house_03_1 );
                    checkview1.setBackgroundResource( R.drawable.house_01 );
                    checkview2.setBackgroundResource( R.drawable.house_02 );
                    checkview4.setBackgroundResource( R.drawable.house_04 );
                    checkview5.setBackgroundResource( R.drawable.house_05 );
                    checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview2_2.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 3;

                } else {
                    checkview3_3.setVisibility( View.GONE );
                    checkview3.setBackgroundResource( R.drawable.house_03 );
                    MainActivity.movingtype_check = 0;
                }

            }
        } );
        click4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview4_4.getVisibility() == View.GONE) {
                    checkview4_4.setVisibility( View.VISIBLE );
                    checkview4.setBackgroundResource( R.drawable.house_04_1 );

                    checkview1.setBackgroundResource( R.drawable.house_01 );
                    checkview2.setBackgroundResource( R.drawable.house_02 );
                    checkview3.setBackgroundResource( R.drawable.house_03 );
                    checkview5.setBackgroundResource( R.drawable.house_05 );
                    checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview2_2.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 4;

                } else {

                    checkview4_4.setVisibility( View.GONE );
                    checkview4.setBackgroundResource( R.drawable.house_04 );
                    MainActivity.movingtype_check = 0;

                }

            }
        } );

        click5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview5_5.getVisibility() == View.GONE) {
                    checkview5_5.setVisibility( View.VISIBLE );
                    checkview5.setBackgroundResource( R.drawable.house_05_1 );

                    checkview1.setBackgroundResource( R.drawable.house_01 );
                    checkview2.setBackgroundResource( R.drawable.house_02 );
                    checkview3.setBackgroundResource( R.drawable.house_03 );
                    checkview4.setBackgroundResource( R.drawable.house_04 );
                    checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview2_2.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 5;

                } else {

                    checkview5_5.setVisibility( View.GONE );
                    checkview5.setBackgroundResource( R.drawable.house_05 );
                    MainActivity.movingtype_check = 0;

                }

            }
        } );
        click6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview6_6.getVisibility() == View.GONE) {
                    checkview6_6.setVisibility( View.VISIBLE );
                    checkview6.setBackgroundResource( R.drawable.house_06_1 );
                    checkview1.setBackgroundResource( R.drawable.house_01 );
                    checkview2.setBackgroundResource( R.drawable.house_02 );
                    checkview3.setBackgroundResource( R.drawable.house_03 );
                    checkview4.setBackgroundResource( R.drawable.house_04 );
                    checkview5.setBackgroundResource( R.drawable.house_05 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview2_2.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 6;

                } else {

                    checkview6_6.setVisibility( View.GONE );
                    checkview6.setBackgroundResource( R.drawable.house_06 );

                    MainActivity.movingtype_check = 0;
                }

            }
        } );


        okButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String type = intent.getStringExtra( "type" );
                Intent resultIntent = new Intent();
                resultIntent.putExtra( "result", type );
                setResult( RESULT_OK, resultIntent );
                finish();

            }
        } );
        cancelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}