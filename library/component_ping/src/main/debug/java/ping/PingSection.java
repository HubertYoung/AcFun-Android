package ping;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hubertyoung.common.widget.sectioned.Section;
import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.component_ping.R;
import com.hubertyoung.ping.bean.PingNetEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/7/31 17:57
 * @since:V$VERSION
 * @desc:ping
 */
class PingSection extends Section {
	private final Context mContext;
	private List< PingNetEntity > data;

	public PingSection( Context context ) {
		super( new SectionParameters.Builder( R.layout.ping_item_body ).build() );
		this.mContext = context;
	}

	@Override
	public int getContentItemsTotal() {
		return data == null ? 0 : data.size();
	}

	@Override
	public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
		return new ViewHolder( view );
	}

	@Override
	public void onBindItemViewHolder( RecyclerView.ViewHolder holder, int position ) {
		PingNetEntity pingNetEntity = data.get( position );
		ViewHolder viewHolder = ( ViewHolder ) holder;
		viewHolder.mTvPingHost.setText( pingNetEntity.host );
		viewHolder.mTvPingIp.setText( pingNetEntity.ip );
		viewHolder.mTvPingHostDelay.setText( pingNetEntity.pingTime );
		viewHolder.mTvPingIpDelay.setText( pingNetEntity.pingWtime + "" );
		viewHolder.mTvPingContent.setText( pingNetEntity.resultBuffer.toString() );
	}

	public void addData( PingNetEntity pingNetEntity ) {
		if ( data == null ) {
			data = new ArrayList<>();
		}
		data.add( pingNetEntity );
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		View view;
		TextView mTvPingHost;
		TextView mTvPingIp;
		TextView mTvPingHostDelay;
		TextView mTvPingIpDelay;
		TextView mTvPingContent;

		ViewHolder( View view ) {
			super( view );
			this.view = view;
			this.mTvPingHost = ( TextView ) view.findViewById( R.id.tv_ping_host );
			this.mTvPingIp = ( TextView ) view.findViewById( R.id.tv_ping_ip );
			this.mTvPingHostDelay = ( TextView ) view.findViewById( R.id.tv_ping_host_delay );
			this.mTvPingIpDelay = ( TextView ) view.findViewById( R.id.tv_ping_ip_delay );
			this.mTvPingContent = ( TextView ) view.findViewById( R.id.tv_ping_content );
		}
	}
}
