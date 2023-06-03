package org.example;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class LabeledCheckboxTextField {
    private final JLabel label;
    private final JCheckBox checkBox;
    private final JTextField textField;

    public LabeledCheckboxTextField(String labelText) {
        label = new JLabel(labelText);
        checkBox = new JCheckBox();
        textField = new JTextField();
        textField.setEnabled(false);

        checkBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                textField.setEnabled(true);
            } else {
                textField.setEnabled(false);
            }
        });
    }

    public JLabel getLabel() {
        return label;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public JTextField getTextField() {
        return textField;
    }
}
