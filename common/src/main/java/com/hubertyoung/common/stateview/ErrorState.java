//package com.hubertyoung.common.stateview;
//
//import android.content.Context;
//import android.view.View;
//import android.widget.TextView;
//
//import com.hubertyoung.common.R;
//import com.hubertyoung.stateview.stateview.BaseStateControl;
//
//
///**
// */
//public class ErrorState extends BaseStateControl {
//    @Override
//    protected int onCreateView() {
//        return R.layout.widget_error_holder;
//    }
//
//    @Override
//    protected void onViewCreate( Context context, View view) {
//        TextView refreshClick = view.findViewById(R.id.refresh_click);
//        refreshClick.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick( View v ) {
//                onClickRefresh( v );
//            }
//        } );
////        ImageView errorIcon = view.findViewById(R.id.iv_error_icon);
////        if (view.getTag() != null) {
////            if (view.getTag().equals("1")) {
////                errorDesc.setText("网络不给力～_~");
////                errorIcon.setImageResource(R.mipmap.empty_network);
////            } else if (view.getTag().equals("2")) {
////                errorDesc.setText("服务器异常");
////                errorIcon.setImageResource(R.mipmap.empty_server);
////            }
////
////        }
//    }
//
//    @Override
//    public boolean isVisible() {
//        return super.isVisible();
//    }
//
//    @Override
//    protected boolean onReloadEvent( Context context, View view) {
//        return true;
//    }
//}