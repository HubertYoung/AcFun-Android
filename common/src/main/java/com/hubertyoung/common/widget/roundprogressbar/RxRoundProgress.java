package com.hubertyoung.common.widget.roundprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.acty.litemall.R;


/**
 * @author:Yang
 * @date:2017/12/2 16:40
 * @since:V1.0
 * @desc:com.kento.common.commonwidget.roundprogressbar
 * @param:圆形进度条
 */
public class RxRoundProgress extends View {
	public static final int STROKE = 0;
	/**
	 * 画笔对象的引用
	 */
	private Paint paint;
	/**
	 * 圆环的颜色
	 */
	private int roundColor;
	/**
	 * 圆环进度的颜色
	 */
	private int roundProgressColor;
	/**
	 * 圆环的宽度
	 */
	private float roundWidth;
	/**
	 * 最大进度
	 */
	private double max;
	/**
	 * 当前进度
	 */
	private double progress;
	/**
	 * 进度的风格，实心或者空心
	 */
	private int style;


	public RxRoundProgress( Context context ) {
		this( context, null );
	}

	public RxRoundProgress( Context context, AttributeSet attrs ) {
		this( context, attrs, 0 );
	}

	public RxRoundProgress( Context context, AttributeSet attrs, int defStyle ) {
		super( context, attrs, defStyle );

		paint = new Paint();//进度条画笔
		TypedArray mTypedArray = context.obtainStyledAttributes( attrs, R.styleable.RxRoundProgress );

		//获取自定义属性和默认值
		roundColor = mTypedArray.getColor( R.styleable.RxRoundProgress_roundColor, Color.WHITE );
		roundProgressColor = mTypedArray.getColor( R.styleable.RxRoundProgress_roundProgressColor, Color.parseColor( "#F6B141" ) );
		roundWidth = mTypedArray.getDimension( R.styleable.RxRoundProgress_roundWidth, 20 );
		max = mTypedArray.getInteger( R.styleable.RxRoundProgress_max, 100 );
		style = mTypedArray.getInt( R.styleable.RxRoundProgress_style, 0 );

		mTypedArray.recycle();
	}


	@Override
	protected void onDraw( Canvas canvas ) {
		super.onDraw( canvas );

		/**
		 * 画最外层的大圆环
		 */
		int centre = getWidth() / 2; //获取圆心的x坐标
		int radius = ( int ) ( centre - roundWidth / 2 ); //圆环的半径
		RectF oval = new RectF( centre - radius, centre - radius, centre + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限
		paint.setColor( roundColor ); //设置圆环的颜色
		paint.setStyle( Paint.Style.STROKE ); //设置空心
		paint.setStrokeWidth( roundWidth ); //设置圆环的宽度
		paint.setAntiAlias( true );  //消除锯齿
		paint.setStrokeCap( Paint.Cap.ROUND );//设置边缘为圆角
//		canvas.drawRect(0, 0, getWidth(), getWidth(), paint);// 正方形  
		canvas.drawArc( oval, -90, 360, false, paint );  //根据进度画圆弧
		int percent = ( int ) ( ( ( float ) progress / ( float ) max ) * 100 );  //中间的进度百分比，先转换成float在进行除法运算，不然都为0
		float textWidth = paint.measureText( percent + "%" );   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间
		/**
		 * 画圆弧 ，画圆环的进度
		 */

		//设置进度是实心还是空心
		paint.setStrokeWidth( roundWidth ); //设置圆环的宽度
		paint.setColor( roundProgressColor );  //设置进度的颜色

		switch ( style ) {
			case STROKE: {
				paint.setStyle( Paint.Style.STROKE );
				if ( progress >= 0 ) {
					canvas.drawArc( oval, -90, 360 * ( ( float ) progress / ( float ) max ), false, paint );  //根据进度画圆弧
				}
				break;
			}
		}

	}

	public synchronized double getMax() {
		return max;
	}

	/**
	 * 设置进度的最大值
	 *
	 * @param max
	 */
	public synchronized void setMax( double max ) {
		if ( max < 0 ) {
			throw new IllegalArgumentException( "max not less than 0" );
		}
		this.max = max;
	}

	/**
	 * 获取进度.需要同步
	 *
	 * @return
	 */
	public synchronized double getProgress() {
		return progress;
	}

	/**
	 * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
	 * 刷新界面调用postInvalidate()能在非UI线程刷新
	 *
	 * @param progress
	 */
	public synchronized void setProgress( double progress ) {
		if ( progress < 0 ) {
			this.progress = progress;
			//throw new IllegalArgumentException("progress not less than 0");
		}
		if ( progress > max ) {
			progress = max;
		}
		if ( progress <= max ) {
			this.progress = progress;
			postInvalidate();
		}

	}


	public int getCricleColor() {
		return roundColor;
	}

	public void setCricleColor( int cricleColor ) {
		this.roundColor = cricleColor;
	}

	public int getCricleProgressColor() {
		return roundProgressColor;
	}

	public void setCricleProgressColor( int cricleProgressColor ) {
		this.roundProgressColor = cricleProgressColor;
	}

	public float getRoundWidth() {
		return roundWidth;
	}

	public void setRoundWidth( float roundWidth ) {
		this.roundWidth = roundWidth;
	}


}
