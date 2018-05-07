package com.kento.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.kento.common.R;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.autolayout.utils.AutoUtils;


/*<com.snad.loadingbutton.LoadingButton
		android:id="@+id/second"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:layout_marginBottom="24dp"
        android:background="#FFE548"
        app:pbLoadingText="LOGIN"
        app:pbProgressColor="#582707"
        app:pbText="CLICK"
        app:pbTextColor="#7286A0"
        app:pbTextSize="16sp"/>*/
public class LoadingButton extends AutoRelativeLayout {

	//region Variables
	static final int DEFAULT_COLOR = android.R.color.white;
	public static final int IN_FROM_RIGHT = 0;
	public static final int IN_FROM_LEFT = 1;

	private int mDefaultTextSize;
	private ProgressBar mProgressBar;
	private TextSwitcher mTextSwitcher;
	/*这里是默认loading文案*/
	private String mLoadingMessage = "";
	/*这里是默认显示的文案*/
	private String mButtonText;
	private float mTextSize;
	private ColorStateList mTextColor;
	private boolean mIsLoadingShowing;
	private Typeface mTypeface;
	private Animation inRight;
	private Animation inLeft;
	private int mCurrentInDirection;
	private boolean mTextSwitcherReady;
	private int height;
	private int width;
	private boolean mEnable;
	//endregion

	//region Constructors
	public LoadingButton( Context context ) {
		super( context );
		init( context, null );
	}

	public LoadingButton( Context context, AttributeSet attrs ) {
		super( context, attrs );
		init( context, attrs );
	}

	public LoadingButton( Context context, AttributeSet attrs, int defStyleAttr ) {
		super( context, attrs, defStyleAttr );
		init( context, attrs );
	}

	@Override
	protected void onMeasure( int widthMeasureSpec, int heightMeasureSpec ) {
		super.onMeasure( widthMeasureSpec, heightMeasureSpec );
		width = getMeasuredWidth();
		height = getMeasuredHeight();
	}

	public float getTextSize() {
		return mTextSize;
	}

	public Typeface getTypeface() {
		return mTypeface;
	}

	public void setProgressColor( int colorRes ) {
		mProgressBar.getIndeterminateDrawable()
					.mutate()
					.setColorFilter( colorRes, PorterDuff.Mode.SRC_ATOP );
	}

	public void setTypeface( @NonNull Typeface typeface ) {
		this.mTypeface = typeface;
	}

	public void setAnimationInDirection( int inDirection ) {
		mCurrentInDirection = inDirection;
	}

	public void setText( String text ) {
		if ( text != null ) {
			mButtonText = text;
			if ( mTextSwitcherReady ) {
//				mTextSwitcher.setInAnimation( mCurrentInDirection == IN_FROM_LEFT ? inLeft : inRight );
				mTextSwitcher.setText( mButtonText );
			}
		}
	}

	public String getText() {
		return mButtonText;
	}

	public void setLoadingText( String text ) {
		if ( text != null ) {
			mLoadingMessage = text;
		}
	}

	public void setTextFactory( @NonNull ViewSwitcherFactory factory ) {
		mTextSwitcher.removeAllViewsInLayout();
		mTextSwitcher.setFactory( factory );
		mTextSwitcher.setText( mButtonText );
	}

	public void showLoading() {
		if ( !mIsLoadingShowing ) {
			mProgressBar.setVisibility( View.VISIBLE );
			mTextSwitcher.setText( mLoadingMessage );
			mTextSwitcher.setVisibility( TextUtils.isEmpty( mLoadingMessage ) ? View.GONE : View.VISIBLE );
			mIsLoadingShowing = true;
			setEnabled( false );
		}
	}

	public void showButtonText() {
		if ( mIsLoadingShowing ) {
			mProgressBar.setVisibility( View.GONE );
			mTextSwitcher.setVisibility( View.VISIBLE );
			mTextSwitcher.setText( mButtonText );
			mIsLoadingShowing = false;
			setEnabled( true );
		}
		invalidate();
	}
	public void showButtonText(boolean isEnable) {
		if ( mIsLoadingShowing ) {
			mProgressBar.setVisibility( View.GONE );
			mTextSwitcher.setVisibility( View.VISIBLE );
			mTextSwitcher.setText( mButtonText );
			mIsLoadingShowing = false;
			setEnabled( isEnable );
		}
		invalidate();
	}

