package com.example.idol;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Slider {
    int min;
    int max;
    int start;
    int step;
    String info;

    Slider(int min,int max ,int start,int step, String role){
        this.min=min;
        this.max=max;
        this.start=start;
        this.step=step;
        this.info=role;
    }

    int return_value () {
        JFrame parent = new JFrame();

        JOptionPane optionPane = new JOptionPane();
        JSlider slider = getSlider();

        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider theSlider = (JSlider) changeEvent.getSource();
                if (!theSlider.getValueIsAdjusting()) {
                    optionPane.setMessage(new Object[]{info, theSlider, "current value: ", theSlider.getValue()});
                }
            }
        };
        slider.addChangeListener(changeListener);
        optionPane.setMessage(new Object[]{info, slider, "current value: ", start});
        optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
        optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);


        JDialog dialog = optionPane.createDialog(parent, "My Slider");
        dialog.setVisible(true);

        return slider.getValue();
    }

    JSlider getSlider() {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min,max,start);
        slider.setMajorTickSpacing(step);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }
}