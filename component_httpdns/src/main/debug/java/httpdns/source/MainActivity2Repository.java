package httpdns.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.baserx.RxSchedulers;

import java.util.Map;

import httpdns.api.ApiHomeService;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 11:23
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.sign.source
 */
public class MainActivity2Repository extends AbsRepository {


	public Flowable< String > requestVerificationCodeInfo( Map< String, String > headMap, Map< String, String > map ) {
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )
				.getRetrofitClient()
				.setBaseUrl( map.get( "url" ) )
				.builder( ApiHomeService.class )
				.request(headMap,map,map.get( "path" ))
				.compose( RxSchedulers.io_main() )//
				.subscribeOn( AndroidSchedulers.mainThread() );
	}
}
