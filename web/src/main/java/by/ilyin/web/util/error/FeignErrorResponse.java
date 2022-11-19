package by.ilyin.web.util.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class FeignErrorResponse {

    private String timestamp;
    private HttpStatus httpStatus;
    private List<String> errors;
    private StackTraceElement[] stackTrace;

}
