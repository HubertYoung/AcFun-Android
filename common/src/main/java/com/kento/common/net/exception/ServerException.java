package com.kento.common.net.exception;

/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用
 */

public class ServerException extends RuntimeException {
	public String status;
	public String result;

	public ServerException( String result, String status ) {
		this.result = result;
		this.status = status;
	}

	@Override
	public String toString() {
		return result;
	}
}
