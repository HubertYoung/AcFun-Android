package com.hubertyoung.component_banner.banner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

/**
 * Created by JIUU on 16/8/7 21:18
 * <p/>
 * Banner适配器
 */
public class BannerAdapter extends PagerAdapter {

    private List< View > mList;

    private int pos;

    private ViewPagerOnItemClickListener mViewPagerOnItemClickListener;

    public void setmViewPagerOnItemClickListener( ViewPagerOnItemClickListener mViewPagerOnItemClickListener ) {

        this.mViewPagerOnItemClickListener = mViewPagerOnItemClickListener;
    }

    BannerAdapter( List< View > list ) {
        if ( this.mList != null ) this.mList.clear();
        if ( list != null ) this.mList = list;
    }

    @Override
    public int getCount() {
//        if ( mList.size() == 1 ) {
//            return 1;
//        } else {
            return mList == null ? 0 : mList.size();
//        }
    }

    @Override
    public boolean isViewFromObject( View view, Object object ) {
        return view == object;
    }

    @Override
    public Object instantiateItem( ViewGroup container, int position ) {

        //对ViewPager页号求模取出View列表中要显示的项
//        position %= mList.size();
//        if ( position < 0 ) {
//            position = mList.size() + position;
//        }
        View v = mList.get( position );
//        pos = position;
//        if ( v instanceof ImageView ) ( ( ImageView ) v ).setScaleType( ImageView.ScaleType.FIT_XY );
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = v.getParent();
        if ( vp != null ) {
            ViewGroup parent = ( ViewGroup ) vp;
            parent.removeView( v );
        }
        v.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                if ( mViewPagerOnItemClickListener != null ) {
                    mViewPagerOnItemClickListener.onItemClick();
                }
            }
        } );

        container.addView( v );
        return v;
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object ) {
        container.removeView((View) object);
    }


    public interface ViewPagerOnItemClickListener {

        void onItemClick();
    }
}
