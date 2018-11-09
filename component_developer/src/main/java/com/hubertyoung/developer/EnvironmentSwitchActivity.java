package com.hubertyoung.developer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hubertyoung.base.Constants;
import com.hubertyoung.base.bean.EnvironmentBean;
import com.hubertyoung.base.bean.ModuleBean;
import com.hubertyoung.common.base.BaseActivityNew;
import com.hubertyoung.component_developer.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentSwitchActivity extends BaseActivityNew {

	private static final int TYPE_MODULE = 0;

	private static final int TYPE_ENVIRONMENT = 1;
	private Toolbar mToolbar;

	public static void launch( Context context ) {
		if ( context instanceof BaseActivityNew ) {
			( ( BaseActivityNew ) context ).startActivity( EnvironmentSwitchActivity.class );
		} else {
			Intent intent = new Intent( context, EnvironmentSwitchActivity.class );
			if ( !( context instanceof Activity ) ) {
				//调用方没有设置context或app间组件跳转，context为application
				intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
			}
			context.startActivity( intent );
		}
	}

	private List< EnvironmentBean > environmentBeans = new ArrayList<>();
	private Adapter adapter;

	@Override
	public int getLayoutId() {
		return R.layout.developer_environment_switcher_activity;
	}

	@Override
	public void initPresenter() {

	}

	@Override
	public void initView( Bundle savedInstanceState ) {
		mToolbar = findViewById( R.id.view_toolbar );
		try {
			Class< ? > environmentSwitcherClass = Class.forName( Constants.PACKAGE_NAME + "." + Constants.ENVIRONMENT_SWITCHER_FILE_NAME );
			Method getEnvironmentConfigMethod = environmentSwitcherClass.getMethod( Constants.METHOD_NAME_GET_MODULE_LIST );
			ArrayList< ModuleBean > modules = ( ArrayList< ModuleBean > ) getEnvironmentConfigMethod.invoke( environmentSwitcherClass.newInstance() );

			ArrayList< EnvironmentBean > environmentBeans = new ArrayList<>();
			for (ModuleBean module : modules) {
				EnvironmentBean environmentModule = new EnvironmentBean( "", "", module.getAlias(), module, false );
				environmentBeans.add( environmentModule );
				environmentBeans.addAll( module.getEnvironments() );
			}
			this.environmentBeans = environmentBeans;
			for (EnvironmentBean environmentBean : this.environmentBeans) {
				Method getXXEnvironmentBeanMethod = environmentSwitcherClass.getMethod( "get" + environmentBean.getModule().getName() + "EnvironmentBean", Context.class, boolean.class );
				EnvironmentBean environment = ( EnvironmentBean ) getXXEnvironmentBeanMethod.invoke( environmentSwitcherClass.newInstance(), this, true );
				environmentBean.setChecked( environment.equals( environmentBean ) );
			}
			ListView recyclerView = findViewById( R.id.list_view );
			adapter = new Adapter();
			recyclerView.setAdapter( adapter );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		} catch ( NoSuchMethodException e ) {
			e.printStackTrace();
		} catch ( IllegalAccessException e ) {
			e.printStackTrace();
		} catch ( InstantiationException e ) {
			e.printStackTrace();
		} catch ( InvocationTargetException e ) {
			e.printStackTrace();
		}
		initAction();
	}

	private void initAction() {
		mToolbar.setNavigationOnClickListener( new View.OnClickListener() {
			public void onClick( View view ) {
				EnvironmentSwitchActivity.this.onBackPressed();
			}
		} );
	}

	@Override
	protected void loadData() {

	}

	@Override
	public void initToolBar() {

	}

	class Adapter extends BaseAdapter {
		@Override
		public int getCount() {
			return environmentBeans.size();
		}

		@Override
		public EnvironmentBean getItem( int position ) {
			return environmentBeans.get( position );
		}

		@Override
		public long getItemId( int position ) {
			return position;
		}

		@Override
		public View getView( int position, View convertView, ViewGroup parent ) {
			final EnvironmentBean environmentBean = getItem( position );

			if ( getItemViewType( position ) == TYPE_MODULE ) {
				convertView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.developer_environment_switcher_item_module, parent, false );
				TextView tvName = convertView.findViewById( R.id.tv_name );

				String moduleName = environmentBean.getModule().getName();
				String alias = environmentBean.getAlias();
				tvName.setText( TextUtils.isEmpty( alias ) ? moduleName : alias );
			} else {
				convertView = LayoutInflater.from( parent.getContext() ).inflate( R.layout.developer_environment_switcher_item_environment, parent, false );
				TextView tvName = convertView.findViewById( R.id.tv_name );
				TextView tvUrl = convertView.findViewById( R.id.tv_url );
				ImageView ivMark = convertView.findViewById( R.id.iv_mark );

				tvUrl.setText( environmentBean.getUrl() );
				String alias = environmentBean.getAlias();
				tvName.setText( TextUtils.isEmpty( alias ) ? environmentBean.getName() : alias );
				ivMark.setVisibility( environmentBean.isChecked() ? View.VISIBLE : View.INVISIBLE );

				convertView.setOnClickListener( new View.OnClickListener() {
					@Override
					public void onClick( View v ) {
						try {
							Class< ? > environmentSwitcher = Class.forName( Constants.PACKAGE_NAME + "." + Constants.ENVIRONMENT_SWITCHER_FILE_NAME );
							Method method = environmentSwitcher.getMethod( "set" + environmentBean.getModule().getName() + "Environment", Context.class, EnvironmentBean.class );
							method.invoke( environmentSwitcher.newInstance(), EnvironmentSwitchActivity.this, environmentBean );
							for (EnvironmentBean bean : environmentBeans) {
								if ( bean.getModule().equals( environmentBean.getModule() ) ) {
									bean.setChecked( TextUtils.equals( bean.getUrl(), environmentBean.getUrl() ) );
								}
							}
							adapter.notifyDataSetChanged();
						} catch ( ClassNotFoundException e ) {
							e.printStackTrace();
						} catch ( NoSuchMethodException e ) {
							e.printStackTrace();
						} catch ( IllegalAccessException e ) {
							e.printStackTrace();
						} catch ( InvocationTargetException e ) {
							e.printStackTrace();
						} catch ( InstantiationException e ) {
							e.printStackTrace();
						}
					}
				} );
			}

			return convertView;
		}

		@Override
		public int getItemViewType( int position ) {
			if ( TextUtils.isEmpty( getItem( position ).getName() ) ) {
				return TYPE_MODULE;
			} else {
				return TYPE_ENVIRONMENT;
			}
		}
	}

}
