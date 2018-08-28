package insung.moving.customer.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import insung.moving.customer.R;

public class AgreementDetail extends BaseActivity {
    
    SharedPreferences.Editor sp_edit;
    SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView( R.layout.agreedetail);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		
		Intent in = getIntent();
		int Index = in.getIntExtra("INDEX",0);
		
		TextView AgreeTitle = (TextView) findViewById(R.id.AgreeTitle);		        
        WebView mWeb = (WebView) findViewById(R.id.agreeWebView);
		LinearLayout llTextView = (LinearLayout) findViewById(R.id.llTextView);
		LinearLayout llWebView = (LinearLayout) findViewById(R.id.llWebView);
		if(Index == 1){
			AgreeTitle.setText("개인정보 수집 및 이용 동의");
        	mWeb.loadUrl("http://24.414.co.kr/Privacy/35.html");
        }
        else if(Index == 2){
			AgreeTitle.setText("위치정보 서비스 이용약관");
        	mWeb.loadUrl("http://0.283.co.kr/updateInfo/Agreement3.html");
        }
        else if(Index == 3){
			AgreeTitle.setText("제3자 제공 등에 관한 동의");
			mWeb.loadUrl("http://0.283.co.kr/updateInfo/Agreement1.html");
		}
		else if(Index == 4){
			llWebView.setVisibility(View.GONE);
			llTextView.setVisibility(View.VISIBLE);
			AgreeTitle.setText("애플리케이션 접근 권한 안내");
//			mWeb.loadUrl("http://0.283.co.kr/updateInfo/Agreement1.html");
		}

        Button AgreeClose = (Button)findViewById(R.id.AgreeClose);
        AgreeClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
	}
}
