package insung.moving.customer.activity;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import insung.moving.customer.R;
import insung.moving.customer.databinding.ActivityPrivacyBinding;
import insung.moving.customer.databinding.NotittleToolbarBinding;

/**
 * Created by user on 2018-08-22.
 */

public class PrivacyActivity extends BaseActivity {

    private ActivityPrivacyBinding binding;
    private NotittleToolbarBinding notittleToolbarBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_privacy );
        notittleToolbarBinding = DataBindingUtil.bind( binding.notittleToolbar.getRoot() );
        notittleToolbarBinding.toolbarTitle.setText( "개인정보처리방침" );

        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
        setSupportActionBar( notittleToolbarBinding.toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayShowTitleEnabled( false );


        WebView webView = (WebView) findViewById( R.id.web );
        webView.setWebViewClient( new WebViewClient() ); // 이걸 안해주면 새창이 뜸
        webView.loadUrl( "http://24.414.co.kr/Privacy/35.html" );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected( item );
    }

}
