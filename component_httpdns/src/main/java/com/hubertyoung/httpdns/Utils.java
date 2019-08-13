package com.hubertyoung.httpdns;
//       _       _    _     _
//__   _(_)_ __ | | _(_) __| |
//\ \ / / | '_ \| |/ / |/ _` |
// \ V /| | |_) |   <| | (_| |
//  \_/ |_| .__/|_|\_\_|\__,_|
//        |_|

/* guolei2@vipkid.com.cn
 *
 *2018/11/2
 */

import android.content.Context;

public class Utils {

  public static String stringArrayToString(String[] array) {
    if (array == null || array.length == 0) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    for (String item : array) {
      sb.append(item).append("   ");
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * 检测系统是否已经设置代理
   */
  static boolean detectIfProxyExist(Context ctx) {
    String proxyHost;
    int proxyPort;
    proxyHost = System.getProperty("http.proxyHost");
    String port = System.getProperty("http.proxyPort");
    proxyPort = Integer.parseInt(port != null ? port : "-1");
    return proxyHost != null && proxyPort != -1;
  }
}
