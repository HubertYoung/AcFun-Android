package activity;

import android.os.Bundle;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.utils.BarUtils;
import com.kento.component_home.R;


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
        BarUtils.statusBarLightMode( this, true, 0 );
        BarUtils.immersiveStatusBar( this.getWindow(), 0 );
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
