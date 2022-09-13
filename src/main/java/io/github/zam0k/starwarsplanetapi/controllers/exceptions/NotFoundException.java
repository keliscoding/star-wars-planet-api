package io.github.zam0k.starwarsplanetapi.controllers.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
