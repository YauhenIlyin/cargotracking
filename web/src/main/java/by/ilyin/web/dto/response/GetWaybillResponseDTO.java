package by.ilyin.web.dto.response;

import by.ilyin.web.entity.Waybill;

import java.time.LocalDateTime;

public class GetWaybillResponseDTO {

    private Long id;
    private String sender;
    private String receiver;
    private String invoiceNumber;
    private String carNumber;
    private LocalDateTime startDate;
    private Waybill.Status status;
    private Long invoiceId;
    private Long carId;

}