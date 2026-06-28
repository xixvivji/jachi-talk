package com.jachitalk.api.room.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jachitalk.api.room.application.RoomHandoverPostService;
import com.jachitalk.api.room.dto.CreateRoomHandoverPostRequest;
import com.jachitalk.api.room.dto.RoomHandoverPostResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/room-handover-posts")
public class RoomHandoverPostController {

    private final RoomHandoverPostService roomHandoverPostService;

    public RoomHandoverPostController(RoomHandoverPostService roomHandoverPostService) {
        this.roomHandoverPostService = roomHandoverPostService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomHandoverPostResponse create(@Valid @RequestBody CreateRoomHandoverPostRequest request) {
        return roomHandoverPostService.create(request);
    }

    @GetMapping
    public Page<RoomHandoverPostResponse> findOpenPosts(Pageable pageable) {
        Pageable normalizedPageable = normalize(pageable);
        return roomHandoverPostService.findOpenPosts(normalizedPageable);
    }

    @GetMapping("/{id}")
    public RoomHandoverPostResponse getById(@PathVariable Long id) {
        return roomHandoverPostService.getById(id);
    }

    private Pageable normalize(Pageable pageable) {
        int page = Math.max(pageable.getPageNumber(), 0);
        int size = Math.min(Math.max(pageable.getPageSize(), 1), 50);

        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}

