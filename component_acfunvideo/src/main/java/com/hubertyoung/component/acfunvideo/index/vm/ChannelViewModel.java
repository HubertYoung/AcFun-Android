package com.hubertyoung.component.acfunvideo.index.vm;

import android.app.Application;

import com.hubertyoung.common.base.AbsViewModel;
import com.hubertyoung.component.acfunvideo.index.source.ChannelRepository;

import androidx.annotation.NonNull;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/12 11:25
 * @since:V$VERSION
 * @desc:com.hubertyoung.component_acfunmine.ui.sign.vm
 */
public class ChannelViewModel extends AbsViewModel< ChannelRepository > {

	public ChannelViewModel( @NonNull Application application ) {
		super( application );
	}

	public void requestChannel( String pos, int status ) {
		mRepository.requestChannel( pos, status );
	}
}
