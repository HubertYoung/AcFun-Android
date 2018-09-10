package com.hubertyoung.baseplatform.tools;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @author:Yang
 * @date:2017/11/17 13:29
 * @since:V1.0.0
 * @desc:ddframework.gent.common.framwork.tools
 * @param:
 */
public class Hashon {
	public Hashon() {
	}

	public < T > HashMap< String, T > fromJson( String jsonStr ) {
		if ( TextUtils.isEmpty( jsonStr ) ) {
			return new HashMap();
		} else {
			try {
				if ( jsonStr.startsWith( "[" ) && jsonStr.endsWith( "]" ) ) {
					jsonStr = "{\"fakelist\":" + jsonStr + "}";
				}

				JSONObject t = new JSONObject( jsonStr );
				return this.fromJson( t );
			} catch ( Throwable var3 ) {
				PayLogUtil.loge( var3.getMessage().toString() );
				return new HashMap();
			}
		}
	}

	private < T > HashMap< String, T > fromJson( JSONObject json ) throws JSONException {
		HashMap map = new HashMap();
		Iterator iKey = json.keys();

		while ( iKey.hasNext() ) {
			String key = ( String ) iKey.next();
			Object value = json.opt( key );
			if ( JSONObject.NULL.equals( value ) ) {
				value = null;
			}

			if ( value != null ) {
				if ( value instanceof JSONObject ) {
					value = this.fromJson( ( JSONObject ) value );
				} else if ( value instanceof JSONArray ) {
					value = this.fromJson( ( JSONArray ) value );
				}

				map.put( key, value );
			}
		}

		return map;
	}

	private ArrayList< Object > fromJson( JSONArray array ) throws JSONException {
		ArrayList list = new ArrayList();
		int i = 0;

		for (int size = array.length(); i < size; ++i) {
			Object value = array.opt( i );
			if ( value instanceof JSONObject ) {
				value = this.fromJson( ( JSONObject ) value );
			} else if ( value instanceof JSONArray ) {
				value = this.fromJson( ( JSONArray ) value );
			}

			list.add( value );
		}

		return list;
	}

	public < T > String fromHashMap( HashMap< String, T > map ) {
		try {
			JSONObject t = this.getJSONObject( map );
			return t == null ? "" : t.toString();
		} catch ( Throwable var3 ) {
			PayLogUtil.loge( var3.getMessage().toString() );
			return "";
		}
	}

	private < T > JSONObject getJSONObject( HashMap< String, T > map ) throws JSONException {
		JSONObject json = new JSONObject();

		Map.Entry entry;
		Object value;
		for (Iterator iterator = map.entrySet()
							  .iterator(); iterator.hasNext(); json.put( ( String ) entry.getKey(), value )) {
			entry = ( Map.Entry ) iterator.next();
			value = entry.getValue();
			if ( value instanceof HashMap ) {
				value = this.getJSONObject( ( HashMap ) value );
			} else if ( value instanceof ArrayList ) {
				value = this.getJSONArray( ( ArrayList ) value );
			} else if ( this.isBasicArray( value ) ) {
				value = this.getJSONArray( ( ArrayList< Object > ) this.arrayToList( value ) );
			}
		}

		return json;
	}

	private boolean isBasicArray( Object value ) {
		return value instanceof byte[] || value instanceof short[] || value instanceof int[] || value instanceof long[] || value instanceof float[] || value instanceof double[] || value instanceof
				char[] || value instanceof boolean[] || value instanceof String[];
	}

