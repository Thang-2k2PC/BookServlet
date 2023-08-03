package com.bookshop.paging;

import com.bookshop.dto.SearchOrderDTO;
import com.bookshop.dto.SearchProductObject;

public class PageOrderRequest implements PageOrder{
    private Integer page;
    private Integer maxPageItem;
    private SearchOrderDTO searchOrderDTO;

    public PageOrderRequest(Integer page, Integer maxPageItem, SearchOrderDTO searchOrderDTO) {
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.searchOrderDTO = searchOrderDTO;
    }

    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public Integer getOffset() {
        if (this.page != null || this.maxPageItem != null) {
            return (this.page - 1) * this.maxPageItem;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxPageItem;
    }

    @Override
    public SearchOrderDTO searchOrderDto() {
        String statusOrder = this.searchOrderDTO.getStatusOrder();
        return this.searchOrderDTO;
    }

}
