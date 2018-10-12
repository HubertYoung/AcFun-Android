package statsdk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hubertyoung.component_statsdk.R;
import com.hubertyoung.statsdk.core.TcStatInterface;

import java.util.HashMap;

public class MainActivity extends TamicActivity implements View.OnClickListener {

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
		findViewById(R.id.id_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TcStatInterface.onEvent("main", "onlick", "send data");
				//发送数据
				TcStatInterface.reportData();

			}

		});

		findViewById(R.id.id_button2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// test
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id1", "xxx");
				map.put("id2", "yyyy");

				TcStatInterface.onEvent("open next", map);

				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				startActivity(intent);

			}

		});
	}

	@Override
	public void onClick( View v ) {

	}
}
