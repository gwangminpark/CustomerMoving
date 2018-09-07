package insung.moving.customer.activity;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import insung.moving.customer.R;
import insung.moving.customer.databinding.ActivityClauseBinding;
import insung.moving.customer.databinding.NotittleToolbarBinding;
import insung.moving.customer.util.ReferrerReceiver;

/**
 * Created by user on 2018-08-22.
 */

public class ClauseActivity extends BaseActivity {

    private ActivityClauseBinding binding;
    private NotittleToolbarBinding notittleToolbarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_clause);
        notittleToolbarBinding= DataBindingUtil.bind(binding.notittleToolbar.getRoot());
        notittleToolbarBinding.toolbarTitle.setText("이용약관"+ ReferrerReceiver.referrer);

        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar(notittleToolbarBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        WebView webView = (WebView)findViewById(R.id.web);
        webView.setWebViewClient(new WebViewClient()); // 이걸 안해주면 새창이 뜸
        webView.loadUrl("http://24.414.co.kr/Agreement/35.html");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
