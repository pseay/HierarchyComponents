package hswing;

import javax.swing.*;

/**
 * Creates an association between a name and a {@link JComponent}.
 * This name can be used later by a {@link Navigator} in order to retrieve the {@link JComponent}.
 */
public class NamedComponent {
    private final String name;
    private final JComponent component;

    /**
     * Creates a new NamedComponent with the given name and {@link JComponent}
     * @param name  the name of a component to be used by a {@link Navigator}
     * @param component  the {@link JComponent} associated with the name
     */
    private NamedComponent(String name, JComponent component) {
        this.name = name;
        this.component = component;
    }

    /**
     * Makes a new named component with the given name and component.
     * A null value for the name parameter will cause the component
     * to be ignored by the {@link Navigator}.
     * @param name  the name for a new {@link NamedComponent}
     * @param component  the component for a new {@link NamedComponent}
     * @return  a new named component with the name and component
     */
    public static NamedComponent named(String name, JComponent component) {
        return new NamedComponent(name, component);
    }

    /**
     * Gets a new named component without a name.
     * This will be ignored by the {@link Navigator}.
     * @param component  the component for a new not-named NamedComponent
     */
    public static NamedComponent notNamed(JComponent component) {
        return new NamedComponent(null, component);
    }

    /**
     * Gets the name
     * @return  the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the component
     * @return  the component
     */
    public JComponent getComponent() {
        return component;
    }
}