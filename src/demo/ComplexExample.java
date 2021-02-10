package demo;
import demo.demoClasses.ValueLabel;
import hswing.HButton;
import hswing.HFrame;
import hswing.HPanel;
import hswing.Navigator;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static hswing.NamedComponent.*;


public class ComplexExample {

    public static void main(String[] args) {
        new ComplexExample();
    }

    Navigator navigator;

    public ComplexExample() {
        //This example uses many features of the hswing package
        //making a new frame
        HFrame hFrame = new HFrame("Basic",
            named("mainPanel", new HPanel(
                //uses the layout parameter to make it a BoxLayout with a direction
                notNamed(new HPanel( BoxLayout.PAGE_AXIS,
                    //shows how buttons can have a unique name
                    //shows how buttons can have actionListeners built in
                    named("button1", new HButton("-1", this::buttonListener)),
                    named("button2", new HButton("1", this::buttonListener)),
                    named("button3", new HButton("5", (e) -> {
                        //using getList (could use getListByClass and wouldn't have to cast)
                        navigator.getList("label").forEach(component -> {
                            ((ValueLabel)component).increment(5);
                        });
                    }))
                )),
                //uses notNamed to be ignored by the Navigator
                notNamed(new HPanel( BoxLayout.PAGE_AXIS,
                    //shows how multiple items can have the same name
                    named("label", new ValueLabel(0)),
                    named("label", new ValueLabel(5)),
                    named("label", new ValueLabel(10))
                ))
            )
        ));
        navigator = hFrame.getNavigator();

        hFrame.setVisible(true);
        hFrame.pack();
        hFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void buttonListener(ActionEvent e) {

        int change = 0;
        //uses get single to get a single JComponent
        if (e.getSource() == navigator.getSingle("button1")) {
            change = -1;
        } else if (e.getSource() == navigator.getSingle("button2")) {
            change = 1;
        }

        final int fChange = change;
        //uses getListByClass (does not have to cast)
        navigator.getListByClass(ValueLabel.class).forEach(valueLabel -> {
            valueLabel.increment(fChange);
        });
    }
}