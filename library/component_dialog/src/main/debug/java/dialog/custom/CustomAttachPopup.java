package dialog.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.hubertyoung.common.utils.display.ToastUtil;
import com.hubertyoung.component_dialog.R;
import com.lxj.xpopup.core.HorizontalAttachPopupView;

/**
 * Description:
 * Create by lxj, at 2019/3/13
 */
public class CustomAttachPopup extends HorizontalAttachPopupView {
    public CustomAttachPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_zan).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showWarning( "赞" );
                dismiss();
            }
        });
        findViewById(R.id.tv_comment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showWarning( "评论" );

                dismiss();
            }
        });
    }
}
