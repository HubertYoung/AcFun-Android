package com.kento.component.basic.commonwidget.limitscrollerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kento.component.basic.R;
import com.kento.component.basic.commonutils.TimeUtil;
import com.kento.component.basic.commonutils.imageloader.ImageLoaderUtils;

import java.util.List;


/**
 * @author:Yang
 * @date:2017/8/29 23:09
 * @since:V1.0
 * @desc:ddframework.gent.common.commonwidget
 * @param:显示控件
 */
public class ShowWinnerTextView extends LinearLayout {

	private final Context mContext;
	private int height;//滚动高度（控件高度）
	private int limit;          //可见条目数量
	private int position;          //索引
	private int durationTime;   //动画执行时间
	private int periodTime;     //间隔时间
	private List< WinnerEntity > winnerList;
	private LinearLayout linearLayoutContent;
	private ObjectAnimator animator;

	private boolean isCancel;      //是否停止滚动动画
	private boolean boundData;     //是否已经第一次绑定过数据

	private final int MSG_SETDATA = 1;
	private final int MSG_SCROL = 2;

	private int scrollHeight;

	private OnClickListener listener;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage( Message msg ) {
			if ( msg.what == MSG_SETDATA ) {
//				boundData( true );
			} else if ( msg.what == MSG_SCROL ) {
				if ( isCancel ) return;

				initAnimation();
				startAnima();
			}
		}
	};

	public ShowWinnerTextView( Context context ) {
		this( context, null );
	}

	public ShowWinnerTextView( Context context, AttributeSet attrs ) {
		this( context, attrs, 0 );
	}

	public ShowWinnerTextView( Context context, AttributeSet attrs, int defStyleAttr ) {
		super( context, attrs, defStyleAttr );
		this.mContext = context;
		initData( context, attrs, defStyleAttr );
	}

	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {

		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
		//设置高度为整体高度的一般，以达到遮盖预备容器的效果
//		setMeasuredDimension( getMeasuredWidth(), getMeasuredHeight() / 2 );
		//此处记下控件的高度，此高度就是动画执行时向上滚动的高度
		height = getMeasuredHeight();
		setMeasuredDimension( getMeasuredWidth(), getMeasuredHeight() * limit / ( limit + 1 ) );
		scrollHeight = getMeasuredHeight() / ( limit );
	}

	private void initData( Context context, AttributeSet attrs, int defStyleAttr ) {
		if ( attrs != null ) {
			TypedArray ta = context.obtainStyledAttributes( attrs, R.styleable.LimitScroller );
			limit = ta.getInt( R.styleable.LimitScroller_limit, 1 );
			position = limit;
			durationTime = ta.getInt( R.styleable.LimitScroller_durationTime, 1000 );
			periodTime = ta.getInt( R.styleable.LimitScroller_periodTime, 1000 );
			ta.recycle();  //注意回收
		}
		linearLayoutContent = new LinearLayout( getContext() );
		linearLayoutContent.setOrientation( LinearLayout.VERTICAL );
		linearLayoutContent.setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT ) );
		addView( linearLayoutContent );
	}

	public void setListener( OnClickListener listener ) {
		this.listener = listener;
	}

	public void setData( List< WinnerEntity > adList ) {
		if ( adList == null || adList.isEmpty() ) return;
		winnerList = adList;

		linearLayoutContent.removeAllViews();

		for (int i = 0; i < limit + 1; i++) {
			View view = LayoutInflater.from( mContext )
									  .inflate( R.layout.item_winner_textview, null );
			ImageView ivItemWinnerHeadpic = ( ImageView ) view.findViewById( R.id.iv_item_winner_headpic );
			TextView tvItemWinnerNick = ( TextView ) view.findViewById( R.id.tv_item_winner_nick );
			TextView tvItemWinnerCommodity = ( TextView ) view.findViewById( R.id.tv_item_winner_commodity );
			TextView tvItemWinnerTime = ( TextView ) view.findViewById( R.id.tv_item_winner_time );
			ImageLoaderUtils.getInstance()
							.displayRound( mContext, ivItemWinnerHeadpic, winnerList.get( i ).HeadPic );
			tvItemWinnerNick.setText( winnerList.get( i ).UserName );
			tvItemWinnerCommodity.setText( winnerList.get( i ).PrizeName );
			tvItemWinnerTime.setText( TimeUtil.formatDateStrByDesc( winnerList.get( i ).CreateTime ) );

			view.findViewById( R.id.ll_item_winner_root )
				.setOnClickListener( new OnClickListener() {

					@Override
					public void onClick( View v ) {

						if ( listener != null ) {
							listener.onClick( v );
						}
					}
				} );

			linearLayoutContent.addView( view );

		}

		invalidate();

		startScroll();

	}

	/**
	 * 2、开始滚动
	 * 应该在两处调用此方法：
	 * ①、Activity.onStart()
	 * ②、MyLimitScrllAdapter.setDatas()
	 */
	public void startScroll() {
		if ( winnerList == null || winnerList.isEmpty() ) return;
		if ( !boundData ) {
			handler.sendEmptyMessage( MSG_SETDATA );
		}
		isCancel = false;
		handler.removeMessages( MSG_SCROL );   //先清空所有滚动消息，避免滚动错乱
		handler.sendEmptyMessageDelayed( MSG_SCROL, periodTime );
	}

	public void initAnimation() {
		animator = ObjectAnimator.ofFloat( linearLayoutContent, "translationY", 0, -scrollHeight );
		animator.setDuration( durationTime );
		animator.setInterpolator( new AnticipateOvershootInterpolator() );

		animator.addListener( new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart( Animator animation ) {
				ConstraintLayout winnerLayout = ( ConstraintLayout ) linearLayoutContent.getChildAt( 0 );
				linearLayoutContent.removeView( winnerLayout );
				ImageLoaderUtils.getInstance().displayRound( mContext,( ImageView ) winnerLayout.getChildAt( 0 ), winnerList.get( position ).HeadPic );
				( ( TextView ) winnerLayout.getChildAt( 1 ) ).setText( winnerList.get( position ).UserName );
				( ( TextView ) winnerLayout.getChildAt( 3 ) ).setText( winnerList.get( position ).PrizeName );
				( ( TextView ) winnerLayout.getChildAt( 4 ) ).setText( TimeUtil.formatDateStrByDesc( winnerList.get( position ).CreateTime ) );
				winnerLayout.setTag( winnerList.get( position ) );

				linearLayoutContent.addView( winnerLayout );

				if ( ++position >= winnerList.size() ) {
					position = 0;
				}

			}

			@Override
			public void onAnimationEnd( Animator animation ) {
				if ( handler == null ) return;
				handler.removeMessages( MSG_SCROL );
				if ( isCancel ) {
					return;
				}
				handler.sendEmptyMessageDelayed( MSG_SCROL, periodTime );
			}

			@Override
			public void onAnimationCancel( Animator animation ) {

			}

			@Override
			public void onAnimationRepeat( Animator animation ) {


			}
		} );

	}

	public void startAnima() {
		animator.start();
	}

	public void cancelAnimation() {
		if ( animator != null ) {
			animator.cancel();
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if ( handler != null ) {
			handler.removeMessages( MSG_SCROL );   //先清空所有滚动消息，避免滚动错乱
			handler = null;
		}
		cancelAnimation();
	}
}
