package id.ac.ui.cs.pustakaone.identity.exceptions;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ErrorTemplate(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
    public HttpStatus getStatus() {
        return this.httpStatus;
    }

    public String getMessage() {
        return this.message;
    }
}
