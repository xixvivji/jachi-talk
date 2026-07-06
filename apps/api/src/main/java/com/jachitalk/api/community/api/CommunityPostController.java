package com.jachitalk.api.community.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jachitalk.api.community.application.CommunityPostService;
import com.jachitalk.api.community.domain.CommunityPostCategory;
import com.jachitalk.api.community.dto.CommunityPostResponse;
import com.jachitalk.api.community.dto.CreateCommunityPostRequest;
import com.jachitalk.api.community.dto.UpdateCommunityPostRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/community-posts")
public class CommunityPostController {

    private final CommunityPostService communityPostService;

    public CommunityPostController(CommunityPostService communityPostService) {
        this.communityPostService = communityPostService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommunityPostResponse create(@Valid @RequestBody CreateCommunityPostRequest request) {
        return communityPostService.create(request);
    }

    @GetMapping
    public Page<CommunityPostResponse> findPosts(@RequestParam(required = false) Long regionId,
                                                 @RequestParam(required = false) CommunityPostCategory category,
                                                 Pageable pageable) {
        Pageable normalizedPageable = normalize(pageable);
        return communityPostService.findPosts(regionId, category, normalizedPageable);
    }

    @GetMapping("/{id}")
    public CommunityPostResponse getById(@PathVariable Long id) {
        return communityPostService.getById(id);
    }

    @PatchMapping("/{id}")
    public CommunityPostResponse update(@PathVariable Long id,
                                        @Valid @RequestBody UpdateCommunityPostRequest request) {
        return communityPostService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        communityPostService.delete(id);
    }

    private Pageable normalize(Pageable pageable) {
        int page = Math.max(pageable.getPageNumber(), 0);
        int size = Math.min(Math.max(pageable.getPageSize(), 1), 50);

        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}
