package com.hubertyoung.common.image.fresco;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

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
	public static void display( Context context, String str, SimpleDraweeView simpleDraweeView ) {
		display( str, simpleDraweeView, true, null );
	}

	public static void display( String str, SimpleDraweeView simpleDraweeView, boolean isAutoPlay, ControllerListener controllerListener ) {
		if ( TextUtils.isEmpty( str ) ) {
			str = "http://";
		}
		ImageRequest build = ImageRequestBuilder.newBuilderWithSource( Uri.parse( str ) ).setProgressiveRenderingEnabled( false ).build();
		PipelineDraweeControllerBuilder newDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
		newDraweeControllerBuilder.setImageRequest( build ).setAutoPlayAnimations( isAutoPlay );
		if ( controllerListener != null ) {
			newDraweeControllerBuilder.setControllerListener( controllerListener );
		}
		simpleDraweeView.setController( newDraweeControllerBuilder.build() );
	}
}
