package section;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hubertyoung.common.widget.sectioned.SectionParameters;
import com.hubertyoung.common.widget.sectioned.SectionedRecyclerViewAdapter;
import com.hubertyoung.common.widget.sectioned.StatelessSection;
import com.hubertyoung.component_section.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Example5Fragment extends Fragment {

    private SectionedRecyclerViewAdapter sectionAdapter;

    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_ex5, container, false);

        sectionAdapter = new SectionedRecyclerViewAdapter();

        sectionAdapter.addSection(new MovieSection(getString(R.string.top_rated_movies_topic), getTopRatedMoviesList()));
        sectionAdapter.addSection(new MovieSection(getString(R.string.most_popular_movies_topic), getMostPopularMoviesList()));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        GridLayoutManager glm = new GridLayoutManager(getContext(), 2);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (sectionAdapter.getSectionItemViewType(position)) {
                    case SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER:
                        return 2;
                    default:
                        return 1;
                }
            }
        });
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(sectionAdapter);
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        divider.setDrawable( ContextCompat.getDrawable(getActivity(),R.drawable.custom_divider));
        recyclerView.addItemDecoration(divider);
        return view;
    }

    private List<Movie> getTopRatedMoviesList() {
        List<String> arrayList = new ArrayList<>(Arrays.asList(getResources()
                .getStringArray(R.array.top_rated_movies)));

        List<Movie> movieList = new ArrayList<>();

        for (String str : arrayList) {
            String[] array = str.split("\\|");
            movieList.add(new Movie(array[0], array[1]));
        }

        return movieList;
    }

    private List<Movie> getMostPopularMoviesList() {
        List<String> arrayList = new ArrayList<>(Arrays.asList(getResources()
                .getStringArray(R.array.most_popular_movies)));

        List<Movie> movieList = new ArrayList<>();

        for (String str : arrayList) {
            String[] array = str.split("\\|");
            movieList.add(new Movie(array[0], array[1]));
        }

        return movieList;
    }

    private class MovieSection extends StatelessSection {

        final String title;
        final List<Movie> list;

        MovieSection(String title, List<Movie> list) {
            super( SectionParameters.builder()
                    .itemResourceId(R.layout.section_ex5_item)
                    .headerResourceId(R.layout.section_ex5_header)
                    .build());

            this.title = title;
            this.list = list;
        }

        @Override
        public int getContentItemsTotal() {
            return list.size();
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder( View view, int itemType ) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            String name = list.get(position).getName();
            String category = list.get(position).getCategory();

            itemHolder.tvItem.setText(name);
            itemHolder.tvSubItem.setText(category);

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s",
                            sectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()), title),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);

            headerHolder.btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on more button from the header of Section %s",
                            title),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final Button btnMore;

        HeaderViewHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
            btnMore = view.findViewById(R.id.btnMore);
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView tvItem;
        private final TextView tvSubItem;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            tvItem = view.findViewById(R.id.tvItem);
            tvSubItem = view.findViewById(R.id.tvSubItem);
        }
    }

    private class Movie {
        final String name;
        final String category;

        Movie(String name, String category) {
            this.name = name;
            this.category = category;
        }

        String getName() {
            return name;
        }

        String getCategory() {
            return category;
        }
    }
}
