package com.hubertyoung.component_acfunarticle.article.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.component_acfunarticle.article.source.ArticleRepository;

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
		mRepository.requestAllChannel(  );
	}
}
