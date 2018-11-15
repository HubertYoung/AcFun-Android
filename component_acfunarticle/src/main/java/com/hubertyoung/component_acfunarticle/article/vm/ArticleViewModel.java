package com.hubertyoung.component_acfunarticle.article.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.common.baserx.RxSubscriber;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_acfunarticle.article.source.ArticleRepository;
import com.hubertyoung.component_acfunarticle.config.ArticleConstants;
import com.hubertyoung.component_acfunarticle.entity.Channel;

import androidx.annotation.NonNull;

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
public class ArticleViewModel extends AbsViewModel< ArticleRepository > {

	public ArticleViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestAllChannel() {
		addDisposable( mRepository.requestAllChannel()//
				.subscribeWith( new RxSubscriber< Channel >() {
					@Override
					protected void showLoading() {
						showDialogLoading( "" );
					}

					@Override
					protected void finishLoading() {
						stopLoading();
					}

					@Override
					public void onSuccess( Channel channel ) {
						sendData( ArticleConstants.EVENT_KEY_ARTICLE_PLATFORM_LOGIN, channel );
					}

					@Override
					public void onFailure( String msg ) {
						ToastUtil.showError( msg );
					}
				} ) );
	}
}
