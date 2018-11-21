package activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.hubertyoung.component_skeleton.R;
import com.hubertyoung.component_skeleton.skeleton.ViewReplacer;


public class StatusViewActivity extends AppCompatActivity {
    private ViewReplacer mViewReplacer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_status_view);
        mViewReplacer = new ViewReplacer(findViewById(R.id.tv_content));
        findViewById(R.id.btn_loading).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewReplacer.replace(R.layout.layout_progress);
            }
        });

        findViewById(R.id.btn_error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewReplacer.replace(R.layout.layout_error);
            }
        });

        findViewById(R.id.btn_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewReplacer.replace(R.layout.layout_empty_view);
            }
        });

        findViewById(R.id.btn_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewReplacer.restore();
            }
        });

    }

    public void gotoSet(View view) {
        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
    }
}
