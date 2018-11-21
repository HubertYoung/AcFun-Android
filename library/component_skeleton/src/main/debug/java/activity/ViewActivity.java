package activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.hubertyoung.component_skeleton.R;
import com.hubertyoung.component_skeleton.skeleton.Skeleton;
import com.hubertyoung.component_skeleton.skeleton.SkeletonScreen;

import java.lang.ref.WeakReference;

import adapter.TopicAdapter;

public class ViewActivity extends AppCompatActivity {


    private static final String PARAMS_TYPE = "params_type";
    public static final String TYPE_IMG_LOADING = "type_img";
    public static final String TYPE_VIEW = "type_view";
    private SkeletonScreen skeletonScreen;

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, ViewActivity.class);
        intent.putExtra(PARAMS_TYPE, type);
        context.startActivity(intent);
    }

    public static class MyHandler extends android.os.Handler {
        private final WeakReference<ViewActivity> activityWeakReference;

        MyHandler(ViewActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (activityWeakReference.get() != null) {
                activityWeakReference.get().skeletonScreen.hide();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_view);
        String mType = getIntent().getStringExtra(PARAMS_TYPE);
        View rootView = findViewById( R.id.rootView);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if (TYPE_VIEW.equals(mType)) {
            skeletonScreen = Skeleton.bind(rootView)
                                     .load(R.layout.activity_view_skeleton)
                                     .duration(1000)
                                     .color(R.color.shimmer_color)
                                     .angle(0)
                                     .show();
        }
        if (TYPE_IMG_LOADING.equals(mType)) {
            skeletonScreen = Skeleton.bind(rootView)
                                     .load(R.layout.layout_img_skeleton)
                                     .duration(1000)
                                     .color(R.color.shimmer_color_for_image)
                                     .show();
        }

        TopicAdapter adapter = new TopicAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        MyHandler myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(1, 3000);
    }


}
