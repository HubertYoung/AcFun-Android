package dynamicsoreview;

import android.app.Activity;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hubertyoung.common.image.fresco.ImageLoaderUtil;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.component_dynamicsoreview.R;

import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <br>
 * <p>
 *
 * @author:Yang
 * @date:2018/2/6 下午5:08
 * @since:V$VERSION
 * @desc:com.gent.cardsharelife.ui.home.section
 */
public class DynamicSection extends Section {
	private Activity activity;
	private List< DynamicEntity > data;

	public DynamicSection( Activity activity, List< DynamicEntity > data ) {
		super( new SectionParameters.Builder( R.layout.dynamicsoreview_item_menu_body ).build() );
		this.activity = activity;
		this.data = data;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null || data.isEmpty() ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new CardShareMenuBodyViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		CardShareMenuBodyViewHolder viewHolder = ( CardShareMenuBodyViewHolder ) holder;
		DynamicEntity bean = data.get( position );
		ImageLoaderUtil.loadNetImage( bean.Pic,viewHolder.mIvMenu );
		viewHolder.mTvMenu.setText( bean.title );
		if ( mOnItemClickListener != null ) {
			viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
				@Override
				public void onClick( View v ) {
					mOnItemClickListener.onitemClick( v, bean.Pic + "", bean.title );
				}
			} );
		}
	}

	public interface OnItemClickListener {
		void onitemClick( View v, String Pic, String title );
	}

	private OnItemClickListener mOnItemClickListener;

	public void setOnItemClickListener( OnItemClickListener onItemClickListener ) {
		mOnItemClickListener = onItemClickListener;
	}

	static class CardShareMenuBodyViewHolder extends RecyclerView.ViewHolder {
		SimpleDraweeView mIvMenu;
		AppCompatTextView mTvMenu;

		CardShareMenuBodyViewHolder( View view ) {
			super( view );
			mIvMenu = view.findViewById( R.id.iv_menu );
			mTvMenu = view.findViewById( R.id.tv_menu );
		}
	}
}
