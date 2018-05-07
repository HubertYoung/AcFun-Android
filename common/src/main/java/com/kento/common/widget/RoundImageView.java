package com.kento.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.kento.common.R;


/**
 * @author:Yang
 * @date:2017/8/4 10:29
 * @since:v1.0
 * @desc:com.kento.common.commonwidget
 * @param:圆形头像
 */
public class RoundImageView extends AppCompatImageView {
	private static final int STROKE_WIDTH = 0;
	private Context mContext;

	public RoundImageView( Context context ) {
		this( context, null );
	}

	public RoundImageView( Context context, AttributeSet attrs ) {
		this( context, attrs, 0 );
	}

	public RoundImageView( Context context, AttributeSet attrs, int defStyle ) {
		super( context, attrs, defStyle );
		this.mContext = context;
	}

	@SuppressWarnings( "unused" )
	@Override
	protected void onDraw( Canvas canvas ) {

		Drawable drawable = getDrawable();

		if ( drawable == null ) {
			return;
		}

		if ( getWidth() == 0 || getHeight() == 0 ) {
			return;
		}

		Bitmap b = drawableToBitmap( drawable );

		if ( null == b ) {
			return;
		}

		Bitmap bitmap = b.copy( Config.ARGB_8888, true );

		int w = getWidth(), h = getHeight();

		Bitmap roundBitmap = getCroppedBitmap( bitmap, w );
		canvas.drawBitmap( roundBitmap, 0, 0, null );

	}

	@SuppressWarnings( "unused" )
	public Bitmap getCroppedBitmap( Bitmap bmp, int radius ) {
		Bitmap sbmp;
		if ( bmp.getWidth() != radius || bmp.getHeight() != radius ) sbmp = Bitmap.createScaledBitmap( bmp, radius, radius, false );
		else sbmp = bmp;
		Bitmap output = Bitmap.createBitmap( sbmp.getWidth(), sbmp.getHeight(), Config.ARGB_8888 );
		Canvas canvas = new Canvas( output );

		final int color = 0xffa19774;
		final Paint paint = new Paint();
		final Rect rect = new Rect( 0, 0, sbmp.getWidth(), sbmp.getHeight() );

		paint.setAntiAlias( true );
		paint.setFilterBitmap( true );
		paint.setDither( true );
		canvas.drawARGB( 0, 0, 0, 0 );
		paint.setColor( ContextCompat.getColor( mContext, R.color.colorPrimary ) );
		canvas.drawCircle( sbmp.getWidth() / 2, sbmp.getHeight() / 2 , sbmp.getWidth() / 2, paint );
		paint.setXfermode( new PorterDuffXfermode( Mode.SRC_IN ) );
		canvas.drawBitmap( sbmp, rect, rect, paint );

		paint.reset();

//		paint.setColor( ContextCompat.getColor( mContext, R.color.colorPrimary ) );
//		paint.setStyle( Paint.Style.STROKE );
//		paint.setStrokeWidth( STROKE_WIDTH );
//		paint.setAntiAlias( true );
//		canvas.drawCircle( radius / 2, radius / 2, radius / 2 - STROKE_WIDTH / 2, paint );
		return output;
	}

	public Bitmap drawableToBitmap( Drawable drawable ) {

		Bitmap bitmap = Bitmap.createBitmap(

				drawable.getIntrinsicWidth(),

				drawable.getIntrinsicHeight(),

				drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888

						: Config.RGB_565 );

		Canvas canvas = new Canvas( bitmap );

		// canvas.setBitmap(bitmap);

		drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight() );

		drawable.draw( canvas );

		return bitmap;

	}
}
