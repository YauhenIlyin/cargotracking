package by.ilyin.web.exception.http;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class CustomFeignException extends RuntimeException {

    private String timestamp;
    private HttpStatus httpStatus;
    private List<String> errors;

}
