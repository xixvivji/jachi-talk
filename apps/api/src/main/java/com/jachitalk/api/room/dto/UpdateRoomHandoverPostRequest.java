package com.jachitalk.api.room.dto;

import java.time.LocalDate;

import com.jachitalk.api.room.domain.LandlordConsentStatus;
import com.jachitalk.api.room.domain.RoomType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateRoomHandoverPostRequest(
        Long regionId,
        @Size(max = 120) @Pattern(regexp = ".*\\S.*") String title,
        @Min(0) Long deposit,
        @Min(0) Long monthlyRent,
        @Min(0) Long maintenanceFee,
        LocalDate availableFrom,
        LocalDate contractUntil,
        @Size(max = 80) String nearestStation,
        RoomType roomType,
        @Size(max = 30) String floor,
        Boolean petAllowed,
        LandlordConsentStatus landlordConsentStatus,
        @Pattern(regexp = ".*\\S.*") String description
) {
}
