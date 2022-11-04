package by.ilyin.web.dto.response;

import by.ilyin.web.dto.CustomUserDTO;
import lombok.*;

import java.util.List;

@Data
public class GetUsersResponseDTO {

    private List<CustomUserDTO> content;
    private long totalElements;

}
