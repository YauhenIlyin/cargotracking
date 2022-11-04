package by.ilyin.core.dto.request;

import lombok.*;

import java.util.List;

@Data
public class DeleteUserRequestDTO {

    private List<Long> userIdList;

}
