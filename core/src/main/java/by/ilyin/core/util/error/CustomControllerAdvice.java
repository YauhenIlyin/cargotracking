package by.ilyin.core.util.error;

import by.ilyin.core.evidence.DefaultExceptionMessages;
import by.ilyin.core.exception.http.CustomConstraintValidationException;
import by.ilyin.core.exception.http.client.*;
import by.ilyin.core.exception.http.server.NotImplementedMethodException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.net.BindException;

@ControllerAdvice
public class CustomControllerAdvice {

    //todo add javadoc 400
    
    @ExceptionHandler({
            BindException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            TypeMismatchException.class,
            IncorrectValueFormatException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    //todo add javadoc 401

    @ExceptionHandler(UnauthorizedRequestException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedRequestExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.UNAUTHORIZED);
    }

    //todo add javadoc 403

    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleCustomAccessDeniedExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.FORBIDDEN);
    }

    //todo add javadoc 404

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    //todo add javadoc 409

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExists(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.CONFLICT);
    }

    //todo add javadoc 500

    @ExceptionHandler({
            ConversionNotSupportedException.class,
            HttpMessageNotWritableException.class,
            Exception.class
    })
    public ResponseEntity<ErrorResponse> handleCInternalServerErrorExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //todo add javadoc ConstraintValidator impl custom annotation exception

    @ExceptionHandler(CustomConstraintValidationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationExceptions(CustomConstraintValidationException e) {
        HttpStatus httpStatus = e.getHttpStatus();
        if (httpStatus == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (e.getMessages() == null || e.getMessages().length == 0) {
            e.setMessages(DefaultExceptionMessages.INTERNAL_SERVER_ERROR);
        }
        return buildSimpleErrorResponse(e, httpStatus, e.getMessages());
    }

    //todo add javadoc 501

    @ExceptionHandler(NotImplementedMethodException.class)
    public ResponseEntity<ErrorResponse> handleNotImplementedMethodExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.NOT_IMPLEMENTED);
    }

    private ResponseEntity<ErrorResponse> buildSimpleErrorResponse(Exception e, HttpStatus httpStatus) {
        return buildSimpleErrorResponse(e, httpStatus, e.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildSimpleErrorResponse(Exception e, HttpStatus httpStatus, String... message) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        httpStatus,
                        message
                ),
                httpStatus
        );
    }

}
