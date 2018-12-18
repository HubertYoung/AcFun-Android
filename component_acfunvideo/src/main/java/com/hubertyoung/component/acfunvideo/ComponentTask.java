package com.hubertyoung.component.acfunvideo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.bangumidetail.activity.BangumiDetailActivityNew;
import com.hubertyoung.component.acfunvideo.index.fragment.HomePageFragment;
import com.hubertyoung.component.acfunvideo.index.fragment.NewRecommendFragment;

/**
 * <br>
 * function:任务组件功能实现
 * <p>
 *
 * @author:Yang
 * @date:2018/5/9 10:33 AM
 * @since:V1.0
 * @desc:com.hubertyoung.component.home
 */
public class ComponentTask implements IComponent {

	private HomePageFragment mHomePageFragment;
	private ArrayMap< String, BaseFragment > newRecommendFragments = new ArrayMap<>( 1 );

	@Override
	public String getName() {
		return "ComponentAcFunIndex";
	}

	@Override
	public boolean onCall( CC cc ) {
		Context context = cc.getContext();
		Intent intent;
		switch ( cc.getActionName() ) {
			case "HomePageFragment":
				if ( mHomePageFragment == null ) {
					mHomePageFragment = HomePageFragment.newInstance( "", "" );
				}
				CC.sendCCResult( cc.getCallId(), CCResult.success( "fragment", mHomePageFragment ) );
				break;
			case "NewRecommendFragment":
				String channelId = cc.getParamItem( "channelId" );
				if ( newRecommendFragments.get( channelId ) == null ) {
					newRecommendFragments.put( channelId, NewRecommendFragment.newInstance( channelId, "" ) );
				}
				CC.sendCCResult( cc.getCallId(), CCResult.success( "fragment", newRecommendFragments.get( channelId ) ) );
				break;
//			case "VideoDetailActivity":
//				int contentId = cc.getParamItem( VideoDetailActivity.contentId );
//				String reqId = cc.getParamItem( VideoDetailActivity.reqId );
//				String groupId = cc.getParamItem( VideoDetailActivity.groupId );
//				String from = cc.getParamItem( VideoDetailActivity.from );
//				VideoDetailActivity.launch( context, contentId, reqId, groupId, from );
//				CC.sendCCResult( cc.getCallId(), CCResult.success() );
//				break;
			case "Activity":
				int actionId = cc.getParamItem( "actionId" );
				String contentId = cc.getParamItem( "contentId" );
				if ( actionId == 11 ) {
					contentId = "-1";
				}

				if ( actionId == 2 ) {
					BangumiDetailActivityNew.launch( context, contentId );
				} else {
					ToastUtil.showWarning( "未处理" );
//					VideoDetailActivity.launch( context, contentId, reqId, groupId, from );
				}
				CC.sendCCResult( cc.getCallId(), CCResult.success() );
				break;
			default:
				CC.sendCCResult( cc.getCallId(), CCResult.error( "unsupported action:" + cc.getActionName() ) );
				break;
		}
		return false;
	}
}
