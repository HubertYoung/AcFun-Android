package com.hubertyoung.component_gamecenter.gamecenter.section;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.entity.Regions;
import com.hubertyoung.common.entity.RegionsContent;
import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.common.widget.textswitcher.AdvTextSwitcher;
import com.hubertyoung.common.widget.textswitcher.Switcher;
import com.hubertyoung.component_gamecenter.R;

import java.util.ArrayList;

/**
 * @desc:
 * @author:HubertYoung
 * @date 2018/12/4 15:05
 * @since:
 * @see GameCenterNewsDownUpSection
 */
public class GameCenterNewsDownUpSection extends StatelessSection {
	private BaseActivity mActivity;
	private Regions regions;
	private Switcher mSwitcher;

	public GameCenterNewsDownUpSection( BaseActivity activity ) {
		super( SectionParameters.builder().itemResourceId( R.layout.item_region_game_center_rolling_news )//
//				.headerResourceId( R.layout.widget_region_header_text )//
//				.footerResourceId( R.layout.widget_region_bottom_menu )//
				.build() );
		this.mActivity = activity;
	}

	public int getSpanSizeLookup( int position ) {
		return 6;
	}

	@Override
	public int getContentItemsTotal() {
		if ( regions != null && !regions.contents.isEmpty() ) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new NewsDownUpViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		NewsDownUpViewHolder viewHolder = ( NewsDownUpViewHolder ) holder;
		ArrayList< String > strList = new ArrayList<>( regions.contents.size() );
		for (RegionsContent content : regions.contents) {
			strList.add( content.title );
		}
		viewHolder.text.setTexts( strList.toArray( new String[ strList.size() ] ) );
		if ( mSwitcher == null ) {
			mSwitcher = new Switcher();
			mSwitcher.setDuration( 3000 );
		}
		mSwitcher.attach( viewHolder.text ).start();
		viewHolder.text.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View v ) {
				ToastUtil.showWarning( strList.toString() );
			}
		} );
	}

	public void setRegions( Regions regions ) {
		this.regions = regions;
	}

	static class NewsDownUpViewHolder extends RecyclerView.ViewHolder {
		AdvTextSwitcher text;

		NewsDownUpViewHolder( View view ) {
			super( view );
			this.text = ( AdvTextSwitcher ) view.findViewById( R.id.item_game_center_news_text );
		}
	}
}
