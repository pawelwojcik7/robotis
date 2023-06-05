package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private final DataPanel dataPanel;
    private final RecordPanel recordPanel;
    private final XmlPanel xmlPanel;

    public MainFrame() {
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        dataPanel = new DataPanel();
        recordPanel = new RecordPanel();
        xmlPanel = new XmlPanel(this::fillForm);

        getContentPane().add(recordPanel, BorderLayout.NORTH);
        getContentPane().add(dataPanel, BorderLayout.CENTER);
        getContentPane().add(xmlPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    private Object fillForm(Object o) {
        try {
            String producer = null;
            String typMatrycy = null;
            Robot robot = new Robot();

            robot.mouseMove(80, 50);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            robot.mouseMove(100, 83);

            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            int recordNumber = Integer.parseInt(recordPanel.getRecordField().getText());
            for (int i = 0; i < recordNumber - 1; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
                Thread.sleep(100);
            }

            Thread.sleep(2000);

            for (LabeledCheckboxTextField labeledCheckboxTextField : dataPanel.getLabeledCheckboxTextFields()) {

                if (labeledCheckboxTextField.getLabelEnum().equals(LabelEnum.TYP_MATRYCY)) {
                    typMatrycy = copyValue(robot);
                }
                if (labeledCheckboxTextField.getLabelEnum().equals(LabelEnum.NAZWA_PRODUCENTA)) {
                    producer = copyValue(robot);
                }
                if (!labeledCheckboxTextField.getCheckBox().isSelected()) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    Thread.sleep(20);
                } else {
                    String text = labeledCheckboxTextField.getTextField().getText();
                    if(labeledCheckboxTextField.getLabelEnum().equals(LabelEnum.TYP_MATRYCY)){
                        typMatrycy =text;
                    }
                    if(labeledCheckboxTextField.getLabelEnum().equals(LabelEnum.NAZWA_PRODUCENTA)){
                       producer =text;
                    }
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

            if (xmlPanel.getXmlCheckBox().isSelected()) {
                robot.mouseMove(800, 30);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }

            robot.mouseMove(749, 45);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            handleStrings(robot, producer, typMatrycy);
        } catch (AWTException | InterruptedException e) {
            return null;
        }
        return "Sukces";
    }

    private String copyValue(Robot robot) throws InterruptedException {
        try {
            robot.keyPress(KeyEvent.VK_INSERT);
            robot.keyRelease(KeyEvent.VK_INSERT);
            Thread.sleep(20);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(20);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(20);
            robot.keyPress(KeyEvent.VK_ESCAPE);

            robot.keyRelease(KeyEvent.VK_ESCAPE);

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);

            if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    String text = (String) contents.getTransferData(DataFlavor.stringFlavor);
                    System.out.println("Tekst ze schowka: " + text);
                    return text;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            return null;
        }
        return "";
    }

    public void handleStrings(Robot robot, String str1, String str2) throws InterruptedException {
        Producers producer = null;
        try {
            producer = Producers.valueOf(str1.toUpperCase());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (producer != null) {

            robot.mouseMove(1359, 105);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            for (int i = 0; i < 6; i++) {
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
                Thread.sleep(20);
            }

            for (int i = 0; i < producer.getNumber(); i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
                Thread.sleep(20);
            }

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } else {
            robot.mouseMove(1359, 105);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            for (int i = 0; i < 6; i++) {
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
                Thread.sleep(20);
            }
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        robot.mouseMove(1791, 49);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        robot.mouseMove(1681, 50);
        for (int i = 0; i < 3; i++) {
            robot.keyPress(KeyEvent.VK_UP);
            robot.keyRelease(KeyEvent.VK_UP);
            Thread.sleep(20);
        }

        if ("".equals(str2)) {
            robot.mouseMove(1681, 50);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.mouseMove(1521, 53);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } else if ("matowa".equals(str2)) {
            robot.mouseMove(1681, 50);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.mouseMove(1521, 53);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } else if ("blyszczaca".equals(str2)) {
            robot.mouseMove(1521, 53);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } else {
            // handling for other or empty value is the same as for ""
            robot.mouseMove(1681, 50);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.mouseMove(1521, 53);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        }
    }


}
