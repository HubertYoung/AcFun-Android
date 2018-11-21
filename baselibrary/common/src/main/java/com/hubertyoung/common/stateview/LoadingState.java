//package com.hubertyoung.common.stateview;
//
//
//import android.content.Context;
//import android.view.View;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.hubertyoung.common.R;
//import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
//import com.hubertyoung.stateview.stateview.BaseStateControl;
//
///**
// */
//public class LoadingState extends BaseStateControl {
//    @Override
//    protected int onCreateView() {
//        return R.layout.widget_loading_holder;
//    }
//    @Override
//    protected void onViewCreate( Context context, View view) {
//        SimpleDraweeView simpleDraweeView = view.findViewById(R.id.widget_loading_holder_gif);
//        ImageLoaderUtil.loadResourceImage( R.drawable.image_loading_holder, simpleDraweeView );
//    }
//    @Override
//    public boolean isVisible() {
//        return super.isVisible();
//    }
//
//}
