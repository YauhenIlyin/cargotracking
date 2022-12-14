package by.ilyin.web.dto.page;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO<T> {

    private long totalElements;
    private List<T> content;
    private PageableDTO pageable;

}
