package com.hubertyoung.aggregation.dialog;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

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
	private Activity mActivity;

	public ShareBottomDialog( FragmentActivity activity ) {
		this.mActivity = activity;
//		new TDialog.Builder(activity.getSupportFragmentManager())
//				.setLayoutRes( R.layout.layout_share_bottom_dialog)
//				.setScreenWidthAspect(this, 1.0f)
//				.setGravity( Gravity.BOTTOM)
//				.addOnClickListener(R.id.btn_evluate)
//				.setOnBindViewListener(new OnBindViewListener() {
//					@Override
//					public void bindView(BindViewHolder viewHolder) {
//						final EditText editText = viewHolder.getView(R.id.editText);
//						editText.post(new Runnable() {
//							@Override
//							public void run() {
//								InputMethodManager imm = (InputMethodManager) DiffentDialogActivity.this.getSystemService( Context.INPUT_METHOD_SERVICE);
//								imm.showSoftInput(editText, 0);
//							}
//						});
//					}
//				})
//				.setOnViewClickListener(new OnViewClickListener() {
//					@Override
//					public void onViewClick( BindViewHolder viewHolder, View view, TDialog tDialog) {
//						EditText editText = viewHolder.getView(R.id.editText);
//						String content = editText.getText().toString().trim();
//						Toast.makeText(DiffentDialogActivity.this, "评价内容:" + content, Toast.LENGTH_SHORT).show();
//						tDialog.dismiss();
//					}
//				})
//				.create()
//				.show();
	}
}
