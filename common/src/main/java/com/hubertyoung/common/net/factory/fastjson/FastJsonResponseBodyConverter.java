package com.hubertyoung.common.net.factory.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.hubertyoung.common.net.config.NetStatus;
import com.hubertyoung.common.net.response.BaseResponse;
import com.hubertyoung.common.utils.CommonLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class FastJsonResponseBodyConverter< T > implements Converter< ResponseBody, T > {

	private static final Feature[] EMPTY_SERIALIZER_FEATURES = new Feature[ 0 ];

	private Type mType;

	private ParserConfig config;
	private int featureValues;
	private Feature[] features;

	FastJsonResponseBodyConverter( Type type, ParserConfig config, int featureValues, Feature... features ) {
		mType = type;
		this.config = config;
		this.featureValues = featureValues;
		this.features = features;
	}

	@Override
	public T convert( ResponseBody responseBody ) throws IOException {
		String value = responseBody.string();

		BaseResponse baseRespose = new BaseResponse();
//		Type objectType = type( baseRespose.getClass(), type );
		try {
			JSONObject response = new JSONObject( value );
			int status = NetStatus.Server_Fail.getIndex();
			if ( response.has( "errorid" ) ) {
				status = response.getInt( "errorid" );
			}
			CommonLog.logi( "value ==> " + value );
			if ( ( status == NetStatus.Success.getIndex() ) || ( status == NetStatus.Server_Success.getIndex() ) ) {
				return com.alibaba.fastjson.JSONObject.parseObject( value, mType, config, featureValues, features != null ? features : EMPTY_SERIALIZER_FEATURES );
			} else if ( response.has( "code" ) && response.has( "vdata" ) && response.has( "message" ) ) {
				baseRespose.setStatus( status );
				if ( response.has( "code" ) ) {
					baseRespose.setCode( response.getInt( "code" ) );
				}
				if ( response.has( "vdata" ) ) {
					baseRespose.setData( response.getString( "vdata" ) );
				}
				if ( response.has( "message" ) ) {
					baseRespose.setResult( response.getString( "message" ) );
				}
//				return ( T ) gson.fromJson( value, baseRespose.getClass() );
				return ( T ) com.alibaba.fastjson.JSONObject.parseObject( value, baseRespose.getClass(), config, featureValues, features != null ? features : EMPTY_SERIALIZER_FEATURES );
			} else {
				return JSON.parseObject( value, mType, config, featureValues, features != null ? features : EMPTY_SERIALIZER_FEATURES );
			}
		} catch ( JSONException e ) {

		} finally {
			responseBody.close();
		}
		return JSON.parseObject( value, mType, config, featureValues, features != null ? features : EMPTY_SERIALIZER_FEATURES );
	}
}
