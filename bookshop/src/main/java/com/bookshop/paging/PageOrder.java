package com.bookshop.paging;

import com.bookshop.dto.SearchOrderDTO;

public interface PageOrder {
    Integer getPage();
    Integer getOffset();
    Integer getLimit();
    SearchOrderDTO searchOrderDto();
}
