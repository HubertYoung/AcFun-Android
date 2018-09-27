package bankcard;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.hubertyoung.component_bankcard.R;
import com.hubertyoung.component_bankcard.bankcard.BankInfoUtil;
import com.hubertyoung.component_bankcard.bankcard.ContentWithSpaceEditText;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

	@BindView( R.id.cws_bankcardnum )
	ContentWithSpaceEditText mCwsBankcardnum;
	@BindView( R.id.tv_bank_name )
	AppCompatTextView mTvBankName;
	@BindView( R.id.rg_bankcard )
	RadioGroup mRgBankcard;

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.bankcard_activity_main );
		ButterKnife.bind( this );

		RxTextView.textChanges( mCwsBankcardnum )
				.throttleLast( 500, TimeUnit.MILLISECONDS )
				.map( charSequence -> {
					BankInfoUtil mInfoUtil = new BankInfoUtil( charSequence.toString().replace( " ", "" ) );
					StringBuffer buffer = new StringBuffer();
					if ( !TextUtils.isEmpty( mInfoUtil.getBankName() ) ) {
						buffer.append( mInfoUtil.getBankName() );
					} else {
						return "";
					}
					if ( !TextUtils.isEmpty( mInfoUtil.getCardType() ) ) {
						buffer.append( " - " + mInfoUtil.getCardType() );
					}
					return buffer.toString();
				} )
				.observeOn( AndroidSchedulers.mainThread() )
				.subscribe( new Consumer< String >() {
					@Override
					public void accept( String s ) throws Exception {
						mTvBankName.setText( s );
					}
				} );
		RxRadioGroup.checkedChanges( mRgBankcard )
				.observeOn( AndroidSchedulers.mainThread() )
				.subscribe( new Consumer< Integer >() {
					@Override
					public void accept( Integer integer ) throws Exception {
						switch ( integer ) {
							case 1:
								mCwsBankcardnum.setContentType( ContentWithSpaceEditText.TYPE_PHONE );
							    break;
							case 2:
								mCwsBankcardnum.setContentType( ContentWithSpaceEditText.TYPE_BANK_CARD );
								break;
							case 3:
								mCwsBankcardnum.setContentType( ContentWithSpaceEditText.TYPE_ID_CARD );
								break;
						}
					}
				} );
		(( RadioButton )mRgBankcard.getChildAt( 0 )).setChecked( true );
	}
}
