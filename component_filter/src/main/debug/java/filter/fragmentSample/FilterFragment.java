package filter.fragmentSample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kento.component_filter.R;
import com.kento.component_filter.filter.DropDownMenu;
import com.kento.component_filter.filter.interfaces.OnFilterDoneListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import filter.DropMenuAdapter;
import filter.entity.FilterUrl;

/**
 * author: baiiu
 * date: on 18/1/8 11:52
 * description:
 */
public class FilterFragment extends Fragment implements OnFilterDoneListener {

	@BindView( R.id.dropDownMenu )
	DropDownMenu dropDownMenu;
	@BindView( R.id.mFilterContentView )
	TextView mFilterContentView;
	@BindView( R.id.toolbar )
	Toolbar mToolbar;

	@Nullable
	@Override
	public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {

		View view = inflater.inflate( R.layout.filter_activity_main, container, false );
		ButterKnife.bind( this, view );

		mToolbar.setTitle( R.string.app_name );

		return view;
	}

	@Override
	public void onActivityCreated( @Nullable Bundle savedInstanceState ) {
		super.onActivityCreated( savedInstanceState );
		initFilterDropDownView();
	}

	private void initFilterDropDownView() {
		String[] titleList = new String[]{ "第一个", "第二个", "第三个", "第四个" };
		dropDownMenu.setMenuAdapter( new DropMenuAdapter( getContext(), titleList, this ) );
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		FilterUrl.instance()
				 .clear();
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
