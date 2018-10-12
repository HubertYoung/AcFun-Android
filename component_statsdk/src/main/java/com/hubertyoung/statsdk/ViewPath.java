package com.hubertyoung.statsdk;

import android.view.View;

/**
 * Created by LIUYONGKUI726 on 2017-12-07.
 */

public class ViewPath {

    public View view;              //view
    public String viewTree;          //view在视图树上的路径
    public String specifyTag;
    public int level = 0;//层级默认为0
    public int filterLevelCount = 3;//需要过滤的层级

    public ViewPath( View view, String viewTree ) {
        this.view = view;
        this.viewTree = viewTree;
    }

}
