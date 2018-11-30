package com.hubertyoung.common.net.exception;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:Yang
 * @date:2018/3/7 下午7:06
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.net.exception
 */
public class TryWhenException extends IllegalArgumentException {
	public TryWhenException() {
		super( "联网失败" );
	}
}
