package dialog.custom;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hubertyoung.component_dialog.R;
import com.lxj.xpopup.core.ImageViewerPopupView;


/**
 * Description:
 * Create by dance, at 2019/5/8
 */
public class CustomImageViewerPopup extends ImageViewerPopupView {
    public CustomImageViewerPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_image_viewer_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
//        tv_pager_indicator.setVisibility(GONE);
    }
}
