package com.hubertyoung.component.acfunvideo.index.source;

import com.hubertyoung.common.api.ApiImpl;
import com.hubertyoung.common.api.HostType;
import com.hubertyoung.common.base.AbsRepository;
import com.hubertyoung.common.net.transformer.DefaultTransformer;
import com.hubertyoung.component.acfunvideo.api.ApiHomeService;
import com.hubertyoung.component.acfunvideo.entity.ChannelOperate;

import java.util.HashMap;

import io.reactivex.Flowable;

/**
 * <br>
 * function:
 * <p>
 *
 * @author:HubertYoung
 * @date:2018/11/15 15:36
 * @since:V5.2.7
 * @desc:com.hubertyoung.component.acfunvideo.index.source
 */
public class ChannelRepository extends AbsRepository {
    public Flowable< ChannelOperate > requestChannel( String pos ) {
        HashMap map = new HashMap< String, String >();
        map.put( "pos", pos );
        return ApiImpl.getInstance( HostType.APP_NEWAPI_HOST )//
                .getRetrofitClient()//
                .builder( ApiHomeService.class )//
                .requestChannelOperate( map )//
                .compose( new DefaultTransformer() );
    }
}
