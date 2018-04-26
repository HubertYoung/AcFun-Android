package com.kento.component_banner.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author:Yang
 * @date:2017-7-15 23:04:55
 * @since:v1.0
 * @desc:
 * @param:banner的ViewPager
 */
public class BannerViewPager extends ViewPager {
    private boolean scrollable = true;

    public BannerViewPager( Context context ) {
        super( context );
    }

    public BannerViewPager( Context context, AttributeSet attrs ) {
        super( context, attrs );
    }


    @Override
    public boolean onTouchEvent( MotionEvent ev ) {
        return this.scrollable && super.onTouchEvent( ev );
    }

    @Override
    public boolean onInterceptTouchEvent( MotionEvent ev ) {
        return this.scrollable && super.onInterceptTouchEvent( ev );
    }

    public void setScrollable( boolean scrollable ) {
        this.scrollable = scrollable;
    }

//    /**
//     * 起始坐标
//     */
//    private float startX;
//    private float startY;
//
//    @Override
//    public boolean dispatchTouchEvent( MotionEvent ev ) {
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getParent().getParent().getParent().requestDisallowInterceptTouchEvent( true );
//                setScrollable(true);
//                //1.记录起始坐标
//                startX = ev.getX();
//                startY = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                //2.来到新的坐标
//                float endX = ev.getX();
//                float endY = ev.getY();
//                //3.计算偏移量
//                float distanceX = endX - startX;
//                float distanceY = endY - startY;
//                //4.判断滑动方向
//                if ( Math.abs( distanceX ) - Math.abs( distanceY ) > 5 ) {
//                    //水平方向滑动
////                    2.1，当滑动到ViewPager的第0个页面，并且是从左到右滑动
//                    if ( getCurrentItem() == 1 && distanceX > 0 ) {
//                        getParent().getParent().getParent().requestDisallowInterceptTouchEvent( false );
//                        setScrollable(false);
//                    }
////                    2.2，当滑动到ViewPager的最后一个页面，并且是从右到左滑动
//                    else if ( ( getCurrentItem() == ( getAdapter().getCount() - 2 ) ) && distanceX < 0 ) {
//                        getParent().getParent().getParent().requestDisallowInterceptTouchEvent( false );
//                        setScrollable(false);
//                    }
////                    2.3，其他,中间部分
//                    else {
//                        getParent().getParent().getParent().requestDisallowInterceptTouchEvent( true );
//                        setScrollable(true);
//                    }
//                } else {
//                    //竖直方向滑动
//                    getParent().getParent().getParent().requestDisallowInterceptTouchEvent( true );
//                    setScrollable(true);
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            default:
//                break;
//        }
//        return super.dispatchTouchEvent( ev );
//    }
}
