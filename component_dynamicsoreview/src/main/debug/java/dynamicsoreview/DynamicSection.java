package dynamicsoreview;

import android.app.Activity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.component_dynamicsoreview.R;
import com.hubertyoung.common.ImageLoaderUtils;
import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
		ImageLoaderUtils.getInstance().display( activity, viewHolder.mIvMenu, bean.Pic );
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
		@BindView( R.id.iv_menu )
		AppCompatImageView mIvMenu;
		@BindView( R.id.tv_menu )
		AppCompatTextView mTvMenu;

		CardShareMenuBodyViewHolder( View view ) {
			super( view );
			ButterKnife.bind( this, view );
		}
	}
}
