package be.bstorm.tf2026javaspringmvc.pl.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundHttpException extends HttpException {
    public NotFoundHttpException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
