//package com.kento.component.basic.commonutils.mob;
//
//import android.app.Activity;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.kento.component.basic.R;
//
//
///**
// * 作者：JIUU on 2017/3/10 10:34
// * QQ号：1344393464
// * 作用：底部分享dialog
// */
//public class MobShareBottomDialog extends BottomBaseDialog< MobShareBottomDialog > {
//
//
//	public static final int NORMAL = 1;
//	public static final int SERVICE_MANAGEMENT = 2;
//	LinearLayout rlPopup;
//	private String jumpUrl;
//	private String title;
//	private String content;
//
//	//    private PlatformActionListener platformActionListener;
//	public static String imageUrl = "http://kbpics.oss.aliyuncs.com/cardshare/icon/201803/c1c8feff-dcae-4097-8cc2-459a622ba750.png";
//	//	public static String imageUrl = "http://api.d1-bus.com/assets/images/d1busicon.png";
//	private int status;
//	private ImageView ivCancal;
//	private ImageView ivWeixin;
//	private ImageView ivFriend;
//	private ImageView ivWeibo;
//	private ImageView ivqq;
//	private ImageView ivqqZone;
//	private LinearLayout mLlShareBottomMenu;
//	private Activity mActivity;
//	@NonNull
//	private OnCallback onCallback = new OnCallback() {
//		@Override
//		public void onStarted( Activity activity ) {
//
//		}
//
//		@Override
//		public void onCompleted( Activity activity ) {
//
//		}
//
//		@Override
//		public void onSucceed( Activity activity, Object result ) {
//
//		}
//
//		@Override
//		public void onFailed( Activity activity, int code, String message ) {
//
//		}
//	};
//
//	public MobShareBottomDialog( @NonNull Activity activity, View animateView, String icon, @NonNull String title, @NonNull String content, @NonNull String url, @NonNull int Status ) {
//		super( activity, animateView );
//		initView( activity, icon, title, content, url, Status );
//	}
//
//	public MobShareBottomDialog( @NonNull Activity activity, String icon, @NonNull String title, @NonNull String content, @NonNull String jumpUrl, @NonNull int Status ) {
//		this( activity, null, icon, title, content, jumpUrl, Status );
//	}
//
//	//	{"Icon":"","Title":"卡享生活","Desc":"。","Url":""}
//	private void initView( Activity activity, String icon, String title, String content, String jumpUrl, int status ) {
//		this.mActivity = activity;
//		if ( !TextUtils.isEmpty( icon ) ) {
//			this.imageUrl = icon;
//		}
//		this.jumpUrl = jumpUrl;
//		this.status = status;
//		this.title = title;
//		this.content = content;
//
//		if ( status == NORMAL ) {
//			this.title = title;
//			this.content = content;
//		} else if ( status == SERVICE_MANAGEMENT ) {
//			this.title = "卡享生活";
//			this.content = "共享一次，多赢一次。为持卡用户手中的会员卡提供安全保障，让持卡用户手中的会员卡好玩、有价值。每天实时发布，消费者登录平台即可发现周边商品/服务优惠";
//		}
//
//	}
//
//	@Override
//	public View onCreateView() {
//		showAnim( new FlipVerticalSwingEnter() );
//		dismissAnim( null );
//		View view = LayoutInflater.from( mContext ).inflate( R.layout.dialog_bottom_share, null );
//
//		mLlShareBottomMenu = ( LinearLayout ) view.findViewById( R.id.ll_share_bottom_menu );
//		ivWeixin = ( ImageView ) view.findViewById( R.id.iv_weixin );
//		ivFriend = ( ImageView ) view.findViewById( R.id.iv_friend );
//		ivWeibo = ( ImageView ) view.findViewById( R.id.iv_weibo );
//		ivqq = ( ImageView ) view.findViewById( R.id.iv_qq );
//		ivqqZone = ( ImageView ) view.findViewById( R.id.iv_qqzone );
//		ivCancal = ( ImageView ) view.findViewById( R.id.iv_cancal );
//		ivqqZone.setVisibility( View.GONE );
//		ivWeibo.setVisibility( View.GONE );
////		if ( status == SERVICE_MANAGEMENT ) {
////			mLlShareBottomMenu.setVisibility( View.VISIBLE );
////		}
//
//		return view;
//	}
//
//	@Override
//	public void setUiBeforShow() {
//
//		ivFriend.setOnClickListener( v -> {//朋友圈
//			weixin( ShareTo.WXTimeline );
//			dismiss();
//		} );
//		ivWeixin.setOnClickListener( new View.OnClickListener() {
//			@Override
//			public void onClick( View v ) {
//				weixin( ShareTo.WXSession );
//				dismiss();
//			}
//		} );
//		ivqq.setOnClickListener( v -> {//QQ
//			qq();
//			dismiss();
//		} );
//		ivWeibo.setOnClickListener( v -> {//微博
//			weibo();
//			dismiss();
//		} );
//		ivCancal.setOnClickListener( v -> {//取消
//			dismiss();
//		} );
//		ivqqZone.setOnClickListener( v -> {
//			qqZone();
//			dismiss();
//		} );
//	}
//
//	private void weibo() {
//		ShareSDK.make( mActivity, title, new MoImage( imageUrl ) ).share( ShareTo.Weibo, onCallback );
//	}
//
////    private void friend() {
////        Platform.ShareParams sp = new Platform.ShareParams();
////        sp.setShareType(Platform.SHARE_WEBPAGE);
////        sp.setText(content);
////        sp.setTitle(title);
////        sp.setImageUrl(imageUrl);
////        // sp.setImagePath(hostImagePath);
////        sp.setUrl(url);
////        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
////        platform.setPlatformActionListener(platformActionListener); // 设置分享事件回调
////        // 执行图文分享
////        platform.SSOSetting(false);
////        platform.share(sp);
////    }
//
//	private void weixin( String WXType ) {
//		UrlResource urlResource = new UrlResource( imageUrl );
//		ShareSDK.make( mActivity, new MoWeb( jumpUrl ) ).withTitle( title ).withDescription( content ).withThumb( urlResource ).share( WXType, onCallback );
//	}
//
//	private void qq() {
//		UrlResource urlResource = new UrlResource( imageUrl );
//		ShareSDK.make( mActivity, new MoWeb( jumpUrl ) ).withTitle( title ).withDescription( content ).withThumb( urlResource ).share( ShareTo.QQ, onCallback );
//	}
//
//	private void qqZone() {
//		UrlResource urlResource = new UrlResource( imageUrl );
//		ShareSDK.make( mActivity, new MoWeb( jumpUrl ) ).withTitle( title ).withDescription( content ).withThumb( urlResource ).share( ShareTo.QZone, onCallback );
//	}
//
//	public void setOnShareUrlClickListener( OnCallback onCallback ) {
//		this.onCallback = onCallback;
//	}
//	//    public PlatformActionListener getPlatformActionListener() {
////        return platformActionListener;
////    }
//
////    public void setPlatformActionListener(PlatformActionListener platformActionListener) {
////        this.platformActionListener = platformActionListener;
////    }
//
//	/**
//	 * @param title
//	 * @param content
//	 * @param hostImagePath
//	 * @param url
//	 * @param paListener
//	 * @Title: showWechat
//	 * @Description: 微信
//	 * @author: lee
//	 * @return: void
//	 */
////    public static void shareWechat(String title, String content, String hostImagePath, String url, PlatformActionListener paListener) {
////        if (StaticValues.isWeixinAvilible(MyApplication.getInstance())) {
////            ShareSDK.initSDK(MyApplication.getInstance());
////            Platform.ShareParams sp = new Platform.ShareParams();
////            sp.setShareType( Platform.SHARE_WEBPAGE);
////            sp.setText(content);
////            sp.setTitle(title);
////            sp.setImageUrl(hostImagePath);
////            // sp.setImagePath(hostImagePath);
////            sp.setUrl(url);
////
////            Platform platform = ShareSDK.getPlatform( Wechat.NAME);
////            platform.setPlatformActionListener(paListener); // 设置分享事件回调
////            // 执行图文分享
////            platform.SSOSetting(true);
////            platform.share(sp);
////        } else {
////            ToastUtil.showToast("暂未安装微信客户端");
////        }
////    }
//
//	/**
//	 * @param title
//	 * @param content
//	 * @param hostImagePath
//	 * @param url
//	 * @param paListener
//	 * @Title: showWechat
//	 * @Description: 微信朋友圈
//	 * @author: lee
//	 * @return: void
//	 */
////    public static void shareWechatMoments(String title, String content, String hostImagePath, String url, PlatformActionListener paListener) {
////        if (StaticValues.isWeixinAvilible(MyApplication.getInstance())) {
////            ShareSDK.initSDK(MyApplication.getInstance());
////            Platform.ShareParams sp = new Platform.ShareParams();
////            sp.setShareType( Platform.SHARE_WEBPAGE);
////            sp.setText(content);
////            sp.setTitle(title);
////            sp.setImageUrl(hostImagePath);
////            // sp.setImagePath(hostImagePath);
////            sp.setUrl(url);
////            Platform platform = ShareSDK.getPlatform( WechatMoments.NAME);
////            platform.setPlatformActionListener(paListener); // 设置分享事件回调
////            // 执行图文分享
////            platform.SSOSetting(false);
////            platform.share(sp);
////        } else {
////            ToastUtil.showToast("暂未安装微信客户端");
////        }
////    }
//
//	/**
//	 * @param title
//	 * @param content
//	 * @param imageUrl
//	 * @param url
//	 * @param paListener
//	 * @Title: shareQzone
//	 * @Description: 空间分享
//	 * @author: lee
//	 * @return: void
//	 */
////    public static void shareQzone(String title, String content, String imageUrl, String url, PlatformActionListener paListener) {
////        Platform.ShareParams sp = new Platform.ShareParams();
////        sp.setTitle(title);
////        sp.setText(content);
////        sp.setTitleUrl(url);
////        sp.setImageUrl(imageUrl);
////        Platform platform = ShareSDK.getPlatform(QZone.NAME);
////        platform.setPlatformActionListener(paListener); // 设置分享事件回调
////        // 执行图文分享
////        platform.SSOSetting(false);
////        platform.share(sp);
////    }
//
////    public static void shareQQ(String title, String content, String imageUrl, String url, PlatformActionListener paListener) {
////        // ShareParams sp = new ShareParams();
////        // sp.setTitle(shareParams.getTitle());
////        // sp.setTitleUrl(shareParams.getUrl()); // 标题的超链接
////        // sp.setText(shareParams.getText());
////        // sp.setImageUrl(shareParams.getImageUrl());
////        // sp.setComment("我对此分享内容的评论");
////        // sp.setSite(shareParams.getTitle());
////        // sp.setSiteUrl(shareParams.getUrl());
////        // Platform qq = ShareSDK.getPlatform(context, "QQ");
////        // qq.setPlatformActionListener(platformActionListener);
////        // qq.share(sp);
////        // **********
////
////        Platform.ShareParams sp = new Platform.ShareParams();
////        // sp.setShareType(Platform.SHARE_WEBPAGE);
////        sp.setTitle(title);
////        sp.setTitleUrl(url);
////        sp.setText(content);
////        sp.setImageUrl(imageUrl);
////        sp.setSiteUrl(url);
////        // sp.setImageData(BitmapFactory.decodeResource(activity.getResources(),
////        // R.drawable.app_icon));
////        Platform platform = ShareSDK.getPlatform(QQ.NAME);
////        platform.setPlatformActionListener(paListener); // 设置分享事件回调
////        // 执行图文分享
////        platform.SSOSetting(true);
////        platform.share(sp);
////    }
//
//	/**
//	 * @param paListener
//	 * @Title: showSina
//	 * @Description: 新浪分享
//	 * @author: lee
//	 * @return: void
//	 */
////    public static void showSina(String title, String content, String imageUrl, String url, PlatformActionListener paListener) {
////        Platform.ShareParams sp = new Platform.ShareParams();
////        if (TextUtils.isEmpty(content) && TextUtils.isEmpty(imageUrl)) {
////            return;
////        }
////        sp.setText(content);
////        sp.setTitle(title);
////        sp.setImageUrl(imageUrl);
////        sp.setUrl(url);
////        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
////        platform.setPlatformActionListener(paListener); // 设置分享事件回调
////        // 执行图文分享
////        platform.share(sp);
////    }
//}