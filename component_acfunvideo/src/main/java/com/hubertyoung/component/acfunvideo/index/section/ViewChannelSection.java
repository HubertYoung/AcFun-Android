package com.hubertyoung.component.acfunvideo.index.section;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.display.DisplayUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;
import com.hubertyoung.component_acfunvideo.R;

import java.util.List;

import android.support.v7.widget.RecyclerView;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/5 11:01
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.section
 */
public class ViewChannelSection extends StatelessSection {
    private Context mActivity;
    private List< ChannelOperate.ChannelData > data;

    public ViewChannelSection( Context activity ) {
        super( SectionParameters.builder()
                .itemResourceId( R.layout.item_channel )//
                .headerResourceId( R.layout.item_channel_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
                .build() );
        this.mActivity = activity;
    }

    @Override
    public int getContentItemsTotal() {
        if ( data != null ) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getSpanSizeLookup( int position ) {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
        return new ViewChannelHolder( view );
    }

    @Override
    public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
        ViewChannelHolder viewChannelHolder = ( ViewChannelHolder ) holder;
        ChannelOperate.ChannelData channelData = data.get( position );
        if ( !( data == null || TextUtils.isEmpty( channelData.img ) ) ) {
            ImageLoaderUtil.loadNetImage( channelData.img, viewChannelHolder.mImageView, false, null );
        }
        if ( position % 4 == 0 ) {
            viewChannelHolder.mRoot.setPadding( DisplayUtil.dip2px( 10 ),//
                    viewChannelHolder.mRoot.getPaddingTop(),//
                    DisplayUtil.dip2px( 5 ),//
                    viewChannelHolder.mRoot.getPaddingBottom() );
        } else if ( position % 4 == 1 || position % 4 == 2 ) {
            viewChannelHolder.mRoot.setPadding( DisplayUtil.dip2px( 5 ),//
                    viewChannelHolder.mRoot.getPaddingTop(),//
                    DisplayUtil.dip2px( 5 ),//
                    viewChannelHolder.mRoot.getPaddingBottom() );
        } else if ( position % 4 == 3 ) {
            viewChannelHolder.mRoot.setPadding( DisplayUtil.dip2px( 5 ),//
                    viewChannelHolder.mRoot.getPaddingTop(),//
                    DisplayUtil.dip2px( 10 ),//
                    viewChannelHolder.mRoot.getPaddingBottom() );
        }
        viewChannelHolder.mTextView.setText( channelData.name );
    }

    public void setChannelList( List< ChannelOperate.ChannelData > channelList ) {
        this.data = channelList;
    }

    static class ViewChannelHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView mImageView;
        public TextView mTextView;
        public LinearLayout mRoot;

        ViewChannelHolder( View view ) {
            super( view );
            mRoot = ( LinearLayout ) view.findViewById( R.id.root );
            mImageView = ( SimpleDraweeView ) view.findViewById( R.id.item_channel_img );
            mTextView = ( TextView ) view.findViewById( R.id.item_channel_text );
            int width = ( int ) ( DisplayUtil.getScreenWidth( view.getContext() ) * 0.106666f );
            LinearLayout.LayoutParams layoutParams = ( LinearLayout.LayoutParams ) mImageView.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = width;
            mImageView.setLayoutParams( layoutParams );
        }
    }
}
