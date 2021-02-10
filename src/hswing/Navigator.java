package hswing;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Monitors the creation of Hierarchy Containers and stores
 * the components according to their name. The Navigator will
 * automatically be stored with a HFrame and have all the
 * NamedComponents within it
 */
public class Navigator {
    private static Navigator navigator;
    private final HashMap<String, List<JComponent>> components;
    private HFrame frame;

    /**
     * Makes a default Navigator
     */
    private Navigator() {
        components = new HashMap<>();
    }

    /**
     * Used to add named {@link JComponent}s to a Navigator list.
     * It will be ignored if the name is null
     * @param name  the name of the {@link JComponent}
     * @param component  the {@link JComponent} associated with the name
     */
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

    /**
     * Gets a single {@link JComponent} according to the name
     * @param name  the name of the {@link JComponent} to get
     * @return  the {@link JComponent} with the name. If the {@link JComponent} does not exist, it will return null
     * @throws MoreThanOneElementException if there are multiple {@link JComponent}s with the same name
     */
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

    /**
     * Gets a list of {@link JComponent}s with a name
     * @param name  the search name
     * @return  a list of {@link JComponent}s with that name
     */
    public List<JComponent> getList(String name) {
        return components.get(name);
    }

    /**
     * Gets a T {@link java.util.List} that inherit from a certain class
     * @param t  the class that all the {@link JComponent}s in the list will inherit from
     * @param <T>  the class
     * @return  a T {@link java.util.List} that are stored in the navigator
     */
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

    /**
     * Gets a T {@link java.util.List} that all have the same name and class
     * @param name  the name that all returned Ts will have
     * @param t  the class that all the {@link JComponent}s in the list will inherit from
     * @param <T>  the class to be searched
     * @return  a T {@link java.util.List} where all components have the name
     */
    public <T extends JComponent> List<T> getListByClass(String name, Class<T> t) {
        ArrayList<T> list = new ArrayList<>();
        if (!components.containsKey(name))
            return list;
        for (JComponent component : components.get(name)) {
            if (t.isInstance(component)) {
                list.add((T)component);
            }
        }
        return list;
    }

    /**
     * Gets the {@link HFrame} it is associated with
     * @return  the frame
     */
    public HFrame getFrame() {
        return frame;
    }

    /**
     * Removes all {@link JComponent}s that are not stored in the provided container
     * @param container  the container that will have all the {@link JComponent}s after
     *                   this action is performed
     */
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

    /**
     * Gets all the items in a container and adds them to the list provided
     * @param container  the container with all the {@link JComponent}s
     * @param alreadyFound  the list to add all the {@link JComponent}s to
     */
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

    /**
     * Gets the navigator that stored the last made NamedComponents
     * @return  the navigator
     */
    public static Navigator getNavigator() {
        if (navigator == null) {
            navigator = new Navigator();
        }
        return navigator;
    }

    /**
     * Gets a navigator with all the named {@link JComponent}s in it
     * and sets the static navigator to null (this will make the next
     * navigator retrieved not have any components of the current
     * navigator)
     * @param frame  the frame for the navigator
     * @return  the navigator with the frame
     */
    static Navigator getConstructed(HFrame frame) {
        navigator.frame = frame;
        Navigator constructed = navigator;
        navigator = null;
        return constructed;
    }
}