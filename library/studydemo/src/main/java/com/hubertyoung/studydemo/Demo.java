package com.hubertyoung.studydemo;

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
//	@Module
//	private class App {
//		@Environment(url = "https://gank.io/api/", isRelease = true, alias = "正式")
//		private String online;
//	}

	public static void main(String[] arg){
//		HelloWorld.main(null);
//		s = "3[a]2[bc]", 返回 "aaabcbc".
//				s = "3[a2[c]]", 返回 "accaccacc".
//				s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

		String s = "3[a]2[bc]";
		StringBuffer sb = new StringBuffer();
		String[] split = s.split( "\\[" );
		for (int i = 0; i < split.length; i++) {
			int indexStart = s.indexOf( "[", i );
			int indexStop = s.indexOf( "]", i );

			System.out.println(s.subSequence( indexStart,indexStop ));
		}
	}
}
