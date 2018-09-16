package com.hubertyoung.component_acfunmine.mine.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.acty.component_acfunmine.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.basebean.MyRequestMap;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.component_acfunmine.entity.User;
import com.hubertyoung.component_acfunmine.mine.control.MineControl;
import com.hubertyoung.component_acfunmine.mine.model.MineModelImp;
import com.hubertyoung.component_acfunmine.mine.presenter.MinePresenterImp;
import com.hubertyoung.component_acfunmine.utils.SigninHelper;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/14 20:01
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.mine
 */
public class MineFragment extends BaseFragment< MinePresenterImp, MineModelImp > implements MineControl.View, View.OnClickListener {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;
	private SimpleDraweeView mUserAvatar;
	private ImageView mUserGender;
	private FrameLayout mAvatarLayout;
	private TextView mTvNickname;
	/**
	 * login
	 */
	private TextView mTvLogin;
	/**
	 * regist
	 */
	private TextView mTvRegister;
	private LinearLayout mLoginLayout;
	/**
	 * un_login_text
	 */
	private TextView mUnLoginText;
	/**
	 * 12
	 */
	private TextView mUserLevel;
	/**
	 * 正式会员
	 */
	private TextView mUserGroupLevel;
	private TextView mBananaCount;
	private TextView mGoldBananaCount;
	private LinearLayout mBananaInfo;
	private LinearLayout mUserInfoLayout;
	/**
	 * 0
	 */
	private TextView mTvFollowCount;
	private LinearLayout mFollowCountLayout;
	/**
	 * 0
	 */
	private TextView mTvFansCount;
	private LinearLayout mFansCountLayout;
	/**
	 * 0
	 */
	private TextView mTvUploadCount;
	private LinearLayout mUploadCountLayout;
	private LinearLayout mCountLayout;
	/**
	 * 该去答题了,答题之后才能吐槽
	 */
	private TextView mTvTest;
	private RelativeLayout mTestLayout;
	/**
	 * slide_menu_user_clock_in
	 */
	private TextView mTvClockIn;
	/**
	 * slide_menu_user_clocked_in
	 */
	private TextView mTvClocked;
	private RelativeLayout mChockinLayout;
	private RelativeLayout mChockLayout;
	private RelativeLayout mUploadLayout;
	private RelativeLayout mDraftLayout;
	private RelativeLayout mHistoryLayout;
	private RelativeLayout mDownloadedLayout;
	private RelativeLayout mFavoriteLayout;
	private TextView mMsgDot;
	private RelativeLayout mMessageLayout;
	private RelativeLayout mGameLayout;
	private RelativeLayout mShopLayout;
	private RelativeLayout mAcFlowLayout;
	private RelativeLayout mMarketLayout;
	private RelativeLayout mSettingLayout;
	private RelativeLayout mFeedbackLayout;


	public MineFragment() {
		// Required empty public constructor
	}

	public static MineFragment newInstance( String param1, String param2 ) {
		MineFragment fragment = new MineFragment();
		Bundle args = new Bundle();
		args.putString( ARG_PARAM1, param1 );
		args.putString( ARG_PARAM2, param2 );
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		if ( getArguments() != null ) {
			mParam1 = getArguments().getString( ARG_PARAM1 );
			mParam2 = getArguments().getString( ARG_PARAM2 );
		}
	}

	@Override
	protected void initToolBar() {
//		BarUtils.setPaddingSmart( mVTopRoot );
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.fragment_mine_layout;
	}

