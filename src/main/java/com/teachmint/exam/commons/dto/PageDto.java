package com.teachmint.exam.commons.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDto<T> {
    private static final long serialVersionUID = -5371201158843795779L;

    private boolean hasNextPage;

    private long totalElements;

    private List<T> results;

    private int currentPage;

    private int pageSize;

    private String message;

    public static <Y> PageDto<Y> newPage(Page page, List<Y> elements){
        PageDto<Y> newPageInfo = new PageDto<Y>();
        newPageInfo.setHasNextPage(page.hasNext());
        newPageInfo.setTotalElements(page.getTotalElements());
        newPageInfo.setResults(elements);
        newPageInfo.setCurrentPage(page.getPageable().getPageNumber());
        newPageInfo.setPageSize(page.getPageable().getPageSize());
        return newPageInfo;
    }
}
