package hswing;

import javax.swing.*;

public class HFrame extends JFrame {

    Navigator nav;

    public HFrame(NamedComponent... components) {
        this("", components);
    }

    /**
     *
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