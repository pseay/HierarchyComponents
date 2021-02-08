package hswing;

import javax.swing.*;
import java.awt.*;

public class HPanel extends JPanel {

    /**
     *
     * @param components - the components to be added to the panel
     */

    public HPanel(NamedComponent... components) {
        super();
        for (NamedComponent c : components) {
            add(c.getComponent());
            Navigator.getNavigator().add(c.getName(), c.getComponent());
        }
    }

    /**
     *
     * @param boxLayoutAxis the axis to lay out components along. Can be one of:
     *              {@code BoxLayout.X_AXIS, BoxLayout.Y_AXIS,
     *              BoxLayout.LINE_AXIS} or {@code BoxLayout.PAGE_AXIS}
     * @param components the components to be added to the panel
     *
     * @exception AWTError  if the value of {@code axis} is invalid
     */
    public HPanel(int boxLayoutAxis, NamedComponent... components) {
        super();
        this.setLayout(new BoxLayout(this, boxLayoutAxis));
        for (NamedComponent c : components) {
            add(c.getComponent());
            Navigator.getNavigator().add(c.getName(), c.getComponent());
        }
    }
}