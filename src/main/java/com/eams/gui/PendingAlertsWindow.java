package com.eams.gui;

import com.eams.dao.AlertDAO;
import com.eams.model.Alert;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PendingAlertsWindow extends JFrame {

    private JTable table;
    private AlertDAO alertDAO = new AlertDAO();

    public PendingAlertsWindow() {
        setTitle("Pending Alerts");
        setSize(600, 400);
        setLocationRelativeTo(null);

        table = new JTable();
        loadAlerts();

        JButton broadcastBtn = new JButton("Broadcast Selected Alert");
        broadcastBtn.addActionListener(e -> broadcastAlert());

        add(new JScrollPane(table), "Center");
        add(broadcastBtn, "South");
    }

    private void loadAlerts() {
        try {
            List<Alert> alerts = alertDAO.listPendingAlerts();
            DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Title", "Severity"}, 0);

            for (Alert a : alerts) {
                model.addRow(new Object[]{a.getId(), a.getTitle(), a.getSeverity()});
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadcastAlert() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an alert first.");
            return;
        }

        int alertId = (int) table.getValueAt(row, 0);

        try {
            alertDAO.markBroadcasted(alertId);
            JOptionPane.showMessageDialog(this, "Alert broadcasted!");
            loadAlerts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
