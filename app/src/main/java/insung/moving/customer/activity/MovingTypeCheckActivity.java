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

        final ImageView checkview1_1 = (ImageView) findViewById( R.id.checkview1_1 );
        final ImageView checkview2_2 = (ImageView) findViewById( R.id.checkview2_2 );
        final ImageView checkview3_3 = (ImageView) findViewById( R.id.checkview3_3 );
        final ImageView checkview4_4 = (ImageView) findViewById( R.id.checkview4_4 );
        final ImageView checkview5_5 = (ImageView) findViewById( R.id.checkview5_5 );
        final ImageView checkview6_6 = (ImageView) findViewById( R.id.checkview6_6 );
        // 집 이미지 형광,검은색 교체 ex) 1_1 붙은건 형광색 이미지

        binding.checkview1.setBackgroundResource( R.drawable.house_01 );
        switch (MainActivity.movingtype_check) {
            case 1:
                checkview1_1.setVisibility( View.VISIBLE );
                binding.checkview1.setBackgroundResource( R.drawable.house_01_1 );
                break;

            case 2:
                checkview2_2.setVisibility( View.VISIBLE );
                binding.checkview2.setBackgroundResource( R.drawable.house_02_1 );
                break;

            case 3:
                checkview3_3.setVisibility( View.VISIBLE );
                binding.checkview3.setBackgroundResource( R.drawable.house_03_1 );
                break;

            case 4:
                checkview4_4.setVisibility( View.VISIBLE );
                binding.checkview4.setBackgroundResource( R.drawable.house_04_1 );
                break;

            case 5:
                checkview5_5.setVisibility( View.VISIBLE );
                binding.checkview5.setBackgroundResource( R.drawable.house_05_1 );
                break;

            case 6:
                checkview6_6.setVisibility( View.VISIBLE );
                binding.checkview6.setBackgroundResource( R.drawable.house_06_1 );
                break;

        }

        binding.click1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkview1_1.getVisibility() == View.GONE) {
                    //가정이사 클릭시
                    checkview1_1.setVisibility( View.VISIBLE );
                    binding.checkview1.setBackgroundResource( R.drawable.house_01_1 );
                    binding.checkview2.setBackgroundResource( R.drawable.house_02 );
                    binding.checkview3.setBackgroundResource( R.drawable.house_03 );
                    binding.checkview4.setBackgroundResource( R.drawable.house_04 );
                    binding.checkview5.setBackgroundResource( R.drawable.house_05 );
                    binding.checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview2_2.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 1;
                    //메인에서 선언한 type 체크를 위한 static 변수
                } else {
                    checkview1_1.setVisibility( View.GONE );
                    binding.checkview1.setBackgroundResource( R.drawable.house_01 );

                    MainActivity.movingtype_check = 0;
                    //메인에서 선언한 type 체크를 위한 static 변수
                }
            }
        } );

        binding.click2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview2_2.getVisibility() == View.GONE) {
                    //사무실이사 클릭시
                    checkview2_2.setVisibility( View.VISIBLE );
                    binding.checkview2.setBackgroundResource( R.drawable.house_02_1 );
                    binding.checkview1.setBackgroundResource( R.drawable.house_01 );
                    binding.checkview3.setBackgroundResource( R.drawable.house_03 );
                    binding.checkview4.setBackgroundResource( R.drawable.house_04 );
                    binding.checkview5.setBackgroundResource( R.drawable.house_05 );
                    binding.checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 2;
                    //메인에서 선언한 type 체크를 위한 static 변수

                } else {
                    checkview2_2.setVisibility( View.GONE );
                    binding.checkview2.setBackgroundResource( R.drawable.house_02 );
                    MainActivity.movingtype_check = 0;
                    //메인에서 선언한 type 체크를 위한 static 변수

                }

            }
        } );
        binding.click3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview3_3.getVisibility() == View.GONE) {
                    checkview3_3.setVisibility( View.VISIBLE );
                    binding.checkview3.setBackgroundResource( R.drawable.house_03_1 );
                    binding.checkview1.setBackgroundResource( R.drawable.house_01 );
                    binding.checkview2.setBackgroundResource( R.drawable.house_02 );
                    binding.checkview4.setBackgroundResource( R.drawable.house_04 );
                    binding.checkview5.setBackgroundResource( R.drawable.house_05 );
                    binding.checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview2_2.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 3;

                } else {
                    checkview3_3.setVisibility( View.GONE );
                    binding.checkview3.setBackgroundResource( R.drawable.house_03 );
                    MainActivity.movingtype_check = 0;
                }

            }
        } );
        binding.click4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview4_4.getVisibility() == View.GONE) {
                    checkview4_4.setVisibility( View.VISIBLE );
                    binding.checkview4.setBackgroundResource( R.drawable.house_04_1 );

                    binding.checkview1.setBackgroundResource( R.drawable.house_01 );
                    binding.checkview2.setBackgroundResource( R.drawable.house_02 );
                    binding.checkview3.setBackgroundResource( R.drawable.house_03 );
                    binding.checkview5.setBackgroundResource( R.drawable.house_05 );
                    binding.checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview2_2.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 4;

                } else {

                    checkview4_4.setVisibility( View.GONE );
                    binding.checkview4.setBackgroundResource( R.drawable.house_04 );
                    MainActivity.movingtype_check = 0;

                }

            }
        } );

        binding.click5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview5_5.getVisibility() == View.GONE) {
                    checkview5_5.setVisibility( View.VISIBLE );
                    binding.checkview5.setBackgroundResource( R.drawable.house_05_1 );

                    binding.checkview1.setBackgroundResource( R.drawable.house_01 );
                    binding.checkview2.setBackgroundResource( R.drawable.house_02 );
                    binding.checkview3.setBackgroundResource( R.drawable.house_03 );
                    binding.checkview4.setBackgroundResource( R.drawable.house_04 );
                    binding.checkview6.setBackgroundResource( R.drawable.house_06 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview2_2.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview6_6.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 5;

                } else {

                    checkview5_5.setVisibility( View.GONE );
                    binding.checkview5.setBackgroundResource( R.drawable.house_05 );
                    MainActivity.movingtype_check = 0;

                }

            }
        } );
        binding.click6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkview6_6.getVisibility() == View.GONE) {
                    checkview6_6.setVisibility( View.VISIBLE );
                    binding.checkview6.setBackgroundResource( R.drawable.house_06_1 );
                    binding.checkview1.setBackgroundResource( R.drawable.house_01 );
                    binding.checkview2.setBackgroundResource( R.drawable.house_02 );
                    binding.checkview3.setBackgroundResource( R.drawable.house_03 );
                    binding.checkview4.setBackgroundResource( R.drawable.house_04 );
                    binding.checkview5.setBackgroundResource( R.drawable.house_05 );
                    checkview1_1.setVisibility( View.GONE );
                    checkview2_2.setVisibility( View.GONE );
                    checkview3_3.setVisibility( View.GONE );
                    checkview4_4.setVisibility( View.GONE );
                    checkview5_5.setVisibility( View.GONE );

                    MainActivity.movingtype_check = 6;

                } else {

                    checkview6_6.setVisibility( View.GONE );
                    binding.checkview6.setBackgroundResource( R.drawable.house_06 );

                    MainActivity.movingtype_check = 0;
                }

            }
        } );

        binding.okButton.setOnClickListener( new View.OnClickListener() {
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
        binding.cancelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
    }
}