package demo;
import hswing.HButton;
import hswing.HFrame;
import hswing.HPanel;
import hswing.Navigator;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static hswing.NamedComponent.named;


public class Basic {
    Navigator navigator;

    public Basic() {
        HFrame hFrame = new HFrame("Basic",
                named("mainPanel", new HPanel(
                                named("buttonPanel", new HPanel( BoxLayout.PAGE_AXIS,
                                        named("button1", new HButton("1", this::buttonListener)),
                                        named("button2", new HButton("2", this::buttonListener)),
                                        named("button3", new HButton("3", this::buttonListener))
                                )),
                                named("labelPanel", new HPanel( BoxLayout.PAGE_AXIS,
                                        named("label", new JLabel("0")),
                                        named("label", new JLabel("1")),
                                        named("label", new JLabel("2"))
                                ))
                        )
                ));
        navigator = hFrame.getNavigator();

        hFrame.setVisible(true);
        hFrame.pack();
        hFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Basic();
    }

    private void buttonListener(ActionEvent e) {
        for (JLabel label : navigator.getListByClass(JLabel.class)) {
            label.setText(""+(Integer.parseInt(label.getText()) + Integer.parseInt(((JButton) e.getSource()).getText())));
        }
    }
}