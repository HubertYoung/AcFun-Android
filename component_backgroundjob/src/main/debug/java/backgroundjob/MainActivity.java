package backgroundjob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hubertyoung.backgroundjob.IntentWrapper;
import com.hubertyoung.component_backgroundjob.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        IntentWrapper.whiteListMatters(this, null);


    }
}
