package multiimageview;

import android.os.Bundle;

import com.hubertyoung.component_multiimageview.R;
import com.hubertyoung.component_multiimageview.multiimageview.ImageAttr;
import com.hubertyoung.component_multiimageview.multiimageview.MultiImageView;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

	@BindView( R.id.btn_change )
	AppCompatButton mBtnChange;
	@BindView( R.id.btn_clear )
	AppCompatButton mBtnClear;
	@BindView( R.id.multiImagView )
	MultiImageView mMultiImagView;
	private String[] imgs= {"http://i2.17173cdn.com/2fhnvk/YWxqaGBf/cms3/fjANkWbkDFfmuuD.jpg","http://i1.17173cdn.com/2fhnvk/YWxqaGBf/cms3/eVJuzhbkDFfmveD.jpg"};

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.multiimageview_activity_main );
		ButterKnife.bind( this );
		List<ImageAttr> list = new ArrayList<>();
		RxView.clicks( mBtnChange )
				.throttleFirst( 500, TimeUnit.MILLISECONDS )
				.subscribe( new Consumer< Object >() {
					@Override
					public void accept( Object o ) throws Exception {
						list.add( new ImageAttr( imgs[ new Random().nextInt( 2 ) ] ) );
						mMultiImagView.setList( list );
					}
				} );
		RxView.clicks( mBtnClear )
				.throttleFirst( 500,TimeUnit.MILLISECONDS )
				.subscribe( new Consumer< Object >() {
					@Override
					public void accept( Object o ) throws Exception {
						list.clear();
						mMultiImagView.setList( list );
					}
				} );
	}
}
