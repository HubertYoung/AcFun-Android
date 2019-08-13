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

import android.util.Log;

public final class DefaultDnsMonitor implements HttpDnsMonitor {

  @Override
  public void onHttpDnsParseEnd(String host, String ip) {
    Log.d(Constants.TAG, String.format(Constants.HTTPDNS_RESULT_FORMAT, host, ip));
  }
}
