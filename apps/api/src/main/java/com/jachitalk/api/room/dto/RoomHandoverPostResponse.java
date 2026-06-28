package com.jachitalk.api.room.dto;

import java.time.Instant;
import java.time.LocalDate;

import com.jachitalk.api.room.domain.LandlordConsentStatus;
import com.jachitalk.api.room.domain.RoomHandoverPost;
import com.jachitalk.api.room.domain.RoomHandoverStatus;
import com.jachitalk.api.room.domain.RoomType;

public record RoomHandoverPostResponse(
        Long id,
        Long authorId,
        String authorNickname,
        Long regionId,
        String regionName,
        String title,
        Long deposit,
        Long monthlyRent,
        Long maintenanceFee,
        LocalDate availableFrom,
        LocalDate contractUntil,
        String nearestStation,
        RoomType roomType,
        String floor,
        Boolean petAllowed,
        LandlordConsentStatus landlordConsentStatus,
        String description,
        RoomHandoverStatus status,
        Instant createdAt,
        Instant updatedAt
) {

    public static RoomHandoverPostResponse from(RoomHandoverPost post) {
        return new RoomHandoverPostResponse(
                post.getId(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getRegion().getId(),
                post.getRegion().getSido() + " " + post.getRegion().getSigungu() + " " + post.getRegion().getEupmyeondong(),
                post.getTitle(),
                post.getDeposit(),
                post.getMonthlyRent(),
                post.getMaintenanceFee(),
                post.getAvailableFrom(),
                post.getContractUntil(),
                post.getNearestStation(),
                post.getRoomType(),
                post.getFloor(),
                post.getPetAllowed(),
                post.getLandlordConsentStatus(),
                post.getDescription(),
                post.getStatus(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}

