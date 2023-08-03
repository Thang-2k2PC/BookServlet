package com.bookshop.paging;

import com.bookshop.dto.SearchProductObject;
import com.bookshop.sort.Sorter;

public class PageRequest implements Pageble {
    private Integer page;
    private Integer maxPageItem;
    private SearchProductObject searchProductObject;

    public PageRequest(Integer page, Integer maxPageItem, SearchProductObject searchProductObject) {
        this.page = page;
        this.maxPageItem = maxPageItem;
        this.searchProductObject = searchProductObject;
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
    public SearchProductObject getSearchProduct() {
        String price = this.searchProductObject.getPrice();
        if(price != null){
            switch (price) {
                case "duoi-5-chuc":
                    price = " < 50000";
                    break;

                case "5-chuc-den-1-tram":
                    price = "BETWEEN 50000 AND 100000";
                    break;

                case "1-tram-den-2-tram":
                    price = "BETWEEN 100000 AND 200000";
                    break;

                case "2-tram-den-3-tram":
                    price = "BETWEEN 200000 AND 300000";
                    break;

                case "tren-3-tram":
                    price = " > 300000";
                    break;

                default:
//                    price = null;
                    break;
            }
        }


        String nxb = this.searchProductObject.getNxb_id();


        this.searchProductObject.setPrice(price);
        this.searchProductObject.setNxb_id(nxb);
//        searchProductObject.setCategory_id(this.getSearchProduct().getCategory_id());

//        return this.searchProductObject = searchProductObject;
        return this.searchProductObject;
    }


}
