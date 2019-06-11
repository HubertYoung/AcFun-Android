package com.hubertyoung.alipayplatform.platforms.alipay;

import com.alipay.sdk.app.EnvUtils;
import com.hubertyoung.baseplatform.PaymentSDK;
import com.hubertyoung.baseplatform.payment.PaymentVia;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * Time::2018/9/10 18:20
 * @since:V1.0.0
 * Pkg:com.hubertyoung.qqplatforms.platforms.qq
 */
public class AlipayPlatFormConfig {
	public static void registerPay( boolean isSanbox ) {
		if ( isSanbox ) {
			EnvUtils.setEnv( EnvUtils.EnvEnum.SANDBOX );
		}
		PaymentSDK.register( PaymentVia.Alipay, Alipay.class );
	}
}
