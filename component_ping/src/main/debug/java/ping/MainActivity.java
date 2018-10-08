package ping;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hubertyoung.common.widget.decoration.HorizontalDividerItemDecoration;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.component_ping.R;
import com.hubertyoung.ping.PingNet;
import com.hubertyoung.ping.bean.PingNetEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	/**
	 * 开始测试
	 */
	private Button mBtnPingStart;
	/**
	 * 复制测试结果
	 */
	private Button mBtnPingCopy;
	/**
	 * TextView
	 */
	private TextView mTvPing;
	private RecyclerView mRvPing;
	private SectionedRecyclerViewAdapter mAdapter;
	private PingSection mPingSection;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.ping_activity_main );
		initView();
		initData();
	}

	private void initData() {
//		{www.bilibili.com/120.92.82.179: lookup=20, connect=7}
		List< PingNetEntity > pingEntities = new ArrayList<>();
		pingEntities.add( new PingNetEntity( "www.bilibili.com", "120.92.82.179", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "interface.bilibili.com", "120.92.113.99", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "comment.bilibili.com", "61.240.155.82", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "api.bilibili.com", "27.221.61.100", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "app.bilibili.com", "120.92.113.99", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "passport.bilibili.com", "27.221.61.100", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "account.bilibili.com", "120.92.113.99", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "bangumi.bilibili.com", "27.221.61.109", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "live.bilibili.com", "120.92.83.126", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "elec.bilibili.com", "27.221.61.100", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "pay.bilibili.com", "27.221.61.100", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "secure.bilibili.com", "101.75.240.7", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "s.search.bilibili.com", "120.92.113.99", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "chat.bilibili.com", "120.92.112.150", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "api.biligame.com", "120.92.82.179", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "apigame.bilibili.com", "101.75.240.7", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "www.im9.com", "101.28.133.102", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "apigame.bilibili.com", "101.75.240.7", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "acg.tv", "120.24.248.50", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "static.hdslb.com", "123.125.7.219", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "i0.hdslb.com", "123.125.7.221", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "i1.hdslb.com", "123.125.7.234", 3, 5, new StringBuffer() ) );
		pingEntities.add( new PingNetEntity( "i2.hdslb.com", "123.125.7.217", 3, 5, new StringBuffer() ) );
		Observable.fromIterable( pingEntities ).map( new Function< PingNetEntity, PingNetEntity >() {
			@Override
			public PingNetEntity apply( PingNetEntity pingEntity ) throws Exception {
				PingNetEntity ping = PingNet.ping( pingEntity );
				return ping;
			}
		} ).subscribeOn( Schedulers.io() )//
				.observeOn( AndroidSchedulers.mainThread() )//
				.subscribe( new Consumer< PingNetEntity >() {
					@Override
					public void accept( PingNetEntity pingNetEntity ) throws Exception {
						mPingSection.addData( pingNetEntity );
						mAdapter.notifyDataSetChanged();
					}
				}, new Consumer< Throwable >() {
					@Override
					public void accept( Throwable throwable ) throws Exception {
						Log.e( "TAG", "" );
					}
				} );
	}

	private void initView() {
		mBtnPingStart = ( Button ) findViewById( R.id.btn_ping_start );
		mBtnPingStart.setOnClickListener( this );
		mBtnPingCopy = ( Button ) findViewById( R.id.btn_ping_copy );
		mBtnPingCopy.setOnClickListener( this );
		mTvPing = ( TextView ) findViewById( R.id.tv_ping );
		mRvPing = ( RecyclerView ) findViewById( R.id.rv_ping );
		LinearLayoutManager layoutManager = new LinearLayoutManager( this );
		mRvPing.setHasFixedSize( true );
		mRvPing.setLayoutManager( layoutManager );
		mAdapter = new SectionedRecyclerViewAdapter(null);

		mPingSection = new PingSection( this );
		mAdapter.addSection( mPingSection );
		mRvPing.setAdapter( mAdapter );
		mRvPing.addItemDecoration( new HorizontalDividerItemDecoration.Builder( this ).colorResId( R.color.line_bg ).size( 1 ).showLastDivider().build() );
	}

	@Override
	public void onClick( View v ) {
		switch ( v.getId() ) {
			default:
				break;
			case R.id.btn_ping_start:
				break;
			case R.id.btn_ping_copy:
				break;
		}
	}
}
