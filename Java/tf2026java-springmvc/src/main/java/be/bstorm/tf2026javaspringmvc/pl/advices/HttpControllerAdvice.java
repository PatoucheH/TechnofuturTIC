package be.bstorm.tf2026javaspringmvc.pl.advices;

import be.bstorm.tf2026javaspringmvc.pl.exceptions.HttpException;
import be.bstorm.tf2026javaspringmvc.pl.exceptions.NotFoundHttpException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HttpControllerAdvice {

    @ExceptionHandler({NotFoundHttpException.class})
    public ModelAndView handleNotFoundHttpException(NotFoundHttpException e) {
        return handleHttpException(e);
    }
    @ExceptionHandler(HttpException.class)
    public ModelAndView handleHttpException(HttpException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("status", e.getStatus());
        return switch (e) {
            case NotFoundHttpException n -> {
                modelAndView.addObject("message", n.getMessage());
                modelAndView.setViewName("error/not-found");
                yield modelAndView;
            }
            default -> {
                modelAndView.setViewName("error/internal-server-error");
                yield modelAndView;
            }
        };
    }
}
