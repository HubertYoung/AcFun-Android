package com.kento.common;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.kento.common.widget.glideimageview.progress.OnGlideImageViewListener;
import com.kento.common.widget.glideimageview.progress.OnProgressListener;
import com.kento.common.widget.glideimageview.progress.ProgressManager;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {

	private OnGlideImageViewListener onGlideImageViewListener;
	private int duration = 500;

	public ImageLoaderUtils() {
	}

	public static ImageLoaderUtils getInstance() {
		return new ImageLoaderUtils();
	}

	/**
	 * 暂定请求
	 *
	 * @param context
	 */
	public void pauseRequests( Context context ) {
		Glide.with( context )
			 .pauseRequests();
	}

	/**
	 * 恢复请求
	 *
	 * @param context
	 */
	public void resumeRequests( Context context ) {
		Glide.with( context )
			 .resumeRequests();
	}

//	public void display( @NonNull Context context, @NonNull ImageView imageView, String url, int placeholder, int error ) {
//		Glide.with( context )
//			 .load( url )
//			 .apply( new RequestOptions().error( error )
//										 .placeholder( placeholder )
////                                                 .diskCacheStrategy( DiskCacheStrategy.ALL )
//										 .dontAnimate() )
//			 .thumbnail( 0.2f )
////			 .transition( new DrawableTransitionOptions().crossFade( duration ) )
//			 .into( imageView );
//	}

	public void display( @NonNull Context context, @NonNull ImageView imageView, @NonNull String url ) {
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
//										 .fallback( R.drawable.banner_default )
										 .dontAnimate() )
			 .thumbnail( 0.2f )
//			 .transition( new DrawableTransitionOptions().crossFade( duration ) )
			 .into( imageView );
	}

	public void display( @NonNull Context context, @NonNull ImageView imageView, String url, boolean isCenter, @DrawableRes int res ) {
		if ( isCenter ) {
			Glide.with( context )
				 .load( url )
				 .apply( new RequestOptions()
//						 .error( res == 0 ? R.drawable.banner_default : res )
//											 .placeholder( res == 0 ? R.drawable.banner_default : res )
											 .diskCacheStrategy( DiskCacheStrategy.ALL )
											 .centerCrop()
											 .dontAnimate() )
//				 .transition( new DrawableTransitionOptions().crossFade( duration ) )
				 .thumbnail( 0.2f )
				 .into( imageView );
		} else {
			Glide.with( context )
				 .load( url )
				 .apply( new RequestOptions()
//						 .error( res == 0 ? R.drawable.banner_default : res )
//											 .placeholder( res == 0 ? R.drawable.banner_default : res )
											 .diskCacheStrategy( DiskCacheStrategy.ALL )
											 .dontAnimate() )
				 .thumbnail( 0.2f )
//				 .transition( new DrawableTransitionOptions().crossFade( duration ) )
				 .into( imageView );
		}
	}

	public void display( @NonNull Context context, @NonNull ImageView imageView, File url, @DrawableRes int res ) {
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
//										 .error( res == 0 ? R.drawable.banner_default : res )
//										 .placeholder( res == 0 ? R.drawable.banner_default : res )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate() )
			 .into( imageView );
	}

	public void display( @NonNull Context context, @NonNull ImageView imageView, File url) {
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate() )
			 .into( imageView );
	}

	public void displaySmallPhoto( @NonNull Context context, @NonNull ImageView imageView, String url ) {
		Glide.with( context )
			 .load( url )
//                .asBitmap()
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate() )
			 .thumbnail( 0.5f )
			 .into( imageView );
	}

	public void displayBigPhoto( @NonNull Context context, @NonNull ImageView imageView, String url ) {
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate()
										 .format( DecodeFormat.PREFER_ARGB_8888 ) )
			 .into( imageView );
	}

	public void displayBlurTrans( @NonNull Context context, @NonNull ImageView imageView, String url, @IntRange( from = 0, to = 25 ) int radius, @IntRange( from = 0, to = 4 ) int sampling ) {
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .bitmapTransform( new BlurTransformation( radius, sampling ) ) )// “23”：设置模糊度(在0.0到25.0之间)，默认”25";"4":图片缩放比例,默认“1”。
			 .thumbnail( 0.5f )
			 .into( imageView );
	}

	public void displayBigPhoto( @NonNull Context context, @NonNull ImageView imageView, String url, OnGlideImageViewListener onGlideImageViewListener ) {
		this.onGlideImageViewListener = onGlideImageViewListener;
		addProgressListener( url );
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate()
										 .format( DecodeFormat.PREFER_ARGB_8888 )
										 .override( Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL ) )
			 .into( imageView );
	}

	public void display( @NonNull Context context, @NonNull ImageView imageView, int url, boolean isPlace ) {
		RequestOptions requestOptions = new RequestOptions().centerCrop()
//															.error( R.drawable.banner_default )
															.diskCacheStrategy( DiskCacheStrategy.ALL )
															.dontAnimate()
															.format( DecodeFormat.PREFER_ARGB_8888 );
//		if ( isPlace ) requestOptions.placeholder( R.drawable.ic_image_loading );
		Glide.with( context )
			 .load( url )
			 .apply( requestOptions )
			 .transition( new DrawableTransitionOptions().crossFade( duration ) )
			 .into( imageView );
	}

	public void displayRound( @NonNull Context context, @NonNull ImageView imageView, String url ) {
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate()
										 .format( DecodeFormat.PREFER_ARGB_8888 )
										 .transform( new CircleCrop() ) )
			 .into( imageView );
	}

	public void displayRound( @NonNull Context context, @NonNull ImageView imageView, String url, @DrawableRes int resId ) {
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
										 .error( resId )
//										 .placeholder( resId )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate()
//										 .format( DecodeFormat.PREFER_ARGB_8888 )
										 .transform( new CircleCrop() ) )
			 .thumbnail( 0.2f )
			 .into( imageView );
	}

	public void displayRound( @NonNull Context context, @NonNull ImageView imageView, File url, int placeholder, int error ) {
		Glide.with( context )
			 .load( url )
			 .apply( new RequestOptions().centerCrop()
										 .error( error )
										 .placeholder( placeholder )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate()
										 .format( DecodeFormat.PREFER_ARGB_8888 )
										 .transform( new CircleCrop() ) )
			 .into( imageView );
	}

	public void displayRound( @NonNull Context context, @NonNull ImageView imageView, int resId ) {
		Glide.with( context )
			 .load( resId )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate()
										 .format( DecodeFormat.PREFER_ARGB_8888 )
										 .transform( new CircleCrop() ) )
			 .into( imageView );
	}

	public void displayGif( @NonNull Context context, @NonNull ImageView imageView, String url ) {
		Glide.with( context )
			 .load( url )
			 .transition( new DrawableTransitionOptions().crossFade( duration ) )
			 .into( imageView );
	}

	private static String BASE_PHOTO_URL = "";

	/**
	 * @param url
	 * @return
	 */
	public static String getImageUrl( String url ) {
		if ( !TextUtils.isEmpty( url ) ) {
			if ( url.contains( "http" ) || new File( url ).isFile() ) {
				return url;
			} else {
				return BASE_PHOTO_URL + url;
			}
		} else {
			return "";
		}
	}

	/**
	 * @param context
	 * @param imageView
	 * @param uri       加载uri图片
	 */
	public void displayRound( @NonNull Context context, @NonNull ImageView imageView, @NonNull Uri uri ) {
		Glide.with( context )
			 .load( uri )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate()
										 .format( DecodeFormat.PREFER_ARGB_8888 )
										 .priorityOf( Priority.LOW )
//										 .fallback( R.drawable.banner_default )
										 .transform( new CircleCrop() ) )
			 .into( imageView );
	}


	private void addProgressListener( @NonNull final String url ) {
		OnProgressListener internalProgressListener = new OnProgressListener() {
			@Override
			public void onProgress( String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception ) {
				if ( totalBytes == 0 ) return;
				if ( !url.equals( imageUrl ) ) return;
				if ( 0 == bytesRead && false == isDone ) return;

				mainThreadCallback( bytesRead, totalBytes, isDone, exception );

				if ( isDone ) {
					ProgressManager.removeProgressListener( this );
				}
			}
		};
		ProgressManager.addProgressListener( internalProgressListener );
	}

	private void mainThreadCallback( final long bytesRead, final long totalBytes, final boolean isDone, final GlideException exception ) {
		new Handler( Looper.getMainLooper() ).post( new Runnable() {
			@Override
			public void run() {
				final int percent = ( int ) ( ( bytesRead * 1.0f / totalBytes ) * 100.0f );
//                if (onProgressListener != null) {
//                    onProgressListener.onProgress((String) mImageUrlObj, bytesRead, totalBytes, isDone, exception);
//                }

				if ( onGlideImageViewListener != null ) {
					onGlideImageViewListener.onProgress( percent, isDone );
				}
			}
		} );
	}

	public void display( @NonNull Context context, @NonNull ImageView imageView, @NonNull Uri uri ) {
		Glide.with( context )
			 .load( uri )
			 .apply( new RequestOptions().centerCrop()
//										 .error( R.drawable.banner_default )
//										 .placeholder( R.drawable.banner_default )
										 .diskCacheStrategy( DiskCacheStrategy.ALL )
										 .dontAnimate()
										 .format( DecodeFormat.PREFER_ARGB_8888 )
										 .priorityOf( Priority.LOW )
//										 .fallback( R.drawable.banner_default )
			 )
			 .into( imageView );
	}
}
