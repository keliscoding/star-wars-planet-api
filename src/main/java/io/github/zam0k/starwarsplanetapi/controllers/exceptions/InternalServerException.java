package io.github.zam0k.starwarsplanetapi.controllers.exceptions;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
