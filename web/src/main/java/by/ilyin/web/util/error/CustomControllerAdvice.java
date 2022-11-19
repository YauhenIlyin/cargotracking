package by.ilyin.web.util.error;

import by.ilyin.web.evidence.DefaultExceptionMessages;
import by.ilyin.web.exception.http.ErrorResponseWrapperException;
import by.ilyin.web.exception.http.client.*;
import by.ilyin.web.exception.http.server.NotImplementedMethodException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.net.BindException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//todo add logs
@ControllerAdvice
public class CustomControllerAdvice {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    //todo add javadoc 400

    @ExceptionHandler({
            BindException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            TypeMismatchException.class,
    })
    public ResponseEntity<RealErrorResponse> handleBadRequestExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    //todo javadoc 400 with message list

    @ExceptionHandler({
            IncorrectValueFormatException.class
    })
    public ResponseEntity<RealErrorResponse> handleIncorrectValueFormatExceptions(IncorrectValueFormatException e) {
        ResponseEntity<RealErrorResponse> result;
        List<String> errorMessages = e.getErrorMessages();
        if (errorMessages != null && errorMessages.size() > 0) {
            result = buildSimpleErrorResponse(LocalDateTime.now().format(formatter),
                    HttpStatus.BAD_REQUEST,
                    errorMessages);
        } else {
            result = buildSimpleErrorResponse(e, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    //todo add javadoc 401

    @ExceptionHandler(UnauthorizedRequestException.class)
    public ResponseEntity<RealErrorResponse> handleUnauthorizedRequestExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.UNAUTHORIZED);
    }

    //todo add javadoc 403

    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<RealErrorResponse> handleCustomAccessDeniedExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.FORBIDDEN);
    }

    //todo add javadoc 404

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RealErrorResponse> handleResourceNotFoundExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    //todo add javadoc 405

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RealErrorResponse> handleHttpRequestMethodNotSupportedExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //todo add javadoc 406

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<RealErrorResponse> handleHttpMediaTypeNotAcceptableExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.NOT_ACCEPTABLE);
    }

    //todo add javadoc 409

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<RealErrorResponse> handleResourceAlreadyExists(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.CONFLICT);
    }

    //todo add javadoc 415

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<RealErrorResponse> handleHttpMediaTypeNotSupportedExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    //todo add javadoc 500

    @ExceptionHandler({
            ConversionNotSupportedException.class,
            HttpMessageNotWritableException.class,
    })
    public ResponseEntity<RealErrorResponse> handleCInternalServerErrorExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //todo add javadoc general-500

    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<RealErrorResponse> handleGeneralExceptions(Exception e) {
        return buildSimpleErrorResponse(
                LocalDateTime.now().format(formatter),
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of(DefaultExceptionMessages.INTERNAL_SERVER_ERROR));
    }

    //todo add javadoc 501

    @ExceptionHandler(NotImplementedMethodException.class)
    public ResponseEntity<RealErrorResponse> handleNotImplementedMethodExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.NOT_IMPLEMENTED);
    }

    //todo add javadoc wrapper exception of core

    @ExceptionHandler(ErrorResponseWrapperException.class)
    public ResponseEntity<RealErrorResponse> handleErrorResponseWrapper(ErrorResponseWrapperException e) {
        FeignErrorResponse feignError = e.getFeignErrorResponse();
        return buildSimpleErrorResponse(
                feignError.getTimestamp(),
                feignError.getHttpStatus(),
                feignError.getErrors());
    }

    private ResponseEntity<RealErrorResponse> buildSimpleErrorResponse(Exception e, HttpStatus httpStatus) {
        return buildSimpleErrorResponse(
                LocalDateTime.now().format(formatter),
                httpStatus,
                List.of(e.getMessage()));
    }

    private ResponseEntity<RealErrorResponse> buildSimpleErrorResponse(String timestamp, HttpStatus httpStatus, List<String> messages) {
        return new ResponseEntity<>(
                new RealErrorResponse(
                        timestamp,
                        httpStatus.value(),
                        messages
                ),
                httpStatus
        );
    }

}
