package activity;

import android.os.Bundle;

import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.utils.bar.BarUtils;
import com.hubertyoung.component_acfunvideo.R;
import com.youku.player.decapi.DecApi;


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
        String data = "/fS/MWxqdRF5NgcpR7AIX5cpEuPsyBjpqiR046BXnSyViZPeVbg183s/PLxiEmj0kGJyg0LuyM3tI4IxQVsLjAAZBtc4U0DIUCls8X6M" +
                "+DqY03ZNBw5QXxOr62irLMNyzbyIFYWlmOJ1RZekOBtniA3ReROKy3PbAut2BML2WEHxFNCt9ig0CK9cn7s4NlPp8/PfGwtDfpoE2NpDWSDlzdUdcMpMrGJsAjqFgAkOMAQMfB195ndHB8YlHasqXohPtD/rv3hvQbbe+DrBaz" +
                "+3fCBIsOv4FW8X260ETCKR2w/bLBSlpcn5UlWPHfh9ezr3Cfu++fgAzK9zPGGi+koCPrP7t0vaqcOWAk/5nK6fLPc20G9E2i8h72jE2dkN8ncrVUr4ZSURzLeu/2l8nJ6AsOT9AsSQSlsdS3ULq/ztk2yU629QktUQZfZEfkP2wheF" +
                "+L1jv4IurjJctNv9cZYpddS+YWWY8gCH4mWe7+xf8cw1QVWq+IsfzKNaTeryxlZXeaOyPMef1lg3tfFHBXKHQzOGNxZPp3i5rbxT5tJcJ0+2XaeUwLzrellaTzxoHYh3Okg8lxH" +
                "+4pBOvVzdHjCf2FeoqQKUgKoDHriu1U3n4t8dMyWMMEVBwid23dqCVel1LnGi4ohAXKYDLOBaUPi17JEa7WqGIAA+B9Bx2e+DmrCppZ5cdY3MI4tVpPKvi10Q3u5Oa+k28xrc/0gQm7QxmtWi5/hPFgPmY8xb9dXEa5VD" +
                "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";
        String videoInfo = DecApi.getVideoInfo( this, data );
        System.out.println( videoInfo );
    }

    @Override
    public void stopLoading() {

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
