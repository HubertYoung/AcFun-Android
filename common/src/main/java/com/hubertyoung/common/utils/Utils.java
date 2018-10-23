package com.hubertyoung.common.utils;

import com.hubertyoung.common.constant.AdMapKey;
import com.hubertyoung.common.utils.os.AppUtils;

import java.util.HashMap;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 19:29
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.utils
 */
public class Utils {
	public static final String game_list_vertic = "game_list_vertic";
	public static final String game_list_horiz = "game_list_horiz";
	public static final String game_banners = "game_banners";
	public static final String uploader_rss = "uploader_rss";
	public static final String dynamic_fav = "dynamic_fav";
	public static final String dynamic_recommend = "dynamic_recommend";
	public static final int G = 0;
	public static final int H = 1;
	public static final int I = 11;
	public static final int J = 12;
	public static final int K = 13;
	public static final int L = 1;
	public static final int M = 2;
	public static final int N = 3;
	public static final int O = 4;
	public static final int P = 5;
	public static final int Q = 6;
	public static final int R = 7;
	public static final int S = 8;
	public static final int T = 9;
	public static final int U = 10;
	public static final int V = 11;
	public static final int W = 12;
	public static final int X = 13;
	public static final int Y = 14;
	public static final int Z = 15;
	public static final int a = 1;
	public static final int aa = 16;
	public static final int ab = 17;
	public static final int ac = 18;
	public static final int ad = 19;
	public static final int b = 2;
	public static final int c = 3;
	public static final String videos = "videos";
	public static final String bangumis = "bangumis";
	public static final String carousels = "carousels";
	public static final String banners = "banners";
	public static final String channels = "channels";
	public static final String users = "users";
	public static final String articles = "articles";
	public static final String videos_rank = "videos_rank";
	public static final String articles_rank = "articles_rank";
	public static final String videos_new = "videos_new";
	public static final String articles_new = "articles_new";
	public static final String bangumis_new = "bangumis_new";
	public static final String cards_users = "cards_users";
	public static final String videos_banana_list = "videos_banana_list";
	public static final String carousels_recommend = "carousels_recommend";
	public static final String banners_fly = "banners_fly";
	public static final String banners_specia_activity = "banners_specia_activity";
	public static final String albums = "albums";
	public static final String anchor = "anchor";
	public static final String spread_banner_game = "spread_banner_game";
	public static final String spread_banner = "spread_banner";
	public static final String shiouqi = "shiouqi";
	public static final String news_down_up = "news_down_up";

