package by.ilyin.web.entity;

import lombok.Data;

@Data
public class ProductWriteOff {

    private Long id;
    private Integer amount;
    private Status status;
    private Product product;
    private CustomUser creator;

    public enum Status {
        LOST,
        STOLEN,
        SPOILED
    }

}
