package by.ilyin.web.dto;

import lombok.Data;

@Data
public class PageableDTO {

    private int size;
    private int page;
    private SortDTO sort;

}
