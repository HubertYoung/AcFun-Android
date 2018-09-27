package filter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.hubertyoung.component_filter.R;
import com.hubertyoung.component_filter.filter.DropDownMenu;
import com.hubertyoung.component_filter.filter.interfaces.OnFilterDoneListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import filter.entity.FilterUrl;
import filter.fragmentSample.FilterActivity;

public class MainActivity extends AppCompatActivity implements OnFilterDoneListener, View.OnClickListener {

	@BindView( R.id.dropDownMenu )
	DropDownMenu dropDownMenu;
	@BindView( R.id.mFilterContentView )
	TextView mFilterContentView;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.filter_activity_main );
		ButterKnife.bind( this );

		Toolbar toolbar = ButterKnife.findById( this, R.id.toolbar );
		setSupportActionBar( toolbar );

		initFilterDropDownView();

		mFilterContentView.setOnClickListener( this );
	}

	private void initFilterDropDownView() {
		String[] titleList = new String[]{ "第一个", "第二个", "第三个", "第四个" };
		dropDownMenu.setMenuAdapter( new DropMenuAdapter( this, titleList, this ) );
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		FilterUrl.instance()
				.clear();
	}

	@Override
	public void onClick( View view ) {
		startActivity( new Intent( this, FilterActivity.class ) );
	}

	@Override
	public void onFilterDone( int position, String positionTitle, String urlValue, String parentValue ) {
		if ( position != 3 ) {
			dropDownMenu.setPositionIndicatorText( FilterUrl.instance().position, FilterUrl.instance().positionTitle );
		}

		dropDownMenu.close();
		mFilterContentView.setText( FilterUrl.instance()
				.toString() );
	}
}
