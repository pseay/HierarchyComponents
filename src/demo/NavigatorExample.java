package demo;

import hswing.*;

import javax.swing.*;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

import static hswing.NamedComponent.*;

public class NavigatorExample {
    public static void main(String[] args) {
        Navigator navigator = new HFrame("Title",
            notNamed(new HPanel( BoxLayout.X_AXIS,
                named("button1", new HButton("Click me to change labels:")),
                notNamed(new HPanel( BoxLayout.Y_AXIS,
                    named("label", new JLabel("a")),
                    named("label", new JLabel("b")),
                    named("label", new JLabel("c"))
                ))
            ))
        ).getNavigator();

        //this would throw a MoreThanOneComponentException
        //navigator.getSingle("label");

        //returns null because no components have the name 'label' and the class of a JButton
        navigator.getSingleByClass("label", JButton.class);

        //gets the one 'button1' with the class 'JButton' (automatically casts it from HButton -> JButton)
        navigator.getSingleByClass("button1", JButton.class).addActionListener(e -> {
            //gets all the JLabels with name 'label'
            navigator.getListByClass("label", JLabel.class).forEach(jLabel -> {
                jLabel.setText(jLabel.getText().repeat(2));
            });
            navigator.getFrame().pack();
        });

        //getting a list of JComponents based on just the class
        navigator.getListByClass(JLabel.class).forEach(label -> {
            label.setBorder(BorderFactory.createLineBorder(Color.blue, 5, true));
        });

        AtomicInteger aint = new AtomicInteger(10);
        //getting a list based on just the name
        navigator.getList("label").forEach(label -> {
            label.setFont(new Font("TimesRoman", Font.ITALIC, aint.getAndAdd(5)));
        });

        //getting one button based on the name
        JComponent button1 = navigator.getSingle("button1");
        Font f = button1.getFont();
        button1.setFont(new Font(f.getFontName(), Font.BOLD, f.getSize()+2));

        navigator.getFrame().open(WindowConstants.EXIT_ON_CLOSE, true);
    }
}
