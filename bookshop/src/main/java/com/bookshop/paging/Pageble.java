package com.bookshop.paging;

import com.bookshop.dto.SearchProductObject;
import com.bookshop.sort.Sorter;

public interface Pageble {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();

    SearchProductObject getSearchProduct();
}