	@Override
	public void initPresenter() {
		mPresenter.setVM( this, mModel );
	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ) {
		mUserAvatar = ( SimpleDraweeView ) view.findViewById( R.id.user_avatar );
		mUserGender = ( ImageView ) view.findViewById( R.id.user_gender );
		mAvatarLayout = ( FrameLayout ) view.findViewById( R.id.avatar_layout );
		mTvNickname = ( TextView ) view.findViewById( R.id.tv_nickname );
		mTvLogin = ( TextView ) view.findViewById( R.id.tv_login );
		mTvRegister = ( TextView ) view.findViewById( R.id.tv_register );
		mLoginLayout = ( LinearLayout ) view.findViewById( R.id.login_layout );
		mUnLoginText = ( TextView ) view.findViewById( R.id.un_login_text );
		mUserLevel = ( TextView ) view.findViewById( R.id.user_level );
		mUserGroupLevel = ( TextView ) view.findViewById( R.id.user_group_level );
		mBananaCount = ( TextView ) view.findViewById( R.id.banana_count );
		mGoldBananaCount = ( TextView ) view.findViewById( R.id.gold_banana_count );
		mBananaInfo = ( LinearLayout ) view.findViewById( R.id.banana_info );
		mUserInfoLayout = ( LinearLayout ) view.findViewById( R.id.user_info_layout );
		mTvFollowCount = ( TextView ) view.findViewById( R.id.tv_follow_count );
		mFollowCountLayout = ( LinearLayout ) view.findViewById( R.id.follow_count_layout );
		mTvFansCount = ( TextView ) view.findViewById( R.id.tv_fans_count );
		mFansCountLayout = ( LinearLayout ) view.findViewById( R.id.fans_count_layout );
		mTvUploadCount = ( TextView ) view.findViewById( R.id.tv_upload_count );
		mUploadCountLayout = ( LinearLayout ) view.findViewById( R.id.upload_count_layout );
		mCountLayout = ( LinearLayout ) view.findViewById( R.id.count_layout );
		mTvTest = ( TextView ) view.findViewById( R.id.tv_test );
		mTestLayout = ( RelativeLayout ) view.findViewById( R.id.test_layout );
		mTvClockIn = ( TextView ) view.findViewById( R.id.tv_clock_in );
		mTvClocked = ( TextView ) view.findViewById( R.id.tv_clocked );
		mChockinLayout = ( RelativeLayout ) view.findViewById( R.id.chockin_layout );
		mChockLayout = ( RelativeLayout ) view.findViewById( R.id.chock_layout );
		mUploadLayout = ( RelativeLayout ) view.findViewById( R.id.upload_layout );
		mDraftLayout = ( RelativeLayout ) view.findViewById( R.id.draft_layout );
		mHistoryLayout = ( RelativeLayout ) view.findViewById( R.id.history_layout );
		mDownloadedLayout = ( RelativeLayout ) view.findViewById( R.id.downloaded_layout );
		mFavoriteLayout = ( RelativeLayout ) view.findViewById( R.id.favorite_layout );
		mMsgDot = ( TextView ) view.findViewById( R.id.msg_dot );
		mMessageLayout = ( RelativeLayout ) view.findViewById( R.id.message_layout );
		mGameLayout = ( RelativeLayout ) view.findViewById( R.id.game_layout );
		mShopLayout = ( RelativeLayout ) view.findViewById( R.id.shop_layout );
		mAcFlowLayout = ( RelativeLayout ) view.findViewById( R.id.ac_flow_layout );
		mMarketLayout = ( RelativeLayout ) view.findViewById( R.id.market_layout );
		mSettingLayout = ( RelativeLayout ) view.findViewById( R.id.setting_layout );
		mFeedbackLayout = ( RelativeLayout ) view.findViewById( R.id.feedback_layout );
		initAction();
		super.onViewCreated( view, savedInstanceState );
	}

	private void initAction() {
		mUserAvatar.setOnClickListener( this );
		mTvLogin.setOnClickListener( this );
		mTvRegister.setOnClickListener( this );
		mTvFollowCount.setOnClickListener( this );
		mTvFansCount.setOnClickListener( this );
		mTvUploadCount.setOnClickListener( this );
		mTvTest.setOnClickListener( this );
		mChockinLayout.setOnClickListener( this );
		mUploadLayout.setOnClickListener( this );
		mDraftLayout.setOnClickListener( this );
		mHistoryLayout.setOnClickListener( this );
		mDownloadedLayout.setOnClickListener( this );
		mFavoriteLayout.setOnClickListener( this );
		mMessageLayout.setOnClickListener( this );
		mGameLayout.setOnClickListener( this );
		mShopLayout.setOnClickListener( this );
		mAcFlowLayout.setOnClickListener( this );
		mMarketLayout.setOnClickListener( this );
		mSettingLayout.setOnClickListener( this );
		mFeedbackLayout.setOnClickListener( this );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		MyRequestMap map = new MyRequestMap();
//		http://apipc.app.acfun.cn/v2/offlines/checkOffline
		mPresenter.requestCheckOfflineInfo( map );

		MyRequestMap map2 = new MyRequestMap();
		map.put( "userId", SigninHelper.getInstance().getUserUid()+"" );
		mPresenter.requestUserInfo( map2 );
//		Log.e( "TAG", "123123" );
	}

