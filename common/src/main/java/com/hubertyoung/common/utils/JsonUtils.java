package com.hubertyoung.common.utils;


import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON解析二次封装
 */
public class JsonUtils {

	// 采取单例模式
	private volatile static Gson gson = new Gson();

	private JsonUtils() {
		gson.serializeNulls();
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
	public static String gsonToString( Object object ) {
		if ( null == object ) {
			return gson.toJson( JsonNull.INSTANCE );
		}
		try {
			return gson.toJson( object );
		} catch ( JsonSyntaxException e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param json
	 * @param classOfT
	 * @return
	 * @MethodName : fromJson
	 * @Description : 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
	 */
	public static < T > T gsonToBean( String json, Class< T > classOfT ) {
		try {
			return gson.fromJson( json, ( Type ) classOfT );
		} catch ( JsonSyntaxException e ) {
			e.printStackTrace();
		}
		return null;
	}

	public static < T > T gsonToBean( InputStream is, Class< T > classOfT ) {
		try {
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[ 4096 ];
			for (int n; ( n = is.read( b ) ) != -1; ) {
				out.append( new String( b, 0, n ) );
			}
			return gson.fromJson( out.toString(), ( Type ) classOfT );
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
	public static < T > List< T > gsonToList( String json, Class< T > cls ) {
		List< T > result = new ArrayList<>();
		if ( TextUtils.isEmpty( json ) ) return result;
		try {
			if ( null != gson ) {
				JsonArray array = new JsonParser().parse( json )
												  .getAsJsonArray();
				for (final JsonElement elem : array) {
					result.add( gson.fromJson( elem, cls ) );
				}
			}
			return result;
		} catch ( Exception e ) {
			CommonLog.loge( e.getMessage()
							  .toString() );
			return new ArrayList< T >();
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
			return gson.fromJson( json, typeOfT );
		} catch ( JsonSyntaxException e ) {
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
