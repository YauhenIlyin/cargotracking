package by.ilyin.core.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteUserRequestDTO {

    private List<Long> userIdList;

}
