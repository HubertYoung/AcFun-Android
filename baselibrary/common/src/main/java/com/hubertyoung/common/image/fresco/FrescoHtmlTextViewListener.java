package com.hubertyoung.common.image.fresco;

import android.graphics.drawable.Drawable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 14:02
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.image.fresco
 */
public interface FrescoHtmlTextViewListener {
	void onDetach();
	boolean isSaveURLDrawable( Drawable drawable );
	void onAttach();
	void onInvalidateAttach();
}
