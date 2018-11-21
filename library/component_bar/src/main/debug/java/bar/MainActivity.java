package bar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.common.utils.bar.BarUtils;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

	private Toolbar mToolbar;
	private CheckBox mInsets;
	private CheckBox mTinting;
	private CheckBox mDark;
	private CheckBox mHidden;
	private CheckBox mAdjust;
	private TextView mHint;

	@Override
	public int getLayoutId() {
		return R.layout.bar_activity_main;
	}

	@Override
	public void initPresenter() {

	}
	@Override
	public void initView( Bundle savedInstanceState ) {
		mToolbar = findViewById( R.id.toolbar );
		mInsets = findViewById( R.id.insets );
		mTinting = findViewById( R.id.tinting );
		mDark = findViewById( R.id.dark );
		mHidden = findViewById( R.id.hidden );
		mAdjust = findViewById( R.id.adjust );
		mHint = findViewById( R.id.hint );
		initAction();
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}

	private void initAction() {

		mInsets.setChecked( isStatusBarTranslucent() );
		mInsets.setOnCheckedChangeListener( this );

		mTinting.setOnCheckedChangeListener( this );
		mDark.setOnCheckedChangeListener( this );
		mHidden.setOnCheckedChangeListener( this );
		mAdjust.setOnCheckedChangeListener( this );
	}

	int statusBarColor = Color.MAGENTA;
	BarStyle statusBarStyle = BarStyle.LightContent;
	boolean statusBarHidden = false;

	@Override
	public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
		mHint.setText( "" );
		switch ( buttonView.getId() ) {
			case R.id.insets:
				// 慎重，会影响整个 Activity
				BarUtils.setStatusBarTranslucent( this.getWindow(), isChecked );
				getWindow().getDecorView().requestLayout();
				mHint.setText( "将影响整个 Activity，打开 Drawer 看看" );
				break;
			case R.id.tinting:
				statusBarColor = isChecked ? Color.MAGENTA : Color.TRANSPARENT;
				BarUtils.setStatusBarColor( this, statusBarColor, true );
				if ( statusBarHidden && isChecked ) {
					mHint.setText( "只有显示状态栏才能看到效果" );
				}
				break;
			case R.id.dark: // 深色状态栏 6.0 以上生效
				statusBarStyle = isChecked ? BarStyle.DarkContent : BarStyle.LightContent;
				BarUtils.statusBarLightMode( this, isChecked );

				if ( statusBarHidden && isChecked ) {
					mHint.setText( "只有显示状态栏才能看到效果" );
				}

				if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.M ) {
					mHint.setText( "只有在 6.0 以上系统才能看到效果" );
				}

				break;
			case R.id.hidden:
				statusBarHidden = isChecked;
				BarUtils.setStatusBarHidden( this, statusBarHidden );
				break;
			case R.id.adjust:
				if ( isChecked ) {
					BarUtils.setPaddingSmart( mToolbar );
				} else {
					BarUtils.removePaddingSmart( mToolbar );
				}
				break;
		}
	}

	public void setStatusBarTranslucent( boolean translucent ) {
		AppUtils.setStatusBarTranslucent( getWindow(), translucent );
//		this.setStatusBarTranslucent( translucent );
	}

}
