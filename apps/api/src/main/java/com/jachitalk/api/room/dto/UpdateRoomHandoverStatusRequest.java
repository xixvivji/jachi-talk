package com.jachitalk.api.room.dto;

import com.jachitalk.api.room.domain.RoomHandoverStatus;

import jakarta.validation.constraints.NotNull;

public record UpdateRoomHandoverStatusRequest(
        @NotNull RoomHandoverStatus status
) {
}
