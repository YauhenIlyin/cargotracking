package by.ilyin.web.util.error;

import by.ilyin.web.evidence.DefaultExceptionMessages;
import by.ilyin.web.exception.CustomConstraintValidationException;
import by.ilyin.web.exception.http.CustomFeignException;
import by.ilyin.web.exception.http.client.*;
import by.ilyin.web.exception.http.server.NotImplementedMethodException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//todo add logs
@ControllerAdvice
public class CustomControllerAdvice {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    //todo add javadoc 400

    @ExceptionHandler({
            BindException.class,
            HttpMessageNotReadableException.class,
            IncorrectValueFormatException.class,
            MissingServletRequestParameterException.class,
            MissingServletRequestPartException.class,
            TypeMismatchException.class,
    })
    public ResponseEntity<CustomErrorResponse> handleBadRequestExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.BAD_REQUEST);
    }

    //todo javadoc 400 with message list

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException e) {
        ResponseEntity<CustomErrorResponse> result;
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError currentError : errors) {
            errorMessages.add(currentError.getDefaultMessage());
        }
        if (errorMessages.size() > 0) {
            result = buildSimpleErrorResponse(LocalDateTime.now().format(formatter),
                    HttpStatus.BAD_REQUEST,
                    errorMessages);
        } else {
            result = buildSimpleErrorResponse(e, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    //todo add javadoc 400 for requestParameters (handle exception of @RequestParam @Size etc.)

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleConstraintViolationExceptions(ConstraintViolationException e) {
        ResponseEntity<CustomErrorResponse> result;
        List<String> errorMessages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());
        if (errorMessages.size() > 0) {
            result = buildSimpleErrorResponse(LocalDateTime.now().format(formatter),
                    HttpStatus.BAD_REQUEST,
                    errorMessages);
        } else {
            result = buildSimpleErrorResponse(e, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    //todo add javadoc 400 for requestParameters

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<RealErrorResponse> handleConstraintViolationExceptions(ConstraintViolationException e) {
        ResponseEntity<RealErrorResponse> result;
        List<String> errorMessages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessageTemplate).collect(Collectors.toList());
        if (errorMessages.size() > 0) {
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
    public ResponseEntity<CustomErrorResponse> handleUnauthorizedRequestExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.UNAUTHORIZED);
    }

    //todo add javadoc 403

    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomAccessDeniedExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.FORBIDDEN);
    }

    //todo add javadoc 404

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFoundExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    //todo add javadoc 409

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<CustomErrorResponse> handleResourceAlreadyExistsExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.CONFLICT);
    }

    //todo add javadoc 500

    @ExceptionHandler({
            ConversionNotSupportedException.class,
            HttpMessageNotWritableException.class,
    })
    public ResponseEntity<CustomErrorResponse> handleInternalServerErrorExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //todo add javadoc general-500

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGeneralExceptions(Exception e) {
        return buildSimpleErrorResponse(
                LocalDateTime.now().format(formatter),
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of(DefaultExceptionMessages.INTERNAL_SERVER_ERROR));
    }

    //todo add javadoc 501

    @ExceptionHandler(NotImplementedMethodException.class)
    public ResponseEntity<CustomErrorResponse> handleNotImplementedMethodExceptions(Exception e) {
        return buildSimpleErrorResponse(e, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(CustomConstraintValidationException.class)
    public ResponseEntity<CustomErrorResponse> handleConstraintViolationExceptions(CustomConstraintValidationException e) {
        HttpStatus httpStatus = e.getHttpStatus();
        if (httpStatus == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        if (e.getMessages() == null || e.getMessages().length == 0) {
            e.setMessages(DefaultExceptionMessages.INTERNAL_SERVER_ERROR);
        }
        return buildSimpleErrorResponse(LocalDateTime.now().toString(), httpStatus, Arrays.asList(e.getMessages()));
    }

    //todo add javadoc wrapper exception of core

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<CustomErrorResponse> handleFeignErrorResponse(CustomFeignException e) {
        return buildSimpleErrorResponse(
                e.getTimestamp(),
                e.getHttpStatus(),
                e.getErrors());
    }

    private ResponseEntity<CustomErrorResponse> buildSimpleErrorResponse(Exception e, HttpStatus httpStatus) {
        return buildSimpleErrorResponse(
                LocalDateTime.now().format(formatter),
                httpStatus,
                List.of(e.getMessage()));
    }

    private ResponseEntity<CustomErrorResponse> buildSimpleErrorResponse(String timestamp, HttpStatus httpStatus, List<String> messages) {
        return new ResponseEntity<>(
                new CustomErrorResponse(
                        timestamp,
                        httpStatus.value(),
                        messages
                ),
                httpStatus
        );
    }
}
