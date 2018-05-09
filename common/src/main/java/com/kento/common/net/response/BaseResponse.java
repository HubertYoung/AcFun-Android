package com.kento.common.net.response;

import com.kento.common.net.config.NetStatus;

import java.io.Serializable;


/**
 * @author:Yang
 * @date:2017/7/13 10:48
 * @since:v1.0
 * @desc:com.kento.common.net.response
 * @param:封装服务器返回数据
 */
public class BaseResponse< T > implements Serializable {
	public int errno;
	public String errmsg;

	public T data;

	public boolean success( ) {
		return errno == NetStatus.Success.getIndex()  || errno == NetStatus.Server_Success.getIndex();
	}
	public boolean isFailed( ) {
		return errno == NetStatus.Server_Fail.getIndex();
	}
	public int getStatus() {
		return errno;
	}

	public void setStatus( int status ) {
		this.errno = status;
	}

	public String getResult() {
		return errmsg;
	}

	public void setResult( String errmsg ) {
		this.errmsg = errmsg;
	}

	public T getData() {
		return data;
	}

	public void setData( T data ) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseResponse{" + "errno=" + errno + ", errmsg='" + errmsg + '\'' + ", data=" + data + '}';
	}
}
