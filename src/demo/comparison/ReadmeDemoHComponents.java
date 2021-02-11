package demo.comparison;

import hswing.HButton;
import hswing.HFrame;
import hswing.HPanel;

import javax.swing.*;

import static hswing.NamedComponent.*;

public class ReadmeDemoHComponents {
    public static void main(String[] args) {
        HFrame frame = new HFrame("Frame Title",
            notNamed(new HPanel(
                named("button1", new HButton("Click me!")),
                named("button2", new HButton("No, click me!", (e)-> {
                    System.out.println("Thanks for clicking me!");
                })),
                named("button3", new HButton("CLICK ME!"))
            ))
        );
        //showing it
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
