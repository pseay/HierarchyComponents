package hswing;

import javax.swing.*;

/**
 * A hierarchy frame that can take {@link NamedComponent}s in the constructor
 * and has a {@link Navigator} so it can navigate through the components made
 */
public class HFrame extends JFrame {

    private final Navigator nav;

    /**
     * Takes {@link NamedComponent}s and generates an {@link HFrame} with those components as children
     * and their names accessible via the {@link Navigator}
     * @param components
     */
    public HFrame(NamedComponent... components) {
        this("", components);
    }

    /**
     * Takes {@link NamedComponent}s and generates an {@link HFrame} with those components as children
     * and their names accessible via the {@link Navigator}. It also makes the title of the frame
     * set to the title given
     * @param title  the title of the frame
     * @param components  the components to add as children (and add to the navigator)
     */
    public HFrame(String title, NamedComponent... components) {
        super(title);
        nav = Navigator.getConstructed(this);
        for (NamedComponent c : components) {
            add(c.getComponent());
            Navigator.getNavigator().add(c.getName(), c.getComponent());
        }
        nav.removeItemsNotInContainer(this);
    }

    /**
     *
     * @return  the navigator that was made during the construction of the object
     *
     * @implNote  the navigator does not have components that were made after the construction of an element
     */
    public Navigator getNavigator() {
        return nav;
    }
}