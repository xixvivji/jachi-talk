package com.jachitalk.api.region.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sido", nullable = false, length = 50)
    private String sido;

    @Column(name = "sigungu", nullable = false, length = 80)
    private String sigungu;

    @Column(name = "eupmyeondong", nullable = false, length = 80)
    private String eupmyeondong;

    @Column(name = "legal_dong_code", nullable = false, unique = true, length = 10)
    private String legalDongCode;

    @Column(name = "center_latitude", precision = 10, scale = 7)
    private BigDecimal centerLatitude;

    @Column(name = "center_longitude", precision = 10, scale = 7)
    private BigDecimal centerLongitude;

    protected Region() {
    }

    public Region(String sido, String sigungu, String eupmyeondong, String legalDongCode) {
        this.sido = sido;
        this.sigungu = sigungu;
        this.eupmyeondong = eupmyeondong;
        this.legalDongCode = legalDongCode;
    }

    public Long getId() {
        return id;
    }

    public String getSido() {
        return sido;
    }

    public String getSigungu() {
        return sigungu;
    }

    public String getEupmyeondong() {
        return eupmyeondong;
    }

    public String getLegalDongCode() {
        return legalDongCode;
    }

    public BigDecimal getCenterLatitude() {
        return centerLatitude;
    }

    public BigDecimal getCenterLongitude() {
        return centerLongitude;
    }
}

