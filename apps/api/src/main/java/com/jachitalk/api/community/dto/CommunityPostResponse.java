package com.jachitalk.api.community.dto;

import java.time.Instant;

import com.jachitalk.api.community.domain.CommunityPost;
import com.jachitalk.api.community.domain.CommunityPostCategory;

public record CommunityPostResponse(
        Long id,
        Long authorId,
        String authorNickname,
        Long regionId,
        String regionName,
        CommunityPostCategory category,
        String title,
        String content,
        Instant createdAt,
        Instant updatedAt
) {

    public static CommunityPostResponse from(CommunityPost post) {
        return new CommunityPostResponse(
                post.getId(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getRegion().getId(),
                post.getRegion().getSido() + " " + post.getRegion().getSigungu() + " " + post.getRegion().getEupmyeondong(),
                post.getCategory(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
