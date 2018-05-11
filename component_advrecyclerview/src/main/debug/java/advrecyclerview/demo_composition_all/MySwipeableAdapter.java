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
package advrecyclerview.demo_composition_all;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.acty.component_advrecyclerview.R;
import com.acty.component_advrecyclerview.advrecyclerview.swipeable.SwipeableItemAdapter;
import com.acty.component_advrecyclerview.advrecyclerview.swipeable.SwipeableItemConstants;
import com.acty.component_advrecyclerview.advrecyclerview.swipeable.action.SwipeResultAction;
import com.acty.component_advrecyclerview.advrecyclerview.swipeable.action.SwipeResultActionDefault;
import com.acty.component_advrecyclerview.advrecyclerview.swipeable.action.SwipeResultActionRemoveItem;
import com.acty.component_advrecyclerview.advrecyclerview.swipeable.annotation.SwipeableItemDrawableTypes;
import com.acty.component_advrecyclerview.advrecyclerview.swipeable.annotation.SwipeableItemResults;
import com.acty.component_advrecyclerview.advrecyclerview.utils.AbstractSwipeableItemViewHolder;
import com.acty.component_advrecyclerview.advrecyclerview.utils.RecyclerViewAdapterUtils;
import com.acty.component_advrecyclerview.advrecyclerview.utils.WrapperAdapterUtils;

import java.util.ArrayList;
import java.util.List;

import advrecyclerview.common.adapter.OnListItemClickMessageListener;

class MySwipeableAdapter
        extends RecyclerView.Adapter<MySwipeableAdapter.MyViewHolder>
        implements SwipeableItemAdapter<MySwipeableAdapter.MyViewHolder>, View.OnClickListener {

    static class MyItem {
        public final long id;
        public final String text;

        public MyItem(long id, String text) {
            this.id = id;
            this.text = text;
        }
    }

    static class MyViewHolder extends AbstractSwipeableItemViewHolder {
        FrameLayout containerView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            containerView = itemView.findViewById( R.id.container);
            textView = itemView.findViewById(android.R.id.text1);
        }

        @Override
        public View getSwipeableContainerView() {
            return containerView;
        }
    }

    interface Swipeable extends SwipeableItemConstants {
    }

    OnListItemClickMessageListener mOnItemClickListener;
    List<MyItem> mItems;

    public MySwipeableAdapter(OnListItemClickMessageListener clickListener) {
        setHasStableIds(true); // this is required for swiping feature.

        mOnItemClickListener = clickListener;
        mItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mItems.add(new MyItem(i, "Item " + i));
        }
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).id; // need to return stable (= not change even after position changed) value
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        vh.containerView.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyItem item = mItems.get(position);
        holder.textView.setText(item.text);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onSwipeItemStarted(MyViewHolder holder, int position) {
        notifyDataSetChanged();
    }

    @Override
    public SwipeResultAction onSwipeItem( MyViewHolder holder, int position, @SwipeableItemResults int result) {
        if (result == Swipeable.RESULT_CANCELED) {
            return new SwipeResultActionDefault();
        } else {
            return new MySwipeResultActionRemoveItem(this, position);
        }
    }

    @Override
    public int onGetSwipeReactionType(MyViewHolder holder, int position, int x, int y) {
        return Swipeable.REACTION_CAN_SWIPE_BOTH_H;
    }

    @Override
    public void onSetSwipeBackground(MyViewHolder holder, int position, @SwipeableItemDrawableTypes int type) {
        int bgColor = ContextCompat.getColor(holder.itemView.getContext(), R.color.bg_swipe_item_gray);
        holder.itemView.setBackgroundColor(bgColor);
    }

    @Override
    public void onClick(View v) {
        RecyclerView rv = RecyclerViewAdapterUtils.getParentRecyclerView(v);
        RecyclerView.ViewHolder vh = rv.findContainingViewHolder(v);

        int rootPosition = vh.getAdapterPosition();
        if (rootPosition == RecyclerView.NO_POSITION) {
            return;
        }

        // need to determine adapter local position like this:
        RecyclerView.Adapter rootAdapter = rv.getAdapter();
        int localPosition = WrapperAdapterUtils.unwrapPosition(rootAdapter, this, rootPosition);

        String message = "CLICKED: Swipeable item " + localPosition;

        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClicked(message);
        }
    }

    static class MySwipeResultActionRemoveItem extends SwipeResultActionRemoveItem {
        private MySwipeableAdapter adapter;
        private int position;

        public MySwipeResultActionRemoveItem(MySwipeableAdapter adapter, int position) {
            this.adapter = adapter;
            this.position = position;
        }

        @Override
        protected void onPerformAction() {
            adapter.mItems.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }
}
