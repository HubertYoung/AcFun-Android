package com.hubertyoung.component_acfunarticle.article.fragment;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.DropDownOptionList;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component_acfunarticle.R;
import com.hubertyoung.component_acfunarticle.article.control.ArticleGeneralSecondControl;
import com.hubertyoung.component_acfunarticle.article.model.ArticleGeneralSecondModelImp;
import com.hubertyoung.component_acfunarticle.article.presenter.ArticleGeneralSecondPresenterImp;
import com.hubertyoung.component_acfunarticle.article.section.ArticleRealmSection;
import com.hubertyoung.component_acfunarticle.article.section.ListArticleSection;
import com.hubertyoung.component_acfunarticle.entity.RankAc;
import com.hubertyoung.component_acfunarticle.entity.ServerChannel;
import com.hubertyoung.component_skeleton.skeleton.RecyclerViewSkeletonScreen;
import com.hubertyoung.component_skeleton.skeleton.Skeleton;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/18 13:29
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunarticle.mine.fragment
 */
public class ArticleGeneralSecondFragment extends BaseFragment< ArticleGeneralSecondPresenterImp, ArticleGeneralSecondModelImp > implements ArticleGeneralSecondControl.View {
	private static final String ARG_PARAM1 = "channelId";
	private static final String ARG_PARAM2 = "serverChannel";
	private String mChannelId;
	private ServerChannel serverChannel;
	private RecyclerView realmRecyclerView;
	private DropDownOptionList mDropDownOptionList;
	private RelativeLayout mContent;
	private View mArticleSecondaryViewPtrDivider;
	private SmartRefreshLayout mSrlArticleSecondaryView;
	private RecyclerView mRecyclerView;
	private View mShader;

	private int mChannelIdArticle;
	private SectionedRecyclerViewAdapter mRealAdapter;
	private SectionedRecyclerViewAdapter mAdapter;
	private List< String > titleList;
	private List< String > titleTimeList;
	private ViewHolder mViewHolder;
	private int selectorType;
	private int selectorTimeType;
	private RecyclerViewSkeletonScreen mViewSkeletonScreen;
	private ListArticleSection mListArticleSection;

	public ArticleGeneralSecondFragment() {
	}

	public static ArticleGeneralSecondFragment newInstance( String channelId, ServerChannel serverChannel ) {
		ArticleGeneralSecondFragment fragment = new ArticleGeneralSecondFragment();
		Bundle args = new Bundle();
		args.putString( ARG_PARAM1, channelId );
		args.putParcelable( ARG_PARAM2, serverChannel );
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		if ( getArguments() != null ) {
			mChannelId = getArguments().getString( ARG_PARAM1 );
			serverChannel = getArguments().getParcelable( ARG_PARAM2 );
		}
	}

