package activity;

import android.os.Bundle;

import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.utils.bar.BarUtils;
import com.hubertyoung.component_acfunmine.R;


public class MainActivity extends AbsLifecycleActivity {

    @Override
    public int getLayoutId() {
        return R.layout.home_index_activity_main;
    }

    @Override
    public void doBeforeSetContentView() {
        BarUtils.setStatusBarTranslucent(getWindow(), true);
    }

    @Override
    public void initView( Bundle savedInstanceState ) {

    }

    @Override
    protected void stopLoading() {

    }

    @Override
    protected void showLoading( String title ) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void initToolBar() {

    }

}
