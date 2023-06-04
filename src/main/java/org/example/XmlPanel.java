package org.example;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class XmlPanel extends JPanel {
    @Getter
    private final JCheckBox xmlCheckBox;
    public XmlPanel(Function<?,?> function) {
        super(new GridLayout(1, 3));
        add(new JLabel("Czy wykonać dodatkowy zapis do xml"));
        xmlCheckBox = new JCheckBox();
        add(xmlCheckBox);
        JButton executeButton = new JButton("Wykonaj");
        executeButton.addActionListener(e -> function.apply(null));
        add(executeButton);
    }
}