	public static boolean isSuccess( int i ) {
		return i == -100 || i == 401 || i == 110008;
	}

//	public static List<Regions> drawable(List<Regions> list, int i) {
//		List<Regions> arrayList = new ArrayList();
//		if (list != null) {
//			for (Regions regions : list) {
//				if (regions.type.id == i) {
//					arrayList.add(regions);
//				}
//			}
//		}
//		return arrayList;
//	}

//	public static void startActivity(Activity activity, int i, String str, Bundle bundle) {
//		if (str != null) {
//			try {
//				Intent intent = new Intent();
//				Intent intent2 = null;
//				Bundle bundle2;
//				switch (i) {
//					case 1:
//						IntentHelper.drawable(activity, Integer.valueOf(str).intValue(), "recommend");
//						break;
//					case 2:
//						IntentHelper.c(activity, Integer.valueOf(str).intValue(), "recommend");
//						break;
//					case 3:
//						User user = new User();
//						user.setUid(Integer.valueOf(str).intValue());
//						IntentHelper.drawable(activity, user);
//						break;
//					case 4:
//						if (bundle == null) {
//							bundle = new Bundle();
//						}
//						bundle.putString("url", str);
//						IntentHelper.drawable(activity, WebViewActivity.class, bundle);
//						break;
//					case 5:
//					case 18:
//						Uri parse = Uri.parse(str);
//						intent.setAction(ACTION.HWID_SCHEME_URL);
//						intent.setData(parse);
//						break;
//					case 6:
//						IntentHelper.b(activity, Integer.valueOf(str).intValue());
//						break;
//					case 7:
//						intent.setClass(activity, RankActivity.class);
//						if (!TextUtils.isEmpty(str)) {
//							intent.putExtra(RankActivity.f, -1);
//							intent.putExtra(RankActivity.e, Integer.parseInt(str));
//							break;
//						}
//						intent.putExtra(RankActivity.f, 2);
//						break;
//					case 8:
//						intent.setClass(activity, SerialBangumiActivity.class);
//						intent.putExtra("category", BangumiType.ANIMATION);
//						break;
//					case 9:
//						break;
//					case 10:
//						IntentHelper.b(activity, Integer.valueOf(str).intValue(), "recommend");
//						break;
//					case 11:
//						intent.setClass(activity, RankActivity.class);
//						intent.putExtra(RankActivity.f, 1);
//						break;
//					case 12:
//						IntentHelper.drawable(activity, BangumiSecondaryActivity.class);
//						break;
//					case 13:
//						bundle2 = new Bundle();
//						bundle2.putInt(NewHistoryActivity.g, 1);
//						IntentHelper.drawable(activity, NewHistoryActivity.class, bundle2);
//						break;
//					case 14:
//						try {
//							i = Integer.valueOf(str).intValue();
//						} catch (Exception unused) {
//							i = 0;
//						}
//						IntentHelper.d(activity, i, "recommend");
//						break;
//					case 15:
//						bundle2 = new Bundle();
//						StringBuilder stringBuilder = new StringBuilder();
//						stringBuilder.append(b());
//						stringBuilder.append(str);
//						bundle2.putString("url", stringBuilder.toString());
//						IntentHelper.drawable(activity, WebViewActivity.class, bundle2);
//						break;
//					case 16:
//						IntentHelper.c(activity);
//						break;
//					case 17:
//						if (!TextUtils.isEmpty(str)) {
//							if (bundle == null) {
//								bundle = new Bundle();
//							}
//							bundle.putString(GameDetailActivity.f, str);
//							IntentHelper.drawable(activity, GameDetailActivity.class, bundle);
//							break;
//						}
//						break;
//					case 19:
//						intent = new Intent(activity, ChannelHotActivity.class);
//						intent.putExtra("channel", str);
//						break;
//					default:
//						intent.setClass(activity, WebViewActivity.class);
//						StringBuilder stringBuilder2 = new StringBuilder();
//						stringBuilder2.append(DomainHelper.drawable().m());
//						stringBuilder2.append("/pleaseupdate/");
//						intent.putExtra("url", stringBuilder2.toString());
//						break;
//				}
//				intent2 = intent;
//				if (intent2 != null) {
//					activity.startActivity(intent2);
//				}
//			} catch (Throwable e) {
//				LogUtil.drawable(e);
//			}
//		}
//	}

//	public static void drawable(Activity activity, RegionsContent regionsContent, Bundle bundle) {
//		if (regionsContent.actionId == 4) {
//			bundle = new Bundle();
//			bundle.putInt("share_menu", regionsContent.shareTagShow);
//		}
//		drawable(activity, regionsContent.actionId, regionsContent.url, bundle);
//	}
//
//	public static void drawable(Activity activity, RegionBodyContent regionBodyContent, Bundle bundle) {
//		drawable(activity, regionBodyContent.actionId, regionBodyContent.contentId, bundle);
//	}
//
//	public static void drawable(Activity activity, RegionsContent regionsContent, int i, int i2, int i3) {
//		drawable(activity, regionsContent, null);
//	}
//
//	public static void drawable(Activity activity, RegionsContent regionsContent) {
//		drawable(activity, regionsContent, null);
//	}
//
//	public static void drawable(Context context) {
//		Editor edit = context.getSharedPreferences(SharedPreferencesConst.g, 0).edit();
//		edit.putLong(TtmlNode.START, System.currentTimeMillis());
//		edit.apply();
//	}
//
//	public static void b(Context context) {
//		Editor edit = context.getSharedPreferences(SharedPreferencesConst.g, 0).edit();
//		edit.putLong("length", System.currentTimeMillis() - c(context));
//		edit.apply();
//	}
//
//	public static long c(Context context) {
//		return context.getSharedPreferences(SharedPreferencesConst.g, 0).getLong(TtmlNode.START, 0);
//	}
//
//	public static long d(Context context) {
//		return context.getSharedPreferences(SharedPreferencesConst.g, 0).getLong("length", 0);
//	}
//
//	public static void drawable(final Activity activity) {
//		DialogUtils.drawable(activity, new OnClickListener() {
//			public void onClick( DialogInterface dialogInterface, int i) {
//				dialogInterface.dismiss();
//			}
//		}, new OnClickListener() {
//			public void onClick(DialogInterface dialogInterface, int i) {
//				Intent intent = new Intent(activity, BindPhoneActivityNew.class);
//				intent.putExtra("type", BindPhoneActivityNew.g);
//				activity.startActivity(intent);
//			}
//		}, activity.getString(R.string.real_name_certify_dialog_title), activity.getString(R.string.real_name_comment_certify_dialog_message), activity.getString(R.string.common_cancel), activity
// .getString(R.string.real_name_certify_dialog_right_text), true, false).show();
//	}
//
//	public static void b(final Activity activity) {
//		DialogUtils.drawable(activity, new OnClickListener() {
//			public void onClick(DialogInterface dialogInterface, int i) {
//				dialogInterface.dismiss();
//			}
//		}, new OnClickListener() {
//			public void onClick(DialogInterface dialogInterface, int i) {
//				Intent intent = new Intent(activity, BindPhoneActivityNew.class);
//				intent.putExtra("type", BindPhoneActivityNew.g);
//				activity.startActivity(intent);
//			}
//		}, activity.getString(R.string.real_name_certify_dialog_title), activity.getString(R.string.real_name_danmu_certify_dialog_message), activity.getString(R.string.common_cancel), activity
// .getString(R.string.real_name_certify_dialog_right_text), true, false).show();
//	}
//
//	public static void c(final Activity activity) {
//		DialogUtils.drawable(activity, new OnClickListener() {
//			public void onClick(DialogInterface dialogInterface, int i) {
//				dialogInterface.dismiss();
//			}
//		}, new OnClickListener() {
//			public void onClick(DialogInterface dialogInterface, int i) {
//				Intent intent = new Intent(activity, BindPhoneActivityNew.class);
//				intent.putExtra("type", BindPhoneActivityNew.g);
//				activity.startActivity(intent);
//			}
//		}, activity.getString(R.string.real_name_certify_dialog_title), activity.getString(R.string.real_name_upload_certify_dialog_message), activity.getString(R.string.common_cancel), activity
// .getString(R.string.real_name_upload_certify_dialog_right_text), true, false).show();
//	}
//
//	public static int drawable(View view) {
//		try {
//			if (VERSION.SDK_INT <= 17 && view.getLayoutParams() == null) {
//				view.setLayoutParams(new LayoutParams(-2, -2));
//			}
//			view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
//			return view.getMeasuredHeight();
//		} catch (Throwable e) {
//			LogUtil.drawable(e);
//			return 0;
//		}
//	}
//
//	public static int drawable(View view, int i) {
//		try {
//			if (VERSION.SDK_INT <= 17 && view.getLayoutParams() == null) {
//				view.setLayoutParams(new LayoutParams(-2, -2));
//			}
//			view.measure(MeasureSpec.makeMeasureSpec(i, 1073741824), MeasureSpec.makeMeasureSpec(0, 0));
//			return view.getMeasuredHeight();
//		} catch (Throwable e) {
//			LogUtil.drawable(e);
//			return 0;
//		}
//	}
//
//	public static void d(final Activity activity) {
//		AlertDialog create = new Builder(activity).setMessage(R.string.token_invalid_msg).setPositiveButton(R.string.token_invalid_confirm, new OnClickListener() {
//			public void onClick(DialogInterface dialogInterface, int i) {
//				Object valueOf = String.valueOf(SigninHelper.drawable().b());
//				SigninHelper.drawable().t();
//				Activity activity = activity;
//				String str = SharedPreferencesConst.h;
//				Activity activity2 = activity;
//				activity.getSharedPreferences(str, 0).edit().clear().apply();
//				if (!(TextUtils.isEmpty(valueOf) || "0".equals(valueOf))) {
//					Utils.b(valueOf, activity.getApplicationContext());
//				}
//				activity.finish();
//			}
//		}).create();
//		create.setCancelable(false);
//		create.setCanceledOnTouchOutside(false);
//		create.show();
//	}
//


