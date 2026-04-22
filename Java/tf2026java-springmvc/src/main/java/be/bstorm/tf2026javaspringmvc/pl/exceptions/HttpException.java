package be.bstorm.tf2026javaspringmvc.pl.exceptions;

import org.springframework.http.HttpStatus;

public abstract class HttpException extends RuntimeException {
    private final HttpStatus status;
    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
    public int getStatusCode() {
        return status.value();
    }
    public String getStatusName() {
        return status.toString();
    }
}
