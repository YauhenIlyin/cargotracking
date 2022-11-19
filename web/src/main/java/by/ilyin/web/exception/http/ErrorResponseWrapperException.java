package by.ilyin.web.exception.http;

import by.ilyin.web.util.error.FeignErrorResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseWrapperException extends RuntimeException {

    private FeignErrorResponse feignErrorResponse;

}
