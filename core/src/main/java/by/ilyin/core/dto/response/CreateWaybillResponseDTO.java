package by.ilyin.core.dto.response;

import lombok.Data;

@Data
public class CreateWaybillResponseDTO {

    private String location;

    public CreateWaybillResponseDTO(Long waybillId) {
        this.location = "/api/waybills/" + waybillId;
    }

}
