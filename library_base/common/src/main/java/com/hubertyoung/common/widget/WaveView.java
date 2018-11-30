package com.hubertyoung.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.hubertyoung.common.R;

import java.util.Random;


/**
 * des:波浪view
 */
public class WaveView extends View {

	private Path mAbovePath, mBelowWavePath;
	private Paint mAboveWavePaint, mBelowWavePaint;
	private DrawFilter mDrawFilter;
	private float φ;
	private int rangeY = 8;//Y轴波动幅度
	int max = 20;
	int min = 10;
	private OnWaveAnimationListener mWaveAnimationListener;
	private float randomFloat = 1.1f;
	private int solidColor;

	public WaveView( Context context ) {
		this( context, null );
	}

	public WaveView( Context context, AttributeSet attrs ) {
		super( context, attrs );
		init( context, attrs );

	}

	private void init( Context context, AttributeSet attrs ) {
		if ( attrs != null ) {
			TypedArray a = context.getTheme()
								  .obtainStyledAttributes( attrs, R.styleable.WaveView, 0, 0 );
			try {
				rangeY = a.getInt( R.styleable.WaveView_wvRangeY, 8 );
				solidColor = a.getColor( R.styleable.WaveView_wvSolidColor, ContextCompat.getColor( context,R.color.colorPrimary ) );
			} finally {
				a.recycle();
			}
		}
		//初始化路径
		mAbovePath = new Path();
		mBelowWavePath = new Path();
		//初始化画笔
		mAboveWavePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		mAboveWavePaint.setAntiAlias( true );
		mAboveWavePaint.setStyle( Paint.Style.FILL );
		//fde533
		mAboveWavePaint.setColor(solidColor );

		mBelowWavePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		mBelowWavePaint.setAntiAlias( true );
		mBelowWavePaint.setStyle( Paint.Style.FILL );
		mBelowWavePaint.setColor( solidColor  );
		mBelowWavePaint.setAlpha( 80 );
		//画布抗锯齿
		mDrawFilter = new PaintFlagsDrawFilter( 0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG );

		randomFloat = new Random().nextInt( 10 ) / 10F + 1;
	}

	@Override
	protected void onDraw( Canvas canvas ) {

		canvas.setDrawFilter( mDrawFilter );

		mAbovePath.reset();
		mBelowWavePath.reset();

		φ -= 0.1f;

		float y, y2;

		double ω = 2 * Math.PI / getWidth();

		mAbovePath.moveTo( getLeft(), getBottom() );
		mBelowWavePath.moveTo( getLeft(), getBottom() );
		for (float x = 0; x <= getWidth(); x += 20) {
			/**
			 *  y=Asin(ωx+φ)+k
			 *  A—振幅越大，波形在y轴上最大与最小值的差值越大
			 *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
			 *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
			 *  k—偏距，反映在坐标系上则为图像的上移或下移。
			 */
			y = ( float ) ( rangeY * Math.cos( 1.1 * ω * x + 1.3 * φ ) + 30 );
			y2 = ( float ) ( rangeY * Math.sin( ω * x + φ ) + 20 );

			mAbovePath.lineTo( x, y );
			mBelowWavePath.lineTo( x, y2 );
			//回调 把y坐标的值传出去(在activity里面接收让小机器人随波浪一起摇摆)
			if ( mWaveAnimationListener != null ) mWaveAnimationListener.OnWaveAnimation( y );
		}
		mAbovePath.lineTo( getRight(), getBottom() );
		mBelowWavePath.lineTo( getRight(), getBottom() );

		canvas.drawPath( mBelowWavePath, mBelowWavePaint );
		canvas.drawPath( mAbovePath, mAboveWavePaint );

		postInvalidateDelayed( ( long ) ( 20 * randomFloat ) );

	}

	public void setOnWaveAnimationListener( OnWaveAnimationListener l ) {
		this.mWaveAnimationListener = l;
	}

	public interface OnWaveAnimationListener {
		void OnWaveAnimation( float y );
	}

	/**
	 * 设置波动幅度
	 *
	 * @param rangeY
	 */
	public void setRangeY( int rangeY ) {
		this.rangeY = rangeY;
	}
}


