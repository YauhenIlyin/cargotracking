package by.ilyin.core.repository.filtration;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageableBuilder {

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_PAGE_SIZE = 2;
    private int currentPageNumber = -1;
    private int currentPageSize = -1;
    List<Sort.Order> currentOrderList = new ArrayList<>();

    private PageableBuilder() {
    }

    public static PageableBuilder getBuilder() {
        return new PageableBuilder();
    }

    public PageableBuilder addPageSizeNumber(int pageNumber, int pageSize) {
        this.currentPageNumber = pageNumber;
        this.currentPageSize = pageSize;
        return this;
    }

    public PageableBuilder addSortParameter(String sortField, String sortType) {
        //todo validate field
        Sort.Order currentOrder;
        if (Sort.Direction.DESC.name().equals(sortType)) {
            currentOrder = Sort.Order.desc(sortField);
        } else {
            currentOrder = Sort.Order.asc(sortField);
        }
        currentOrderList.add(currentOrder);
        return this;
    }

    public Pageable build() {
        if (currentPageNumber < 0) {
            currentPageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (currentPageSize < 0) {
            currentPageSize = DEFAULT_PAGE_SIZE;
        }
        Pageable pageable;
        if (currentOrderList.size() > 0) {
            pageable = PageRequest.of(currentPageNumber, currentPageSize, Sort.by(currentOrderList));
        } else {
            pageable = PageRequest.of(currentPageNumber, currentPageSize);
        }
        return pageable;
    }

}
