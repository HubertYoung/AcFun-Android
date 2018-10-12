package statsdk;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.statsdk.core.TcStatInterface;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/12 17:45
 * @since:V$VERSION
 * @desc:statsdk
 */
public class StatsdkApplication extends CommonApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		int appId = 21212;
		// assets
		String fileName = "stat_id.json";
		String url = "http://apitest.d1-bus.net/member/myCenter";
		// init statSdk
		TcStatInterface.initialize(this, appId, "you app chanel", fileName);
		TcStatInterface.setUrl(url);
		TcStatInterface.setUploadPolicy(TcStatInterface.UploadPolicy.UPLOAD_POLICY_DEVELOPMENT, TcStatInterface.UPLOAD_INTERVAL_REALTIME);
	}
}
