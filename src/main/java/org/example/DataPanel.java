package org.example;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DataPanel extends JPanel {

    @Getter
    private final List<LabeledCheckboxTextField> labeledCheckboxTextFields;

    public DataPanel() {
        super(new GridLayout(LabelEnum.values().length + 1, 3));

        labeledCheckboxTextFields = new ArrayList<>();

        initializeColumnNames();
        initializeRows();
    }

    private void initializeRows() {
        for (LabelEnum label : LabelEnum.sortedValues()) {
            LabeledCheckboxTextField labeledCheckboxTextField = new LabeledCheckboxTextField(label);
            labeledCheckboxTextFields.add(labeledCheckboxTextField);
            add(labeledCheckboxTextField);
        }
    }

    private void initializeColumnNames() {
        add(new JLabel("Cecha"));
        add(new JLabel("Czy zmodyfikować"));
        add(new JLabel("Wartość"));
    }

    public void add(LabeledCheckboxTextField labeledCheckboxTextField){
        add(labeledCheckboxTextField.getLabel());
        add(labeledCheckboxTextField.getCheckBox());
        add(labeledCheckboxTextField.getTextField());
    }

}
