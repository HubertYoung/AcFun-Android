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
import android.os.Build.VERSION;
import android.text.TextUtils;

import com.alibaba.sdk.android.httpdns.DegradationFilter;
import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;
import com.alibaba.sdk.android.httpdns.probe.IPProbeItem;

import java.util.ArrayList;
import java.util.List;

public class AliHttpDnsStrategy implements HttpDnsStrategy {

  private HttpDnsService mHttpDnsService;
  private Context mApplication;

  private AliHttpDnsStrategy(Builder builder) {
    mApplication = builder.context.getApplicationContext();
    if (TextUtils.isEmpty(builder.securityKey)) {
      mHttpDnsService = HttpDns.getService(mApplication, builder.accountId);
    }else {
      mHttpDnsService = HttpDns.getService(mApplication, builder.accountId, builder.securityKey);
    }

    if (builder.preResolveHosts != null && builder.preResolveHosts.size() > 0) {
      mHttpDnsService.setPreResolveHosts(new ArrayList<>(builder.preResolveHosts));
    }
    mHttpDnsService.setPreResolveAfterNetworkChanged(builder.preResolveAfterNetworkChanged);
    if (builder.degradationFilter != null) {
      mHttpDnsService.setDegradationFilter(builder.degradationFilter);
    }else {
      DegradationFilter defaultDegradationFilter = new DegradationFilter() {
        @Override
        public boolean shouldDegradeHttpDNS(String s) {
          return shouldUseHttpDns(s);
        }
      };
      mHttpDnsService.setDegradationFilter(defaultDegradationFilter);
    }
    mHttpDnsService.setExpiredIPEnabled(builder.expiredIPEnabled);
    mHttpDnsService.setCachedIPEnabled(builder.cachedIPEnabled);
    if (builder.time > 0L) {
      mHttpDnsService.setAuthCurrentTime(builder.time);
    }
    mHttpDnsService.setLogEnabled(builder.logEnable);
    if (builder.timeoutInterval > 0) {
      mHttpDnsService.setTimeoutInterval(builder.timeoutInterval);
    }
    mHttpDnsService.setHTTPSRequestEnabled(builder.httpsEnable);
    if (VERSION.SDK_INT >= 28) {
      //因为Android P对 Http的请求限制，这里强制将28以上版本改为Https
      mHttpDnsService.setHTTPSRequestEnabled(true);
    }
    if (builder.ipProbeList != null && builder.ipProbeList.size() > 0) {
      mHttpDnsService.setIPProbeList(builder.ipProbeList);
    }
  }

  @Override
  public boolean shouldUseHttpDns(String host) {
    return Utils.detectIfProxyExist(mApplication);
  }

  @Override
  public String getIpByHost(String host) {
    return mHttpDnsService.getIpByHostAsync(host);
  }

  @Override
  public String[] getIpsByHost(String host) {
    return mHttpDnsService.getIpsByHostAsync(host);
  }

  @SuppressWarnings("unused")
  public static final class Builder {

    Context context;
    String accountId;
    String securityKey;
    List<String> preResolveHosts;
    boolean preResolveAfterNetworkChanged = true;
    DegradationFilter degradationFilter;
    boolean expiredIPEnabled = true;
    boolean cachedIPEnabled = true;
    long time;
    boolean logEnable;
    int timeoutInterval;
    boolean httpsEnable;
    List<IPProbeItem> ipProbeList;


    public Builder(Context context, String accountId) {
      this.context = context;
      this.accountId = accountId;
    }

    public Builder securityKey(String securityKey) {
      this.securityKey = securityKey;
      return this;
    }

    public Builder preResolveHosts(List<String> preResolveHosts) {
      if (preResolveHosts != null && preResolveHosts.size() > 0) {
        this.preResolveHosts = preResolveHosts;
      }
      return this;
    }

    public Builder preResolveAfterNetworkChanged(boolean preResolveAfterNetworkChanged) {
      this.preResolveAfterNetworkChanged = preResolveAfterNetworkChanged;
      return this;
    }

    public Builder degradationFilter(DegradationFilter degradationFilter) {
      this.degradationFilter = degradationFilter;
      return this;
    }

    public Builder expiredIPEnabled(boolean expiredIPEnabled) {
      this.expiredIPEnabled = expiredIPEnabled;
      return this;
    }

    public Builder cachedIPEnabled(boolean cachedIPEnabled) {
      this.cachedIPEnabled = cachedIPEnabled;
      return this;
    }

    /***
     * 校正App签名时间
     * @param time time为epoch时间戳，1970年1月1日以来的秒数
     */
    public Builder authCurrentTime(long time) {
      this.time = time;
      return this;
    }

    public Builder logEnable(boolean enable) {
      this.logEnable = enable;
      return this;
    }

    public Builder timeoutInterval(int timeoutInterval) {
      this.timeoutInterval = timeoutInterval;
      return this;
    }

    public Builder httpsRequestEnable(boolean enable) {
      this.httpsEnable = enable;
      return this;
    }

    public Builder ipProbeList(List<IPProbeItem> ipProbeList) {
      this.ipProbeList = ipProbeList;
      return this;
    }

    public AliHttpDnsStrategy build() {
      return new AliHttpDnsStrategy(this);
    }
  }
}
