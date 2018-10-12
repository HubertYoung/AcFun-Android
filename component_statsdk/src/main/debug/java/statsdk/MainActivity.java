package statsdk;

import android.os.Bundle;
import android.view.View;

import com.hubertyoung.component_statsdk.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.statsdk_activity_main );
		initView();
		initData();
	}

	private void initData() {
	}

	private void initView() {
	}

	@Override
	public void onClick( View v ) {

	}
}
