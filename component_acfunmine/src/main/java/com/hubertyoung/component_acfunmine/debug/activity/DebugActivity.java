package com.hubertyoung.component_acfunmine.debug.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.hubertyoung.common.base.BaseActivity;
import com.hubertyoung.component_acfunmine.R;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/17 14:43
 * @since:V1.0.0
 * @desc:com.hubertyoung.component_acfunmine.debug.activity
 */
public class DebugActivity extends BaseActivity {

	private LinearLayout mActivityRoot;
	private FrameLayout mContentLayout;
	private Toolbar mToolbar;

	@Override
	public int getLayoutId() {
		return R.layout.acfunmine_activity_debug;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView( Bundle savedInstanceState ) {

	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {
		mToolbar = findViewById( R.id.view_toolbar );
		mActivityRoot = findViewById( R.id.activity_root );
		mContentLayout = findViewById( R.id.content_layout );

		setSupportActionBar( mToolbar );
		ActionBar actionBar = getSupportActionBar();
		if ( actionBar != null ) {
			actionBar.setDisplayHomeAsUpEnabled( true );
		}
		getFragment( "",BiliPreferencesFragment.class.getName(),null,false );
	}

	private Fragment getFragment( CharSequence charSequence, String str, Bundle bundle, boolean isBackStack) {
//		if (a(str)) {
			setTitle(charSequence);
			Fragment instantiate = Fragment.instantiate(this, str, bundle);
			FragmentTransaction beginTransaction = getFragmentTransaction();
//			if (!TextUtils.equals(str, BiliPreferencesFragment.class.getName())) {
//				beginTransaction.setCustomAnimations(this.j, 0, 0, 0);
//			}
			beginTransaction.replace(R.id.content_layout, instantiate, str);
			if (isBackStack) {
				beginTransaction.addToBackStack("a");
			}
			if (charSequence != null) {
				beginTransaction.setBreadCrumbTitle(charSequence);
			}
			beginTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			beginTransaction.commitAllowingStateLoss();
			return instantiate;
//		}
//		throw new IllegalArgumentException("error");
	}


	public static class BiliPreferencesFragment extends PreferenceFragmentCompat {

		@Override
		public void onCreatePreferences( Bundle savedInstanceState, String rootKey ) {
			addPreferencesFromResource( R.xml.main_preferences );
//
			findPreference( getString( R.string.pref_key_feed_back ) ).setOnPreferenceClickListener( new Preference.OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick( Preference preference ) {
					return false;
				}
			} );
//
//			Preference findPreference = findPreference(getString(R.string.pref_key_wifi_auto_update));
//			if (findPreference != null) {
////				findPreference.a(new b() {
////					public boolean a(Preference preference, Object obj) {
////						if (obj.equals(Boolean.valueOf(false))) {
////							hdp.a(jsx.a(new byte[]{(byte) 107, (byte) 96, (byte) 114, (byte) 100, (byte) 117, (byte) 117, (byte) 90, (byte) 114, (byte) 108, (byte) 99, (byte) 108, (byte) 97, (byte) 106, (byte) 114, (byte) 107, (byte) 105, (byte) 106, (byte) 100, (byte) 97, (byte) 100, (byte) 117, (byte) 117, (byte) 90, (byte) 118, (byte) 96, (byte) 113, (byte) 113, (byte) 108, (byte) 107, (byte) 98, (byte) 118, (byte) 90, (byte) 113, (byte) 112, (byte) 119, (byte) 107, (byte) 106, (byte) 99, (byte) 99}), new String[0]);
////						}
////						return true;
////					}
////				});
//			}
//			findPreference(getString(R.string.pref_key_logout)).a(new Preference.c() {
//				public boolean a(Preference preference) {
//					BiliPreferencesFragment.this.d();
//					nvb.a(BiliPreferencesFragment.this.getContext(), preference.B());
//					return true;
//				}
//			});
		}
	}


	public static void launch( BaseActivity activity ) {
		activity.startActivity( DebugActivity.class );
	}

}
