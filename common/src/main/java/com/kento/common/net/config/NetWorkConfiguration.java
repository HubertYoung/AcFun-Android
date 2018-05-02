package com.kento.common.net.config;

import android.content.Context;

import com.facebook.stetho.common.LogUtil;
import com.kento.common.utils.AppFileUtils;
import com.kento.common.utils.AppUtils;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.ConnectionPool;


/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：对网络进行配置
 */
public final class NetWorkConfiguration {
    /**
     * 默认缓存
     */
    private boolean isCache;
    //    是否进行磁盘缓存
    private boolean isDiskCache;
    //        是否进行内存缓存
    private boolean isMemoryCache;
    //    内存缓存时间单位S （默认为60s）
    private int memoryCacheTime;

    //    本地缓存时间单位S (默认为4周)
    private int diskCacheTime;

    //    缓存本地大小 单位字节（默认为30M）
    private int maxDiskCacheSize;
    //      缓存路径
    private Cache diskCache;

    //     设置超时时间
    private int connectTimeout;
    private int readTimeout;

    //    设置网络最大连接数
    private ConnectionPool connectionPool;
    //    设置HttpS客户端带证书访问
    private InputStream[] certificates;
    public Context context;

    //    设置网络BaseUrl地址
    private String baseUrl;
    /*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/
    public NetWorkConfiguration( Context context ) {
        this.isCache = false;
        this.isDiskCache = false;
        this.isMemoryCache = false;
        this.memoryCacheTime = 60;
        this.diskCacheTime = 60 * 60 * 24 * 28;
        this.maxDiskCacheSize = 100 * 1024 * 1024;
        this.context = context.getApplicationContext();
        //缓存
        this.diskCache = new Cache( new File( AppFileUtils.getAppCacheDir(), "networkCache" ), maxDiskCacheSize );
        int maxTimeOut = AppUtils.isDebuggable( ) ? 60 : 10;
        this.connectTimeout = 1000 * maxTimeOut;
        this.readTimeout = 1000 * maxTimeOut;
        this.connectionPool = new ConnectionPool( 50, 60, TimeUnit.SECONDS );
        certificates = null;
        baseUrl = null;
    }

    /**
     * 设置是否进行缓存
     *
     * @param iscache
     * @return
     */
    public NetWorkConfiguration isCache( boolean iscache ) {
        this.isCache = iscache;
        return this;
    }

    public boolean getIsCache() {
        return this.isCache;
    }

    /**
     * 是否进行磁盘缓存
     *
     * @param diskcache
     * @return
     */
    public NetWorkConfiguration isDiskCache( boolean diskcache ) {
        this.isDiskCache = diskcache;
        return this;
    }

    public boolean getIsDiskCache() {
        return this.isDiskCache;
    }

    /**
     * 是否进行内存缓存
     *
     * @param memorycache
     * @return
     */
    public NetWorkConfiguration isMemoryCache( boolean memorycache ) {
        this.isMemoryCache = memorycache;
        return this;
    }

    public boolean getIsMemoryCache() {
        return this.isMemoryCache;
    }

    /**
     * 设置内存缓存时间
     *
     * @param memorycachetime
     * @return
     */
    public NetWorkConfiguration memoryCacheTime( int memorycachetime ) {
        if ( memorycachetime <= 0 ) {

            LogUtil.e( "NetWorkConfiguration", " configure memoryCacheTime  exception!" );
            return this;
        }
        this.memoryCacheTime = memorycachetime;
        return this;
    }

    public int getmemoryCacheTime() {
        return this.memoryCacheTime;
    }

    /**
     * 设置本地缓存时间
     *
     * @param diskcahetime
     * @return
     */
    public NetWorkConfiguration diskCacheTime( int diskcahetime ) {
        if ( diskcahetime <= 0 ) {
            LogUtil.e( "NetWorkConfiguration", " configure diskCacheTime  exception!" );
            return this;
        }
        this.diskCacheTime = diskcahetime;
        return this;
    }

    public int getDiskCacheTime() {
        return this.diskCacheTime;
    }

    /**
     * 设置本地缓存路径以及 缓存大小
     *
     * @param saveFile         本地路径
     * @param maxDiskCacheSize 大小
     * @return
     */
    public NetWorkConfiguration diskCaChe( File saveFile, int maxDiskCacheSize ) {
        if ( !saveFile.exists() && maxDiskCacheSize <= 0 ) {
            LogUtil.e( "NetWorkConfiguration", " configure connectTimeout  exception!" );
            return this;
        }
        diskCache = new Cache( saveFile, maxDiskCacheSize );
        return this;
    }

    public Cache getDiskCache() {
        return this.diskCache;
    }

    /**
     * 设置网络超时时间
     *
     * @param timeout
     * @return
     */
    public NetWorkConfiguration connectTimeOut( int timeout ) {
        if ( connectTimeout <= 0 ) {
            LogUtil.e( "NetWorkConfiguration", " configure connectTimeout  exception!" );
            return this;
        }
        this.connectTimeout = timeout;
        return this;
    }

    public int getConnectTimeOut() {
        return this.connectTimeout;
    }
    public int getReadTimeOut() {
        return this.readTimeout;
    }

    /**
     * 设置网络线程池
     *
     * @param connectionCount 线程个数
     * @param connectionTime  连接时间
     * @param unit            时间单位
     * @return
     */
    public NetWorkConfiguration connectionPool( int connectionCount, int connectionTime, TimeUnit unit ) {
        /**
         *    线程池 线程个数和连接时间设置过小
         */
        if ( connectionCount <= 0 && connectionTime <= 0 ) {
            LogUtil.e( "NetWorkConfiguration", " configure connectionPool  exception!" );
            return this;
        }
        this.connectionPool = new ConnectionPool( connectionCount, connectionTime, unit );
        return this;
    }

    public ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    /**
     * 设置Https客户端带证书访问
     *
     * @param certificates
     * @return
     */
    public NetWorkConfiguration certificates( InputStream... certificates ) {
        if ( certificates != null ) {
            this.certificates = certificates;
        } else {

            LogUtil.e( "NetWorkConfiguration", "no  certificates" );
        }
        return this;
    }

    public InputStream[] getCertificates() {
        return this.certificates;
    }

    /**
     * 设置网络BaseUrl地址
     *
     * @param url
     * @return
     */
    public NetWorkConfiguration baseUrl( String url ) {
        if ( url != null ) {
            this.baseUrl = url;
        } else {
            LogUtil.e( "NetWorkConfiguration no  baseUrl" );
        }
        return this;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    @Override
    public String toString() {
        return "NetWorkConfiguration{" + "isCache=" + isCache + ", isDiskCache=" + isDiskCache + ", isMemoryCache=" + isMemoryCache + ", " +
                "memoryCacheTime=" + memoryCacheTime + ", diskCacheTime=" + diskCacheTime + ", maxDiskCacheSize=" + maxDiskCacheSize + ", " +
                "diskCache=" + diskCache + ", connectTimeout=" + connectTimeout + ", connectionPool=" + connectionPool + ", certificates=" + Arrays
                .toString( certificates ) + ", context=" + context + ", baseUrl='" + baseUrl + '\'' + '}';
    }
}
