package com.jachitalk.api.logging.domain;

import java.time.Instant;

import com.jachitalk.api.region.domain.Region;

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
@Table(name = "public_data_snapshots")
public class PublicDataSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Column(name = "source", nullable = false, length = 80)
    private String source;

    @Column(name = "data_type", nullable = false, length = 80)
    private String dataType;

    @Column(name = "payload_json", nullable = false, columnDefinition = "text")
    private String payloadJson;

    @Column(name = "collected_at", nullable = false)
    private Instant collectedAt;

    protected PublicDataSnapshot() {
    }

    public PublicDataSnapshot(Region region, String source, String dataType, String payloadJson, Instant collectedAt) {
        this.region = region;
        this.source = source;
        this.dataType = dataType;
        this.payloadJson = payloadJson;
        this.collectedAt = collectedAt;
    }

    public Long getId() {
        return id;
    }

    public Region getRegion() {
        return region;
    }

    public String getSource() {
        return source;
    }

    public String getDataType() {
        return dataType;
    }

    public String getPayloadJson() {
        return payloadJson;
    }

    public Instant getCollectedAt() {
        return collectedAt;
    }
}

