package org.example;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class RecordPanel extends JPanel {
    @Getter
    private final JTextField recordField;

    public RecordPanel() {
        super(new GridLayout(1, 2));

        add(new JLabel("Który rekord ma zostać zmodyfikowany"));
        recordField = new JTextField();
        recordField.setDocument(new NumberOnlyDocument());
        add(recordField);
    }
}
