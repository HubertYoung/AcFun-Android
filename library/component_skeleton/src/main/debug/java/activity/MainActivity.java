package activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.hubertyoung.component_skeleton.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.skeleton_activity_main);
        findViewById( R.id.btn_list).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerViewActivity.start(MainActivity.this, RecyclerViewActivity.TYPE_LINEAR);
            }
        });
        findViewById(R.id.btn_grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerViewActivity.start(MainActivity.this, RecyclerViewActivity.TYPE_GRID);
            }
        });
        findViewById(R.id.btn_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewActivity.start(MainActivity.this, ViewActivity.TYPE_VIEW);
            }
        });
        findViewById(R.id.btn_Imgloading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewActivity.start(MainActivity.this, ViewActivity.TYPE_IMG_LOADING);
            }
        });

        findViewById(R.id.btn_status).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StatusViewActivity.class));
            }
        });
    }
}
