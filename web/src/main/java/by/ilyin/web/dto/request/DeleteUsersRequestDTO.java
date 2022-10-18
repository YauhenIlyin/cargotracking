package by.ilyin.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUsersRequestDTO {

    private List<Long> userIdList;

}