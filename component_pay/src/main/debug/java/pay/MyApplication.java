package pay;

import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.qqplatforms.platforms.qq.QQPlatFormConfig;
import com.hubertyoung.wechatplatforms.platforms.weixin.WechatPlatFormConfig;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/10 20:09
 * @since:V$VERSION
 * @desc:pay
 */
public class MyApplication extends CommonApplication {
	@Override
	public void onCreate() {
		super.onCreate();

		QQPlatFormConfig.registerShare( "1106891112","QiIFegOZGaRmeC4S" );
		WechatPlatFormConfig.registerShare( "wxf143a66eb7528d12","e408746be2a052ae1f294aa91595227c" );
	}
}
