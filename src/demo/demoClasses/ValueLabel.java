package demo.demoClasses;

import javax.swing.*;

public class ValueLabel extends JLabel {
    private int value;
    public ValueLabel(int value) {
        super("" + value);
        this.value = value;
    }

    @Override
    public void setText(String text) {
        try {
            int newV = Integer.parseInt(text);
            this.value = newV;
            setTextToValue();
        } catch (Exception ignored) { }
    }

    public int getValue() {
        return value;
    }

    public void increment(int i) {
        value+=i;
        setTextToValue();
    }

    private void setTextToValue() {
        super.setText("" + value);
    }
}
