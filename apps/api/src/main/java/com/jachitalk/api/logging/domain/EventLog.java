package com.jachitalk.api.logging.domain;

import java.time.Instant;

import com.jachitalk.api.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "event_logs")
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "event_name", nullable = false, length = 80)
    private String eventName;

    @Column(name = "properties_json", columnDefinition = "text")
    private String propertiesJson;

    @Column(name = "request_id", length = 100)
    private String requestId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected EventLog() {
    }

    public EventLog(User user, String eventName, String propertiesJson, String requestId, Instant createdAt) {
        this.user = user;
        this.eventName = eventName;
        this.propertiesJson = propertiesJson;
        this.requestId = requestId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getEventName() {
        return eventName;
    }

    public String getPropertiesJson() {
        return propertiesJson;
    }

    public String getRequestId() {
        return requestId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}

