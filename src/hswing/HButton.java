package hswing;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * An implementation of a hierarchy button.
 * <p>
 * This button allows an {@link ActionListener} to be put into the constructor.
 * </p>
 */

public class HButton extends JButton {

    /**
     * Creates a button with the given text
     *
     * @param text  the text of the button
     */
    public HButton(String text) {
        super(text);
    }


    /**
     * Creates a button with the given text and {@link ActionListener}
     *
     * @param text  the text of the button
     * @param listener  the {@link ActionListener} for the button
     */
    public HButton(String text, ActionListener listener) {
        super(text);
        addActionListener(listener);
    }

}