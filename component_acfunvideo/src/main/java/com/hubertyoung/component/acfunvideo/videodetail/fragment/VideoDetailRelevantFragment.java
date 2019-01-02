package com.hubertyoung.component.acfunvideo.videodetail.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.common.base.BaseListFragment;
import com.hubertyoung.common.entity.FullContent;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component.acfunvideo.config.VideoConstants;
import com.hubertyoung.component.acfunvideo.videodetail.section.RelevantHeadSection;
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
//		content = findViewById( R.id.content );
//		recommendRefreshList = findViewById( R.id.recommend_refresh_list );
//		recyclerView = findViewById( R.id.recommend_video_list );
//		partsVideoList = findViewById( R.id.video_detail_part_video_grid );
		initInfo();
		initViewInfo();
	}

	@Override
	protected void loadNewData() {

	}

	@Override
	protected void loadData() {
		stopLoading();
	}

	@Override
	protected RecyclerView.LayoutManager createLayoutManager() {
		GridLayoutManager layoutManager = new GridLayoutManager( activity, 2 );
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
			mRelevantHeadSection = new RelevantHeadSection( activity );
			addSection( mRelevantHeadSection );
			mRelevantHeadSection.setData( mFullContent );
			getAdapter().notifyDataSetChanged();
		}
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
	}

	private void initInfo() {
		Bundle arguments = getArguments();
		if ( arguments != null ) {
			mFullContent = ( FullContent ) arguments.getSerializable( "fullContent" );
			mCid = mFullContent.getCid();
			mViewModel.requestUserInfo( mFullContent.getUser().getUid() );
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

