package com.hubertyoung.component.acfunvideo.videodetail.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.common.base.BaseListFragment;
import com.hubertyoung.common.entity.FullContent;
import com.hubertyoung.common.entity.RecommendBangumiEntity;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.common.utils.os.AppUtils;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.videodetail.section.RecommondVideoList;
import com.hubertyoung.component.acfunvideo.videodetail.section.RelevantHeadSection;
import com.hubertyoung.component.acfunvideo.videodetail.section.VideoPartListSection;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailRelevantViewModel;

/**
 * desc: VideoDetailRelevantFragment
 *
 * @author:HubertYoung
 * @date: 2019/01/02 14:45
 * @since:
 * @see VideoDetailRelevantViewModel
 */
public class VideoDetailRelevantFragment extends BaseListFragment< VideoDetailRelevantViewModel > {

	//	private SmartRefreshLayout recommendRefreshList;
//	private RecyclerView recyclerView;
//	private RecyclerView partsVideoList;
	private FullContent mFullContent;
	private int mCid;
	private User mUser;
	private RelevantHeadSection mRelevantHeadSection;
	private VideoPartListSection mVideoPartListSection;
	private RecommondVideoList mRecommondVideoList;

	public static VideoDetailRelevantFragment newInstance( FullContent fullContent ) {
		Bundle bundle = new Bundle();
		bundle.putSerializable( "fullContent", fullContent );
//		VideoDetailRelevantFragment videoDetailRelevantFragment = new VideoDetailRelevantFragment(iVideoDetailView);
		VideoDetailRelevantFragment videoDetailRelevantFragment = new VideoDetailRelevantFragment();
		videoDetailRelevantFragment.setArguments( bundle );
		return videoDetailRelevantFragment;
	}

	@Override
	protected void initView( Bundle state ) {
		super.initView( state );
		mToolbar.setVisibility( View.GONE );
		getSrlContainer().setEnableRefresh( false );
		getSrlContainer().setEnableLoadMore( false );
		initInfo();
		initViewInfo();
	}

	@Override
	protected void loadNewData() {

	}

	@Override
	protected void loadData() {
		String s;
		if ( SigninHelper.getInstance().isLogin() ) {
			StringBuilder stringBuilder2 = new StringBuilder();
			stringBuilder2.append( SigninHelper.getInstance().getUserUid() );
			s = stringBuilder2.toString();
		} else {
			s = AppUtils.getUUID();
		}
		getMViewModel().requestRelativeRecommendInfo( mCid, s, getAdapter().mPageBean.getLoadPage() );
		stopLoading();
	}

	@Override
	protected RecyclerView.LayoutManager createLayoutManager() {
		GridLayoutManager layoutManager = new GridLayoutManager( mActivity, 2 );
		layoutManager.setSpanSizeLookup( new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize( int position ) {
				switch ( getAdapter().getSectionItemViewType( position ) ) {
					case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
						return 2;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_FOOTER:
						return 2;
					case SectionedRecyclerViewAdapter.VIEW_TYPE_ITEM_LOADED: {
						int spanSizeLookup = getAdapter().getSectionForPosition( position ).spanSizeLookup;
						return spanSizeLookup;
					}
					default:
						return 2;
				}
			}
		} );
		return layoutManager;
	}

//	@Override
//	public int getLayoutResource() {
//		return R.layout.fragment_video_detail_relevant;
//	}

	@Override
	protected void initToolBar() {

	}

	private void initViewInfo() {
		if ( mRelevantHeadSection == null ) {
			mRelevantHeadSection = new RelevantHeadSection( mActivity );
			addSection( mRelevantHeadSection );
			mRelevantHeadSection.setData( mFullContent );
		}
		if ( mVideoPartListSection == null && mFullContent != null && !mFullContent.getVideos().isEmpty() ) {
			mVideoPartListSection = new VideoPartListSection( mActivity );
			addSection( mVideoPartListSection );
			mVideoPartListSection.setData( mFullContent );
		}
		getAdapter().notifyDataSetChanged();
	}

	@Override
	protected void dataObserver() {
		super.dataObserver();
		registerObserver( VideoConstants.EVENT_KEY_VIDEO_RELEVANT_USERINFO, User.class ).observe( this, new Observer< User >() {
			@Override
			public void onChanged( @Nullable User user ) {
				mUser = user;
				// TODO: 2019/1/2 控制器数据
//				if ( VideoDetailRelevantFragment.this.o != null ) {
//					VideoDetailRelevantFragment.this.o.a( VideoDetailRelevantFragment.this.q );
//				}
			}
		} );
		registerObserver( VideoConstants.EVENT_KEY_VIDEO_RELATIVERECOMMEND_INFO, RecommendBangumiEntity.class ).observe( this, new Observer< RecommendBangumiEntity >() {
			@Override
			public void onChanged( @Nullable RecommendBangumiEntity recommendBangumiEntity ) {
				if ( mRecommondVideoList == null && !recommendBangumiEntity.list.isEmpty() ) {
					mRecommondVideoList = new RecommondVideoList( mActivity );
					addSection( mRecommondVideoList );
					mRecommondVideoList.setData( recommendBangumiEntity.list );
				}
				getAdapter().notifyDataSetChanged();
			}
		} );
	}

	private void initInfo() {
		Bundle arguments = getArguments();
		if ( arguments != null ) {
			mFullContent = ( FullContent ) arguments.getSerializable( "fullContent" );
			mCid = mFullContent.getCid();
			getMViewModel().requestUserInfo( mFullContent.getUser().getUid() );
//			ApiHelper.a().a(this.a, this.n.getUser().getUid(), new UserCallback() {
//				public void a(User user) {
//					super.a(user);
//					VideoDetailRelevantFragment.this.q = user;
//					if (VideoDetailRelevantFragment.this.o != null) {
//						VideoDetailRelevantFragment.this.o.a(VideoDetailRelevantFragment.this.q);
//					}
//				}
//			});
		}
	}
}

