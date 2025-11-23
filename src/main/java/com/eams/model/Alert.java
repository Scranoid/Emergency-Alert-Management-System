
package com.eams.model;

import java.time.LocalDateTime;

public class Alert {
    private int id;
    private String title;
    private String message;
    private String severity;
    private int createdBy;
    private LocalDateTime createdAt;
    private boolean isBroadcasted;
    private LocalDateTime broadcastedAt;

    public Alert() {}

    public Alert(String title, String message, String severity, int createdBy) {
        this.title = title;
        this.message = message;
        this.severity = severity;
        this.createdBy = createdBy;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isBroadcasted() { return isBroadcasted; }
    public void setBroadcasted(boolean broadcasted) { isBroadcasted = broadcasted; }

    public LocalDateTime getBroadcastedAt() { return broadcastedAt; }
    public void setBroadcastedAt(LocalDateTime broadcastedAt) { this.broadcastedAt = broadcastedAt; }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", severity='" + severity + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", isBroadcasted=" + isBroadcasted +
                '}';
    }
}
