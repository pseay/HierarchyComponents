package demo.comparison;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ReadmeDemoJComponents {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Frame Title");
        JPanel panel = new JPanel();
        JButton button1 = new JButton("Click me!");
        JButton button2 = new JButton("No, click me!");
        JButton button3 = new JButton("CLICK ME!");

        button2.addActionListener((e) -> {
            System.out.println("Thanks for clicking me!");
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        frame.add(panel);
        //showing it
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
