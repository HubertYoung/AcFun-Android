/*
 *    Copyright (C) 2015 Haruki Hasegawa
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package advrecyclerview.demo_s_viewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.acty.component_advrecyclerview.R;

import advrecyclerview.common.data.AbstractDataProvider;
import advrecyclerview.common.fragment.ExampleDataProviderFragment;


public class ViewPagerSwipeableExampleActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG_DATA_PROVIDER_1 = "data provider 1";
    public static final String FRAGMENT_TAG_DATA_PROVIDER_2 = "data provider 2";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_demo_viewpager);

        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.pager);

        mViewPager.setAdapter(new ViewPagerSwipeableExamplePagerAdapter(getSupportFragmentManager()));

        mTabLayout.setupWithViewPager(mViewPager);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(new ExampleDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER_1)
                    .add(new ExampleDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER_2)
                    .commit();
        }
    }

    public AbstractDataProvider getDataProvider( String dataProviderName) {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(dataProviderName);
        return ((ExampleDataProviderFragment) fragment).getDataProvider();
    }
}
