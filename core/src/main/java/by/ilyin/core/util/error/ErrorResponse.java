package by.ilyin.core.util.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private final LocalDateTime timestamp;
    private HttpStatus httpStatus;
    private List<String> errors = new ArrayList<>();
    private StackTraceElement[] stackTrace;

    public ErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus httpStatus,
                         String... messages) {
        this();
        this.httpStatus = httpStatus;
        Collections.addAll(this.errors, messages);
    }

    public ErrorResponse(StackTraceElement[] stackTrace,
                         HttpStatus httpStatus,
                         String... messages) {
        this(httpStatus, messages);
        this.stackTrace = stackTrace;
    }

}
