package com.hubertyoung.component.acfunvideo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Serializable {
    public String cover;
    public int id;
    public String name;
    public int oldTagId;
    public int sort;
    public int type;

    public Category convertToCategory() {
        Category category = new Category();
        category.setCid(this.oldTagId == 0 ? this.id : this.oldTagId);
        category.setCover(this.cover);
        category.setTitle(this.name);
        return category;
    }

    public static List<Category> convertToCategoryList(List<Tag> list) {
        List<Category> arrayList = new ArrayList();
        for (Tag convertToCategory : list) {
            arrayList.add(convertToCategory.convertToCategory());
        }
        return arrayList;
    }
}
