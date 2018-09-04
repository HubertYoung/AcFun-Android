package com.hubertyoung.common.image.fresco;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html.ImageGetter;
import android.text.TextUtils;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.MultiDraweeHolder;
import com.facebook.imagepipeline.image.ImageInfo;
import com.hubertyoung.common.constant.AppConfig;
import com.hubertyoung.common.utils.CommonLog;
import com.hubertyoung.common.utils.DisplayUtil;

import org.jetbrains.annotations.Contract;

import java.lang.ref.WeakReference;

import javax.annotation.Nullable;

import pl.droidsonroids.gif.GifDrawable;


/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 13:55
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.image.fresco
 */
public class FrescoImageGetter implements Drawable.Callback, ImageGetter, FrescoHtmlTextViewListener {
	private String TAG = "FrescoImageGetter";
	private Context mContext;
	private WeakReference< TextView > mTextViewWeakReference;
	private MultiDraweeHolder< GenericDraweeHierarchy > mMultiDraweeHolder;
	private static int widthPixels;

	/* compiled from: unknown */
	private static final class URLDrawable extends BitmapDrawable {

		private Drawable mDrawable;

		public void draw( Canvas canvas ) {
			if ( mDrawable != null ) {
				mDrawable.draw( canvas );
			}
			CommonLog.logi( "FrescoImageGetter", "URLDrawable" );
		}

		@Contract( pure = true )
		public Drawable getDrawable() {
			return mDrawable;
		}

		public void setDrawable( Drawable drawable ) {
			mDrawable = drawable;
		}
	}

	public FrescoImageGetter( Context context ) {
		mContext = context;
		mMultiDraweeHolder = new MultiDraweeHolder();
		widthPixels = ( int ) ( ( ( float ) DisplayUtil.getScreenWidth( context ) ) * 0.8f );
	}

	public void addTextView( TextView textView ) {
		mTextViewWeakReference = new WeakReference( textView );
		if ( mTextViewWeakReference.get() instanceof HtmlTextViewWatcher ) {
			( ( HtmlTextViewWatcher ) mTextViewWeakReference.get() ).setListener( this );
		}
	}

	public void invalidateDrawable( Drawable drawable ) {
		if ( mTextViewWeakReference != null && mTextViewWeakReference.get() != null ) {
			mTextViewWeakReference.get().invalidate();
		}
	}

	public void scheduleDrawable( Drawable drawable, Runnable runnable, long delayMillis ) {
		if ( mTextViewWeakReference != null && mTextViewWeakReference.get() != null ) {
			mTextViewWeakReference.get().postDelayed( runnable, delayMillis );
		}
	}

	public void unscheduleDrawable( Drawable drawable, Runnable runnable ) {
		if ( mTextViewWeakReference != null && mTextViewWeakReference.get() != null ) {
			mTextViewWeakReference.get().removeCallbacks( runnable );
		}
	}

	public Drawable getDrawable( String url ) {
		if ( TextUtils.isEmpty( url ) ) {
			return null;
		}
		if ( url.startsWith( "http" ) || url.startsWith( "https" ) ) {
			return getUrlDrawable( url );
		}
		return getUriDrawable( url );
	}