	private ArrayList< ? > arrayToList( Object value ) {
		ArrayList list;
		int len$;
		int i$;
		if ( value instanceof byte[] ) {
			list = new ArrayList();
			byte[] var15 = ( byte[] ) ( ( byte[] ) value );
			len$ = var15.length;

			for (i$ = 0; i$ < len$; ++i$) {
				byte var23 = var15[ i$ ];
				list.add( Byte.valueOf( var23 ) );
			}

			return list;
		} else if ( value instanceof short[] ) {
			list = new ArrayList();
			short[] var14 = ( short[] ) ( ( short[] ) value );
			len$ = var14.length;

			for (i$ = 0; i$ < len$; ++i$) {
				short var22 = var14[ i$ ];
				list.add( Short.valueOf( var22 ) );
			}

			return list;
		} else if ( value instanceof int[] ) {
			list = new ArrayList();
			int[] var13 = ( int[] ) ( ( int[] ) value );
			len$ = var13.length;

			for (i$ = 0; i$ < len$; ++i$) {
				int var21 = var13[ i$ ];
				list.add( Integer.valueOf( var21 ) );
			}

			return list;
		} else if ( value instanceof long[] ) {
			list = new ArrayList();
			long[] var12 = ( long[] ) ( ( long[] ) value );
			len$ = var12.length;

			for (i$ = 0; i$ < len$; ++i$) {
				long var20 = var12[ i$ ];
				list.add( Long.valueOf( var20 ) );
			}

			return list;
		} else if ( value instanceof float[] ) {
			list = new ArrayList();
			float[] var11 = ( float[] ) ( ( float[] ) value );
			len$ = var11.length;

			for (i$ = 0; i$ < len$; ++i$) {
				float var19 = var11[ i$ ];
				list.add( Float.valueOf( var19 ) );
			}

			return list;
		} else if ( value instanceof double[] ) {
			list = new ArrayList();
			double[] var10 = ( double[] ) ( ( double[] ) value );
			len$ = var10.length;

			for (i$ = 0; i$ < len$; ++i$) {
				double var18 = var10[ i$ ];
				list.add( Double.valueOf( var18 ) );
			}

			return list;
		} else if ( value instanceof char[] ) {
			list = new ArrayList();
			char[] var9 = ( char[] ) ( ( char[] ) value );
			len$ = var9.length;

			for (i$ = 0; i$ < len$; ++i$) {
				char var17 = var9[ i$ ];
				list.add( Character.valueOf( var17 ) );
			}

			return list;
		} else if ( value instanceof boolean[] ) {
			list = new ArrayList();
			boolean[] var8 = ( boolean[] ) ( ( boolean[] ) value );
			len$ = var8.length;

			for (i$ = 0; i$ < len$; ++i$) {
				boolean var16 = var8[ i$ ];
				list.add( Boolean.valueOf( var16 ) );
			}

			return list;
		} else if ( !( value instanceof String[] ) ) {
			return null;
		} else {
			list = new ArrayList();
			String[] arr$ = ( String[] ) ( ( String[] ) value );
			len$ = arr$.length;

			for (i$ = 0; i$ < len$; ++i$) {
				String item = arr$[ i$ ];
				list.add( item );
			}

			return list;
		}
	}

	private JSONArray getJSONArray( ArrayList< Object > list ) throws JSONException {
		JSONArray array = new JSONArray();

		Object value;
		for (Iterator i$ = list.iterator(); i$.hasNext(); array.put( value )) {
			value = i$.next();
			if ( value instanceof HashMap ) {
				value = this.getJSONObject( ( HashMap ) value );
			} else if ( value instanceof ArrayList ) {
				value = this.getJSONArray( ( ArrayList ) value );
			}
		}

		return array;
	}

	public String format( String jsonStr ) {
		try {
			return this.format( "", this.fromJson( jsonStr ) );
		} catch ( Throwable var3 ) {
			PayLogUtil.loge( var3.getMessage().toString() );
			return "";
		}
	}

	private String format( String sepStr, HashMap< String, Object > map ) {
		StringBuffer sb = new StringBuffer();
		sb.append( "{\n" );
		String mySepStr = sepStr + "\t";
		int i = 0;

		for (Iterator i$ = map.entrySet()
							  .iterator(); i$.hasNext(); ++i) {
			Map.Entry entry = ( Map.Entry ) i$.next();
			if ( i > 0 ) {
				sb.append( ",\n" );
			}

			sb.append( mySepStr )
			  .append( '\"' )
			  .append( ( String ) entry.getKey() )
			  .append( "\":" );
			Object value = entry.getValue();
			if ( value instanceof HashMap ) {
				sb.append( this.format( mySepStr, ( HashMap ) value ) );
			} else if ( value instanceof ArrayList ) {
				sb.append( this.format( mySepStr, ( ArrayList ) value ) );
			} else if ( value instanceof String ) {
				sb.append( '\"' )
				  .append( value )
				  .append( '\"' );
			} else {
				sb.append( value );
			}
		}

		sb.append( '\n' )
		  .append( sepStr )
		  .append( '}' );
		return sb.toString();
	}

	private String format( String sepStr, ArrayList< Object > list ) {
		StringBuffer sb = new StringBuffer();
		sb.append( "[\n" );
		String mySepStr = sepStr + "\t";
		int i = 0;

		for (Iterator i$ = list.iterator(); i$.hasNext(); ++i) {
			Object value = i$.next();
			if ( i > 0 ) {
				sb.append( ",\n" );
			}

			sb.append( mySepStr );
			if ( value instanceof HashMap ) {
				sb.append( this.format( mySepStr, ( HashMap ) value ) );
			} else if ( value instanceof ArrayList ) {
				sb.append( this.format( mySepStr, ( ArrayList ) value ) );
			} else if ( value instanceof String ) {
				sb.append( '\"' )
				  .append( value )
				  .append( '\"' );
			} else {
				sb.append( value );
			}
		}

		sb.append( '\n' )
		  .append( sepStr )
		  .append( ']' );
		return sb.toString();
	}
}
