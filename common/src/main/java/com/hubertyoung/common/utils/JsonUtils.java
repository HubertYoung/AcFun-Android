package com.hubertyoung.common.utils;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON解析二次封装
 */
public class JsonUtils {

	private JsonUtils() {
//		sJSONObject.serializeNulls();
		//                gson.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE) //设置字段(即Key值)首字母大写——暂时没用
//                gson.setPrettyPrinting()   //对JSON结果格式化，添加换行等——生成JSON数据的时候用
//                gson.setVersion(1.0)       //设置版本号——暂时没用
	}

	/**
	 * @param object :将要被转化的对象
	 * @return :转化后的JSON串
	 * @MethodName : toJson
	 * @Description : 将对象转为JSON串，此方法能够满足大部分需求
	 */
	public static String objToString( Object object ) {
		String objString = null;
		objString = com.alibaba.fastjson.JSONObject.toJSONString( object );
		return objString;
	}

	/**
	 * @param json
	 * @param classOfT
	 * @return
	 * @MethodName : fromJson
	 * @Description : 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
	 */
	public static < T > T jsonToBean( String json, Class< T > classOfT ) {
		try {
			return JSON.parseObject( json, classOfT );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	public static < T > T jsonToBean( InputStream is, Class< T > classOfT ) {
		try {
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[ 4096 ];
			for (int n; ( n = is.read( b ) ) != -1; ) {
				out.append( new String( b, 0, n ) );
			}
			return JSON.parseObject( out.toString(), classOfT );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author:Yang
	 * @date:2017-8-2 14:12:48
	 * @since:v1.0
	 * @desc:com.hubertyoung.common.commonutils
	 * @param: 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
	 */
	public static < T > List< T > jsonToList( String json, Class< T > cls ) {
		List< T > result = new ArrayList<>();
		if ( TextUtils.isEmpty( json ) ) return result;
		try {
			result.addAll( JSONArray.parseArray( json, cls ) );
			return result;
		} catch ( Exception e ) {
			result.clear();
			return result;
		}
	}

	/**
	 * @param json
	 * @param typeOfT
	 * @return
	 * @MethodName : fromJson
	 * @Description : 用来将JSON串转为对象，此方法可用来转带泛型的集合，如：Type为 new
	 * TypeToken<List<T>>(){}.getType()
	 * ，其它类也可以用此方法调用，就是将List<T>替换为你想要转成的类
	 */
	public static Object fromJson( String json, Type typeOfT ) {
		try {
			return JSON.parseObject( json, typeOfT );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取json中的某个值
	 *
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getValue( String json, String key ) {
		try {
			JSONObject object = new JSONObject( json );
			return object.getString( key );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取json中的list值
	 *
	 * @param json
	 * @return
	 */
	public static String getListValue( String json ) {
		try {
			JSONObject object = new JSONObject( json );
			return object.getString( "list" );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	public static Double getDoubleValue( String json, String key ) {
		try {
			JSONObject object = new JSONObject( json );
			return object.getDouble( key );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getIntValue( String json, String key ) {
		try {
			JSONObject object = new JSONObject( json );
			return object.getInt( key );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return 0;
	}

}
