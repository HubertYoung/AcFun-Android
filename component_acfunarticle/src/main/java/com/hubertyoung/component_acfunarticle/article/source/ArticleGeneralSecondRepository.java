package com.hubertyoung.component_acfunarticle.article.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component_acfunarticle.api.ApiArticleService;
import com.hubertyoung.component_acfunarticle.entity.RankAc;

import java.util.HashMap;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/15 15:22
 * @since:V5.2.7
 * @desc:com.hubertyoung.component_acfunarticle.article.source
 */
public class ArticleGeneralSecondRepository extends AbsRepository {

	public Flowable< RankAc > requestArticleGeneralSecond( String mChannelId, String selectorTimeType, String selectorType, String realmIds, String pageNo, String pageSize ) {
		HashMap map = new HashMap< String, String >();
		map.put( "channelId", mChannelId );
		map.put( "day", selectorTimeType );
		map.put( "sort", selectorType );
		map.put( "realmIds", realmIds );
		map.put( "pageNo", pageNo );
		map.put( "pageSize", pageSize );
		return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )
				.getRetrofitClient()
//				.setBaseUrl( EnvironmentSwitcher.getMineEnvironment( CommonApplication.getAppContext(), BuildConfig.DEBUG ) )
				.builder( ApiArticleService.class )
				.requestArticleGeneralSecond( map )
				.compose( new DefaultTransformer() );
	}
}
