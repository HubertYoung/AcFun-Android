package com.kento.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * view底部呈现波浪动画效果
 */

public class WaveViewDown extends View {

	private Path mAbovePath, mBelowWavePath, mBelow2WavePath;
	private Paint mAboveWavePaint, mBelowWavePaint, mBelow2WavePaint;

	private DrawFilter mDrawFilter;
	private int rangeY = 12;//Y轴波动幅度
	private float φ;

	private OnWaveAnimationListener mWaveAnimationListener;

	public WaveViewDown( Context context, AttributeSet attrs ) {
		super( context, attrs );
		//初始化路径
		mAbovePath = new Path();
		mBelowWavePath = new Path();
		mBelow2WavePath = new Path();
		//初始化画笔
		mAboveWavePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		mAboveWavePaint.setAntiAlias( true );
		mAboveWavePaint.setStyle( Paint.Style.FILL );
		mAboveWavePaint.setColor( Color.WHITE );

		mBelowWavePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		mBelowWavePaint.setAntiAlias( true );
		mBelowWavePaint.setStyle( Paint.Style.FILL );
		mBelowWavePaint.setColor( Color.WHITE );
		mBelowWavePaint.setAlpha( 80 );

		mBelow2WavePaint = new Paint( Paint.ANTI_ALIAS_FLAG );
		mBelow2WavePaint.setAntiAlias( true );
		mBelow2WavePaint.setStyle( Paint.Style.FILL );
		mBelow2WavePaint.setColor( Color.WHITE );
		mBelow2WavePaint.setAlpha( 125 );

		//画布抗锯齿
		mDrawFilter = new PaintFlagsDrawFilter( 0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG );
	}

	@Override
	protected void onDraw( Canvas canvas ) {

		canvas.setDrawFilter( mDrawFilter );

		mAbovePath.reset();
		mBelowWavePath.reset();
		mBelow2WavePath.reset();

		φ -= 0.1f;

		float y, y2, y3;

		double ω = 2 * Math.PI / getWidth();

		mAbovePath.moveTo( getLeft(), getBottom() );
		mBelowWavePath.moveTo( getLeft(), getBottom() );
		mBelow2WavePath.moveTo( getLeft(), getBottom() );
		for (float x = 0; x <= getWidth(); x += 20) {
			/**
			 *  y=Asin(ωx+φ)+k
			 *  A—振幅越大，波形在y轴上最大与最小值的差值越大
			 *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
			 *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
			 *  k—偏距，反映在坐标系上则为图像的上移或下移。
			 */
			y = ( float ) ( rangeY * Math.cos( 1.1 * ω * x + 1.3 * φ ) + 30 );
			y2 = ( float ) ( rangeY * Math.sin( ω * x + φ ) + 15 );
			y3 = ( float ) ( rangeY * Math.sin( 1.2 * ω * x + 0.5 * φ ) + 15 );
			mAbovePath.lineTo( x, y );
			mBelowWavePath.lineTo( x, y2 );
			mBelow2WavePath.lineTo( x, y3 );
			//回调 把y坐标的值传出去(在activity里面接收让小机器人随波浪一起摇摆)
			if ( mWaveAnimationListener != null ) {
				mWaveAnimationListener.OnWaveAnimation( y );
			}
		}
		mAbovePath.lineTo( getRight(), getBottom() );
		mBelowWavePath.lineTo( getRight(), getBottom() );
		mBelow2WavePath.lineTo( getRight(), getBottom() );

		canvas.drawPath( mAbovePath, mAboveWavePaint );
		canvas.drawPath( mBelowWavePath, mBelowWavePaint );
		canvas.drawPath( mBelow2WavePath, mBelow2WavePaint );

		postInvalidateDelayed( 20 );

	}

	//imageview随波浪一起动
	public void setOnWaveAnimationListener( OnWaveAnimationListener l ) {
		this.mWaveAnimationListener = l;
	}

	public interface OnWaveAnimationListener {
		void OnWaveAnimation( float y );
	}

}


