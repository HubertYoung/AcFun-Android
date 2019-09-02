package httpdns.vm;

import android.app.Application;
import android.support.annotation.NonNull;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.utils.display.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import httpdns.source.MainActivity2Repository;
import io.reactivex.functions.Consumer;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 11:25
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.sign.vm
 */
public class MainActivity2ViewModel extends AbsViewModel< MainActivity2Repository > {


	public MainActivity2ViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestVerificationCodeInfo( Map< String, String > headMap, Map< String, String > map ) {
		addDisposable( mRepository.requestVerificationCodeInfo(headMap,map)//
				.subscribe( new Consumer< String >() {
					@Override
					public void accept( String s ) throws Exception {
						ToastUtil.showSuccess( s );
					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( Throwable throwable ) throws Exception {
						ToastUtil.showError( throwable.getMessage() );
					}
				} ) );
	}
}
