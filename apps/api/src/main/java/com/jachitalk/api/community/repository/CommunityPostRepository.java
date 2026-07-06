package com.jachitalk.api.community.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jachitalk.api.community.domain.CommunityPost;
import com.jachitalk.api.community.domain.CommunityPostCategory;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    @Query("""
            SELECT post
            FROM CommunityPost post
            WHERE post.hidden = false
              AND (:regionId IS NULL OR post.region.id = :regionId)
              AND (:category IS NULL OR post.category = :category)
            """)
    Page<CommunityPost> findVisiblePosts(
            @Param("regionId") Long regionId,
            @Param("category") CommunityPostCategory category,
            Pageable pageable
    );
}
