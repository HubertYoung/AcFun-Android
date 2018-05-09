package com.kento.common.net.factory;

import com.google.gson.Gson;
import com.kento.common.net.config.NetStatus;
import com.kento.common.net.response.BaseResponse;
import com.kento.common.utils.CommonLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * 作者：JIUU on 2017-7-10 16:00:51
 * QQ号：1344393464
 * 作用：gson响应体
 */

public class ExGsonResponseBodyConverter< T > implements Converter< ResponseBody, T > {

    private final Gson gson;
    private final Type type;

    ExGsonResponseBodyConverter( Gson gson, Type type ) {
        this.gson = gson;
        this.type = type;

    }

    /**
     * 进行解析预处理操作
     *
     * @param responseBody
     * @return
     * @throws IOException
     */
    @Override
    public T convert( ResponseBody responseBody ) throws IOException {
        String value = responseBody.string();

        BaseResponse baseRespose = new BaseResponse();
//		Type objectType = type( baseRespose.getClass(), type );
        try {
            JSONObject response = new JSONObject( value );
            int status = response.getInt( "status" );
            CommonLog.loge("status：" + status);
            if ( ( status == NetStatus.Success.getIndex() ) || ( status == NetStatus.Server_Success.getIndex() ) ) {
				return gson.fromJson( value, type );
            }else{
				baseRespose.setStatus( status );
				baseRespose.setData( response.getString( "data" ) );
				baseRespose.setResult( response.getString( "result" ) );
				return ( T ) gson.fromJson( value, baseRespose.getClass() );
			}
        } catch ( JSONException e ) {
        }
        return gson.fromJson( value, type );
    }

//	public ParameterizedType type( final Class raw, final Type... args ) {
//		return new ParameterizedType() {
//			public Type getRawType() {
//				return raw;
//			}
//
//			public Type[] getActualTypeArguments() {
//				return args;
//			}
//
//			public Type getOwnerType() {
//				return null;
//			}
//		};
//	}
}
