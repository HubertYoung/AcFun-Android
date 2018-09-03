package activity;

import android.os.Bundle;

import com.acty.component_acfunvideo.R;
import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.utils.BarUtils;


public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.home_index_activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void doBeforeSetContentView() {
        BarUtils.setStatusBarTranslucent(getWindow(), true);
    }

    @Override
    public void initView( Bundle savedInstanceState ) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void initToolBar() {

    }
}
