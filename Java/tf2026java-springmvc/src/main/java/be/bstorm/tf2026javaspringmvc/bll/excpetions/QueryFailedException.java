package be.bstorm.tf2026javaspringmvc.bll.excpetions;

public class QueryFailedException extends Exception {
    public QueryFailedException(Class<?> context, String message) {
        super(message);
    }
}
