package org.school.anywr.exception;

import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
  @org.springframework.web.bind.annotation.ExceptionHandler
  public ResponseEntity<Object> handleEntityException(
      EntityNotFoundException ex, WebRequest request) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @org.springframework.web.bind.annotation.ExceptionHandler
  protected ResponseEntity<Object> handleBaqRequestException(IllegalArgumentException ex) {
    logger.error("Exception during processing", ex);
    return new ResponseEntity<>(
        new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex,
      @Nullable Object body,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    logger.error("Exception during processing", ex);

    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
      request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
    }
    return new ResponseEntity<>(body, headers, status);
  }
  
  @org.springframework.web.bind.annotation.ExceptionHandler
  protected ResponseEntity<Object> handleInvalidDataBody(
      ConstraintViolationException constraintViolationException) {
    logger.error(constraintViolationException.getLocalizedMessage());
    Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
    String errorMessage = "";
    if (!violations.isEmpty()) {
      StringBuilder builder = new StringBuilder();
      violations.forEach(vilation -> builder.append(vilation.getMessage()).append(","));
      errorMessage = builder.toString();
    }
    return new ResponseEntity<>(
        new ApiError(HttpStatus.BAD_REQUEST, errorMessage), HttpStatus.BAD_REQUEST);
  }
}
