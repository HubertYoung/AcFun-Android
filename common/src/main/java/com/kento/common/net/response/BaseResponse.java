package com.kento.common.net.response;

import android.text.TextUtils;

import com.kento.common.net.config.NetStatus;

import java.io.Serializable;


/**
 * @author:Yang
 * @date:2017/7/13 10:48
 * @since:v1.0
 * @desc:ddframework.gent.common.net.response
 * @param:封装服务器返回数据
 */
public class BaseResponse< T > implements Serializable {
	public String status;
	public String result;

	public T data;

	public boolean success( ) {
		return TextUtils.equals( status, NetStatus.Success.getIndex() )  || TextUtils.equals( status, NetStatus.Server_Success.getIndex() );
	}
	public boolean isFailed( ) {
		return TextUtils.equals( status, NetStatus.Server_Fail.getIndex());
	}
	public String getStatus() {
		return status;
	}

	public void setStatus( String status ) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult( String result ) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData( T data ) {
		this.data = data;
	}

	@Override
	public String toString( ) {
		return "BaseResponse{" + "status='" + status + '\'' + ", result='" + result + '\'' + ", data=" + data + '}';
	}
}
