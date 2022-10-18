package by.ilyin.web.dto.response;

import by.ilyin.web.dto.request.CreateUserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUsersResponseDTO {

    private List<CreateUserRequestDTO> content;
    private long totalElements;

}