package backgroundjob;

import com.hubertyoung.backgroundjob.DaemonEnv;
import com.hubertyoung.backgroundjob.service.DaemonService;
import com.hubertyoung.common.CommonApplication;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2019-08-15 16:27
 * @since:V$VERSION
 * @desc:backgroundjob
 */
public class JobApplication extends CommonApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        DaemonEnv.initialize(this.getApplicationContext(), ServiceImpl.class, Integer.valueOf(DaemonEnv.DEFAULT_WAKE_UP_INTERVAL));
        ServiceImpl.sShouldStopService = false;
        DaemonEnv.startServiceMayBind(ServiceImpl.class);
        DaemonEnv.startServiceMayBind( DaemonService.class);

    }
}
