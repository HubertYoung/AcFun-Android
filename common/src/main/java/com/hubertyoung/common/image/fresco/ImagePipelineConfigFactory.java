package com.hubertyoung.common.image.fresco;

import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.HashSet;
import java.util.Set;

import okhttp3.OkHttpClient;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/9/4 13:39
 * @since:V$VERSION
 * @desc:com.hubertyoung.common.image.fresco
 */
public class ImagePipelineConfigFactory {
	private static final String IMAGE_PIPELINE_CACHE_DIR = "imagepipeline_cache";
	private static ImagePipelineConfig sImagePipelineConfig;
	private static ImagePipelineConfig sOkHttpImagePipelineConfig;

	/**
	 * 使用Android自带的网络加载图片
	 */
	public static ImagePipelineConfig getImagePipelineConfig( Context context ) {
		if ( sImagePipelineConfig == null ) {
			ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder( context );
			configBuilder.setProgressiveJpegConfig( mProgressiveJpegConfig );
			configBuilder.setBitmapsConfig( Bitmap.Config.ARGB_4444 );
			configureCaches( configBuilder, context );
			configureLoggingListeners( configBuilder );
			configureOptions( configBuilder );
			sImagePipelineConfig = configBuilder.build();
		}
		return sImagePipelineConfig;
	}

	/**
	 * 使用OkHttp网络库加载图片
	 */
	public static ImagePipelineConfig getOkHttpImagePipelineConfig( Context context ) {
		if ( sOkHttpImagePipelineConfig == null ) {
			OkHttpClient okHttpClient = new OkHttpClient();
			ImagePipelineConfig.Builder configBuilder = OkHttpImagePipelineConfigFactory.newBuilder( context, okHttpClient );
			configureCaches( configBuilder, context );
			configureLoggingListeners( configBuilder );
			sOkHttpImagePipelineConfig = configBuilder.build();
		}
		return sOkHttpImagePipelineConfig;
	}

	/**
	 * 配置内存缓存和磁盘缓存
	 */
	private static void configureCaches( ImagePipelineConfig.Builder configBuilder, Context context ) {
		final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams( ConfigConstants.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
				Integer.MAX_VALUE,                     // Max entries in the cache
				ConfigConstants.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
				Integer.MAX_VALUE,                     // Max length of eviction queue
				Integer.MAX_VALUE );                    // Max cache entry size
		configBuilder.setBitmapMemoryCacheParamsSupplier( new Supplier< MemoryCacheParams >() {
			@Override
			public MemoryCacheParams get() {
				return bitmapCacheParams;
			}
		} )
				.setMainDiskCacheConfig( DiskCacheConfig.newBuilder( context )
						.setBaseDirectoryPath( context.getApplicationContext().getCacheDir() )
						.setBaseDirectoryName( IMAGE_PIPELINE_CACHE_DIR )
						.setMaxCacheSize( ConfigConstants.MAX_DISK_CACHE_SIZE )
						.build() );
	}

	private static void configureLoggingListeners( ImagePipelineConfig.Builder configBuilder ) {
		Set< RequestListener > requestListeners = new HashSet<>();
		requestListeners.add( new RequestLoggingListener() );
		configBuilder.setRequestListeners( requestListeners );
	}

	private static void configureOptions( ImagePipelineConfig.Builder configBuilder ) {
		configBuilder.setDownsampleEnabled( true );
	}

	//渐进式图片
	static ProgressiveJpegConfig mProgressiveJpegConfig = new ProgressiveJpegConfig() {
		@Override
		public int getNextScanNumberToDecode( int scanNumber ) {
			return scanNumber + 2;
		}

		@Override
		public QualityInfo getQualityInfo( int scanNumber ) {
			boolean isGoodEnough = ( scanNumber >= 5 );
			return ImmutableQualityInfo.of( scanNumber, isGoodEnough, false );
		}
	};
}
