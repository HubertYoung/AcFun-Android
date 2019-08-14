package com.hubertyoung.aggregation.dialog;

import android.app.Activity;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.enums.PopupType;

import java.util.List;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/12 19:54
 * @since:V$VERSION
 * @desc:com.hubertyoung.aggregation.dialog
 */
public class ShareBottomDialog {
    private Activity mContext;
    private BasePopupView mView;
    private final ShareBottomPopup mShareBottomPopup;

    public ShareBottomDialog( Activity context ) {
        this.mContext = context;
        mShareBottomPopup = new ShareBottomPopup( mContext );
        mView = new XPopup.Builder( context )//
                .moveUpToKeyboard( false ) //如果不加这个，评论弹窗会移动到软键盘上面
                .popupAnimation( PopupAnimation.TranslateAlphaFromBottom )
                .enableDrag( false )
                .popupType( PopupType.Bottom )
                .asCustom( mShareBottomPopup );
//				.setScreenWidthAspect( mContext, 1.0f )//
//				.setGravity( Gravity.BOTTOM )//
//				.setDimAmount( 0.5f )//
//				.setDialogAnimationRes( R.style.animate_dialog_scale )//
//				.addOnClickListener( R.id.cancel );

    }

    public ShareBottomDialog create( List< BottomShareEntity > list, List< BottomShareEntity > list2 ) {
        mShareBottomPopup.create( list, list2 );
        return this;
    }

    public ShareBottomDialog show() {
        mView.show();
        return this;
    }
}
