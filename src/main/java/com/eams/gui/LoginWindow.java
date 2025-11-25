package com.eams.gui;

import com.eams.dao.UserDAO;
import com.eams.model.User;
import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserDAO userDAO = new UserDAO();

    public LoginWindow() {
        setTitle("EAMS - Login");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JPanel userPanel = new JPanel();
        userPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        userPanel.add(usernameField);

        JPanel passPanel = new JPanel();
        passPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        passPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());

        add(userPanel);
        add(passPanel);
        add(loginButton);
    }

    private void login() {
        try {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = userDAO.findByUsername(username);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "Invalid username.");
                return;
            }

            // bcrypt verification
            if (!org.mindrot.jbcrypt.BCrypt.checkpw(password, user.getPasswordHash())) {
                JOptionPane.showMessageDialog(this, "Wrong password.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Login successful!");

            new Dashboard(user).setVisible(true);
            this.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error logging in.");
        }
    }
}
