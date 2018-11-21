package com.hubertyoung.common.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hubertyoung.common.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/10/18 13:16
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.widget
 */
public class DropDownOptionList extends RelativeLayout {
	private View currentListBindingView;
	private DropDownAdapter dropDownAdapter;
	public ViewGroup dropDownLayout;
	private HeaderItemClickListener headerItemClickListener;
	private View headerView;
	private Map< Integer, List< String > > listContentsMap;
	private OnSelectListener onSelectListener;
	public ListView selectList;
	private SparseIntArray selectedContentsMap;
	private boolean toggle = false;
	private OnToggleListener toggleListener;

	/* compiled from: unknown */
	private class DropDownAdapter extends BaseAdapter {
		private Context context;
		private List< String > options;
		private int selectedIndex;

		@Override
		public long getItemId( int position ) {
			return position;
		}

		public DropDownAdapter( Context context ) {
			this.context = context;
		}

		public List< String > getOptions() {
			return options;
		}

		public void setOptions( List< String > list ) {
			options = list;
		}

		public int getSelectedIndex() {
			return selectedIndex;
		}

		public void setSelectedIndex( int position ) {
			selectedIndex = position;
		}

		public int getCount() {
			return options == null ? 0 : options.size();
		}

		public Object getItem( int position ) {
			return options == null ? null : options.get( position );
		}

		public View getView( int position, View view, ViewGroup viewGroup ) {
			if ( view == null ) {
				view = LayoutInflater.from( context ).inflate( R.layout.item_drop_down_list, viewGroup, false );
				view.setTag( new ViewHolder( view ) );
			}
			ViewHolder viewHolder = ( ViewHolder ) view.getTag();
			viewHolder.contentText.setText( ( String ) options.get( position ) );
			if ( position == selectedIndex ) {
				viewHolder.contentText.setTextColor( context.getResources().getColor( R.color.them_color ) );
				viewHolder.selectImage.setVisibility( VISIBLE );
			} else {
				viewHolder.contentText.setTextColor( context.getResources().getColor( R.color.secondary_text ) );
				viewHolder.selectImage.setVisibility( GONE );
			}
			return view;
		}
	}

	/* compiled from: unknown */
	private class HeaderItemClickListener implements OnClickListener {
		@Override
		public void onClick( View view ) {
			List list = listContentsMap.get( Integer.valueOf( view.getId() ) );
			if ( list != null ) {
				currentListBindingView = view;
				dropDownAdapter.setOptions( list );
				dropDownAdapter.setSelectedIndex( selectedContentsMap.get( view.getId() ) );
				toggleList();
			}
		}
	}

	/* compiled from: unknown */
	public interface OnSelectListener {
		void onSelect( AdapterView< ? > adapterView, View view, int position, long id );
	}

	/* compiled from: unknown */
	public interface OnToggleListener {
		void onToggle( boolean isToggle );
	}

	/* compiled from: unknown */
	static class ViewHolder {
		public TextView contentText;
		public ImageView selectImage;

		public ViewHolder( View view ) {
			contentText = view.findViewById( R.id.content_text );
			selectImage = view.findViewById( R.id.select_image );
		}
	}

	public DropDownOptionList( Context context ) {
		super( context );
		initView( context );
	}

	public DropDownOptionList( Context context, AttributeSet attributeSet ) {
		super( context, attributeSet );
		initView( context );
	}

	public DropDownOptionList( Context context, AttributeSet attributeSet, int i ) {
		super( context, attributeSet, i );
		initView( context );
	}

	private void initView( Context context ) {
		View view = LayoutInflater.from( context ).inflate( R.layout.widget_drop_down_list, null );
		dropDownLayout = view.findViewById( R.id.list_layout );
		selectList = view.findViewById( R.id.select_list );
		dropDownAdapter = new DropDownAdapter( context );
		selectList.setAdapter( dropDownAdapter );
		listContentsMap = new HashMap();
		selectedContentsMap = new SparseIntArray();
		headerItemClickListener = new HeaderItemClickListener();
		selectList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick( AdapterView< ? > parent, View view, int position, long id ) {
				dropDownAdapter.setSelectedIndex( position );
				toggleList();
				if ( currentListBindingView != null ) {
					selectedContentsMap.put( currentListBindingView.getId(), position );
				}
				if ( onSelectListener != null ) {
					onSelectListener.onSelect( parent, view, position, id );
				}
			}
		} );
	}

	public View getHeaderView() {
		return this.headerView;
	}

	public void setHeaderView( View view ) {
		this.headerView = view;
		addView( view, 0, new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT ) );
		RelativeLayout.LayoutParams layoutParams = new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT );
		layoutParams.addRule( 3, view.getId() );
		dropDownLayout.setLayoutParams( layoutParams );
		addView( dropDownLayout, 1, layoutParams );
		invalidate();
	}

	public void setListContents( View view, List< String > list ) {
		view.setOnClickListener( headerItemClickListener );
		listContentsMap.put( Integer.valueOf( view.getId() ), list );
		selectedContentsMap.put( view.getId(), 0 );
		dropDownAdapter.setOptions( list );
		dropDownAdapter.notifyDataSetChanged();
	}

	public int getSelected() {
		return dropDownAdapter.getSelectedIndex();
	}

	public void setSelected( int i ) {
		dropDownAdapter.setSelectedIndex( i );
		dropDownAdapter.notifyDataSetChanged();
	}

	public OnToggleListener getToggleListener() {
		return toggleListener;
	}

	public void setToggleListener( OnToggleListener onToggleListener ) {
		toggleListener = onToggleListener;
	}

	public void toggleList() {
		toggle = !toggle;
		selectList.setVisibility( toggle ? VISIBLE : GONE );
		dropDownAdapter.notifyDataSetChanged();
		if ( toggleListener != null ) {
			toggleListener.onToggle( toggle );
		}
	}

	public boolean isToggle() {
		return this.toggle;
	}

	public View getCurrentListBindingView() {
		return this.currentListBindingView;
	}

	public void setSelected( int id, int position ) {
		selectedContentsMap.put( id, position );
	}

	public int getSelected( int i ) {
		return selectedContentsMap.get( i );
	}

	public OnSelectListener getOnSelectListener() {
		return onSelectListener;
	}

	public void setOnSelectListener( OnSelectListener onSelectListener ) {
		this.onSelectListener = onSelectListener;
	}
}
