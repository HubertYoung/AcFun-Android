package com.hubertyoung.ping.bean;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/7/31 18:10
 * @since:V$VERSION
 * @desc:ping
 */
public class PingNetEntity {
	public String host;
	public String ip;
	public int pingCount;
	public int pingWtime;
	public StringBuffer resultBuffer;
	public String pingTime;
	public boolean result;

	public PingNetEntity(String host, String ip, int pingCount, int pingWtime, StringBuffer resultBuffer ) {
		this.host = host;
		this.ip = ip;
		this.pingWtime = pingWtime;
		this.pingCount = pingCount;
		this.resultBuffer = resultBuffer;
	}

}
