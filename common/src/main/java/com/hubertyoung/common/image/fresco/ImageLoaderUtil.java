package com.hubertyoung.common.image.fresco;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.hubertyoung.common.CommonApplication;

import jp.wasabeef.fresco.processors.BlurPostprocessor;
import jp.wasabeef.fresco.processors.CombinePostProcessors;
import jp.wasabeef.fresco.processors.GrayscalePostprocessor;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 19:34
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.image.fresco
 */
public class ImageLoaderUtil {

	private ImageLoaderUtil() {
		throw new AssertionError( "No instances." );
	}

	public static void loadNetImage( String url, SimpleDraweeView simpleDraweeView ) {
		if ( TextUtils.isEmpty( url ) ) {
			url = "http://";
		}
		Uri uri = Uri.parse( url );
		loadImage( uri, simpleDraweeView );
	}

	public static void loadNetImage( String url, SimpleDraweeView simpleDraweeView, boolean isAutoPlay, ControllerListener controllerListener ) {
		if ( TextUtils.isEmpty( url ) ) {
			url = "http://";
		}
		Uri uri = Uri.parse( url );
		loadImage( uri, simpleDraweeView, isAutoPlay, controllerListener );
	}

	public static void loadLocalImage( String fileName, SimpleDraweeView simpleDraweeView ) {
		Uri uri = Uri.parse( "file://" + fileName );
		loadImage( uri, simpleDraweeView );
	}

	public static void loadResourceImage( @DrawableRes int resId, SimpleDraweeView simpleDraweeView ) {
		Uri uri = Uri.parse( "res:///" + resId );
		loadImage( uri, simpleDraweeView );
	}

	public static void loadContentProviderImage( @IdRes int resId, SimpleDraweeView simpleDraweeView ) {
		Uri uri = Uri.parse( "content:///" + resId );
		loadImage( uri, simpleDraweeView );
	}

	public static void loadAssetImage( int resId, SimpleDraweeView simpleDraweeView ) {
		Uri uri = Uri.parse( "asset:///" + resId );
		loadImage( uri, simpleDraweeView );
	}

	public static void loadImage( Uri uri, SimpleDraweeView simpleDraweeView ) {
		loadImage( uri, simpleDraweeView, true, null );
	}

	public static void loadImage( Uri uri, SimpleDraweeView simpleDraweeView, boolean isAutoPlay ) {
		loadImage( uri, simpleDraweeView, isAutoPlay, null );
	}

	private static void loadImage( Uri uri, SimpleDraweeView simpleDraweeView, boolean isAutoPlay, ControllerListener controllerListener ) {
		//设置Hierarchy
//		setHierarchay(simpleDraweeView.getHierarchy());

		ImageRequest build = ImageRequestBuilder.newBuilderWithSource( uri )//
				.setProgressiveRenderingEnabled( false )//
				.build();
		PipelineDraweeControllerBuilder newDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
		newDraweeControllerBuilder.setImageRequest( build )//
				.setAutoPlayAnimations( isAutoPlay );
		if ( controllerListener != null ) {
			newDraweeControllerBuilder.setControllerListener( controllerListener );
		}
		simpleDraweeView.setController( newDraweeControllerBuilder.build() );
	}

	public static void loadImageSmallToBig( Uri smallUri, Uri bigUri, SimpleDraweeView simpleDraweeView ) {
		//设置Hierarchy
		setHierarchay( simpleDraweeView.getHierarchy() );
		//构建小图的图片请求
		ImageRequest smallRequest = getImageRequest( smallUri, simpleDraweeView );
		//构建大图的图片请求
		ImageRequest bigRequest = getImageRequest( bigUri, simpleDraweeView );
		//构建Controller
		DraweeController draweeController = getSmallToBigController( smallRequest, bigRequest, simpleDraweeView.getController() );
		//开始加载
		simpleDraweeView.setController( draweeController );
	}

	//加载网络图片，先加载小图，待大图加载完成后替换
	public static void loadNetImageSmallToBig( String smallUrl, String bigUrl, SimpleDraweeView simpleDraweeView ) {
		Uri smallUri = Uri.parse( smallUrl );
		Uri bigUri = Uri.parse( bigUrl );
		loadImageSmallToBig( smallUri, bigUri, simpleDraweeView );
	}

	public static DraweeController getSmallToBigController( ImageRequest smallRequest, ImageRequest bigRequest, @Nullable DraweeController oldController ) {

		PipelineDraweeControllerBuilder builder = Fresco.newDraweeControllerBuilder();
		builder.setLowResImageRequest( smallRequest );//小图的图片请求
		builder.setImageRequest( bigRequest );//大图的图片请求
		builder.setTapToRetryEnabled( false );//设置是否允许加载失败时点击再次加载
		builder.setAutoPlayAnimations( true );//设置是否允许动画图自动播放
		builder.setOldController( oldController );
		return builder.build();
	}

	//对Hierarchy进行设置，如各种状态下显示的图片
	public static void setHierarchay( GenericDraweeHierarchy hierarchy ) {
		if ( hierarchy != null ) {
			//重新加载显示的图片
//			hierarchy.setRetryImage(retryImage);
//			//加载失败显示的图片
//			hierarchy.setFailureImage(failureImage, ScalingUtils.ScaleType.CENTER_CROP);
//			//加载完成前显示的占位图
//			hierarchy.setPlaceholderImage(placeholderImage, ScalingUtils.ScaleType.CENTER_CROP);
			//设置加载成功后图片的缩放模式
			hierarchy.setActualImageScaleType( ScalingUtils.ScaleType.CENTER_CROP );

			//显示加载进度条，使用自带的new ProgressBarDrawable()
			//默认会显示在图片的底部，可以设置进度条的颜色。
			hierarchy.setProgressBarImage( new ProgressBarDrawable() );
			//设置图片加载为圆形
			hierarchy.setRoundingParams( RoundingParams.asCircle() );
			//设置图片加载为圆角，并可设置圆角大小
//			hierarchy.setRoundingParams(RoundingParams.fromCornersRadius(radius));

			//其他设置请查看具体API。
		}
	}

	/**
	 * 构建、获取ImageRequest
	 *
	 * @param uri              加载路径
	 * @param simpleDraweeView 加载的图片控件
	 * @return ImageRequest
	 */
	public static ImageRequest getImageRequest( Uri uri, SimpleDraweeView simpleDraweeView ) {
		int width;
		int height;
		if ( Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN ) {
			width = simpleDraweeView.getWidth();
			height = simpleDraweeView.getHeight();
		} else {
			width = simpleDraweeView.getMaxWidth();
			height = simpleDraweeView.getMaxHeight();
		}

		//根据请求路径生成ImageRequest的构造者
		ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource( uri );
		//调整解码图片的大小
		if ( width > 0 && height > 0 ) {
			builder.setResizeOptions( new ResizeOptions( width, height ) );
		}
		//设置是否开启渐进式加载，仅支持JPEG图片
		builder.setProgressiveRenderingEnabled( true );

		//图片变换处理
		CombinePostProcessors.Builder processorBuilder = new CombinePostProcessors.Builder();
		//加入模糊变换
		processorBuilder.add( new BlurPostprocessor( CommonApplication.getAppContext(), 20 ) );
		//加入灰白变换
		processorBuilder.add( new GrayscalePostprocessor() );
		//应用加入的变换
		builder.setPostprocessor( processorBuilder.build() );
		//更多图片变换请查看https://github.com/wasabeef/fresco-processors
		return builder.build();
	}
}
