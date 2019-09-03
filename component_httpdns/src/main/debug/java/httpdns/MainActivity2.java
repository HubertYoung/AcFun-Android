package httpdns;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hubertyoung.common.base.AbsLifecycleActivity;
import com.hubertyoung.common.utils.data.SPUtils;
import com.hubertyoung.common.utils.display.DisplayUtil;
import com.hubertyoung.common.utils.os.TimeUtil;
import com.hubertyoung.component_httpdns.R;

import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import httpdns.vm.MainActivity2ViewModel;


public class MainActivity2 extends AbsLifecycleActivity< MainActivity2ViewModel > {

    private TableLayout mTlRoot;
    private Map< String, String > map = new TreeMap();
    private Map< String, String > headMap = new TreeMap();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView( @Nullable Bundle savedInstanceState ) {
        super.initView( savedInstanceState );
        map.put( "signSection", TimeUtil.getCurrentDate( TimeUtil.dateFormatYMD ) + " 06:00:00#" + TimeUtil.getNextDay( 1, TimeUtil.dateFormatYMD ) + " 02:00:00" );
        map.put( "min", "480" );
        map.put( "longitude", "116.4822" + new Random().nextInt( 99 ) );
        map.put( "latitude", "39.991708" + new Random().nextInt( 99 ) );
        map.put( "position", "北京市朝阳区望京街靠近方恒国际中心" );
        map.put( "sid", "sw-bj" );
        map.put( "mac", "DC:FE:18:00:08:2A" );
        map.put( "deviceId", "db10e3a7-b9d6-4537-bca8-dd9ecb177b3d" );
        map.put( "browser", "EMobile" );
        map.put( "_ec_ismobile", "true" );
        map.put( "_ec_browser", "EMobile" );
        map.put( "_ec_browserVersion", "7.0.32" );
        map.put( "_ec_os", "iOS" );
        map.put( "_ec_osVersion", "12.1" );
        map.put( "ismobile", "1" );

        mTlRoot = ( TableLayout ) findViewById( R.id.tl_root );
        Iterator< Map.Entry< String, String > > entries = map.entrySet().iterator();
        while ( entries.hasNext() ) {
            Map.Entry< String, String > entry = entries.next();
            String key = entry.getKey();
            String value = entry.getValue();
            addView( key, value );
        }
        headMap.put( "Cookie", "aaa_rsZ06CIkrbzjr1YZw" );
        addView( "Cookie", headMap.get( "Cookie" ) );
        initAction();
    }

    private void initAction() {
        findViewById( R.id.btn1 ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                map.put( "type","on" );
                TreeMap treeMap = new TreeMap();
                treeMap.putAll( headMap );
                String cookie = ( String ) treeMap.get( "Cookie" );
                treeMap.put( "Cookie", "JSESSIONID="+cookie+"; ecology_JSessionId="+cookie+"; languageidweaver=7; loginidweaver=112" );
                getMViewModel().requestVerificationCodeInfo( treeMap, map );
            }
        } );
        findViewById( R.id.btn2 ).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                map.put( "type","off" );
                TreeMap treeMap = new TreeMap();
                treeMap.putAll( headMap );
                String cookie = ( String ) treeMap.get( "Cookie" );
                treeMap.put( "Cookie", "JSESSIONID="+cookie+"; ecology_JSessionId="+cookie+"; languageidweaver=7; loginidweaver=112" );
                getMViewModel().requestVerificationCodeInfo( treeMap, map );
            }
        } );
    }

    private void addView( String key, String value ) {
        if ( TextUtils.isEmpty( SPUtils.getSharedStringData( key, "" ) ) ) {
            SPUtils.setSharedStringData( key, value );
            SPUtils.setSharedStringData( "day", TimeUtil.getCurrentDate( TimeUtil.dateFormatYMD ) );
        } else if ( TextUtils.equals( SPUtils.getSharedStringData( "day", "" ), TimeUtil.getCurrentDate( TimeUtil.dateFormatYMD ) ) ) {
            value = SPUtils.getSharedStringData( key );
            if ( map.containsKey( key ) ) {
                map.put( key, value );
            } else {
                headMap.put( key, value );
            }
        } else {
            SPUtils.setSharedStringData( "day", TimeUtil.getCurrentDate( TimeUtil.dateFormatYMD ) );
        }
        TableRow tableRow = new TableRow( this );
        TextView tvKey = new TextView( this );
        EditText editValue = new EditText( this );
        editValue.setWidth( DisplayUtil.dip2px( 200 ) );
        tvKey.setText( key );
        editValue.setText( value );
        editValue.setTag( key );
        tableRow.addView( tvKey );
        tableRow.addView( editValue );
        mTlRoot.addView( tableRow );


        editValue.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged( CharSequence s, int start, int count, int after ) {

            }

            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count ) {

            }

            @Override
            public void afterTextChanged( Editable s ) {
                String tag = ( String ) editValue.getTag();
                SPUtils.setSharedStringData( tag, s.toString() );
                if ( map.containsKey( tag ) ) {
                    map.put( tag, s.toString() );
                } else {
                    headMap.put( ( String ) editValue.getTag(), s.toString() );
                }
            }
        } );
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void initToolBar() {

    }
}
