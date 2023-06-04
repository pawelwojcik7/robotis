package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ItemEvent;
@Getter
public class LabeledCheckboxTextField {
    private final JLabel label;
    private final JCheckBox checkBox;
    private final JTextField textField;
    private final LabelEnum labelEnum;

    public LabeledCheckboxTextField(LabelEnum label) {
        this.label = new JLabel(label.getLabel());
        this.labelEnum = label;
        checkBox = new JCheckBox();
        textField = new JTextField();
        textField.setEnabled(false);

        checkBox.addItemListener(e -> {
            textField.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
        });
    }

}
