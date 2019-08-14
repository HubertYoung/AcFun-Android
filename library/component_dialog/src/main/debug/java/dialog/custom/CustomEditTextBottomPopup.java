package dialog.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.hubertyoung.component_dialog.R;
import com.lxj.xpopup.core.BottomPopupView;


/**
 * Description: 带有输入框的Bottom弹窗
 * Create by dance, at 2019/2/27
 */
public class CustomEditTextBottomPopup extends BottomPopupView {
    public CustomEditTextBottomPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_edittext_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onShow() {
        super.onShow();
//        Log.e("tag", "CustomEditTextBottomPopup  onShow");
        findViewById(R.id.btn_finish).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
//        Log.e("tag", "CustomEditTextBottomPopup  onDismiss");
    }

    public String getComment(){
        EditText et = findViewById(R.id.et_comment);
        return et.getText().toString();
    }

//    @Override
//    protected int getMaxHeight() {
//        return (int) (XPopupUtils.getWindowHeight(getContext())*0.75);
//    }
}
