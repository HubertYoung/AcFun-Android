package dynamicsoreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.component_dynamicsoreview.R;
import com.hubertyoung.component_dynamicsoreview.dynamicsoreview.DynamicSoreView;
import com.hubertyoung.component_dynamicsoreview.dynamicsoreview.Interface.IDynamicSore;
import com.hubertyoung.common.utils.ToastUtil;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView( R.id.ds_main )
	DynamicSoreView mDsMain;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.dynamicsoreview_activity_main );
		ButterKnife.bind( this );


		ArrayList< DynamicEntity > list = new ArrayList<>();
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","11" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","12" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5b66336a4154fb5687b7a4ca852d68e4c63e124c.jpg","13" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5b66336a4154fb5687b7a4ca852d68e4c63e124c.jpg","14" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5b66336a4154fb5687b7a4ca852d68e4c63e124c.jpg","21" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","22" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5b66336a4154fb5687b7a4ca852d68e4c63e124c.jpg","23" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5b66336a4154fb5687b7a4ca852d68e4c63e124c.jpg","24" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","11" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","11" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","12" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5b66336a4154fb5687b7a4ca852d68e4c63e124c.jpg","13" ) );
		list.add( new DynamicEntity( "https://i0.hdslb.com/bfs/archive/5b66336a4154fb5687b7a4ca852d68e4c63e124c.jpg","14" ) );

		mDsMain.setiDynamicSore( new IDynamicSore< DynamicEntity >() {

			@Override
			public void setGridView( View view, int type, List< DynamicEntity > data ) {
				GridLayoutManager manager = new GridLayoutManager( MainActivity.this, 4 );
				mDsMain.setNumColumns( manager );

				RecyclerView rvBody = ( RecyclerView ) view.findViewById( R.id.rv_body );
				rvBody.setPadding( AutoUtils.getPercentHeightSizeBigger( 30 ), AutoUtils.getPercentHeightSizeBigger( 20 ), AutoUtils.getPercentHeightSizeBigger( 30 ), AutoUtils.getPercentHeightSizeBigger( 20 ) );
				rvBody.setHasFixedSize( true );

				rvBody.setNestedScrollingEnabled( false );
				SectionedRecyclerViewAdapter menuAdapter = new SectionedRecyclerViewAdapter();
				DynamicSection cardShareMenuBodySection = new DynamicSection( MainActivity.this, data );
				menuAdapter.addSection( cardShareMenuBodySection );
				rvBody.setAdapter( menuAdapter );
				rvBody.setLayoutManager( manager );
				menuAdapter.notifyDataSetChanged();
				cardShareMenuBodySection.setOnItemClickListener( new DynamicSection.OnItemClickListener() {
					@Override
					public void onitemClick( View v, String Pic, String title ) {
						ToastUtil.showSuccess( title );
					}
				} );
			}
		} );
		mDsMain.setGridView( R.layout.dynamicsoreview_item_recyclerview_body )
				.init( list );

	}
}
