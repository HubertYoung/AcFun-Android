package com.hubertyoung.component_acfunmine.mine;


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
public class MineFragment extends BaseFragment {
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	private String mParam1;
	private String mParam2;
	private View view;
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
		super.onViewCreated( view, savedInstanceState );
	}

	@Override
	protected void initView( Bundle savedInstanceState ) {

	}
}
