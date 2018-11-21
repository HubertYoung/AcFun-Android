package titlebar;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubertyoung.component_titlebar.R;

/**
 * Created by liufei on 2017/12/1.
 */

public class TabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_tab, null);
    }
}
