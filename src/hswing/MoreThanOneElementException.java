package hswing;

public class MoreThanOneElementException extends RuntimeException {
    public MoreThanOneElementException(String name) {
        super("Asked for one element when multiple had the same name: " + name);
    }
}