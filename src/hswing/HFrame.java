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
     * @param components  the {@link NamedComponent}s to be added to the children and {@link Navigator}
     */
    public HFrame(NamedComponent... components) {
        this("", components);
    }

    /**
     * Takes {@link NamedComponent}s and generates an {@link HFrame} with those components as children
     * and their names accessible via the {@link Navigator}. It also makes the title of the frame
     * set to the title given
     * @param title  the title of the frame
     * @param components  the {@link NamedComponent}s to be added to the children and {@link Navigator}
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

    /**
     * Sets the frame to visible and sets the default close operation
     *
     * <ul>
     * <li><code>DO_NOTHING_ON_CLOSE</code>
     * (defined in <code>WindowConstants</code>):
     * Don't do anything; require the
     * program to handle the operation in the <code>windowClosing</code>
     * method of a registered <code>WindowListener</code> object.
     *
     * <li><code>HIDE_ON_CLOSE</code>
     * (defined in <code>WindowConstants</code>):
     * Automatically hide the frame after
     * invoking any registered <code>WindowListener</code>
     * objects.
     *
     * <li><code>DISPOSE_ON_CLOSE</code>
     * (defined in <code>WindowConstants</code>):
     * Automatically hide and dispose the
     * frame after invoking any registered <code>WindowListener</code>
     * objects.
     *
     * <li><code>EXIT_ON_CLOSE</code>
     * (defined in <code>WindowConstants</code>):
     * Exit the application using the <code>System</code>
     * <code>exit</code> method.  Use this only in applications.
     * </ul>
     *
     * @param defaultCloseOperation  the operation which should be performed when the
     *      user closes the frame
     * @exception IllegalArgumentException  if defaultCloseOperation value
     *      isn't one of the above valid values
     * @see #setVisible(boolean)
     * @see #setDefaultCloseOperation(int)
     */
    public void open(int defaultCloseOperation) {
        open(defaultCloseOperation, false);
    }

    /**
     * Sets the frame to visible, packs it if packed is true, and sets the default close operation
     * <ul>
     * <li><code>DO_NOTHING_ON_CLOSE</code>
     * (defined in <code>WindowConstants</code>):
     * Don't do anything; require the
     * program to handle the operation in the <code>windowClosing</code>
     * method of a registered <code>WindowListener</code> object.
     *
     * <li><code>HIDE_ON_CLOSE</code>
     * (defined in <code>WindowConstants</code>):
     * Automatically hide the frame after
     * invoking any registered <code>WindowListener</code>
     * objects.
     *
     * <li><code>DISPOSE_ON_CLOSE</code>
     * (defined in <code>WindowConstants</code>):
     * Automatically hide and dispose the
     * frame after invoking any registered <code>WindowListener</code>
     * objects.
     *
     * <li><code>EXIT_ON_CLOSE</code>
     * (defined in <code>WindowConstants</code>):
     * Exit the application using the <code>System</code>
     * <code>exit</code> method.  Use this only in applications.
     * </ul>
     * @param defaultCloseOperation  the operation which should be performed when the
     *      user closes the frame
     * @param pack  if pack is true, it will pack the frame
     * @exception IllegalArgumentException  if defaultCloseOperation value
     *      isn't one of the above valid values
     * @see #setVisible(boolean)
     * @see #setDefaultCloseOperation(int)
     * @see #pack()
     */
    public void open(int defaultCloseOperation, boolean pack) {
        super.setDefaultCloseOperation(defaultCloseOperation);
        super.setVisible(true);
        if (pack) {
            super.pack();
        }
    }
}