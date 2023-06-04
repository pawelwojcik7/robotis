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
            Robot robot = new Robot();

            robot.mouseMove(80, 30);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            robot.mouseMove(130, 73);

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

                if (!labeledCheckboxTextField.getCheckBox().isSelected()) {
                    robot.keyPress(KeyEvent.VK_RIGHT);
                    robot.keyRelease(KeyEvent.VK_RIGHT);
                    Thread.sleep(20);
                } else {
                    if (labeledCheckboxTextField.getLabelEnum().equals(LabelEnum.TYP_MATRYCY)) {

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

                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        Transferable contents = clipboard.getContents(null);

                        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                            try {
                                String text = (String) contents.getTransferData(DataFlavor.stringFlavor);
                                System.out.println("Tekst ze schowka: " + text);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
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

            if (xmlPanel.getXmlCheckBox().isSelected()) {
                robot.mouseMove(800, 30);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            }
        } catch (AWTException | InterruptedException e) {
            return null;
        }
        return "Sukces";
    }


}
