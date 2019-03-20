package com.hubertyoung.common.net.exception;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.hubertyoung.common.CommonApplication;
import com.hubertyoung.common.R;
import com.hubertyoung.common.net.config.NetStatus;
import com.hubertyoung.common.net.processserver.ProcessServerException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.HttpException;


/**
 * @desc:展示友好UI界面给用户Error
 * @author:HubertYoung
 * @date 2019/3/20 14:03
 * @since:
 * @see ExceptionHandle
 */
public class ExceptionHandle {
    /**
     * 定义网络异常码
     */
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int CLOSE_OPERATION_ENTRANCE = 450;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponeThrowable handleException( Throwable e ) {
        ResponeThrowable ex;
        if ( e instanceof HttpException ) {
            HttpException httpException = ( HttpException ) e;
            ex = new ResponeThrowable( e, NetStatus.Error.getIndex() + "" );
            switch ( httpException.code() ) {
                case UNAUTHORIZED:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_401 );
                    break;
                case FORBIDDEN:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_403 );
                    break;
                case NOT_FOUND:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_404 );
                    break;
                case CLOSE_OPERATION_ENTRANCE:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_450 );
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_500 );
                    break;
                case 11:
                case 600:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_600 );
                    break;
                case 601:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_601 );
                    break;
                case 602:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_602 );
                    break;
                case 603:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_603 );
                    break;
                case 604:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_604 );
                    break;
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_604 ) + "错误码:(" + httpException.code() + ")";
                default:
                    ex.result = CommonApplication.getAppContext().getString( R.string.common_error_604 ) + "错误码:(" + httpException.code() + ")";
                    break;
            }
            return ex;
        } else if ( e instanceof ServerException ) {
            ServerException resultException = ( ServerException ) e;
            ex = new ResponeThrowable( resultException, resultException.status + "" );

            ProcessServerException processServerException = new ProcessServerException( resultException.status );
            processServerException.send();

            ex.result = resultException.result;
            return ex;
        } else if ( e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException ) {
            ex = new ResponeThrowable( e, ERROR.PARSE_ERROR );
            ex.result = "解析异常";
            return ex;
        } else if ( e instanceof ConnectException || e instanceof UnknownHostException ) {
            ex = new ResponeThrowable( e, ERROR.NETWORD_ERROR );
            ex.result = "无网络,请重试!";
            return ex;
        } else if ( e instanceof javax.net.ssl.SSLHandshakeException ) {
            ex = new ResponeThrowable( e, ERROR.SSL_ERROR );
            ex.result = "证书验证异常";
            return ex;
        } else if ( e instanceof ConnectTimeoutException || e instanceof java.net.SocketTimeoutException ) {
            ex = new ResponeThrowable( e, ERROR.TIMEOUT_ERROR );
            ex.result = "连接超时";
            return ex;
        } else {
            ex = new ResponeThrowable( e, ERROR.UNKNOWN );
            ex.result = "未知错误";
            return ex;
        }
    }

    /**
     * 约定异常
     */
    class ERROR {
        /**
         * 未知错误
         */
        public static final String UNKNOWN = "2000";
        /**
         * 解析错误
         */
        public static final String PARSE_ERROR = "2001";
        /**
         * 网络错误
         */
        public static final String NETWORD_ERROR = "2002";
        /**
         * 协议出错
         */
        public static final String HTTP_ERROR = "2003";

        /**
         * 证书出错
         */
        public static final String SSL_ERROR = "2005";

        /**
         * 连接超时
         */
        public static final String TIMEOUT_ERROR = "2006";

    }

}

