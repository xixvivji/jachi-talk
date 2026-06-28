package com.jachitalk.api.room.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jachitalk.api.room.domain.RoomHandoverPost;
import com.jachitalk.api.room.domain.RoomHandoverStatus;

public interface RoomHandoverPostRepository extends JpaRepository<RoomHandoverPost, Long> {

    @Query("""
            SELECT post
            FROM RoomHandoverPost post
            WHERE post.hidden = false
              AND post.status = :status
              AND (:regionId IS NULL OR post.region.id = :regionId)
              AND (:minRent IS NULL OR post.monthlyRent >= :minRent)
              AND (:maxRent IS NULL OR post.monthlyRent <= :maxRent)
            """)
    Page<RoomHandoverPost> findVisiblePosts(
            @Param("status") RoomHandoverStatus status,
            @Param("regionId") Long regionId,
            @Param("minRent") Long minRent,
            @Param("maxRent") Long maxRent,
            Pageable pageable
    );
}
