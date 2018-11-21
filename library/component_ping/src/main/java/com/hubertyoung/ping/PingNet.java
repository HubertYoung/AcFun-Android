package com.hubertyoung.ping;

import android.util.Log;

import com.hubertyoung.ping.bean.PingNetEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/7/31 18:23
 * @since:V$VERSION
 * @desc:com.hubertyoung.ping
 */
public class PingNet {
	private static final String TAG = "PingNet";

	/**
	 * @param pingNetEntity 检测网络实体类
	 * @return 检测后的数据
	 */
	public static PingNetEntity ping( PingNetEntity pingNetEntity ) {
		String line = null;
		Process process = null;
		BufferedReader successReader = null;
		String command = "ping -c " + pingNetEntity.pingCount + " -w " + pingNetEntity.pingWtime + " " + pingNetEntity.ip;
//        String command = "ping -c " + pingCount + " " + host;
		try {
			process = Runtime.getRuntime().exec( command );
			if ( process == null ) {
				Log.e( TAG, "ping fail:process is null." );
				append( pingNetEntity.resultBuffer, "ping fail:process is null." );
				pingNetEntity.pingTime = null;
				pingNetEntity.result = false;
				return pingNetEntity;
			}
			successReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
			while ( ( line = successReader.readLine() ) != null ) {
				Log.i( TAG, line );
				append( pingNetEntity.resultBuffer, line );
				String time;
				if ( ( time = getTime( line ) ) != null ) {
					pingNetEntity.pingTime = time;
				}
			}
			int status = process.waitFor();
			if ( status == 0 ) {
				Log.i( TAG, "exec cmd success:" + command );
				append( pingNetEntity.resultBuffer, "exec cmd success:" + command );
				pingNetEntity.result = true;
			} else {
				Log.e( TAG, "exec cmd fail." );
				append( pingNetEntity.resultBuffer, "exec cmd fail." );
				pingNetEntity.pingTime = null;
				pingNetEntity.result = false;
			}
			Log.i( TAG, "exec finished." );
			append( pingNetEntity.resultBuffer, "exec finished." );
		} catch ( IOException e ) {
			Log.e( TAG, String.valueOf( e ) );
		} catch ( InterruptedException e ) {
			Log.e( TAG, String.valueOf( e ) );
		} finally {
			Log.i( TAG, "ping exit." );
			if ( process != null ) {
				process.destroy();
			}
			if ( successReader != null ) {
				try {
					successReader.close();
				} catch ( IOException e ) {
					Log.e( TAG, String.valueOf( e ) );
				}
			}
		}
		Log.i( TAG, pingNetEntity.resultBuffer.toString() );
		return pingNetEntity;
	}

	private static void append( StringBuffer stringBuffer, String text ) {
		if ( stringBuffer != null ) {
			stringBuffer.append( text + "\n" );
		}
	}

	private static String getTime( String line ) {
		String[] lines = line.split( "\n" );
		String time = null;
		for (String l : lines) {
			if ( !l.contains( "time=" ) ) continue;
			int index = l.indexOf( "time=" );
			time = l.substring( index + "time=".length() );
			Log.i( TAG, time );
		}
		return time;
	}
}
