package com.hubertyoung.backgroundjob;
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

import android.os.Build;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpDnsServiceProvider {

  private static HttpDnsServiceProvider instance;

  public static HttpDnsServiceProvider getInstance(){
    if (instance == null) {
      throw new NullPointerException("must init HttpDnsServiceProvider");
    }
    return instance;
  }

  public static void init(HttpDnsStrategy strategy){
    instance = new HttpDnsServiceProvider(strategy,new DefaultDnsMonitor());
  }

  public static void initWithMonitor(HttpDnsStrategy strategy,HttpDnsMonitor monitor){
    if (strategy == null) {
      throw new NullPointerException("strategy must not null");
    }
    if (monitor == null) {
      monitor = new DefaultDnsMonitor();
    }
    instance = new HttpDnsServiceProvider(strategy,monitor);
  }

  private HttpDnsStrategy mHttpDnsStrategy;
  private HttpDnsMonitor mMonitor;
  private List<String> mBlackList;
  private List<String> mWhiteList;
  private HostIntercept mHostIntercept;

  private HttpDnsServiceProvider(HttpDnsStrategy strategy,HttpDnsMonitor monitor) {
    mHttpDnsStrategy = strategy;
    mMonitor = monitor;
    hookOsInterface();
  }

  public void setBlackAndWhiteList(List<String> blackList,List<String> whiteList) {
    this.mBlackList = blackList;
    this.mWhiteList = whiteList;
  }

  public void setMonitor(HttpDnsMonitor monitor) {
    if (monitor != null) {
      this.mMonitor = monitor;
    }
  }

  public void setHostIntercept(HostIntercept intercept){
    this.mHostIntercept = intercept;
  }

  HttpDnsMonitor getMonitor() {
    return mMonitor;
  }

  HttpDnsStrategy getHttpDnsStrategy(){
    return mHttpDnsStrategy;
  }

  /**
   * 正则 部分匹配
   */
  boolean shouldUseHttpDns(String host) {
    if (mHostIntercept != null) {
      return mHostIntercept.shouldUseHttpDns(host);
    }
    if (TextUtils.isEmpty(host)) return false;

    //黑名单拦截
    if (mBlackList != null && mBlackList.size() > 0) {
      for (String regex : mBlackList) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(host);
        if (matcher.find()) {
          return false;
        }
      }
    }

    //白名单放行
    if (mWhiteList != null && mWhiteList.size() > 0) {
      for (String regex : mWhiteList) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(host);
        if (matcher.find()) {
          return true;
        }
      }
      return false;
    }
    return true;
  }

  private void hookOsInterface() {
    if (Build.VERSION.SDK_INT >= 28) {
      try {
        Class reflectionHelperClz = ReflectHelper.class;
        Class classClz = Class.class;
        Field classLoaderField = classClz.getDeclaredField("classLoader");
        classLoaderField.setAccessible(true);
        classLoaderField.set(reflectionHelperClz, null);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    try {
      Class libcoreClz = ReflectHelper.getClass("libcore.io.Libcore");
      Field osField = ReflectHelper.getField(libcoreClz, "os");
      Object origin = osField.get(null);
      Object proxy =
          Proxy.newProxyInstance(
              libcoreClz.getClassLoader(),
              new Class[]{ReflectHelper.getClass("libcore.io.Os")},
              new OsInvocationHandler(origin));
      osField.set(null, proxy);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
