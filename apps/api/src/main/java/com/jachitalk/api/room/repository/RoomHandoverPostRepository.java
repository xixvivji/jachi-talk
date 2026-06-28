package com.jachitalk.api.room.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jachitalk.api.room.domain.RoomHandoverPost;
import com.jachitalk.api.room.domain.RoomHandoverStatus;

public interface RoomHandoverPostRepository extends JpaRepository<RoomHandoverPost, Long> {

    Page<RoomHandoverPost> findByStatus(RoomHandoverStatus status, Pageable pageable);
}

