package com.kento.component.basic.commonwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kento.component.basic.R;


/**
 * @author:Yang
 * @date:2018/1/9 14:41
 * @since:V1.0.0
 * @desc:ddframework.gent.common.commonwidget
 * @param:自定义EmptyView
 */
public class CustomEmptyView extends FrameLayout {

    private ImageView mEmptyImg;

    private TextView mEmptyText1;
    private ProgressBar mPbLoading;
    private TextView mEmptyText2;

    public CustomEmptyView( Context context, AttributeSet attrs ) {
        this( context, attrs, 0 );
    }

    public CustomEmptyView( Context context ) {
        this( context, null );
    }

    public CustomEmptyView( Context context, AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
        init();
    }

    public void init() {
        View view = LayoutInflater.from( getContext() )
                .inflate( R.layout.layout_empty, this );
        mEmptyImg = ( ImageView ) view.findViewById( R.id.empty_img );
        mEmptyText1 = ( TextView ) view.findViewById( R.id.empty_text1 );
        mEmptyText2 = ( TextView ) findViewById( R.id.empty_text2 );
        mPbLoading = ( ProgressBar ) view.findViewById( R.id.pb_loading );
//        mEmptyImg.setImageResource( R.drawable.icon_default_net );
        mEmptyText2.setText( "网络未连接,请检查网络设置" );
    }

    public CustomEmptyView setEmptyImage( int imgRes ) {
        mEmptyImg.setImageResource( imgRes );
        return this;
    }

    public CustomEmptyView showLoading() {
        mPbLoading.setVisibility( VISIBLE );
        mEmptyImg.setVisibility( GONE );
        mEmptyText1.setVisibility( GONE );
        mEmptyText2.setVisibility( GONE );
        return this;
    }

    public CustomEmptyView showError() {
        mPbLoading.setVisibility( GONE );
        mEmptyImg.setVisibility( VISIBLE );
        mEmptyText2.setVisibility( VISIBLE );
        mEmptyText1.setVisibility( GONE );
        return this;
    }

    public CustomEmptyView setEmptyText1( String text ) {
        mEmptyText1.setText( text );
        return this;
    }

    public CustomEmptyView setEmptyText2( String text ) {
        mEmptyText2.setText( text );
        return this;
    }

    /**
     * text 大字体
     * @return
     */
    public CustomEmptyView showEmptyText1() {
        mEmptyImg.setVisibility( VISIBLE );
        mEmptyText1.setVisibility( VISIBLE );
        mEmptyText2.setVisibility( GONE );
        return this;
    }

    /**
     * 小字体
     * @return
     */
    public CustomEmptyView showEmptyText2() {
        mEmptyImg.setVisibility( VISIBLE );
        mEmptyText1.setVisibility( GONE );
        mEmptyText2.setVisibility( VISIBLE );
        return this;
    }

    /**
     * 都有
     * @return
     */
    public CustomEmptyView showEmptyText() {
        mEmptyImg.setVisibility( VISIBLE );
        mEmptyText1.setVisibility( VISIBLE );
        mEmptyText2.setVisibility( VISIBLE );
        return this;
    }

}
