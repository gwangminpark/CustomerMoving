package insung.moving.customer.activity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import insung.moving.customer.R;
import insung.moving.customer.databinding.ActivityMovingChecklistBinding;
import insung.moving.customer.databinding.NotittleToolbarBinding;


/**
 * Created by user on 2018-07-13.
 */

public class MovingCheckListActivity extends BaseActivity {

    private ActivityMovingChecklistBinding binding;
    private NotittleToolbarBinding notittleToolbarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_moving_checklist);
        notittleToolbarBinding= DataBindingUtil.bind(binding.notittleToolbar.getRoot());
        notittleToolbarBinding.toolbarTitle.setText("이사 체크리스트");

        setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar(notittleToolbarBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
