package com.hubertyoung.base.listener;


import com.hubertyoung.base.bean.EnvironmentBean;
import com.hubertyoung.base.bean.ModuleBean;

public interface OnEnvironmentChangeListener {

    void onEnvironmentChanged( ModuleBean module, EnvironmentBean oldEnvironment, EnvironmentBean newEnvironment );
}