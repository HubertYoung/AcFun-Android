package filter.fragmentSample;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author: baiiu
 * date: on 18/1/8 11:56
 * description:
 */
public class FilterActivity extends AppCompatActivity {

	@Override
	protected void onCreate( @Nullable Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		getSupportFragmentManager().beginTransaction().replace( android.R.id.content, new FilterFragment(), FilterFragment.class.getCanonicalName() ).commitAllowingStateLoss();
	}

}