	@Override
	public void showLoading( String title, int type ) {

	}

	@Override
	public void stopLoading() {

	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showSuccess( msg );
	}

	@Override
	public void onClick( View v ) {
		switch ( v.getId() ) {
			case R.id.ac_flow_layout:
//				this.f.o();
				break;
			case R.id.chockin_layout:
//				if (!SigninHelper.a().s()) {
//					IntentHelper.e(this.g, 7);
//					break;
//				} else {
//					this.f.f();
//					break;
//				}
			case R.id.downloaded_layout:
//				if (s()) {
//					DownloadManager.a();
//					IntentHelper.a(this.g, CacheManageActivity.class);
//					break;
//				}
				break;
			case R.id.draft_layout:
//				KanasUtil.c(KanasConstants.bG, null);
//				IntentHelper.a(getActivity(), DraftBoxActivity.class);
				break;
			case R.id.fans_count_layout:
//				if (!SigninHelper.a().s()) {
//					IntentHelper.e(this.g, 7);
//					break;
//				}
//				bundle = new Bundle();
//				bundle.putInt("selectPage", 1);
//				IntentHelper.a(this.g, AttentionAndFansActivity.class, bundle);
				break;
			case R.id.favorite_layout:
//				if (!SigninHelper.a().s()) {
//					IntentHelper.e(this.g, 7);
//					break;
//				} else {
//					IntentHelper.a(this.g, NewFavoritiesActivity.class);
//					break;
//				}
			case R.id.feedback_layout:
//				IntentHelper.a(this.g, FeedBackActivity.class);
				break;
			case R.id.follow_count_layout:
//				if (!SigninHelper.a().s()) {
//					IntentHelper.e(this.g, 7);
//					break;
//				}
//				bundle = new Bundle();
//				bundle.putInt("selectPage", 0);
//				IntentHelper.a(this.g, AttentionAndFansActivity.class, bundle);
				break;
			case R.id.game_layout:
//				this.f.a(this.g);
				break;
			case R.id.history_layout:
//				IntentHelper.a(this.g, NewHistoryActivity.class);
				break;
			case R.id.market_layout:
//				this.f.b(this.g);
				break;
			case R.id.message_layout:
//				if (!SigninHelper.a().s()) {
//					IntentHelper.e(this.g, 7);
//					break;
//				} else {
//					IntentHelper.a(this.g, MessageAndAtmeActivity.class);
//					break;
//				}
//			case R.id.pop_window_upload_view_article:
//				bundle = new Bundle();
//				bundle.putString("name", KanasConstants.aw);
//				KanasUtil.c(KanasConstants.bH, bundle);
//				if (s()) {
//					IntentHelper.g(getActivity(), -1);
//				}
//				if (this.i != null) {
//					this.i.dismiss();
//					break;
//				}
//				break;
//			case R.id.pop_window_upload_view_close:
//				if (this.i != null) {
//					this.i.dismiss();
//					break;
//				}
//				break;
//			case R.id.pop_window_upload_view_video:
//				bundle = new Bundle();
//				bundle.putString("name", "video");
//				KanasUtil.c(KanasConstants.bH, bundle);
//				bundle = new Bundle();
//				Serializable user = new User();
//				user.setUid(SigninHelper.a().b());
//				bundle.putSerializable("user", user);
//				bundle.putBoolean("openAlbum", true);
//				IntentHelper.a(this.g, MyselfContributionActivity.class, bundle);
//				if (this.i != null) {
//					this.i.dismiss();
//					break;
//				}
				break;
			case R.id.setting_layout:
//				startActivityForResult(new Intent(getContext(), SettingsActivity.class), 8);
				break;
			case R.id.shop_layout:
//				String y = SettingHelper.a().y();
//				try {
//					startActivity(new Intent(ACTION.HWID_SCHEME_URL, Uri.parse(y)));
//					break;
//				} catch (Exception unused) {
//					Intent intent = new Intent(getActivity(), WebViewActivity.class);
//					intent.putExtra("url", y);
//					startActivity(intent);
//					break;
//				}
			case R.id.test_layout:
//				if (!SigninHelper.a().s()) {
//					IntentHelper.e(this.g, 7);
//					break;
//				} else {
//					startActivityForResult(new Intent(getContext(), QuestionActivity.class), 9);
//					break;
//				}
			case R.id.tv_login:
//				IntentHelper.e(this.g, 7);
				break;
			case R.id.tv_register:
//				startActivityForResult(new Intent(getContext(), RegisterActivity.class), 10);
				break;
			case R.id.upload_count_layout:
//				if (SigninHelper.a().s()) {
//					if (s()) {
//						bundle = new Bundle();
//						Serializable user2 = new User();
//						user2.setUid(SigninHelper.a().b());
//						bundle.putSerializable("user", user2);
//						IntentHelper.a(this.g, MyselfContributionActivity.class, bundle);
//						break;
//					}
//				}
//				IntentHelper.e(this.g, 7);
//				break;
//			break;
			case R.id.upload_layout:
//				KanasUtil.c(KanasConstants.bF, null);
//				if (!SigninHelper.a().s()) {
//					IntentHelper.e(this.g, 7);
//					break;
//				} else if (SigninHelper.a().r() || !AcFunApplication.q) {
//					if (s()) {
//						v();
//						break;
//					}
//				} else {
//					Utils.c(getActivity());
//					return;
//				}
				break;
			case R.id.user_avatar:
//				if (!SigninHelper.a().s()) {
//					IntentHelper.e(this.g, 7);
//					break;
//				}
//				this.g.startActivityForResult(new Intent(this.g, ModifyUserInfoActivity.class), 0);
				String s=null;
				s.equals( "" );
				break;
		}
	}

