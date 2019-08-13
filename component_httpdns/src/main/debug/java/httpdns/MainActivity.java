package httpdns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hubertyoung.component_httpdns.R;
import com.hubertyoung.httpdns.HttpDnsMonitor;
import com.hubertyoung.httpdns.HttpDnsServiceProvider;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {

  private TextView mResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView( R.layout.activity_main);
    mResult = findViewById(R.id.result);
    findViewById(R.id.startParse).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new Thread(new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i < Constants.TEST_HOSTS.size(); i++) {
              try {
                InetAddress.getAllByName( Constants.TEST_HOSTS.get( i ) );
              } catch (UnknownHostException e) {
                e.printStackTrace();
              }
            }
          }
        }).start();
      }
    });

    findViewById(R.id.clearLog).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        mResult.setText("");
      }
    });

    HttpDnsServiceProvider.getInstance().setMonitor( new HttpDnsMonitor() {
      @Override
      public void onHttpDnsParseEnd(final String host, final String ip) {
        mResult.post(new Runnable() {
          @Override
          public void run() {
            mResult.setText(mResult.getText() + "\n\r" + "host:" + host + ";ip:" + ip);
          }
        });
      }
    });

  }
}
