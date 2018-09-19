package com.hubertyoung.studydemo;

import com.hubertyoung.base.annotation.Environment;
import com.hubertyoung.base.annotation.Module;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/18 21:02
 * @since:V$VERSION
 * @desc:com.hubertyoung.studydemo
 */
public class Demo {
	@Module
	private class App {
		@Environment(url = "https://gank.io/api/", isRelease = true, alias = "正式")
		private String online;
	}

	public static void main(String[] arg){
//		HelloWorld.main(null);
	}
}
