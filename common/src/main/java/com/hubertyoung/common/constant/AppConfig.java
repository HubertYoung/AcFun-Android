package com.hubertyoung.common.constant;


/**

 */
public class AppConfig {

    /**
     * The constant DEBUG_TAG.
     */
    public static final String DEBUG_TAG = "logger";// LogCat的标记

    public static String CHANNELID = "hubertyoung";
//    public static final String OSTYPE = "OSType";
//    public static final String OSVERSION = "OSVersion";
//    public static final String CLIENTID = "clientID";
    /**
     * 手机设备
     */
    public static final String DID = "did";
    /**
     * //设备类型
     */
    public static final String DT = "dt";
    /**
     * //设备信息
     */
    public static final String DI = "di";
    /**
     * //维度
     */
    public static final String LAT = "lat";
    /**
     * //经度
     */
    public static final String LON = "lon";
    /**
     * //市场类型
     */
    public static final String MT = "mt";
    /**
     * 手机号
     */
    public static final String MOB = "mob";
    /**
     * //操作系统类型(Android)
     */
    public static final String OST = "ost";
    /**
     * //系统版本
     */
    public static final String OSV = "osv";
    /**
     * //版本号
     */
    public static final String VER = "ver";
    /**
     * //区域
     */
    public static final String AREAID = "areaID";
    /**
     * //语言(zh_CN,en_US)
     */
    public static final String LANG = "lang";
    /**
     * 0：登录注册验证码；1：更换手机号验证码；2：找回密码验证码）3.手机验证 修改支付密码验证
     */
    public static final String CODETYPE = "codeType";

    // 图片缓存最大容量，250M，根据自己的需求进行修改
    public static final int GLIDE_CATCH_SIZE = 250 + MemoryConstants.MB;

    /**
     * 项目根目录文件夹
     */
    public static final String APP_DEPLOY_PATH = "hubertyoung";
    /**
     * 缓存文件夹
     */
    public static final String APP_CACHE_PATH = "cache";
    /**
     * 图片文件夹
     */
    public static final String APP_IMAGE_PATH = "image";
    /**
     * 换肤文件夹
     */
    public static final String APP_SKINS_PATH = "skins";
    /**
     * log文件夹
     */
    public static final String APP_LOG_PATH = "log";
    /**
     * download文件夹
     */
    public static final String APP_DOWN_PATH = "download";
    /**
     * 录制视频存放文件夹
     */
    public static final String APP_RECODER_VIDEO_PATH = "recoderVideo";
    /**
     * 录制视频第一帧图片的文件目录
     */
    public static final String APP_RECODER_VIDEO_FIRST_FRAME_PATH = "recoderVideoFirstframe";

    // glide图片缓存子目录
//    public static final String GLIDE_CARCH_DIR = "glide_dir";

    public static final String EMOTION_FILE_SUFFIX = ".gif";

}
