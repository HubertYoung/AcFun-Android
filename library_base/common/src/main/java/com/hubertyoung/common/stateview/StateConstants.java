package com.hubertyoung.common.stateview;

/**
 */
public class StateConstants {

	public static final StateEntity NET_WORK_STATE = StateEntity.Builder.newInstance().setCode( "1" ).build();
	public static final StateEntity ERROR_STATE = StateEntity.Builder.newInstance().setCode( "2" ).build();
	public static final StateEntity LOADING_STATE = StateEntity.Builder.newInstance().setCode( "3" ).build();
	public static final StateEntity SUCCESS_STATE = StateEntity.Builder.newInstance().setCode( "4" ).build();
}
