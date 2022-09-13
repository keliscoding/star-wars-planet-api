package io.github.zam0k.starwarsplanetapi.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String error;
    private List<String> message;
    private Integer status;
    private String path;
    private LocalDateTime timestamp;

    public ApiError(String error, String message, Integer status, String path, LocalDateTime timestamp) {
        this.error = error;
        this.message = List.of(message);
        this.status = status;
        this.path = path;
        this.timestamp = timestamp;
    }
}