	@Override
	public void setCheckOfflineInfo( Boolean b ) {

	}

	@Override
	public void setUserInfo( User user ) {
		this.mTvNickname.setVisibility( View.VISIBLE );
		this.mTvNickname.setText( user.getName() );
		if ( user.getSex() == 0 ) {
			this.mUserGender.setImageResource( R.mipmap.user_gender_girl );
			this.mUserGender.setVisibility( View.VISIBLE );
		} else if ( user.getSex() == 1 ) {
			this.mUserGender.setImageResource( R.mipmap.user_gender_boy );
			this.mUserGender.setVisibility( View.VISIBLE );
		} else {
			this.mUserGender.setVisibility( View.GONE );
		}
		this.mUserLevel.setText( getString( R.string.slide_menu_user_lv, Integer.valueOf( user.getLevel() ) ) );
		mTvFollowCount.setText( user.getFollowing() );
		mTvFansCount.setText( user.getFollowed() );
//		mMarketLayout.setVisibility( user.getFollowing() );
		mTvUploadCount.setText( user.getContributes() );
		mBananaCount.setText( String.valueOf( user.getBananaCount() ) );
		mGoldBananaCount.setText( String.valueOf( user.getGoldBananaCount() ) );

		ImageLoaderUtil.loadNetImage( user.getAvatar(), mUserAvatar );

	}

	@Override
	public void setUserGroupInfo( boolean b ) {
		if ( b ) {
			mTestLayout.setVisibility( View.GONE );
			mUserGroupLevel.setText( R.string.fragment_more_official_text );
			mUserLevel.setVisibility( View.VISIBLE );
			return;
		}
		mTestLayout.setVisibility( View.VISIBLE );
		mUserGroupLevel.setText( R.string.fragment_more_regist_text );
		mUserLevel.setVisibility( View.GONE );
	}

	@Override
	public void setLoginState( boolean b ) {
		if ( b ) {
			mUserInfoLayout.setVisibility( View.VISIBLE );
			mChockLayout.setVisibility( View.VISIBLE );
			mCountLayout.setVisibility( View.VISIBLE );
			mDraftLayout.setVisibility( View.VISIBLE );
			mUploadCountLayout.setVisibility( View.VISIBLE );
			mLoginLayout.setVisibility( View.GONE );
			mUnLoginText.setVisibility( View.GONE );
			return;
		}
		ImageLoaderUtil.loadResourceImage( R.mipmap.ic_slide_menu_avatar_no_login, mUserAvatar );
		mUserGender.setVisibility( View.GONE );
		mTvNickname.setVisibility( View.GONE );
		mUserInfoLayout.setVisibility( View.GONE );
		mChockLayout.setVisibility( View.GONE );
		mCountLayout.setVisibility( View.GONE );
		mDraftLayout.setVisibility( View.GONE );
		mUploadCountLayout.setVisibility( View.GONE );
		mLoginLayout.setVisibility( View.VISIBLE );
		mUnLoginText.setVisibility( View.VISIBLE );
	}
}
