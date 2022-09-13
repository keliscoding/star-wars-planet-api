package io.github.zam0k.starwarsplanetapi.controllers.exceptions.handler;

import io.github.zam0k.starwarsplanetapi.controllers.exceptions.ApiError;
import io.github.zam0k.starwarsplanetapi.controllers.exceptions.BadRequestException;
import io.github.zam0k.starwarsplanetapi.controllers.exceptions.InternalServerException;
import io.github.zam0k.starwarsplanetapi.controllers.exceptions.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class AppExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleAllExceptions(Exception ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            ex.getClass().getSimpleName(),
            ex.getMessage(),
            INTERNAL_SERVER_ERROR.value(),
            request.getRequestURI(),
            LocalDateTime.now());
    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ApiError> handleBadRequestException(
      BadRequestException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            ex.getClass().getSimpleName(),
            ex.getMessage(),
            BAD_REQUEST.value(),
            request.getRequestURI(),
            LocalDateTime.now());
    return ResponseEntity.status(BAD_REQUEST).body(error);
  }

  @ExceptionHandler(InternalServerException.class)
  public ResponseEntity<ApiError> handleInternalServerException(
      InternalServerException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            ex.getClass().getSimpleName(),
            ex.getMessage(),
            INTERNAL_SERVER_ERROR.value(),
            request.getRequestURI(),
            LocalDateTime.now());
    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(error);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiError> handleNotFoundException(
      NotFoundException ex, HttpServletRequest request) {
    ApiError error =
        new ApiError(
            ex.getClass().getSimpleName(),
            ex.getMessage(),
            NOT_FOUND.value(),
            request.getRequestURI(),
            LocalDateTime.now());
    return ResponseEntity.status(NOT_FOUND).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {

    List<String> messages =
        ex.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());

    ApiError error =
        new ApiError(
            ex.getClass().getSimpleName(),
            messages,
            BAD_REQUEST.value(),
            request.getRequestURI(),
            LocalDateTime.now());
    return ResponseEntity.status(BAD_REQUEST).body(error);
  }
}
