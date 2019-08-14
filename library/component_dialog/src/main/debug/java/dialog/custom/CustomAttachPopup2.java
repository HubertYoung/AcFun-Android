package dialog.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.hubertyoung.component_dialog.R;
import com.lxj.xpopup.core.AttachPopupView;


/**
 * Description:
 * Create by lxj, at 2019/3/13
 */
public class CustomAttachPopup2 extends AttachPopupView {
    public CustomAttachPopup2(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_popup2;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

    }

    //如果要自定义弹窗的背景，不要给布局设置背景图片，重写这个方法返回一个Drawable即可
    @Override
    protected Drawable getPopupBackground() {
        return getResources().getDrawable(R.drawable.shadow_bg);
    }
}
