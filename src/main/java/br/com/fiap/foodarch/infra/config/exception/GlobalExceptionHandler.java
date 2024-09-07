package br.com.fiap.foodarch.infra.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", "Resource not found.");
    body.put("details", ex.getMessage());
    body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
    body.put("status", status.value());

    logger.warn("Resource not found: {}", ex.getMessage());
    return new ResponseEntity<>(body, status);
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", "No resource found for this request.");
    body.put("details", ex.getMessage());
    body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
    body.put("status", status.value());

    logger.warn("No resource found: {}", ex.getMessage());
    return new ResponseEntity<>(body, status);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", "No handler found for this request.");
    body.put("details", ex.getMessage());
    body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
    body.put("status", status.value());

    logger.warn("No handler found: {}", ex.getMessage());
    return new ResponseEntity<>(body, status);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", "Validation error.");
    body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
    body.put("status", status.value());

    Map<String, String> fieldErrors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
        fieldErrors.put(error.getField(), error.getDefaultMessage()));

    body.put("errors", fieldErrors);

    logger.warn("Validation error: {}", ex.getMessage());
    return new ResponseEntity<>(body, status);
  }

  @ExceptionHandler({RuntimeException.class, Exception.class})
  public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    try {
      Method getStatusMethod = ex.getClass().getMethod("getStatus");
      if (HttpStatus.class.isAssignableFrom(getStatusMethod.getReturnType())) {
        status = (HttpStatus) getStatusMethod.invoke(ex);
      }
    } catch (NoSuchMethodException | SecurityException ignored) {
    } catch (Exception e) {
      logger.error("Unexpected error while handling exception: {}", e.getMessage(), e);
    }

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", ex instanceof RuntimeException ? ex.getMessage() : "Unexpected error.");
    body.put("details", ex.getMessage());
    body.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
    body.put("status", status.value());

    String originatingClass = ex.getStackTrace().length > 0 ? ex.getStackTrace()[0].getClassName() : "Unknown";
    body.put("originatingClass", originatingClass);

    List<String> exceptionChain = new ArrayList<>();
    Throwable currentException = ex;
    while (currentException != null) {
      exceptionChain.add(currentException.getClass().getName());
      currentException = currentException.getCause();
    }
    body.put("exceptionChain", exceptionChain);

    logger.error("Exception occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>(body, status);
  }
}