	private Drawable getUriDrawable( String uri ) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( uri );
		stringBuilder.append( AppConfig.EMOTION_FILE_SUFFIX );
		Drawable gifDrawable = null;
		try {
			String processedUrl = ( stringBuilder.toString() );
			if ( !TextUtils.isEmpty( processedUrl ) ) {
				if ( processedUrl.contains( "ac3/" ) || //
						processedUrl.contains( "brd/" ) || //
						processedUrl.contains( "td/" ) || //
						processedUrl.contains( "tsj/" ) || //
						processedUrl.contains( "dog/" ) ) {
					gifDrawable = new GifDrawable( mContext.getAssets(), processedUrl );
					gifDrawable.setCallback( this );
				}
				gifDrawable = Drawable.createFromStream( mContext.getAssets().open( processedUrl ), processedUrl );
				gifDrawable.setBounds( 0, 0, DisplayUtil.dip2px( 48.0f ), DisplayUtil.dip2px( 48.0f ) );
			}
		} catch ( Exception e ) {
			CommonLog.loge( e );
		}
		return gifDrawable;
	}

	public Drawable getUrlDrawable( String url ) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( "loadUrlDrawable:" );
		stringBuilder.append( url );
		CommonLog.logi( TAG, stringBuilder.toString() );
		URLDrawable uRLDrawable = new URLDrawable();
		DraweeHolder draweeHolder = new DraweeHolder( new GenericDraweeHierarchyBuilder( mContext.getResources() ).build() );
		mMultiDraweeHolder.add( draweeHolder );
		draweeHolder.setController( ( ( Fresco.newDraweeControllerBuilder()//
				.setUri( Uri.parse( url ) )//
				.setOldController( draweeHolder.getController() ) )//
				.setControllerListener( new ControllerListener< ImageInfo >() {
					public void processImage( String id, ImageInfo imageInfo, Animatable animatable ) {
						Drawable topLevelDrawable = draweeHolder.getHierarchy().getTopLevelDrawable();
						int width = imageInfo.getWidth();
						int height = imageInfo.getHeight();
						if ( width > widthPixels ) {
							height = ( height / width * widthPixels );
							width = widthPixels;
						}
						topLevelDrawable.setBounds( 0, 0, width, height );
						uRLDrawable.setBounds( 0, 0, width, height );
						uRLDrawable.setDrawable( topLevelDrawable );
						TextView textView = mTextViewWeakReference != null ? mTextViewWeakReference.get() : null;
						if ( textView != null ) {
							textView.setText( textView.getText() );
						}
						textView.postInvalidateDelayed( 500 );
					}

					public void processImage( String id, ImageInfo imageInfo ) {
						StringBuilder stringBuilder = new StringBuilder();
						stringBuilder.append( "onIntermediateImageSet width:" );
						stringBuilder.append( imageInfo.getWidth() );
						stringBuilder.append( ",height:" );
						stringBuilder.append( imageInfo.getHeight() );
						CommonLog.logi( TAG, stringBuilder.toString() );
					}

					@Override
					public void onSubmit( String id, Object callerContext ) {
						CommonLog.logi( TAG, "onSubmit" );
					}

					@Override
					public void onFinalImageSet( String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable ) {
						processImage( id, imageInfo, animatable );
					}

					@Override
					public void onIntermediateImageSet( String id, @Nullable ImageInfo imageInfo ) {
						processImage( id, imageInfo );
					}

					@Override
					public void onIntermediateImageFailed( String id, Throwable throwable ) {

					}

					@Override
					public void onFailure( String id, Throwable throwable ) {

					}

					public void onRelease( String str ) {
						CommonLog.logi( TAG, "onSubmit" );
					}
				} ) ).build() );
		return uRLDrawable;
	}

	public String miPushProcess( String url ) {
//		if ( TextUtils.isEmpty(str) || !str.contains(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP)) {
		if ( TextUtils.isEmpty( url ) || !url.contains( "/" ) ) {
			return "";
		}
//		String[] split = str.split(com.miPushProcessxiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
		String[] split = url.split( "/" );
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append( split[ 0 ] );
//		stringBuilder.append( HttpUtils.PATHS_SEPARATOR);
		stringBuilder.append( split[ 1 ] );
		url = stringBuilder.toString();
		StringBuilder stringBuilder2 = new StringBuilder();
		stringBuilder2.append( "file=" );
		stringBuilder2.append( url );
		CommonLog.logi( TAG, stringBuilder2.toString() );
		return url;
	}

	public boolean isSaveURLDrawable( Drawable drawable ) {
		return ( drawable instanceof URLDrawable ) && mMultiDraweeHolder.verifyDrawable( ( ( URLDrawable ) drawable ).getDrawable() );
	}

	public void onDetach() {
		mMultiDraweeHolder.onDetach();
	}

	public void onInvalidateAttach() {
		if ( mTextViewWeakReference != null ) {
			mTextViewWeakReference.get().postInvalidate();
		}
		mMultiDraweeHolder.onAttach();
	}
	public void onAttach() {
		mMultiDraweeHolder.onAttach();
	}
}