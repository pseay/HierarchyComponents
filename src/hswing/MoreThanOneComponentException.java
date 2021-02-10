package hswing;

/**
 * An exception used for when one component with a name is requested, but multiple exist
 */
public class MoreThanOneComponentException extends RuntimeException {
    /**
     * Constructs an exception with a message for that name
     * @param name  the name that multiple components had
     */
    public MoreThanOneComponentException(String name) {
        super("Asked for one component when multiple had the same name: " + name);
    }
}