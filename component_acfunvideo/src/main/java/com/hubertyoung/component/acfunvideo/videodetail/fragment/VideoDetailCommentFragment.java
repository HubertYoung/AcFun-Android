package com.hubertyoung.component.acfunvideo.videodetail.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.common.base.BaseListFragment;
import com.hubertyoung.common.entity.FullContent;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.component.acfunvideo.videodetail.activity.VideoDetailActivity;
import com.hubertyoung.component.acfunvideo.videodetail.vm.VideoDetailCommentViewModel;

/**
 * desc: VideoDetailCommentFragment
 *
 * @author:HubertYoung
 * @date: 2019/01/02 14:49
 * @since:
 * @see VideoDetailCommentViewModel
 */
public class VideoDetailCommentFragment extends BaseListFragment< VideoDetailCommentViewModel > {
	private int mCid;
	private boolean mIsHapame;

	private String mRequestId;
	private String mGroupId;
	private int mVideoId;
	private FullContent mFullContent;
	private String from;
	private ClipboardManager mClipboardManager;

	public static VideoDetailCommentFragment newInstance( FullContent fullContent, int floor, String from, boolean isHapame, String requestId, String groupId, int videoId ) {
		Bundle bundle = new Bundle();
		bundle.putSerializable( "fullContent", fullContent );
		bundle.putInt( "floor", floor );
		bundle.putString( "from", from );
		bundle.putBoolean( "isHapame", isHapame );
		bundle.putString( "requestId", requestId );
		bundle.putString( VideoDetailActivity.groupId, groupId );
		bundle.putInt( "videoId", videoId );
		VideoDetailCommentFragment videoDetailCommentFragment = new VideoDetailCommentFragment();
		videoDetailCommentFragment.setArguments( bundle );
		return videoDetailCommentFragment;
	}

	@Override
	protected void initView( Bundle state ) {
		super.initView( state );
		mToolbar.setVisibility( View.GONE );
		getIntentInfo();
		initData();
	}

	private void initData() {
		if ( this.mFullContent.isComment() ) {
			mViewModel.requestCommentInfo( mCid, 3, "0", 20, 1, SigninHelper.getInstance().getUserToken() );
		}
//		if (this.mIsHapame ) {
//			this.y = new VideoDetailCommentHeader(this.r, this.mFullContent, this.mIsHapame );
//			this.y.a(this.E);
//		}
//		this.h = LayoutInflater.from(this.r).inflate(R.layout.item_video_detail_comment_footer, null);
//		this.i = (TextView ) this.h.findViewById(R.id.video_detail_comment_footer_text);
//		if (this.mFullContent.isComment()) {
//			this.i.setText(R.string.no_comment_count_text);
//			this.h.setVisibility(8);
//		} else {
//			this.i.setText(R.string.forbid_comment_text);
//			this.h.setVisibility(0);
//		}
//		View linearLayout = new LinearLayout(getContext());
//		ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
//		layoutParams.gravity = 17;
//		linearLayout.addView(this.h, layoutParams);
//		this.v = new CommentAdapter(getActivity(), String.valueOf(this.mFullContent.getCid()));
//		this.v.a(this.mIsHapame );
//		this.v.a(new ExtOnCommentClickListener());
//		this.v.a((Link.OnClickListener ) this);
//		this.g = new RecyclerAdapterWithHF(this.v);
//		if (this.mIsHapame ) {
//			this.g.a(this.y.a());
//		}
//		this.g.c(linearLayout);
//		this.w = new LinearLayoutManager(this.r);
//		this.recyclerView.setLayoutManager(this.w);
//		this.recyclerView.setAdapter(this.g);
//		if (this.mIsHapame ) {
//			r();
//		}
	}

	private void getIntentInfo() {
		Bundle arguments = getArguments();
		if ( arguments != null ) {
			this.mFullContent = ( FullContent ) arguments.getSerializable( "fullContent" );
			this.from = arguments.getString( "from" );
			this.mCid = this.mFullContent.getCid();
			this.mIsHapame = arguments.getBoolean( "isHapame" );
			this.mRequestId = arguments.getString( "requestId" );
			this.mGroupId = arguments.getString( VideoDetailActivity.groupId );
			this.mVideoId = arguments.getInt( "videoId" );
		}
		this.mClipboardManager = ( ClipboardManager ) activity.getSystemService( Context.CLIPBOARD_SERVICE );
	}

	@Override
	protected void loadNewData() {

	}

	@Override
	protected void loadData() {

	}

	@Override
	protected RecyclerView.LayoutManager createLayoutManager() {
		return new LinearLayoutManager( activity );
	}

	@Override
	protected void initToolBar() {

	}

}

