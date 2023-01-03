package by.ilyin.web.exception.http;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomFeignException extends RuntimeException {

    private String timestamp;
    private HttpStatus httpStatus;
    private List<String> errors;

}
