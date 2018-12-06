package com.hubertyoung.component.acfunvideo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponent;
import com.hubertyoung.common.base.BaseFragment;
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
			case "getHomePageFragment":
				if ( mHomePageFragment == null ) {
					mHomePageFragment = HomePageFragment.newInstance( "", "" );
				}
				CC.sendCCResult( cc.getCallId(), CCResult.success( "fragment", mHomePageFragment ) );
				break;
			case "getNewRecommendFragment":
				String channelId = cc.getParamItem( "channelId" );
				if ( newRecommendFragments.get( channelId ) == null ) {
					newRecommendFragments.put( channelId, NewRecommendFragment.newInstance( channelId, "" ) );
				}
				CC.sendCCResult( cc.getCallId(), CCResult.success( "fragment", newRecommendFragments.get( channelId ) ) );
				break;
//            case "toIssueActivity":
//                intent = new Intent(context, IssueActivity.class);
//                intent.putExtra("callId", cc.getCallId());
//                if (!(context instanceof Activity )) {
//                    //调用方没有设置context或app间组件跳转，context为application
//                    intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
//                }
//                intent.putExtra(ISSUE_TYPE, (int) cc.getParamItem("ISSUE_TYPE"));
//                intent.putExtra("toTeamsName", (String ) cc.getParamItem("toTeamsName"));
//                intent.putExtra("toTeamsId", (String ) cc.getParamItem("toTeamsId"));
//
//                context.startActivity(intent);
//                CC.sendCCResult(cc.getCallId(), CCResult.success());
//                break;
		}
		return false;
	}
}
