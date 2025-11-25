package com.eams.gui;

import com.eams.model.User;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    private User loggedInUser;

    public Dashboard(User user) {
        this.loggedInUser = user;

        setTitle("EAMS - Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton createAlertBtn = new JButton("Create Alert");
        JButton viewAlertsBtn = new JButton("View Pending Alerts");
        JButton exitBtn = new JButton("Exit");

        createAlertBtn.addActionListener(e -> new CreateAlertForm(loggedInUser).setVisible(true));
        viewAlertsBtn.addActionListener(e -> new PendingAlertsWindow().setVisible(true));
        exitBtn.addActionListener(e -> System.exit(0));

        add(createAlertBtn);
        add(viewAlertsBtn);
        add(exitBtn);
    }
}