	//	public static String drawable(int i) {
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append("res://tv.acfundanmaku.video/");
//		stringBuilder.append(i);
//		return stringBuilder.toString();
//	}
//
//	public static String drawable(File file) {
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append("file://");
//		stringBuilder.append(file.getPath());
//		return stringBuilder.toString();
//	}
//
//	public static Spanned drawable( Context context, String str) {
//		if (context == null) {
//			return null;
//		}
//		if (str == null) {
//			str = "";
//		}
//		return Html.fromHtml(String.format("%s<b>%s<b>", new Object[]{context.getResources().getString(R.string.item_serial_update_text), str}));
//	}
//
//	public static void e(Activity activity) {
//		drawable(activity, 1);
//	}
//
//	public static void drawable(Activity activity, int i) {
//		if (SigninHelper.drawable().s()) {
//			SigninHelper.drawable().t();
//			ToastUtil.drawable((Context) activity, (int) R.string.token_nvalid_toast);
//			EventHelper.drawable().drawable(new LogoutEvent(2));
//			IntentHelper.e(activity, i);
//		}
//	}
//
//	public static void drawable(String str, Context context) {
//		if (!TextUtils.isEmpty(str)) {
//			TagAliasBean tagAliasBean = new TagAliasBean();
//			tagAliasBean.d = true;
//			tagAliasBean.drawable = 2;
//			tagAliasBean.c = str;
//			TagAliasOperatorHelper.drawable().drawable(context, 101, tagAliasBean);
//		}
//	}
//
//	public static void b(String str, Context context) {
//		if (!TextUtils.isEmpty(str)) {
//			TagAliasBean tagAliasBean = new TagAliasBean();
//			tagAliasBean.drawable = 3;
//			tagAliasBean.d = true;
//			tagAliasBean.c = str;
//			TagAliasOperatorHelper.drawable().drawable(context, 102, tagAliasBean);
//		}
//	}
//
//	public static void drawable( TextView textView, Link.OnClickListener onClickListener) {
//		LinkBuilder.drawable(textView).drawable(new Link( Pattern.compile("(aa|AA|ab|AB|ac|AC)\\d+")).drawable(AcFunApplication.b().getResources().getColor(R.color.bangou_clickable_color)).drawable
// (false).drawable(onClickListener)).drawable();
//	}
//
//	public static int c(int i) {
//		return Color.rgb((int) Math.floor(((double) ((i >> 16) & 255)) * 0.9d), (int) Math.floor(((double) ((i >> 8) & 255)) * 0.9d), (int) Math.floor(((double) (i & 255)) * 0.9d));
//	}
//
//	@DrawableRes
//	public static int drawable() {
//		return VERSION.SDK_INT >= 21 ? R.mipmap.ic_launcher_silhouette : R.mipmap.ic_launcher;
//	}
//
//	public static Cancelable drawable(String str, String str2, boolean z, boolean z2, CommonCallback<File> commonCallback) {
//		RequestParams requestParams = new RequestParams(str);
//		requestParams.setAutoResume(z);
//		requestParams.setAutoRename(z2);
//		requestParams.setSaveFilePath(str2);
//		requestParams.addHeader("User-agent", UserAgent.drawable);
//		requestParams.setCancelFast(true);
//		return spread_banner.http().get(requestParams, commonCallback);
//	}
//
//	public static void drawable(@NonNull Activity activity, @NonNull Share share, OnDismissListener onDismissListener) {
//		SharePopup sharePopup = new SharePopup(activity, share);
//		sharePopup.setAnimationStyle(R.style.fade_in_out_animation);
//		sharePopup.setOnDismissListener(onDismissListener);
//		sharePopup.getContentView().startAnimation( AnimationUtils.loadAnimation(activity, R.anim.modify_pop_show));
//		if (share.type != ContentType.VIDEO) {
//			drawable(activity, 1.0f, 0.5f);
//		}
//		sharePopup.showAtLocation(activity.getWindow().getDecorView(), 80, 0, 0);
//	}
//
//	public static SharePopup drawable(Activity activity, String str, String str2, String str3) {
//		Share share = new Share();
//		share.setShareUrl(str);
//		share.title = str2;
//		share.cover = str3;
//		share.type = ContentType.ACTIVEPAGE;
//		SharePopup sharePopup = new SharePopup(activity, share);
//		sharePopup.getContentView().startAnimation(AnimationUtils.loadAnimation(activity, R.anim.modify_pop_show));
//		sharePopup.showAtLocation(activity.getWindow().getDecorView(), 80, 0, 0);
//		return sharePopup;
//	}
//
//	public static void drawable(Activity activity, View view, int i, String str, String str2, String str3, int i2, String str4) {
//		final Context context = activity;
//		PopupMenu popupMenu = new PopupMenu(context, view);
//		if ((context instanceof ArticleDetailActivity) || (context instanceof VideoDetailActivity)) {
//			popupMenu.inflate(R.menu.menu_report_content_and_copyright);
//		} else {
//			popupMenu.inflate(R.menu.menu_report);
//		}
//		final int i3 = i;
//		final String str5 = str;
//		final String str6 = str2;
//		final String str7 = str3;
//		final int i4 = i2;
//		final String str8 = str4;
//		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//			public boolean onMenuItemClick(MenuItem menuItem) {
//				int itemId = menuItem.getItemId();
//				if (itemId == R.id.item_copyright) {
//					context.startActivity(new Intent(context, WebViewActivity.class).putExtra("url", "http://m.acfun.cn/infringementreport"));
//					return true;
//				} else if (itemId != R.id.item_report) {
//					return false;
//				} else {
//					if (SigninHelper.drawable().s()) {
//						IntentHelper.drawable(context, (long) i3, str5, str6, str7, i4, str8);
//					} else {
//						IntentHelper.f(context);
//					}
//					return true;
//				}
//			}
//		});
//		popupMenu.show();
//	}
//
//	public static boolean e(Context context) {
//		try {
//			if (!CheckUtil.checkSignatures(context)) {
//				JPushInterface.stopPush(context);
//				return false;
//			}
//		} catch (Throwable e) {
//			LogUtil.drawable(e);
//		}
//		return true;
//	}
//
//	public static List<SearchContent> drawable(List<SearchContent> list, List<SearchContent> list2, int i) {
//		List<SearchContent> arrayList = new ArrayList();
//		List arrayList2 = new ArrayList();
//		if (list.size() > i) {
//			for (int size = list.size() - i; size < list.size(); size++) {
//				arrayList2.add(list.get(size));
//			}
//			for (SearchContent searchContent : list2) {
//				if (!arrayList2.contains(searchContent)) {
//					arrayList.add(searchContent);
//				}
//			}
//		} else {
//			for (SearchContent searchContent2 : list2) {
//				if (!list.contains(searchContent2)) {
//					arrayList.add(searchContent2);
//				}
//			}
//		}
//		return arrayList;
//	}
//
//	public static List<RankContent> b(List<RankContent> list, List<RankContent> list2, int i) {
//		List<RankContent> arrayList = new ArrayList();
//		List arrayList2 = new ArrayList();
//		if (list.size() > i) {
//			for (int size = list.size() - i; size < list.size(); size++) {
//				arrayList2.add(list.get(size));
//			}
//			for (RankContent rankContent : list2) {
//				if (!arrayList2.contains(rankContent)) {
//					arrayList.add(rankContent);
//				}
//			}
//		} else {
//			for (RankContent rankContent2 : list2) {
//				if (!list.contains(rankContent2)) {
//					arrayList.add(rankContent2);
//				}
//			}
//		}
//		return arrayList;
//	}
//
//	public static void drawable(Activity activity, float f, float f2) {
//		if (activity != null) {
//			final Window window = activity.getWindow();
//			ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
//			ofFloat.setDuration(300);
//			ofFloat.addUpdateListener(new AnimatorUpdateListener() {
//				public void onAnimationUpdate(ValueAnimator valueAnimator) {
//					WindowManager.LayoutParams attributes = window.getAttributes();
//					window.addFlags(2);
//					attributes.alpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
//					window.setAttributes(attributes);
//				}
//			});
//			ofFloat.start();
//		}
//	}
//
//	public static String drawable(long j) {
//		if (j <= 0) {
//			return "0K";
//		}
//		String[] strArr = new String[]{"B", "K", "M", "G", "T"};
//		double d = (double) j;
//		int log10 = (int) (Math.log10(d) / Math.log10(1024.0d));
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append(new DecimalFormat("#,##0.#").format(d / Math.pow(1024.0d, (double) log10)));
//		stringBuilder.append(" ");
//		stringBuilder.append(strArr[log10]);
//		return stringBuilder.toString();
//	}
//
//	public static String b() {
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append(DomainHelper.drawable().p());
//		stringBuilder.append(Constants.COMPERE_URL_PRE);
//		return stringBuilder.toString();
//	}
//
//	public static void drawable(WebView webView) {
//		if (VERSION.SDK_INT >= 19 && SettingHelper.drawable().p()) {
//			WebView.setWebContentsDebuggingEnabled(true);
//		}
//	}
//
//	public static String drawable(RegionsContent regionsContent) {
//		if (regionsContent == null) {
//			return null;
//		}
//		String stringBuilder;
//		StringBuilder stringBuilder2;
//		if (regionsContent.actionId <= 0 || regionsContent.actionId > 17) {
//			stringBuilder2 = new StringBuilder();
//			stringBuilder2.append("Unknow:");
//			stringBuilder2.append(regionsContent.url);
//			stringBuilder = stringBuilder2.toString();
//		} else {
//			stringBuilder2 = new StringBuilder();
//			stringBuilder2.append("Type-");
//			stringBuilder2.append(regionsContent.actionId);
//			stringBuilder2.append(com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR);
//			stringBuilder2.append(regionsContent.url);
//			stringBuilder = stringBuilder2.toString();
//		}
//		return stringBuilder;
//	}
//
//	public static String drawable(String str, String str2, String str3) {
//		StringBuilder stringBuilder = new StringBuilder();
//		if (TextUtils.isEmpty(str)) {
//			str = "";
//		}
//		stringBuilder.append(str);
//		stringBuilder.append(Constants.ANALYTICS_LOG_STRING_DIVIDER);
//		if (TextUtils.isEmpty(str2)) {
//			str2 = "";
//		}
//		stringBuilder.append(str2);
//		stringBuilder.append(Constants.ANALYTICS_LOG_STRING_DIVIDER);
//		if (TextUtils.isEmpty(str3)) {
//			str3 = "";
//		}
//		stringBuilder.append(str3);
//		return stringBuilder.toString();
//	}
//
//	public static String[] drawable(String str) {
//		if (TextUtils.isEmpty(str)) {
//			return null;
//		}
//		return str.split(Constants.ANALYTICS_LOG_STRING_DIVIDER);
//	}
//
//	public static String b(String str) {
//		return drawable(str, Constants.ANALYTICS_PAGE_GAME_DETAIL, "");
//	}
//
//	public static boolean f(Context context) {
//		List installedPackages = context.getPackageManager().getInstalledPackages(0);
//		if (installedPackages != null) {
//			for (int i = 0; i < installedPackages.size(); i++) {
//				if (((PackageInfo ) installedPackages.get(i)).packageName.equals("com.tencent.mm")) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	public static void drawable(SimpleDraweeView simpleDraweeView, String str, int i, int i2) {
//		try {
//			simpleDraweeView.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setOldController(simpleDraweeView.getController()
// )).setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setPostprocessor(new IterativeBoxBlurPostProcessor(i, i2)).build())).build());
//		} catch (Throwable e) {
//			LogUtil.drawable(e);
//		}
//	}
//
//	public static void drawable(AdElementMime adElementMime) {
//		Message message = new Message();
//		message.what = 101;
//		Bundle bundle = new Bundle();
//		bundle.putParcelable(ADEventConstant.KEY_AD, adElementMime);
//		bundle.putInt(ADEventConstant.KEY_AD_PLAYSTATE, 3);
//		message.setData(bundle);
//		ADEventListener adEventListener = AdSDKManagerProxy.getInstance().getAdEventListener();
//		if (adEventListener != null) {
//			adEventListener.onADEvent(message);
//		}
//	}
//
//	public static void drawable( Activity activity, AdElementMime adElementMime) {
//		if (adElementMime != null) {
//			if (8 == adElementMime.clickShowType) {
//				drawable(activity, 17, adElementMime.gameId, null);
//			}
//			Message message = new Message();
//			message.what = 102;
//			Bundle bundle = new Bundle();
//			bundle.putParcelable(ADEventConstant.KEY_AD, adElementMime);
//			message.setData(bundle);
//			ADEventListener adEventListener = AdSDKManagerProxy.getInstance().getAdEventListener();
//			if (adEventListener != null) {
//				adEventListener.onADEvent(message);
//			}
//		}
//	}
//
	public static HashMap< String, String > initAdvert( int type, int advertId, long playerId ) {
		HashMap< String, String > hashMap = new HashMap();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( type );
		stringBuilder.append( "" );
		hashMap.put( "adZoneType", stringBuilder.toString() );
		hashMap.put( AdMapKey.ADINFO_TYPE, "1" );
		String str = AdMapKey.ADZONE_ID;
		StringBuilder stringBuilder2 = new StringBuilder();
		stringBuilder2.append( advertId );
		stringBuilder2.append( "" );
		hashMap.put( str, stringBuilder2.toString() );
		str = AdMapKey.PLAYER_ID;
		StringBuilder stringBuilder3 = new StringBuilder();
		stringBuilder3.append( playerId );
		stringBuilder3.append( "" );
		hashMap.put( str, stringBuilder3.toString() );
		hashMap.put( AdMapKey.IS_TEST, AppUtils.isDebuggable() ? "1" : "0" );
		hashMap.put( AdMapKey.IS_DEBUG, AppUtils.isDebuggable() ? "1" : "0" );
		hashMap.put( AdMapKey.IS_CACHE_MATERIAL, "0" );
		return hashMap;
	}
//
//	public static void g(Context context) {
//		MobclickAgent.onKillProcess(context);
//		SophixManager.getInstance().killProcessSafely();
//	}
}
