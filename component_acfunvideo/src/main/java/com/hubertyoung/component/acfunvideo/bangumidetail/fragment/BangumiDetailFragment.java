package com.hubertyoung.component.acfunvideo.bangumidetail.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hubertyoung.common.base.AbsLifecycleFragment;
import com.hubertyoung.common.entity.NetVideo;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component.acfunvideo.bangumidetail.adapter.BangumiDetailVideoRecyclerAdapter;
import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBean;
import com.hubertyoung.component_acfunvideo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/19 16:27
 * @since:
 * @see BangumiDetailFragment
 */
public class BangumiDetailFragment extends AbsLifecycleFragment {
	public static final String CONTENT_LIST = "bangumi_detail_fragment_content_list_data";
	public static final String TITLE_LIST = "bangumi_detail_fragment_title_list_data";
	public static final String BANGUMI_ID = "bangumi_detail_fragment_bangumi_id";
	public static final String BANGUMI_DETAIL_DATA = "bangumi_detail_data";
//	public static final int j = 6;
//	private static final int k = 2;
	private List< NetVideo > mNetVideoList = new ArrayList();
	private List< BangumiDetailBean.VideoGroupTitleBean > mGroupTitleBeanList = new ArrayList();
	LinearLayout mHistoryLayout;
	TextView mHistoryText;
	TextView mLookAllTextView;
	RecyclerView mRecyclerView;
	private NetVideo mNetVideo;
	private String mBangumiId;
	private BangumiDetailBean mBangumiDetailBean;

	public static BangumiDetailFragment newInstance( ArrayList< BangumiDetailBean.VideoGroupTitleBean > videoGroupTitleBeanList, ArrayList< NetVideo > netVideoList, String mContentId,
													 BangumiDetailBean bangumiDetailBean ) {
		Bundle bundle = new Bundle();
		bundle.putSerializable( TITLE_LIST, videoGroupTitleBeanList );
		bundle.putSerializable( CONTENT_LIST, netVideoList );
		bundle.putString( BANGUMI_ID, mContentId );
		bundle.putSerializable( BANGUMI_DETAIL_DATA, bangumiDetailBean );
		BangumiDetailFragment bangumiDetailFragment = new BangumiDetailFragment();
		bangumiDetailFragment.setArguments( bundle );
		return bangumiDetailFragment;
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_bangumi_detail;
	}

	@Override
	protected void initView( Bundle state ) {
		super.initView( state );
		mHistoryLayout = findViewById( R.id.ll_play_history );
		mHistoryText = findViewById( R.id.tv_history );
		mRecyclerView = findViewById( R.id.gv );
		mLookAllTextView = findViewById( R.id.tv_look_all );

		initData();
		showUi();
		initRecyclerView();
	}

	public void onResume() {
		super.onResume();
		loadHistory();
	}

	private void loadHistory() {
		// TODO: 2018/12/19 通过ID查询数据库找出您观看记录
//		this.initRecyclerView = BangumiDetailHelper.bindView( Integer.valueOf( this.mBangumiId ).intValue() );
		if ( this.mNetVideo == null ) {
			this.mHistoryLayout.setVisibility( View.GONE );
			return;
		}
		this.mHistoryLayout.setVisibility( View.VISIBLE );
		String str = this.mNetVideo.mTitle == null ? "" : this.mNetVideo.mTitle;
		this.mHistoryText.setText( getString( R.string.bangumi_detail_play_history, str ) );
		this.mHistoryLayout.setOnClickListener( new OnClickListener() {
			public void onClick( View view ) {
				// TODO: 2018/12/19  查看历史记录
				ToastUtil.showWarning( "查看历史记录" );
//				BangumiDetailHelper.bindView( BangumiDetailFragment.this.getActivity(), BangumiDetailFragment.this.mBangumiId, BangumiDetailFragment.this.mNetVideo, BangumiDetailFragment.this
//						.mBangumiDetailBean );
			}
		} );
	}

	public void onPause() {
		super.onPause();
	}

	private void showUi() {
		loadHistory();
		if ( mNetVideoList == null || mNetVideoList.isEmpty() ) {
			this.mLookAllTextView.setVisibility( View.GONE );
		} else if ( this.mNetVideoList.size() >= 6 ) {
			this.mLookAllTextView.setVisibility( View.VISIBLE );
			this.mLookAllTextView.setOnClickListener( new OnClickListener() {
				public void onClick( View view ) {
					// TODO: 2018/12/19 跳转BangumiListActivity
					ToastUtil.showWarning( "跳转BangumiListActivity" );
//					KanasCommonUtil.c( KanasConstants.bV, null );
//					Intent intent = new Intent( BangumiDetailFragment.this.getActivity(), BangumiListActivity.class );
//					Bundle bundle = new Bundle();
//					bundle.putSerializable( BangumiListActivity.g, ( Serializable ) BangumiDetailFragment.this.mGroupTitleBeanList );
//					bundle.putSerializable( BANGUMI_DETAIL_DATA, BangumiDetailFragment.this.mBangumiDetailBean );
//					bundle.putString( BangumiListActivity.e, BangumiDetailFragment.this.mBangumiId );
//					intent.putExtras( bundle );
//					IntentHelper.bindView( BangumiDetailFragment.this.getActivity(), intent );
				}
			} );
		} else {
			this.mLookAllTextView.setVisibility( View.GONE );
		}
	}

	private void initData() {
		Bundle arguments = getArguments();
		Serializable serializable = arguments.getSerializable( CONTENT_LIST );
		if ( serializable != null ) {
			this.mNetVideoList = ( ArrayList< NetVideo > ) serializable;
		}
		serializable = arguments.getSerializable( BANGUMI_DETAIL_DATA );
		if ( serializable != null ) {
			this.mBangumiDetailBean = ( BangumiDetailBean ) serializable;
		}
		String bangumiId = arguments.getString( BANGUMI_ID );
		if ( bangumiId != null ) {
			this.mBangumiId = bangumiId;
		}
		serializable = arguments.getSerializable( TITLE_LIST );
		if ( serializable != null ) {
			this.mGroupTitleBeanList = ( ArrayList< BangumiDetailBean.VideoGroupTitleBean > ) serializable;
		}
	}

	private void initRecyclerView() {
		BangumiDetailVideoRecyclerAdapter bangumiDetailVideoRecyclerAdapter = new BangumiDetailVideoRecyclerAdapter( activity );
		bangumiDetailVideoRecyclerAdapter.setData( this.mNetVideoList, this.mBangumiDetailBean );
		GridLayoutManager gridLayoutManager = new GridLayoutManager( getActivity(), 2 );
		gridLayoutManager.setOrientation( GridLayoutManager.VERTICAL );
		this.mRecyclerView.setLayoutManager( gridLayoutManager );
		this.mRecyclerView.setAdapter( bangumiDetailVideoRecyclerAdapter );
	}

	@Override
	protected void initToolBar() {

	}

}
