package com.hubertyoung.component.acfunvideo.index.section.viewholder;

import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.utils.intent.IntentUtils;
import com.hubertyoung.common.entity.Regions;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/5 11:05
 * @since:V$VERSION
 * @desc:com.hubertyoung.component.acfunvideo.index.section
 */
public class BindHeaderTitleViewHolder {
	private BaseActivity mActivity;
	private HeadTitleViewHolder viewHolderTitle;

	public BindHeaderTitleViewHolder( BaseActivity activity, HeadTitleViewHolder viewHolderTitle ) {
		this.mActivity = activity;
		this.viewHolderTitle = viewHolderTitle;
	}

	public void viewBindData( int contentItemsTotal, Regions mRegions ) {
		if ( contentItemsTotal > 0 ) {
			viewHolderTitle.itemView.setVisibility( View.VISIBLE );
			if ( mRegions.bottomText != null ) {
				viewHolderTitle.leftLayout.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick( View v ) {

					}
				} );
			}
			viewHolderTitle.title.setText( mRegions.title );
			if ( TextUtils.isEmpty( mRegions.img ) ) {
				viewHolderTitle.leftIndicator.setVisibility( View.GONE );
				viewHolderTitle.leftNoPic.setVisibility( View.VISIBLE );
			} else {
				viewHolderTitle.leftIndicator.setVisibility( View.VISIBLE );
				viewHolderTitle.leftNoPic.setVisibility( View.GONE );
				ImageLoaderUtil.loadNetImage( mRegions.img, viewHolderTitle.leftIndicator );
			}
			if ( viewHolderTitle.rightMenu != null && mRegions.headerText != null && mRegions.headerText.size() > 0 ) {
				viewHolderTitle.rightMenu.setVisibility( View.VISIBLE );
				switch ( mRegions.headerText.size() ) {
					case 1:
						viewHolderTitle.rightMenuText1.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText2.setVisibility( View.GONE );
						viewHolderTitle.rightMenuText3.setVisibility( View.GONE );
						viewHolderTitle.dotOne.setVisibility( View.GONE );
						viewHolderTitle.dotTwo.setVisibility( View.GONE );
						viewHolderTitle.rightMenuText1.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 15 ) } );
						viewHolderTitle.rightMenuText1.setText( mRegions.headerText.get( 0 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText1, //
								mRegions.headerText.get( 0 ).actionId, //
								mRegions.headerText.get( 0 ).contentId, //
								mRegions, mRegions.headerText.get( 0 ).title );
						break;
					case 2:
						viewHolderTitle.rightMenuText1.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText2.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText3.setVisibility( View.GONE );
						viewHolderTitle.dotOne.setVisibility( View.VISIBLE );
						viewHolderTitle.dotTwo.setVisibility( View.GONE );
						viewHolderTitle.rightMenuText1.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 6 ) } );
						viewHolderTitle.rightMenuText2.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 6 ) } );
						viewHolderTitle.rightMenuText1.setText( mRegions.headerText.get( 1 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText1, //
								mRegions.headerText.get( 1 ).actionId, //
								mRegions.headerText.get( 1 ).contentId, //
								mRegions, mRegions.headerText.get( 1 ).title );
						viewHolderTitle.rightMenuText2.setText( mRegions.headerText.get( 0 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText2,//
								mRegions.headerText.get( 0 ).actionId,//
								mRegions.headerText.get( 0 ).contentId,//
								mRegions, mRegions.headerText.get( 0 ).title );
						break;
					case 3:
						viewHolderTitle.rightMenuText1.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText2.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText3.setVisibility( View.VISIBLE );
						viewHolderTitle.dotOne.setVisibility( View.VISIBLE );
						viewHolderTitle.dotTwo.setVisibility( View.VISIBLE );
						viewHolderTitle.rightMenuText1.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 5 ) } );
						viewHolderTitle.rightMenuText2.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 5 ) } );
						viewHolderTitle.rightMenuText3.setFilters( new InputFilter[]{ new InputFilter.LengthFilter( 5 ) } );
						viewHolderTitle.rightMenuText1.setText( mRegions.headerText.get( 2 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText1, //
								mRegions.headerText.get( 2 ).actionId,//
								mRegions.headerText.get( 2 ).contentId,//
								mRegions, mRegions.headerText.get( 2 ).title );
						viewHolderTitle.rightMenuText2.setText( mRegions.headerText.get( 1 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText2, //
								mRegions.headerText.get( 1 ).actionId, //
								mRegions.headerText.get( 1 ).contentId, //
								mRegions, mRegions.headerText.get( 1 ).title );
						viewHolderTitle.rightMenuText3.setText( mRegions.headerText.get( 0 ).title );
						rightMenuText1Click( viewHolderTitle.rightMenuText3, //
								mRegions.headerText.get( 0 ).actionId, //
								mRegions.headerText.get( 0 ).contentId,//
								mRegions, mRegions.headerText.get( 0 ).title );
						break;
				}
			}else {
				viewHolderTitle.rightMenu.setVisibility( View.GONE );
			}
		} else {
			viewHolderTitle.itemView.setVisibility( View.GONE );
		}
	}

	private void rightMenuText1Click( TextView rightMenuText1, int actionId, String contentId, Regions regions, String title ) {
		rightMenuText1.setOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				if ( regions != null ) {
//					SensorsAnalyticsUtil.e(regions2.title);
				}
				if ( actionId == 11 ) {
					IntentUtils.startActivity( mActivity, actionId, "-1", null );
				} else {
					IntentUtils.startActivity( mActivity, actionId, title, null );
				}
			}
		} );
	}
}
