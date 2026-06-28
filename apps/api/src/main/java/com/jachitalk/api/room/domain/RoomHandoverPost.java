package com.jachitalk.api.room.domain;

import java.time.LocalDate;

import com.jachitalk.api.common.domain.BaseTimeEntity;
import com.jachitalk.api.region.domain.Region;
import com.jachitalk.api.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "room_handover_posts")
public class RoomHandoverPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @Column(name = "deposit", nullable = false)
    private Long deposit;

    @Column(name = "monthly_rent", nullable = false)
    private Long monthlyRent;

    @Column(name = "maintenance_fee")
    private Long maintenanceFee;

    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "contract_until")
    private LocalDate contractUntil;

    @Column(name = "nearest_station", length = 80)
    private String nearestStation;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false, length = 30)
    private RoomType roomType;

    @Column(name = "floor", length = 30)
    private String floor;

    @Column(name = "pet_allowed")
    private Boolean petAllowed;

    @Enumerated(EnumType.STRING)
    @Column(name = "landlord_consent_status", nullable = false, length = 30)
    private LandlordConsentStatus landlordConsentStatus = LandlordConsentStatus.NOT_CHECKED;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private RoomHandoverStatus status = RoomHandoverStatus.OPEN;

    protected RoomHandoverPost() {
    }

    public RoomHandoverPost(User author, Region region, String title, Long deposit, Long monthlyRent,
                            RoomType roomType, String description) {
        this.author = author;
        this.region = region;
        this.title = title;
        this.deposit = deposit;
        this.monthlyRent = monthlyRent;
        this.roomType = roomType;
        this.description = description;
    }

    public void updateDetails(Long maintenanceFee, LocalDate availableFrom, LocalDate contractUntil,
                              String nearestStation, String floor, Boolean petAllowed,
                              LandlordConsentStatus landlordConsentStatus) {
        this.maintenanceFee = maintenanceFee;
        this.availableFrom = availableFrom;
        this.contractUntil = contractUntil;
        this.nearestStation = nearestStation;
        this.floor = floor;
        this.petAllowed = petAllowed;
        this.landlordConsentStatus = landlordConsentStatus;
    }

    public Long getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }

    public Region getRegion() {
        return region;
    }

    public String getTitle() {
        return title;
    }

    public Long getDeposit() {
        return deposit;
    }

    public Long getMonthlyRent() {
        return monthlyRent;
    }

    public Long getMaintenanceFee() {
        return maintenanceFee;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public LocalDate getContractUntil() {
        return contractUntil;
    }

    public String getNearestStation() {
        return nearestStation;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public String getFloor() {
        return floor;
    }

    public Boolean getPetAllowed() {
        return petAllowed;
    }

    public LandlordConsentStatus getLandlordConsentStatus() {
        return landlordConsentStatus;
    }

    public String getDescription() {
        return description;
    }

    public RoomHandoverStatus getStatus() {
        return status;
    }
}
