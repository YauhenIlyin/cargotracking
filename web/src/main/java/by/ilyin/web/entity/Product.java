package by.ilyin.web.entity;

import lombok.Data;

@Data
public class Product {

    private Long id;
    private String name;
    private Integer amount;
    private Invoice invoice;

}
