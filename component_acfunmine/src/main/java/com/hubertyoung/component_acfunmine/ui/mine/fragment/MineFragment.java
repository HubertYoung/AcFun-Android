package com.hubertyoung.component_acfunmine.ui.mine.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.baseplatform.tools.PlatformUtils;
import com.hubertyoung.common.base.BaseFragment;
import com.hubertyoung.common.constant.Constants;
import com.hubertyoung.common.entity.Sign;
import com.hubertyoung.common.entity.User;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.SigninHelper;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.utils.sign.SignInUtil;
import com.hubertyoung.component_acfunmine.R;
import com.hubertyoung.component_acfunmine.ui.mine.control.MineControl;
import com.hubertyoung.component_acfunmine.ui.mine.model.MineModelImp;
import com.hubertyoung.component_acfunmine.ui.mine.presenter.MinePresenterImp;
import com.hubertyoung.component_acfunmine.ui.setting.activity.SettingsActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


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
	private RelativeLayout userInfolayout;
	private FrameLayout avatarLayout;
	private SimpleDraweeView avatar;
	private ImageView gender;
	private ImageView ivHeaddress;
	private TextView nickName;
	private LinearLayout userInfo;
	private TextView userLevel;
	private TextView userGroupLevel;
	private LinearLayout bananaInfo;
	private TextView bananaCount;
	private TextView goldBananaCount;
	private FrameLayout loginLayout;
	private ImageView ivWechatLoginLong;
	private ImageView weChatButton;
	private ImageView qqButton;
	private ImageView phoneButton;
	private ImageView moreButton;
	private LinearLayout countLayout;
	private LinearLayout followLayout;
	private TextView followCount;
	private LinearLayout fansLayout;
	private TextView fansCount;
	private LinearLayout uploadContributorsLayout;
	private TextView uploadCount;
	private RelativeLayout testLayout;
	private TextView tvTest;
	private RelativeLayout chockLayout;
	private RelativeLayout chockInLayout;
	private ImageView tvClockIn;
	private ImageView tvClocked;
	private RelativeLayout qrScanLayout;
	private RelativeLayout uploadLayout;
	private RelativeLayout draftLayout;
	private RelativeLayout historyLayout;
	private RelativeLayout downlaodedLayout;
	private RelativeLayout favoriteLayout;
	private RelativeLayout messageLayout;
	private TextView redDot;
	private RelativeLayout gameLayout;
	private RelativeLayout shopLayout;
	private RelativeLayout acFlowLayout;
	private RelativeLayout marketLayout;
	private RelativeLayout settingtLayout;
	private RelativeLayout feedbackLayout;
	private SignInUtil mSignInUtil;


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


	private void initAction() {
		avatar.setOnClickListener( this );
		followLayout.setOnClickListener( this );
		fansLayout.setOnClickListener( this );
		uploadLayout.setOnClickListener( this );
		testLayout.setOnClickListener( this );
		chockInLayout.setOnClickListener( this );
		uploadContributorsLayout.setOnClickListener( this );
		draftLayout.setOnClickListener( this );
		historyLayout.setOnClickListener( this );
		downlaodedLayout.setOnClickListener( this );
		favoriteLayout.setOnClickListener( this );
		messageLayout.setOnClickListener( this );
		gameLayout.setOnClickListener( this );
		shopLayout.setOnClickListener( this );
		acFlowLayout.setOnClickListener( this );
		marketLayout.setOnClickListener( this );
		settingtLayout.setOnClickListener( this );
		feedbackLayout.setOnClickListener( this );
		weChatButton.setOnClickListener( this );
		qqButton.setOnClickListener( this );
		phoneButton.setOnClickListener( this );
		moreButton.setOnClickListener( this );
		qrScanLayout.setOnClickListener( this );

		mSignInUtil.setOnPlatformNameListener( new SignInUtil.OnPlatformNameListener() {
			@Override
			public void onSuccess( String platformName, Map< String, String > map ) {
				Map< String, String > hashMap = new java.util.HashMap();
				hashMap.put("clientId", Constants.cidKey );
				hashMap.put("accessToken", map.get("token"));
				hashMap.put("openId", map.get("openid"));
				hashMap.put("type", platformName);
				mPresenter.requestPlatformLogin(hashMap);
			}
		} );

	}

	@Override
	protected void initView( Bundle savedInstanceState ) {
		userInfolayout = findViewById( R.id.rl_user_info_layout );
		avatarLayout = findViewById( R.id.avatar_layout );
		avatar = findViewById( R.id.user_avatar );
		gender = findViewById( R.id.user_gender );
		ivHeaddress = findViewById( R.id.iv_headdress );
		nickName = findViewById( R.id.tv_nickname );
		userInfo = findViewById( R.id.user_info_layout );
		userLevel = findViewById( R.id.user_level );
		userGroupLevel = findViewById( R.id.user_group_level );
		bananaInfo = findViewById( R.id.banana_info );
		bananaCount = findViewById( R.id.banana_count );
		goldBananaCount = findViewById( R.id.gold_banana_count );
		loginLayout = findViewById( R.id.fl_login_layout );
		ivWechatLoginLong = findViewById( R.id.iv_wechat_login_long );
		weChatButton = findViewById( R.id.iv_wechat_login );
		qqButton = findViewById( R.id.iv_qq_login );
		phoneButton = findViewById( R.id.iv_phone_login );
		moreButton = findViewById( R.id.iv_more_login );
		countLayout = findViewById( R.id.count_layout );
		followLayout = findViewById( R.id.follow_count_layout );
		followCount = findViewById( R.id.tv_follow_count );
		fansLayout = findViewById( R.id.fans_count_layout );
		fansCount = findViewById( R.id.tv_fans_count );
		uploadContributorsLayout = findViewById( R.id.upload_count_layout );
		uploadCount = findViewById( R.id.tv_upload_count );
		testLayout = findViewById( R.id.test_layout );
		tvTest = findViewById( R.id.tv_test );
		chockLayout = findViewById( R.id.chock_layout );
		chockInLayout = findViewById( R.id.chockin_layout );
		tvClockIn = findViewById( R.id.tv_clock_in );
		tvClocked = findViewById( R.id.tv_clocked );
		qrScanLayout = findViewById( R.id.qr_scan_layout );
		uploadLayout = findViewById( R.id.upload_layout );
		draftLayout = findViewById( R.id.draft_layout );
		historyLayout = findViewById( R.id.history_layout );
		downlaodedLayout = findViewById( R.id.downloaded_layout );
		favoriteLayout = findViewById( R.id.favorite_layout );
		messageLayout = findViewById( R.id.message_layout );
		redDot = findViewById( R.id.msg_dot );
		gameLayout = findViewById( R.id.game_layout );
		shopLayout = findViewById( R.id.shop_layout );
		acFlowLayout = findViewById( R.id.ac_flow_layout );
		marketLayout = findViewById( R.id.market_layout );
		settingtLayout = findViewById( R.id.setting_layout );
		feedbackLayout = findViewById( R.id.feedback_layout );
		mSignInUtil = new SignInUtil( activity );
		initAction();
		HashMap map = new HashMap<String,String>();
//		http://apipc.app.acfun.cn/v2/offlines/checkOffline
		mPresenter.requestCheckOfflineInfo( map );

		HashMap map2 = new HashMap<String,String>();
		map.put( "userId", SigninHelper.getInstance().getUserUid() + "" );
		mPresenter.requestUserInfo( map2 );
//		Log.e( "TAG", "123123" );

	}

	@Override
	public void showLoading( String title, int type ) {
		if ( type == 2 ){
			mSignInUtil.showLoading();
		}
	}

	@Override
	public void stopLoading() {
	}

	@Override
	public void showErrorTip( String msg ) {
		ToastUtil.showSuccess( msg );
	}

	@Override
	@SuppressWarnings( "all" )
	public void onClick( View v ) {
//		CC.obtainBuilder( "ComponentDebug" ).setContext( activity ).setActionName( "toDebugActivity" ).build().call();
		Bundle bundle;
		Serializable user;
		int viewId = v.getId();
		if ( viewId == R.id.ac_flow_layout ) {
//				this.f.o();
		} else if ( viewId == R.id.chockin_layout ) {
//				KanasCommonUtil.c( KanasConstants.de, null );
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.e( this.g, 7 );
//					break;
//				} else {
//					this.f.f();
//					break;
//				}
		} else if ( viewId == R.id.downloaded_layout ) {
//				if ( t() ) {
//					DownloadManager.a();
//					IntentHelper.a( this.g, CacheManageActivity.class );
//					}
//				}
		} else if ( viewId == R.id.draft_layout ) {
//				KanasCommonUtil.c( KanasConstants.cE, null );
//				IntentHelper.a( getActivity(), DraftBoxActivity.class );
		} else if ( viewId == R.id.fans_count_layout ) {
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.e( this.g, 7 );
//					}
//				}
//				bundle = new Bundle();
//				bundle.putInt( "selectPage", 1 );
//				IntentHelper.a( this.g, AttentionAndFansActivity.class, bundle );
		} else if ( viewId == R.id.favorite_layout ) {
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.a( this.g, DialogLoginActivity.class );
//					}
//				} else {
//					IntentHelper.a( this.g, NewFavoritiesActivity.class );
//					}
//				}
		} else if ( viewId == R.id.feedback_layout ) {
//				IntentHelper.a( this.g, FeedBackActivity.class );
		} else if ( viewId == R.id.follow_count_layout ) {
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.e( this.g, 7 );
//					}
//				}
//				bundle = new Bundle();
//				bundle.putInt( "selectPage", 0 );
//				IntentHelper.a( this.g, AttentionAndFansActivity.class, bundle );
		} else if ( viewId == R.id.game_layout ) {
//				this.f.a( this.g );
		} else if ( viewId == R.id.history_layout ) {
//				IntentHelper.a( this.g, NewHistoryActivity.class );
		} else if ( viewId == R.id.iv_more_login ) {
			mSignInUtil.loginAccount();
		} else if ( viewId == R.id.iv_phone_login ) {
//			mSignInUtil.loginQQ();
		} else if ( viewId == R.id.iv_qq_login ) {
			mSignInUtil.loginQQ();
		} else if ( viewId == R.id.iv_wechat_login ) {
			mSignInUtil.loginWechat();
		} else if ( viewId == R.id.market_layout ) {
//				this.f.b( this.g );
		} else if ( viewId == R.id.message_layout ) {
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.a( this.g, DialogLoginActivity.class );
//					}
//				} else {
//					IntentHelper.a( this.g, MessageAndAtmeActivity.class );
//					}
//				}
//			else if ( viewId == R.id.pop_window_upload_view_article){
//				bundle = new Bundle();
//				bundle.putString( "name", KanasConstants.aZ );
//				KanasCommonUtil.c( KanasConstants.cF, bundle );
//				if ( t() ) {
//					IntentHelper.a( getActivity(), -1, -1 );
//				}
//				if ( this.i != null ) {
//					this.i.dismiss();
//					}
//				}
//				}
//			else if ( viewId == R.id.pop_window_upload_view_close){
//				if ( this.i != null ) {
//					this.i.dismiss();
//					}
//				}
//				}
//			else if ( viewId == R.id.pop_window_upload_view_video){
//				bundle = new Bundle();
//				bundle.putString( "name", "video" );
//				KanasCommonUtil.c( KanasConstants.cF, bundle );
//				bundle = new Bundle();
//				user = new User();
//				user.setUid( SigninHelper.a().b() );
//				bundle.putSerializable( "user", user );
//				bundle.putBoolean( "openAlbum", true );
//				IntentHelper.a( this.g, MyselfContributionActivity.class, bundle );
//				if ( this.i != null ) {
//					this.i.dismiss();
//					}
//				}
		} else if ( viewId == R.id.qr_scan_layout ) {
//				if ( SigninHelper.a().u() ) {
//					if ( h( "android.permission.CAMERA" ) == 0 ) {
//						QrScanActivity.a( getContext() );
//						KanasCommonUtil.c( KanasConstants.dl, null );
//						}
//					}
//					PermissionUtils.a( getActivity(), "android.permission.CAMERA" ).b( new Consumer< Permission >() {
//						public /* synthetic */ void accept( Object obj ) throws Exception {
//							a( ( Permission ) obj );
//						}
//
//						public void a( Permission permission ) throws Exception {
//							if ( permission.b ) {
//								QrScanActivity.a( MineFragment.this.getContext() );
//								KanasCommonUtil.c( KanasConstants.dl, null );
//							}
//						}
//					}, Functions.b() );
//					}
//				}
//				IntentHelper.a( this.g, DialogLoginActivity.class );
		} else if ( viewId == R.id.setting_layout ) {
			startActivityForResult( new Intent( getContext(), SettingsActivity.class ), 8 );
		} else if ( viewId == R.id.shop_layout ) {
//				String y = SettingHelper.a().y();
//				try {
//					startActivity( new Intent( AndroidConstants.a, Uri.parse( y ) ) );
//					}
//				} catch ( Exception unused ) {
//					Intent intent = new Intent( getActivity(), WebViewActivity.class );
//					intent.putExtra( "url", y );
//					startActivity( intent );
//					}
//				}
		} else if ( viewId == R.id.test_layout ) {
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.e( this.g, 7 );
//					}
//				} else {
//					startActivityForResult( new Intent( getContext(), QuestionActivity.class ), 9 );
//					}
//				}
		} else if ( viewId == R.id.upload_count_layout ) {
//				if ( SigninHelper.a().u() ) {
//					if ( t() ) {
//						bundle = new Bundle();
//						user = new User();
//						user.setUid( SigninHelper.a().b() );
//						bundle.putSerializable( "user", user );
//						IntentHelper.a( this.g, MyselfContributionActivity.class, bundle );
//						}
//					}
//				}
//				IntentHelper.e( this.g, 7 );
		}
//			}
		else if ( viewId == R.id.upload_layout ) {
//				KanasCommonUtil.c( KanasConstants.cD, null );
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.e( this.g, 7 );
//					}
//				} else if ( SigninHelper.a().t() || !AcFunApplication.t ) {
//					if ( t() ) {
//						w();
//						}
//					}
//				} else {
//					Utils.c( getActivity() );
//					return;
//				}
		} else if ( viewId == R.id.user_avatar ) {
//				if ( !SigninHelper.a().u() ) {
//					IntentHelper.e( this.g, 7 );
//					}
//				}
//				this.g.startActivityForResult( new Intent( this.g, ModifyUserInfoActivity.class ), 0 );
		}
	}

	@Override
	public void setCheckOfflineInfo( Boolean b ) {

	}

	public void setUserNickName( String name ) {
		nickName.setVisibility( View.VISIBLE );
		nickName.setText( name );
	}

	@Override
	public void setUserInfo( User user ) {
		setUserNickName( user.getName() );
		if ( user.getSex() == 0 ) {
			gender.setImageResource( R.mipmap.user_gender_girl );
			gender.setVisibility( View.VISIBLE );
		} else if ( user.getSex() == 1 ) {
			gender.setImageResource( R.mipmap.user_gender_boy );
			gender.setVisibility( View.VISIBLE );
		} else {
			gender.setVisibility( View.GONE );
		}
		userLevel.setText( getString( R.string.slide_menu_user_lv, Integer.valueOf( user.getLevel() ) ) );
		followCount.setText( user.getFollowing() );
		fansCount.setText( user.getFollowed() );
//		mMarketLayout.setVisibility( user.getFollowing() );
		uploadCount.setText( user.getContributes() );
//		this.bananaCount.setText( String.valueOf( Long.valueOf( Long.parseLong( this.bananaCount.getText().toString() ) ).longValue() + l.longValue() ) );
		bananaCount.setText( String.valueOf( user.getBananaCount() ) );
		goldBananaCount.setText( String.valueOf( user.getGoldBananaCount() ) );

		ImageLoaderUtil.loadNetImage( user.getAvatar(), avatar );

	}

	@Override
	public void setUserGroupInfo( boolean isVip ) {
		if ( isVip ) {
			testLayout.setVisibility( View.GONE );
			userGroupLevel.setText( R.string.fragment_more_official_text );
			userLevel.setVisibility( View.VISIBLE );
			return;
		}
		testLayout.setVisibility( View.VISIBLE );
		userGroupLevel.setText( R.string.fragment_more_regist_text );
		userLevel.setVisibility( View.GONE );
	}

	@Override
	public void setLoginState( boolean isLogin ) {
		if ( isLogin ) {
			userInfolayout.setVisibility( View.VISIBLE );
			loginLayout.setVisibility( View.GONE );
			// TODO: 2018/10/29 设置头像 昵称
			ImageLoaderUtil.loadResourceImage( R.mipmap.ic_slide_menu_avatar_no_login, avatar );
//			setUserNickName();
			userInfo.setVisibility( View.VISIBLE );
			chockLayout.setVisibility( View.VISIBLE );
			countLayout.setVisibility( View.VISIBLE );
			draftLayout.setVisibility( View.VISIBLE );
			uploadContributorsLayout.setVisibility( View.VISIBLE );
			ivHeaddress.setVisibility( View.VISIBLE );
			return;
		}
		ImageLoaderUtil.loadResourceImage( R.mipmap.ic_slide_menu_avatar_no_login, avatar );
		userInfolayout.setVisibility( View.GONE );
		loginLayout.setVisibility( View.VISIBLE );
		weChatButton.setVisibility( PlatformUtils.isWxInstalled( activity ) ? View.VISIBLE : View.GONE );
		qqButton.setVisibility( PlatformUtils.isQQInstalled( activity ) ? View.VISIBLE : View.GONE );
		gender.setVisibility( View.GONE );
		nickName.setVisibility( View.GONE );
		userInfo.setVisibility( View.GONE );
		chockLayout.setVisibility( View.GONE );
		countLayout.setVisibility( View.GONE );
		draftLayout.setVisibility( View.GONE );
		testLayout.setVisibility( View.GONE );
		uploadContributorsLayout.setVisibility( View.GONE );
		ivHeaddress.setVisibility( View.GONE );
		setRedDot( false );
	}

	@Override
	public void stopDialogLoading() {
		if ( mSignInUtil.isShowing() ){
			mSignInUtil.dismissLoading();
		}
	}

	@Override
	public void setPlatformLoginInfo( Sign sign ) {
		SigninHelper.getInstance().setUserSign(sign);
		Bundle bundle = new Bundle();
		bundle.putInt( "uid", sign.info.userid );
		bundle.putString( "status", "success" );

		if (sign.isFirstLogin) {
			ToastUtil.showSuccess( R.string.login_success_toast_for_fresh);
		} else {
			ToastUtil.showSuccess( R.string.login_success_toast);
		}

		mRxManager.post( Constants.LoginStatus, Constants.LoginSuccess );

	}

	public void setRedDot( boolean isShowRedDot ) {
		redDot.setVisibility( isShowRedDot ? View.VISIBLE : View.GONE );
	}
}
