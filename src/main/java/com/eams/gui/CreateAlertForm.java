package com.eams.gui;

import com.eams.dao.AlertDAO;
import com.eams.model.Alert;
import com.eams.model.User;
import javax.swing.*;
import java.awt.*;

public class CreateAlertForm extends JFrame {

    private JTextField titleField;
    private JTextArea messageArea;
    private JComboBox<String> severityBox;
    private AlertDAO alertDAO = new AlertDAO();
    private User loggedInUser;

    public CreateAlertForm(User user) {
        this.loggedInUser = user;

        setTitle("Create Alert");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        titleField = new JTextField();
        messageArea = new JTextArea();
        severityBox = new JComboBox<>(new String[]{"LOW", "MEDIUM", "HIGH", "CRITICAL"});

        JButton createBtn = new JButton("Submit Alert");
        createBtn.addActionListener(e -> createAlert());

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Message:"));
        add(new JScrollPane(messageArea));
        add(new JLabel("Severity:"));
        add(severityBox);
        add(createBtn);
    }

    private void createAlert() {
        try {
            String title = titleField.getText();
            String msg = messageArea.getText();
            String severity = severityBox.getSelectedItem().toString();

            Alert alert = new Alert(title, msg, severity, loggedInUser.getId());

            alertDAO.createAlert(alert);

            JOptionPane.showMessageDialog(this, "Alert created successfully!");

            this.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error creating alert.");
        }
    }
}
