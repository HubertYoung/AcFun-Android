//package com.hubertyoung.component.acfunvideo.bangumidetail;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.hubertyoung.common.entity.NetVideo;
//import com.hubertyoung.common.entity.Video;
//import com.hubertyoung.common.utils.SigninHelper;
//import com.hubertyoung.common.utils.display.ToastUtil;
//import com.hubertyoung.component.acfunvideo.entity.BangumiDetailBean;
//import com.hubertyoung.component_acfunvideo.R;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//public class BangumiDetailHelper {
//    public static final int a = 22;
//    public static final int b = 33;
//
//    public static String a() {
//        return "";
//    }
//
////    public static NetVideo a( int i) {
////        RecordVideo recordVideo = (RecordVideo) DBHelper.a().a(RecordVideo.class, i);
////        if (recordVideo == null) {
////            return null;
////        }
////        return recordVideo.convertToNetVideo();
////    }
//
////    public static void a(int i, long j, Video video) {
////        DBHelper.a().a(RecordVideo.parse(i, j, video));
////    }
////
////    public static void a(int i, NetVideo netVideo) {
////        if (netVideo != null) {
////            DBHelper.a().a(RecordVideo.parse(i, netVideo));
////        }
////    }
//
//    public static void a(Activity activity, String str, NetVideo netVideo, BangumiDetailBean bangumiDetailBean) {
//        if (activity != null && str != null && netVideo != null && bangumiDetailBean != null) {
//            switch (netVideo.mVisibleLevel) {
//                case -1:
//                    b(activity, str, netVideo, bangumiDetailBean);
//                    break;
////                case 0:
////                    if (!SigninHelper.a().u()) {
////                        a(activity, 22);
////                        break;
////                    } else {
////                        b(activity, str, netVideo, bangumiDetailBean);
////                        break;
////                    }
////                case 1:
////                    if (SigninHelper.a().u()) {
////                        if (!SigninHelper.a().d()) {
////                            a(activity, 33);
////                            break;
////                        } else {
////                            b(activity, str, netVideo, bangumiDetailBean);
////                            break;
////                        }
////                    }
////                    a(activity, 22);
////                    break;
//                default:
//                    ToastUtil.showWarning(AcFunApplication.b(), (int) R.string.common_cant_play);
//                    break;
//            }
//        }
//    }
//
//    private static void a(final Activity activity, int i) {
//        final PopupWindow popupWindow = new PopupWindow(-1, -1);
//        View inflate = LayoutInflater.from(activity).inflate(R.layout.reminder_login_or_gotoanswer, null);
//        ImageView imageView = (ImageView) inflate.findViewById(R.id.to_login);
//        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.to_answer);
//        ImageView imageView3 = (ImageView) inflate.findViewById(R.id.reminder_close);
//        TextView textView = (TextView) inflate.findViewById(R.id.reminder_text);
//        if (i == 22) {
//            imageView.setVisibility(0);
//            imageView2.setVisibility(4);
//            textView.setText(R.string.activity_player_member_only_tip);
//        } else if (i == 33) {
//            imageView.setVisibility(4);
//            imageView2.setVisibility(0);
//            textView.setText(R.string.activity_player_formal_member_only_tip);
//        }
//        imageView.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                IntentHelper.a(activity);
//                popupWindow.dismiss();
//            }
//        });
//        imageView2.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                IntentHelper.a(activity, QuestionActivity.class);
//                popupWindow.dismiss();
//            }
//        });
//        imageView3.setOnClickListener(new OnClickListener() {
//            public void onClick(View view) {
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow.setContentView(inflate);
//        popupWindow.setOutsideTouchable(false);
//        popupWindow.setAnimationStyle(R.style.fade_in_out_animation);
//        popupWindow.showAtLocation(activity.getWindow().getDecorView(), 80, 0, 0);
//    }
//
//    private static void b(Activity activity, String str, NetVideo netVideo, BangumiDetailBean bangumiDetailBean) {
//        if (netVideo != null && bangumiDetailBean != null) {
//            String str2 = bangumiDetailBean.title;
//            String str3 = bangumiDetailBean.intro;
//            String str4 = bangumiDetailBean.share.shareUrl;
//            Bundle bundle = new Bundle();
//			Video convertToVideo = netVideo.convertToVideo();
//            convertToVideo.setUrl(netVideo.mUrlMobile);
//            bundle.putSerializable("video", convertToVideo);
//            if (netVideo.mMediaType == 1) {
//                a(activity, str, netVideo, str2, str3, str4, bangumiDetailBean.parentChannelId, bangumiDetailBean.channelId);
//            } else if (netVideo.mMediaType == 3) {
//                a(activity, str, netVideo);
//            }
//            a(str, bangumiDetailBean);
//        }
//    }
//
//    private static void a(String str, BangumiDetailBean bangumiDetailBean) {
//        HistoryHelper.a(str, bangumiDetailBean.title, bangumiDetailBean.titleImage, bangumiDetailBean.intro);
//    }
//
//    private static void a(Activity activity, String str, NetVideo netVideo) {
//        a(Integer.valueOf(str).intValue(), netVideo);
//        Bundle bundle = new Bundle();
//        Serializable convertToVideo = netVideo.convertToVideo();
//        convertToVideo.setUrl(netVideo.mUrlMobile);
//        bundle.putSerializable("video", convertToVideo);
//        IntentHelper.a(activity, PlayerWebActivity.class, bundle);
//    }
//
//    private static void a(Activity activity, String str, NetVideo netVideo, String str2, String str3, String str4, int i, int i2) {
//        Video convertToVideo = netVideo.convertToVideo();
//        Bundle bundle = new Bundle();
//        bundle.putInt(AcFunPlayerActivity.h, netVideo.mMediaType);
//        bundle.putString("videoTitle", str2);
//        bundle.putString(AcFunPlayerActivity.k, str4);
//        IntentHelper.a(activity, convertToVideo, i, i2, Integer.valueOf(str).intValue(), 1, str3, bundle);
//    }
//
//    public static BangumiDetailBean b() {
//        int i;
//        StringBuilder stringBuilder;
//        BangumiDetailBean bangumiDetailBean = new BangumiDetailBean();
//        bangumiDetailBean.id = "5035666";
//        bangumiDetailBean.title = "title你懂的";
//        bangumiDetailBean.intro = "不可描述，的---番剧描述";
//        bangumiDetailBean.titleImage = "http://imgs.aixifan.com/cms/2017_11_01/1509531698511.jpg";
//        bangumiDetailBean.viewsCount = "1222";
//        bangumiDetailBean.favouriteCount = "1222";
//        bangumiDetailBean.updateContent = "1222";
//        bangumiDetailBean.commentCount = "1222";
//        ShareBean shareBean = new ShareBean();
//        shareBean.shareUrl = "http://m.acfun.cn/a/aa5035666";
//        bangumiDetailBean.share = shareBean;
//        bangumiDetailBean.isCanDownload = true;
//        bangumiDetailBean.isCanComment = true;
//        bangumiDetailBean.isCanPlay = true;
//        bangumiDetailBean.targetUrl = "http://v.youku.com/v_show/id_XMzExMzgzMDMwNA==.html?spm=a2hww.20027244.m_250003.5~5~5~5~A";
//        List arrayList = new ArrayList();
//        for (i = 0; i < 6; i++) {
//            RelatedBangumisBean relatedBangumisBean = new RelatedBangumisBean();
//            relatedBangumisBean.id = String.valueOf(Integer.valueOf(bangumiDetailBean.id).intValue() + i);
//            stringBuilder = new StringBuilder();
//            stringBuilder.append("第");
//            stringBuilder.append(i);
//            stringBuilder.append("季");
//            relatedBangumisBean.name = stringBuilder.toString();
//            arrayList.add(relatedBangumisBean);
//        }
//        bangumiDetailBean.relatedBangumis = arrayList;
//        arrayList = new ArrayList();
//        for (i = 0; i < 10; i++) {
//            VideoGroupTitleBean videoGroupTitleBean = new VideoGroupTitleBean();
//            videoGroupTitleBean.id = String.valueOf(1480008 + i);
//            stringBuilder = new StringBuilder();
//            stringBuilder.append("剧集分组——");
//            stringBuilder.append(i);
//            videoGroupTitleBean.name = stringBuilder.toString();
//            arrayList.add(videoGroupTitleBean);
//        }
//        bangumiDetailBean.videoGroupTitle = arrayList;
//        arrayList = new ArrayList();
//        for (i = 0; i < 10; i++) {
//            List arrayList2 = new ArrayList();
//            for (int i2 = 0; i2 < 10; i2++) {
//                arrayList2.add(b(i2));
//            }
//            arrayList.add(arrayList2);
//        }
//        bangumiDetailBean.videoGroupContent = arrayList;
//        arrayList = new ArrayList();
//        for (int i3 = 0; i3 < 50; i3++) {
//            Tag tag = new Tag();
//            StringBuilder stringBuilder2 = new StringBuilder();
//            stringBuilder2.append("aaa____aaa____");
//            stringBuilder2.append(i3);
//            tag.name = stringBuilder2.toString();
//            tag.id = 68890 + i3;
//            arrayList.add(tag);
//        }
//        bangumiDetailBean.tags = arrayList;
//        return bangumiDetailBean;
//    }
//
//    private static NetVideo b(int i) {
//        NetVideo netVideo = new NetVideo();
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("aaaaaaa__");
//        stringBuilder.append(i);
//        netVideo.mTitle = stringBuilder.toString();
//        if (i == 0) {
//            netVideo.mVisibleLevel = -1;
//        }
//        if (i == 1) {
//            netVideo.mVisibleLevel = 0;
//        }
//        if (i == 2) {
//            netVideo.mVisibleLevel = 1;
//        }
//        netVideo.mMediaType = 1;
//        netVideo.mVideoId = 1123123;
//        return netVideo;
//    }
//}
