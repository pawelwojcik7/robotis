package org.example;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class App {
    private JFrame frame;
    private JTextField recordField;
    private JCheckBox xmlCheckBox;
    private final String[] labels = {"Nazwa producenta", "Wielkość matrycy", "Rozdzielczość", "Typ matrycy", "Czy dotykowa", "Procesor", "Liczba rdzeni fizycznych", "Taktowanie", "RAM", "Pojemność dysku", "Typ dysku", "Karta graficzna", "Pamięć karty graficznej", "System operacyjny", "Napęd optyczny"};
    private final List<LabeledCheckboxTextField> labeledCheckboxTextFields = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                App window = new App();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public App() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel recordPanel = new JPanel(new GridLayout(1, 2));
        recordPanel.add(new JLabel("Który rekord ma zostać zmodyfikowany"));
        recordField = new JTextField();
        recordField.setDocument(new NumberOnlyDocument());
        recordPanel.add(recordField);
        frame.getContentPane().add(recordPanel, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new GridLayout(labels.length + 1, 3));
        dataPanel.add(new JLabel("Cecha"));
        dataPanel.add(new JLabel("Czy zmodyfikować"));
        dataPanel.add(new JLabel("Wartość"));

        for (String label : labels) {
            LabeledCheckboxTextField labeledCheckboxTextField = new LabeledCheckboxTextField(label);
            labeledCheckboxTextFields.add(labeledCheckboxTextField);
            dataPanel.add(labeledCheckboxTextField.getLabel());
            dataPanel.add(labeledCheckboxTextField.getCheckBox());
            dataPanel.add(labeledCheckboxTextField.getTextField());
        }
        frame.getContentPane().add(dataPanel, BorderLayout.CENTER);

        JPanel xmlPanel = new JPanel(new GridLayout(1, 3));
        xmlPanel.add(new JLabel("Czy wykonać dodatkowy zapis do xml"));
        xmlCheckBox = new JCheckBox();
        xmlPanel.add(xmlCheckBox);
        JButton executeButton = new JButton("Wykonaj");
        executeButton.addActionListener(e -> fillForm(100, 73));
        xmlPanel.add(executeButton);
        frame.getContentPane().add(xmlPanel, BorderLayout.SOUTH);
    }

    private static class NumberOnlyDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return;
                }
            }
            super.insertString(offs, str, a);
        }
    }

    public void fillForm(int x, int y) {
        try {
            Robot robot = new Robot();

            robot.mouseMove(80, 30);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            robot.mouseMove(x, y);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // Wciśnij strzałkę w dół x-1 razy
            int recordNumber = Integer.parseInt(recordField.getText());
            for (int i = 0; i < recordNumber - 1; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
            }

            Thread.sleep(2000);

            for (LabeledCheckboxTextField labeledCheckboxTextField : labeledCheckboxTextFields) {

                if (!labeledCheckboxTextField.getCheckBox().isSelected()) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    Thread.sleep(20);
                } else {

                    String text = labeledCheckboxTextField.getTextField().getText();
                    for (int j = 0; j < 30; j++) {
                        robot.keyPress(KeyEvent.VK_BACK_SPACE);
                        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                    }
                    RobotTyping.typeString(robot, text);
                    Thread.sleep(20);
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    Thread.sleep(20);
                }
            }

            if (xmlCheckBox.isSelected()) {
                robot.mouseMove(800, 30);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
