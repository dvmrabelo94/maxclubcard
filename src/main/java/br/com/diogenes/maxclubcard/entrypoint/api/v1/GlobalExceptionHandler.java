package br.com.diogenes.maxclubcard.entrypoint.api.v1;

import br.com.diogenes.maxclubcard.entrypoint.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<DataOutput<ApiError>> handleUncaughtException(final Throwable throwable) {
        logError(throwable);
        final var responseBody = DataOutput.with(ApiError.from("An unidentified error has occurred"));
        return ResponseEntity.internalServerError()
            .body(responseBody);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DataOutput<ApiError>> handleEntityNotFoundException(final EntityNotFoundException exception) {
        logError(exception);
        final var responseBody = DataOutput.with(ApiError.from(NOT_FOUND.getReasonPhrase(), List.of(exception.getMessage())));
        return ResponseEntity.status(NOT_FOUND)
            .body(responseBody);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DataOutput<ApiError>> handleConstraintViolationException(final ConstraintViolationException exception) {
        logError(exception);
        final var errors = exception.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .toList();
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), errors));
        return ResponseEntity.badRequest()
            .body(responseBody);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<DataOutput<ApiError>> handleHandlerMethodValidationException(final HandlerMethodValidationException exception) {
        logError(exception);
        final var errors = exception.getAllValidationResults()
            .stream()
            .map(ParameterValidationResult::getResolvableErrors)
            .flatMap(List::stream)
            .map(toErrorFieldByField())
            .toList();
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), errors));
        return ResponseEntity.badRequest()
            .body(responseBody);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<DataOutput<ApiError>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), List.of(ex.getMessage())));

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DataOutput<ApiError>> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), List.of(ex.getMessage())));

        return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<DataOutput<ApiError>>  handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), List.of(ex.getMessage())));

        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataOutput<ApiError>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        logError(exception);
        final var errors = exception.getBindingResult()
            .getAllErrors()
            .stream()
            .map(toErrorFieldByField())
            .toList();
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), errors));
        return ResponseEntity.badRequest()
            .body(responseBody);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<DataOutput<ApiError>> handleMissingRequestHeaderException(final MissingRequestHeaderException exception) {
        logError(exception);
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), List.of(exception.getMessage())));
        return ResponseEntity.badRequest()
            .body(responseBody);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DataOutput<ApiError>> handleMissingServletRequestParameterException(final MissingServletRequestParameterException exception) {
        logError(exception);
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), List.of(exception.getMessage())));
        return ResponseEntity.badRequest()
            .body(responseBody);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DataOutput<ApiError>> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException exception) {
        logError(exception);
        final var errors = List.of(
            "The parameter '%s' doesn't accept the value '%s'".formatted(exception.getPropertyName(), exception.getValue())
        );
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), errors));
        return ResponseEntity.badRequest()
            .body(responseBody);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DataOutput<ApiError>> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException exception) {
        logError(exception);
        final var responseBody = DataOutput.with(ApiError.from(BAD_REQUEST.getReasonPhrase(), List.of(exception.getMessage())));
        return ResponseEntity.badRequest()
            .body(responseBody);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<DataOutput<ApiError>> handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException exception) {
        logError(exception);
        final var responseBody = DataOutput.with(ApiError.from(UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(), List.of(exception.getMessage())));
        return ResponseEntity.status(UNSUPPORTED_MEDIA_TYPE)
            .body(responseBody);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DataOutput<ApiError>> handleHttpMessageNotReadableException(final HttpMessageNotReadableException exception) {
        logError(exception);
        final var responseBody = DataOutput.with(ApiError.from("Required request body is missing or invalid"));
        return ResponseEntity.badRequest()
            .body(responseBody);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<DataOutput<ApiError>> handleNoHandlerFoundException(final NoHandlerFoundException exception) {
        logError(exception);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<DataOutput<ApiError>> handleBusinessException(final BusinessException exception) {
        logError(exception);
        final var responseBody = DataOutput.with(ApiError.from(exception));
        return ResponseEntity.status(UNPROCESSABLE_ENTITY)
            .body(responseBody);
    }

    public record ApiError(
        String detail,
        List<String> errors
    ) {

        public static ApiError from(final Exception exception) {
            return new ApiError(exception.getMessage(), Collections.emptyList());
        }

        public static ApiError from(final String message) {
            return new ApiError(message, Collections.emptyList());
        }

        public static ApiError from(final String message, final List<String> errors) {
            return new ApiError(message, errors);
        }

    }

    private void logError(final Throwable throwable) {
        log.error("Request error", throwable);
    }

    private static Function<MessageSourceResolvable, String> toErrorFieldByField() {
        return error -> {
            if (error instanceof FieldError fieldError) {
                final var fieldName = fieldError.getField();
                return StringUtils.join(fieldName, " ", fieldError.getDefaultMessage());
            }
            return error.getDefaultMessage();
        };
    }

}
