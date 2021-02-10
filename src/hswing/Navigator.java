package hswing;

import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Navigator {
    private static Navigator navigator;
    private HashMap<String, List<JComponent>> components;
    private HFrame frame;

    public Navigator() {
        components = new HashMap<>();
    }

    public void add(String name, JComponent component) {
        if (name == null) {
            return;
        }
        if (components.get(name) == null) {
            ArrayList<JComponent> list = new ArrayList<>();
            list.add(component);
            components.put(name, list);
        } else {
            components.get(name).add(component);
        }
    }

    public JComponent getSingle(String name) throws MoreThanOneElementException {
        List<JComponent> componentsWithName = components.get(name);
        if (componentsWithName == null) {
            return null;
        }
        if (componentsWithName.size() == 1) {
            return componentsWithName.get(0);
        }
        throw new MoreThanOneElementException(name);
    }

    public List<JComponent> getList(String name) {
        return components.get(name);
    }

    public <T extends JComponent> List<T> getListByClass(Class<T> t) {
        ArrayList<T> list = new ArrayList<>();
        for (String key : components.keySet()) {
            for (JComponent component : components.get(key)) {
                if (t.isInstance(component)) {
                    list.add((T) component);
                }
            }
        }

        return list;
    }

    public <T extends JComponent> List<T> getListByClass(String name) {
        ArrayList<T> list = new ArrayList<>();
        if (!components.containsKey(name))
            return list;
        for (JComponent component : components.get(name)) {
            try {
                T casted = (T)component;
                list.add(casted);
            } catch (ClassCastException ignored) { }
        }

        return list;
    }

    public HFrame getFrame() {
        return frame;
    }

    public void removeItemsNotInContainer(Container container) {
        ArrayList<JComponent> itemsInContainer = new ArrayList<>();
        //gets the items in the container
        getItemsInContainer(container, itemsInContainer);
        //loops to remove components that aren't in the container
        for (String key : components.keySet().toArray(String[]::new)) {
            //gets all components that were contained in the container
            List<JComponent> componentsOfKey = components.get(key)
                    .stream()
                    .filter(itemsInContainer::contains)
                    .collect(Collectors.toList());
            //sets the map accordingly
            if (componentsOfKey.size() > 0) {
                components.put(key, componentsOfKey);
            } else {
                components.remove(key);
            }
        }
    }

    private void getItemsInContainer(Container container, ArrayList<JComponent> alreadyFound) {
        for (Component component : container.getComponents()) {
            if (component instanceof JComponent) {
                alreadyFound.add((JComponent)component);
            }
            if (component instanceof Container) {
                getItemsInContainer((Container)component, alreadyFound);
            }
        }
    }

    public static Navigator getNavigator() {
        if (navigator == null) {
            navigator = new Navigator();
        }
        return navigator;
    }

    public static Navigator getConstructed(HFrame frame) {
        navigator.frame = frame;
        Navigator constructed = navigator;
        navigator = null;
        return constructed;
    }
}