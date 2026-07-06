package com.jachitalk.api.community.dto;

import com.jachitalk.api.community.domain.CommunityPostCategory;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateCommunityPostRequest(
        Long regionId,
        CommunityPostCategory category,
        @Size(max = 120) @Pattern(regexp = ".*\\S.*") String title,
        @Pattern(regexp = ".*\\S.*") String content
) {
}
