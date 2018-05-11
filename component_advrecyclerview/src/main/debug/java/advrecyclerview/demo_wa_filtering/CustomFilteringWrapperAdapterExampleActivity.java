/*
 *    Copyright (C) 2016 Haruki Hasegawa
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

package advrecyclerview.demo_wa_filtering;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.acty.component_advrecyclerview.BuildConfig;
import com.acty.component_advrecyclerview.R;
import com.acty.component_advrecyclerview.advrecyclerview.utils.DebugWrapperAdapter;

import advrecyclerview.common.adapter.OnListItemClickMessageListener;
import advrecyclerview.common.adapter.SimpleDemoItemAdapter;


public class CustomFilteringWrapperAdapterExampleActivity extends AppCompatActivity {

    private MyItemFilteringAdapter mFilteringAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_demo_minimal);

        OnListItemClickMessageListener clickListener = new OnListItemClickMessageListener() {
            @Override
            public void onItemClicked(String message) {
                View container = findViewById(R.id.container);
                Snackbar.make(container, message, Snackbar.LENGTH_SHORT).show();
            }
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        RecyclerView.Adapter adapter;

        adapter = new SimpleDemoItemAdapter(clickListener);
        adapter = mFilteringAdapter = new MyItemFilteringAdapter(adapter);

        if ( BuildConfig.DEBUG) {
            adapter = new DebugWrapperAdapter(adapter);
        }

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wa_on_off_toggle, menu);

        // setting up the filtering on/off switch
        MenuItem menuSwitchItem = menu.findItem(R.id.menu_switch_on_off);
        CompoundButton actionView = MenuItemCompat.getActionView(menuSwitchItem).findViewById(R.id.switch_view);

        actionView.setChecked(mFilteringAdapter.isFilteringEnabled());

        actionView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFilteringAdapter.setFilteringEnabled(!mFilteringAdapter.isFilteringEnabled());
            }
        });

        return true;
    }
}
