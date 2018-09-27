package com.hubertyoung.common.widget.sectioned;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * @author:Yang
 * @date:2017/10/24 10:52
 * @since:V1.0.0
 * @desc:com.hubertyoung.common.commonwidget.sectioned
 * @param: 自定义section
 */
public abstract class StatelessSection extends Section {

	/**
	 * Create drawable stateless Section object without header and footer
	 *
	 * @deprecated Replaced by {@link #StatelessSection(SectionParameters)}
	 *
	 * @param itemResourceId layout resource for its items
	 */
	@Deprecated
	public StatelessSection(@LayoutRes int itemResourceId) {
		this(new SectionParameters.Builder(itemResourceId)
				.build());
	}

	/**
	 * Create drawable stateless Section object, with drawable custom header but without footer
	 *
	 * @deprecated Replaced by {@link #StatelessSection(SectionParameters)}
	 *
	 * @param headerResourceId layout resource for its header
	 * @param itemResourceId layout resource for its items
	 */
	@Deprecated
	public StatelessSection( @LayoutRes int headerResourceId, @LayoutRes int itemResourceId) {
		this(new SectionParameters.Builder(itemResourceId)
				.headerResourceId(headerResourceId)
				.build());
	}

	/**
	 * Create drawable stateless Section object, with drawable custom header and drawable custom footer
	 *
	 * @deprecated Replaced by {@link #StatelessSection(SectionParameters)}
	 *
	 * @param headerResourceId layout resource for its header
	 * @param footerResourceId layout resource for its footer
	 * @param itemResourceId layout resource for its items
	 */
	@Deprecated
	public StatelessSection( @LayoutRes int headerResourceId, @LayoutRes int footerResourceId,
							 @LayoutRes int itemResourceId) {
		this(new SectionParameters.Builder(itemResourceId)
				.headerResourceId(headerResourceId)
				.footerResourceId(footerResourceId)
				.build());
	}

	/**
	 * Create drawable stateless Section object based on {@link SectionParameters}
	 * @param sectionParameters section parameters
	 */
	public StatelessSection(SectionParameters sectionParameters) {
		super(sectionParameters);

		if (sectionParameters.loadingResourceId != null) {
			throw new IllegalArgumentException("Stateless section shouldn't have drawable loading state resource");
		}

		if (sectionParameters.failedResourceId != null) {
			throw new IllegalArgumentException("Stateless section shouldn't have drawable failed state resource");
		}

		if (sectionParameters.emptyResourceId != null) {
			throw new IllegalArgumentException("Stateless section shouldn't have an empty state resource");
		}
	}

	@Override
	public final void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
		super.onBindLoadingViewHolder(holder);
	}

	@Override
	public final RecyclerView.ViewHolder getLoadingViewHolder( View view) {
		return super.getLoadingViewHolder(view);
	}

	@Override
	public final void onBindFailedViewHolder(RecyclerView.ViewHolder holder) {
		super.onBindFailedViewHolder(holder);
	}

	@Override
	public final RecyclerView.ViewHolder getFailedViewHolder( View view) {
		return super.getFailedViewHolder(view);
	}

	@Override
	public final void onBindEmptyViewHolder(RecyclerView.ViewHolder holder) {
		super.onBindEmptyViewHolder(holder);
	}

	@Override
	public final RecyclerView.ViewHolder getEmptyViewHolder( View view) {
		return super.getEmptyViewHolder(view);
	}
}
