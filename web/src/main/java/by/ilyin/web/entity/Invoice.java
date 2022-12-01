package by.ilyin.web.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Invoice {

    private Long id;
    private String number;
    private Long storageId;
    private Long productOwnerId;
    private Long creatorId;
    private Long driverId;
    private LocalDate creationDate;
    private LocalDate verificationDate;
    private Status status;
    private List<Product> products;

    public enum Status {
        MADE_OUT,
        DELIVERED,
        VERIFICATION_COMPLETE
    }

}
