package com.jachitalk.api.room.dto;

import java.time.LocalDate;

import com.jachitalk.api.room.domain.LandlordConsentStatus;
import com.jachitalk.api.room.domain.RoomType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRoomHandoverPostRequest(
        @NotNull Long authorId,
        @NotNull Long regionId,
        @NotBlank @Size(max = 120) String title,
        @NotNull @Min(0) Long deposit,
        @NotNull @Min(0) Long monthlyRent,
        @Min(0) Long maintenanceFee,
        LocalDate availableFrom,
        LocalDate contractUntil,
        @Size(max = 80) String nearestStation,
        @NotNull RoomType roomType,
        @Size(max = 30) String floor,
        Boolean petAllowed,
        LandlordConsentStatus landlordConsentStatus,
        @NotBlank String description
) {
}

