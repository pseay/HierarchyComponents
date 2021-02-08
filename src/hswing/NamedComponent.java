package hswing;

import javax.swing.*;

public class NamedComponent {
    private String name;
    private JComponent component;
    private NamedComponent(String name, JComponent component) {
        this.name = name;
        this.component = component;
    }

    public static NamedComponent named(String name, JComponent component) {
        return new NamedComponent(name, component);
    }

    public String getName() {
        return name;
    }

    public JComponent getComponent() {
        return component;
    }
}