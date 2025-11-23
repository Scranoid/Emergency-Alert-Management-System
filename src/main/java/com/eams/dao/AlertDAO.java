
package com.eams.dao;

import com.eams.model.Alert;
import com.eams.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertDAO {

    public int createAlert(Alert alert) throws SQLException {
        String sql = "INSERT INTO alerts (title, message, severity, created_by) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, alert.getTitle());
            ps.setString(2, alert.getMessage());
            ps.setString(3, alert.getSeverity());
            ps.setInt(4, alert.getCreatedBy());
            int affected = ps.executeUpdate();
            if (affected == 0) throw new SQLException("Creating alert failed, no rows affected.");
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public List<Alert> listPendingAlerts() throws SQLException {
        String sql = "SELECT * FROM alerts WHERE is_broadcasted = FALSE ORDER BY created_at DESC";
        List<Alert> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alert a = mapRow(rs);
                list.add(a);
            }
        }
        return list;
    }

    public boolean markBroadcasted(int alertId) throws SQLException {
        String sql = "UPDATE alerts SET is_broadcasted = TRUE, broadcasted_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alertId);
            return ps.executeUpdate() > 0;
        }
    }

    private Alert mapRow(ResultSet rs) throws SQLException {
        Alert a = new Alert();
        a.setId(rs.getInt("id"));
        a.setTitle(rs.getString("title"));
        a.setMessage(rs.getString("message"));
        a.setSeverity(rs.getString("severity"));
        a.setCreatedBy(rs.getInt("created_by"));
        Timestamp t = rs.getTimestamp("created_at");
        if (t != null) a.setCreatedAt(t.toLocalDateTime());
        a.setBroadcasted(rs.getBoolean("is_broadcasted"));
        Timestamp b = rs.getTimestamp("broadcasted_at");
        if (b != null) a.setBroadcastedAt(b.toLocalDateTime());
        return a;
    }
}
