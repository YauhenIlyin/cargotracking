package by.ilyin.web.util.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RealErrorResponse {

    private String timestamp;
    private Integer status;
    private List<String> errors;

}
