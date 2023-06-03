package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class RobotTyping {
    private static final Map<Character, Integer> KEY_MAP = new HashMap<>();

    static {
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseLetters = lowerCaseLetters.toUpperCase();
        for (int i = 0; i < lowerCaseLetters.length(); i++) {
            KEY_MAP.put(lowerCaseLetters.charAt(i), KeyEvent.getExtendedKeyCodeForChar(lowerCaseLetters.charAt(i)));
            KEY_MAP.put(upperCaseLetters.charAt(i), KeyEvent.getExtendedKeyCodeForChar(upperCaseLetters.charAt(i)));
        }

        String digits = "0123456789";
        for (int i = 0; i < digits.length(); i++) {
            KEY_MAP.put(digits.charAt(i), KeyEvent.getExtendedKeyCodeForChar(digits.charAt(i)));
        }

        KEY_MAP.put('"', KeyEvent.VK_QUOTE);
    }

    public static void typeString(Robot robot, String str) {
        for (char c : str.toCharArray()) {
            if (KEY_MAP.containsKey(c)) {
                if (c == '"') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }
                robot.keyPress(KEY_MAP.get(c));
                robot.keyRelease(KEY_MAP.get(c));
                if (c == '"') {
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
            }
        }

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
