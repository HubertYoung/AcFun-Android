package dialog.custom;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hubertyoung.component_dialog.R;
import com.lxj.xpopup.impl.FullScreenPopupView;


/**
 * Description:
 * Create by lxj, at 2019/3/12
 */
public class CustomFullScreenPopup extends FullScreenPopupView {
    public CustomFullScreenPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_fullscreen_popup;
    }
}
