package com.eams;

import com.eams.gui.LoginWindow;

public class MainApp {
    public static void main(String[] args) {

        // Launch the Swing GUI instead of console-based backend
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.setVisible(true);
        });
    }
}