	public boolean isLoadingShowing() {
		return mIsLoadingShowing;
	}

	private void init( Context context, AttributeSet attrs ) {
		mDefaultTextSize = getResources().getDimensionPixelSize( R.dimen.text_size34px );
		mIsLoadingShowing = false;
		LayoutInflater.from( getContext() )
					  .inflate( R.layout.view_loading_button, this, true );

		mProgressBar = ( ProgressBar ) findViewById( R.id.pb_progress );
		mTextSwitcher = ( TextSwitcher ) findViewById( R.id.pb_text );


		if ( attrs != null ) {
			TypedArray a = context.getTheme()
								  .obtainStyledAttributes( attrs, R.styleable.LoadingButton, 0, 0 );
			try {
				float textSize = a.getDimensionPixelSize( R.styleable.LoadingButton_pbTextSize, mDefaultTextSize );
				setTextSize( textSize );

				String text = a.getString( R.styleable.LoadingButton_pbText );
				setText( text );

				mLoadingMessage = a.getString( R.styleable.LoadingButton_pbLoadingText );
				mEnable = a.getBoolean( R.styleable.LoadingButton_pbEnable, true );

				if ( mLoadingMessage == null ) {
					mLoadingMessage = "";
				}

				@SuppressLint( "ResourceAsColor" ) int progressColor = a.getColor( R.styleable.LoadingButton_pbProgressColor, DEFAULT_COLOR );
				setProgressColor( progressColor );

				ColorStateList textColor = a.getColorStateList( R.styleable.LoadingButton_pbTextColor );
				setTextColor( textColor );

			} finally {
				a.recycle();
			}
		} else {
			int white = getResources().getColor( DEFAULT_COLOR );
			mLoadingMessage = "";
			setProgressColor( white );
			setTextColor( null );
			setTextSize( mDefaultTextSize );
		}
		setEnabled( mEnable );
		setupTextSwitcher();
	}

	private void setupTextSwitcher() {
		ViewSwitcherFactory factory = new ViewSwitcherFactory( getContext(), mTextColor, mTextSize, mTypeface );
		mTextSwitcher.setFactory( factory );

		inRight = AnimationUtils.loadAnimation( getContext(), R.anim.loading_button_slide_in_right );
		inLeft = AnimationUtils.loadAnimation( getContext(), R.anim.loading_button_slide_in_left );
		Animation out = AnimationUtils.loadAnimation( getContext(), R.anim.loading_button_fade_out );
//		mTextSwitcher.setOutAnimation( out );
//		mTextSwitcher.setInAnimation( inRight );
		mTextSwitcher.setText( mButtonText );
		mTextSwitcherReady = true;
		AutoUtils.autoTextSize( mTextSwitcher );
	}

	private void setTextSize( float size ) {
		mTextSize = AutoUtils.getPercentWidthSize( ( int ) size );
	}

	private void setTextColor( ColorStateList textColor ) {
		this.mTextColor = ( textColor != null ? textColor : ColorStateList.valueOf( 0xffffffff ) );
	}

	public static class ViewSwitcherFactory implements ViewSwitcher.ViewFactory {

		//region Variables
		private final ColorStateList textColor;
		private final float textSize;
		private final Typeface typeFace;
		private final Context context;
		//endregion

		//region Constructor
		public ViewSwitcherFactory( Context context, ColorStateList textColor, float textSize, Typeface typeface ) {
			this.context = context;
			this.textColor = textColor;
			this.textSize = textSize;
			this.typeFace = typeface;
		}
		//endregion

		@Override
		public View makeView() {
			TextView tv = new TextView( context );
			tv.setTextColor( textColor );
			tv.setTextSize( TypedValue.COMPLEX_UNIT_PX, textSize );
			tv.setGravity( Gravity.CENTER );
			tv.setTypeface( typeFace );

			return tv;
		}
	}
}