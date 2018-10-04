package insung.moving.customer.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import insung.moving.customer.R;
import insung.moving.customer.model.OrderData;
import insung.moving.customer.util.ReferrerReceiver;


/**
 * Created by user on 2018-06-14.
 */

public class PhoneDialogActivity extends BaseActivity {

    private Context context;

    private int checkbox_check = 0;
    //체크박스 체크했는지 체크하기 위한 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );



    }

    public PhoneDialogActivity(Context context) {
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog( context );

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature( Window.FEATURE_NO_TITLE );

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView( R.layout.dialogphone );

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final EditText name = (EditText) dlg.findViewById( R.id.name );
        final EditText message = (EditText) dlg.findViewById( R.id.mesgase );
        final Button okButton = (Button) dlg.findViewById( R.id.okButton );
        final Button cancelButton = (Button) dlg.findViewById( R.id.cancelButton );
        final WebView webView=(WebView)dlg.findViewById( R.id.web );
        final CheckBox checkBox = (CheckBox) dlg.findViewById( R.id.checkbox );

        TextView type = ((MainActivity) context).findViewById( R.id.moving_phone );


        webView.setWebViewClient( new WebViewClient() ); // 이걸 안해주면 새창이 뜸
        webView.loadUrl("http://24.414.co.kr/privacy/35_inner.html");

        if (!type.getText().equals( "연락처" )) {
            //최초 입력시
            SharedPreferences prefs = context.getSharedPreferences( "PrefName", MODE_PRIVATE );
            name.setText( prefs.getString( "name", "" ) );
            message.setText( prefs.getString( "phone", "" ) );
        }


        checkBox.setOnClickListener( new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    checkbox_check = 1;
                    //체크박스 체크시 1로바꿔줌
                } else {
                    checkbox_check = 0;
                    //체크안했을시 그대로 0
                }
            }
        } );

        okButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //확인버튼 클릭시
                SharedPreferences prefs = context.getSharedPreferences( "PrefName", MODE_PRIVATE );
                if (checkbox_check == 0) {
                    //약관체크 안했을시
                    Toast.makeText( context, "약관에 동의해주세요.", Toast.LENGTH_SHORT ).show();

                } else if (checkbox_check == 1) {
                    //약관체크 했을시
                    if (name.getText().toString().getBytes().length <= 0) {
                        Toast.makeText( context, "이름을 입력해주세요", Toast.LENGTH_SHORT ).show();

                    } else if (message.getText().toString().getBytes().length <= 0) {
                        Toast.makeText( context, "후대폰번호를 입력해주세요", Toast.LENGTH_SHORT ).show();
                    } else {
                        OrderData orderdata = new OrderData();
                        orderdata.setName( message.getText().toString() );


                        MainActivity.movingname_data = name.getText().toString().replace( " ", "" );
                        MainActivity.movingphone_data = message.getText().toString();

                        MainActivity.MAIN_INPUT_CHECK = 5;
                        //체크변수 변경

                        TextView type = ((MainActivity) context).findViewById( R.id.moving_phone );
                        RelativeLayout search_bt = ((MainActivity) context).findViewById( R.id.search_bt );
                        //메인에있는 TextView
                        type.setText( name.getText().toString() );
                        //넣어줌
                        search_bt.setBackgroundColor( Color.parseColor( "#005FC9" ) );


                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString( "name", String.valueOf( name.getText() ) );
                        editor.putString( "phone", String.valueOf( message.getText() ) );
                        editor.commit();
                        //임시저장

                        // 커스텀 다이얼로그를 종료한다.
                        dlg.dismiss();
                    }
                }
            }
        } );

        cancelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( context, "취소 했습니다", Toast.LENGTH_SHORT ).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        } );
    }
}