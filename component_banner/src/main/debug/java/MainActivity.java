package debug.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kento.component_banner.R;
import com.kento.component_banner.banner.BannerEntity;
import com.kento.component_banner.banner.BannerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView( R.id.bv_main )
	BannerView mBvMain;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.banner_activity_main );
		ButterKnife.bind( this );

		ArrayList< BannerEntity > list = new ArrayList<>();
		list.add( new BannerEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","" ) );
		list.add( new BannerEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","" ) );
		list.add( new BannerEntity( "https://i0.hdslb.com/bfs/archive/5b66336a4154fb5687b7a4ca852d68e4c63e124c.jpg","" ) );
		list.add( new BannerEntity( "https://i0.hdslb.com/bfs/archive/5e5902b22d98f873b2eb79c5a5c05be739b60dc2.png","" ) );
		mBvMain.instance( this );
		mBvMain.setCenter( false );
		mBvMain.delayTime( 3 );
		mBvMain.build( list );
	}
}
