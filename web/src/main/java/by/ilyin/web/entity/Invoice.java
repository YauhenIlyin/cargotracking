package by.ilyin.web.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Invoice {

    private Long id;
    private String number;
    private LocalDate creationDate;
    private LocalDate verificationDate;
    private Status status;
    private List<Product> products;
    private Storage storage;
    private ProductOwner productOwner;
    private CustomUser creator;
    private CustomUser driver;

    public enum Status {
        MADE_OUT,
        DELIVERED,
        VERIFICATION_COMPLETE
    }

}
