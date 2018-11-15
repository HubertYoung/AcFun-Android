package com.hubertyoung.component_acfunarticle.article.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.component_acfunarticle.article.source.ArticleGeneralSecondRepository;

import androidx.annotation.NonNull;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/15 15:22
 * @since:V5.2.7
 * @desc:com.hubertyoung.component_acfunarticle.article.vm
 */
public class ArticleGeneralSecondViewModel extends AbsViewModel< ArticleGeneralSecondRepository > {

	public ArticleGeneralSecondViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestArticleGeneralSecond( String mChannelId, String selectorTimeType, String selectorType, String realmIds, String pageNo, String pageSize ) {
		mRepository.requestArticleGeneralSecond( mChannelId, selectorTimeType, selectorType, realmIds, pageNo, pageSize );
	}
}
