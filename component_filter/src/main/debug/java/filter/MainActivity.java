package filter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;

import com.kento.component_filter.R;
import com.kento.component_filter.filter.DropDownMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView( R.id.mFilterContentView )
	LinearLayoutCompat mMFilterContentView;
	@BindView( R.id.ddm_filter )
	DropDownMenu mDdmFilter;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.filter_activity_main );
		ButterKnife.bind( this );
	}
}