	@Override
	protected void initToolBar() {

	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_article_general_second;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {

		realmRecyclerView = ( RecyclerView ) findViewById( R.id.article_realm_view_list );
		mDropDownOptionList = ( DropDownOptionList ) findViewById( R.id.article_secondary_view_drop_down_list );
		mContent = ( RelativeLayout ) findViewById( R.id.content );
		mArticleSecondaryViewPtrDivider = findViewById( R.id.article_secondary_view_ptr_divider );
		mSrlArticleSecondaryView = ( SmartRefreshLayout ) findViewById( R.id.srl_article_secondary_view );
		mRecyclerView = ( RecyclerView ) findViewById( R.id.article_secondary_view_list );
		mShader = findViewById( R.id.article_secondary_view_shader );
		initData();
	}

	private void initData() {
		if ( selectorType == -1 ) {
			selectorType = 5;
		}
		selectorTimeType = -1;

		mChannelIdArticle = getResources().getInteger( R.integer.channel_id_article );
		initRecyclerView();
		initAction();

		titleList = new ArrayList();
		titleList.add( getResources().getString( R.string.activity_channel_order_by_last_feed_back ) );
		titleList.add( getResources().getString( R.string.activity_channel_time_default ) );
		titleList.add( getResources().getString( R.string.activity_channel_filter_views ) );
		titleList.add( getResources().getString( R.string.activity_channel_filter_comments ) );
		titleTimeList = new ArrayList();
		titleTimeList.add( getResources().getString( R.string.activity_channel_filter_one_week ) );
		titleTimeList.add( getResources().getString( R.string.activity_channel_filter_one_month ) );
		titleTimeList.add( getResources().getString( R.string.activity_channel_filter_three_month ) );
		titleTimeList.add( getResources().getString( R.string.activity_channel_filter_all ) );
		View inflate = LayoutInflater.from( activity ).inflate( R.layout.article_drop_down_header, null );
		mDropDownOptionList.setHeaderView( inflate );
		mDropDownOptionList.setFocusable( true );
		mDropDownOptionList.setFocusableInTouchMode( true );

		mViewHolder = new ViewHolder( inflate );
		mDropDownOptionList.setListContents( mViewHolder.orderBySelect, titleList );
		mDropDownOptionList.setListContents( mViewHolder.rangSelect, titleTimeList );

		mViewHolder.orderByText.setText( titleList.get( formatSelectorPosition( selectorType ) ) );
		mViewHolder.rangeText.setText( titleTimeList.get( 3 ) );
		mDropDownOptionList.setSelected( mViewHolder.rangSelect.getId(), 3 );
		mDropDownOptionList.setSelected( mViewHolder.orderBySelect.getId(), formatSelectorPosition( selectorType ) );

	}

	private int formatSelectorPosition( int position ) {
		if ( position == 5 ) {
			return 0;
		}
		if ( position == 7 ) {
			return 1;
		}
		switch ( position ) {
			case 1:
				return 2;
			case 2:
				return 3;
			default:
				return 0;
		}
	}

	private void initAction() {
		mSrlArticleSecondaryView.setOnRefreshListener( new OnRefreshListener() {
			@Override
			public void onRefresh( RefreshLayout refreshLayout ) {
				mAdapter.getPageBean().refresh = true;
				mAdapter.getPageBean().page = mAdapter.getPageBean().startPage;
				mSrlArticleSecondaryView.finishLoadMore();
				mSrlArticleSecondaryView.setNoMoreData( false );
				loadData();
			}
		} );
		mSrlArticleSecondaryView.setOnLoadMoreListener( new OnLoadMoreListener() {
			@Override
			public void onLoadMore( RefreshLayout refreshLayout ) {
				mAdapter.getPageBean().refresh = false;
				loadData();
			}
		} );
		mDropDownOptionList.setOnKeyListener( new View.OnKeyListener() {
			@Override
			public boolean onKey( View v, int keyCode, KeyEvent event ) {
				int action = event.getAction();
				if ( action == KeyEvent.ACTION_DOWN && action == KeyEvent.KEYCODE_BACK ) {
					if ( mDropDownOptionList.isToggle() ) {
						mDropDownOptionList.toggleList();
						return true;
					}
				}
				return false;
			}
		} );
		mDropDownOptionList.setOnSelectListener( new ExtOnSelectChangeListener() );
		mDropDownOptionList.setToggleListener( new ExtOnToggleListener() );
		mShader.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				if ( mDropDownOptionList.isToggle() ) {
					mDropDownOptionList.toggleList();
				}
			}
		} );
	}

	private void initRecyclerView() {
		mRealAdapter = new SectionedRecyclerViewAdapter( null );
		LinearLayoutManager layoutManagerReal = new LinearLayoutManager( activity, RecyclerView.HORIZONTAL, false );
		realmRecyclerView.setHasFixedSize( true );
		realmRecyclerView.setLayoutManager( layoutManagerReal );
		realmRecyclerView.setAdapter( mRealAdapter );
		if ( serverChannel.realm == null || serverChannel.realm.isEmpty() ) {
			realmRecyclerView.setVisibility( View.GONE );
		} else {
			realmRecyclerView.setVisibility( View.VISIBLE );
		}
		mRealAdapter.addSection( new ArticleRealmSection( activity, serverChannel.realm ) );
		mRealAdapter.notifyDataSetChanged();

		mAdapter = new SectionedRecyclerViewAdapter( null );
		LinearLayoutManager layoutManager = new LinearLayoutManager( activity );
		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( layoutManager );
		//		realmRecyclerView.addItemDecoration(new ArticleGeneralSecondDivider());
		mListArticleSection = new ListArticleSection( activity );
		mAdapter.addSection( mListArticleSection );
		mViewSkeletonScreen = Skeleton.bind( mRecyclerView )//
				.adapter( mAdapter )//
				.shimmer( true )//
				.duration( 1200 )//
				.angle( 20 )//
				.load( R.layout.common_item_skeleton )//
				.show();
	}

	@Override
	protected void lazyLoad() {
		loadData();
	}

	@Override
	public void loadData() {
//		day	-1
//		sort	5
//		realmIds	25,6,7
//		pageNo	1
//		pageSize	10
		MyRequestMap map = new MyRequestMap();
		map.put( "channelId", mChannelId );
		map.put( "day", selectorTimeType + "" );
		map.put( "sort", selectorType + "" );
		map.put( "realmIds", formatRealmIds( serverChannel.realm ) );
		map.put( "pageNo", mAdapter.getPageBean().getLoadPage() + "" );
		map.put( "pageSize", mAdapter.getPageBean().rows + "" );
		mPresenter.requestArticleGeneralSecond( map );
	}

	private String formatRealmIds( List< ServerChannel > list ) {
		if ( list == null || list.isEmpty() ) {
			return "";
		}
		ArrayList< Integer > integers = new ArrayList<>();
		for (ServerChannel channel : list) {
			integers.add( channel.id );
		}
		String s = integers.toString();
		return s.substring( 1, s.length() - 1 ).replaceAll( "\\s*", "" );
	}

	@Override
	public void showLoading( String title, int type ) {

	}

	@Override
	public void stopLoading() {
		mSrlArticleSecondaryView.finishRefresh();
		mSrlArticleSecondaryView.finishLoadMore();
		if ( mViewSkeletonScreen != null && mViewSkeletonScreen.isShowing() ) {
			mViewSkeletonScreen.hide();
		}
	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showError( msg );
	}

	@Override
	public void setArticleGeneralSecondInfo( RankAc rankAc ) {
		mListArticleSection.setArticleGeneralSecondInfo( rankAc.list );
		mAdapter.notifyDataSetChanged();
	}

	private class ExtOnToggleListener implements DropDownOptionList.OnToggleListener {

		public void onToggle( boolean toggle ) {
			mShader.setVisibility( toggle ? View.VISIBLE : View.GONE );
			if ( toggle ) {
				int id = mDropDownOptionList.getCurrentListBindingView().getId();
				if ( id == R.id.order_by_select ) {
					mViewHolder.orderByText.setTextColor( getResources().getColor( R.color.them_color ) );
					mViewHolder.orderByImage.setImageResource( R.mipmap.ic_filtrate_up );
				} else if ( id == R.id.range_select ) {
					mViewHolder.rangeText.setTextColor( getResources().getColor( R.color.them_color ) );
					mViewHolder.rangeImage.setImageResource( R.mipmap.ic_filtrate_up );
				}
			} else {
				mViewHolder.orderByText.setTextColor( getResources().getColor( R.color.primary_text ) );
				mViewHolder.orderByImage.setImageResource( R.mipmap.ic_filtrate_down );
				mViewHolder.rangeText.setTextColor( getResources().getColor( R.color.primary_text ) );
				mViewHolder.rangeImage.setImageResource( R.mipmap.ic_filtrate_down );
			}
			mRecyclerView.setEnabled( toggle );
		}
	}

	private class ExtOnSelectChangeListener implements DropDownOptionList.OnSelectListener {
		@Override
		public void onSelect( AdapterView< ? > adapterView, View view, int position, long j ) {
			int id = mDropDownOptionList.getCurrentListBindingView().getId();
			if ( id == R.id.order_by_select ) {
				switch ( position ) {
					case 0:
						selectorType = 5;
						break;
					case 1:
						selectorType = 7;
						break;
					case 2:
						selectorType = 1;
						break;
					case 3:
						selectorType = 2;
						break;
				}
				mViewHolder.orderByText.setText( titleList.get( position ) );
				mSrlArticleSecondaryView.autoRefresh();
			} else if ( id == R.id.range_select ) {
				switch ( position ) {
					case 0:
						selectorTimeType = 7;
						break;
					case 1:
						selectorTimeType = 30;
						break;
					case 2:
						selectorTimeType = 90;
						break;
					case 3:
						selectorTimeType = -1;
						break;
				}
				mViewHolder.rangeText.setText( titleTimeList.get( position ) );
				mSrlArticleSecondaryView.autoRefresh();
			}
		}
	}

	static class ViewHolder {
		ImageView orderByImage;
		View orderBySelect;
		TextView orderByText;
		View rangSelect;
		ImageView rangeImage;
		TextView rangeText;

		public ViewHolder( View view ) {
			orderByImage = view.findViewById( R.id.order_by_image );
			orderBySelect = view.findViewById( R.id.order_by_select );
			orderByText = view.findViewById( R.id.order_by_text );
			rangSelect = view.findViewById( R.id.range_select );
			rangeImage = view.findViewById( R.id.filter_range_image );
			rangeText = view.findViewById( R.id.filter_range_text );
		}
	}

	@Override
	public void onDestroyView() {
		if ( mDropDownOptionList != null ) {
			mDropDownOptionList.removeAllViews();
			mDropDownOptionList = null;
		}
		super.onDestroyView();

	}
}
