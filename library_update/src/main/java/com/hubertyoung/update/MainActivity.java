package com.hubertyoung.update;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

	private static final String PATH = Environment.getExternalStorageDirectory().getPath();
	private static final String NEW_APK = PATH + File.separator + "new.apk";
	private static final String OUT_APK = PATH + File.separator + "out.apk";
	private static final String PATCH_FILE = PATH + File.separator + "patch";

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		Button btn = findViewById( R.id.button_diff );
		Button btnMake = findViewById( R.id.button_make );
		btn.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View view ) {
				update();
			}
		} );
		btnMake.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick( View view ) {
				diff();
			}
		} );
	}

	private void update() {
		// 执行增量升级
		AppIncrementalUpdateUtil.get( this.getApplicationContext() ).incrementalInstall(NEW_APK, PATCH_FILE );
	}
	private void diff() {
		AppIncrementalUpdateUtil.get( this.getApplicationContext() ).incrementalDiff( OUT_APK, PATCH_FILE );
	}
}
