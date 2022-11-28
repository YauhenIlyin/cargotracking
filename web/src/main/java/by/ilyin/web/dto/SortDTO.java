package by.ilyin.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class SortDTO {

    List<SortDTO.Order> orders;

    public static class Order {
        SortDTO.Direction direction;
        String property;
    }

    public enum Direction {
        ASC,
        DESC
    }

    public enum NullHandling {
        NATIVE,
        NULLS_FIRST,
        NULLS_LAST
    }

}
