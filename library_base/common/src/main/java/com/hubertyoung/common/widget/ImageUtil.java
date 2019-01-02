//package com.hubertyoung.common.widget;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.util.Base64;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//public class ImageUtil {
//	/**
//	 * bitmap转为base64
//	 *
//	 * @param bitmap
//	 * @return
//	 */
//	public static String bitmapToBase64( Bitmap bitmap ) {
//
//		String result = null;
//		ByteArrayOutputStream baos = null;
//		try {
//			if ( bitmap != null ) {
//				baos = new ByteArrayOutputStream();
//				bitmap.compress( Bitmap.CompressFormat.JPEG, 100, baos );
//
//				baos.flush();
//				baos.close();
//
//				byte[] bitmapBytes = baos.toByteArray();
//				result = Base64.encodeToString( bitmapBytes, Base64.DEFAULT );
//			}
//		} catch ( IOException e ) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if ( baos != null ) {
//					baos.flush();
//					baos.close();
//				}
//			} catch ( IOException e ) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	/**
//	 * base64转为bitmap
//	 *
//	 * @param base64Data
//	 * @return
//	 */
//	public static Bitmap base64ToBitmap( String base64Data ) {
//		byte[] bytes = Base64.decode( base64Data, Base64.DEFAULT );
//		return BitmapFactory.decodeByteArray( bytes, 0, bytes.length );
//	}
//
//	/**
//	 * base64转为bitmap
//	 *
//	 * @param str
//	 * @return
//	 */
//	public static Bitmap customBase64ToBitmap( String str ) {
//		try {
//			int indexOf = str.indexOf( "base64," );
//			if ( indexOf > 0 ) {
//				str = str.substring( indexOf + "base64,".length() );
//			}
//			return base64ToBitmap( str );
//		} catch ( Exception e ) {
//			return null;
//		}
//	}
//}
