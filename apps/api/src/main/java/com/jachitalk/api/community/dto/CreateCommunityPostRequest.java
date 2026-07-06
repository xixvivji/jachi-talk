package com.jachitalk.api.community.dto;

import com.jachitalk.api.community.domain.CommunityPostCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommunityPostRequest(
        @NotNull Long authorId,
        @NotNull Long regionId,
        @NotNull CommunityPostCategory category,
        @NotBlank @Size(max = 120) String title,
        @NotBlank String content
) {
}
