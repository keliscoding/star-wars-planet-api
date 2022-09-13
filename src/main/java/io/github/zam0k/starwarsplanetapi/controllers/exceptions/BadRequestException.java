package io.github.zam0k.starwarsplanetapi.controllers.exceptions;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
