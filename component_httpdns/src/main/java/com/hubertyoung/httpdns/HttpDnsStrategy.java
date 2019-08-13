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

public interface HttpDnsStrategy {

  boolean shouldUseHttpDns( String host );

  String getIpByHost( String host );

  String[] getIpsByHost( String host );

}
