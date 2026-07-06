package com.jachitalk.api.community.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jachitalk.api.common.error.NotFoundException;
import com.jachitalk.api.community.domain.CommunityPost;
import com.jachitalk.api.community.domain.CommunityPostCategory;
import com.jachitalk.api.community.dto.CommunityPostResponse;
import com.jachitalk.api.community.dto.CreateCommunityPostRequest;
import com.jachitalk.api.community.dto.UpdateCommunityPostRequest;
import com.jachitalk.api.community.repository.CommunityPostRepository;
import com.jachitalk.api.region.domain.Region;
import com.jachitalk.api.region.repository.RegionRepository;
import com.jachitalk.api.user.domain.User;
import com.jachitalk.api.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class CommunityPostService {

    private final CommunityPostRepository communityPostRepository;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    public CommunityPostService(CommunityPostRepository communityPostRepository,
                                UserRepository userRepository,
                                RegionRepository regionRepository) {
        this.communityPostRepository = communityPostRepository;
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
    }

    @Transactional
    public CommunityPostResponse create(CreateCommunityPostRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다. id=" + request.authorId()));
        Region region = regionRepository.findById(request.regionId())
                .orElseThrow(() -> new NotFoundException("지역을 찾을 수 없습니다. id=" + request.regionId()));

        CommunityPost post = new CommunityPost(
                author,
                region,
                request.category(),
                request.title(),
                request.content()
        );

        return CommunityPostResponse.from(communityPostRepository.save(post));
    }

    public Page<CommunityPostResponse> findPosts(Long regionId, CommunityPostCategory category, Pageable pageable) {
        return communityPostRepository.findVisiblePosts(regionId, category, pageable)
                .map(CommunityPostResponse::from);
    }

    public CommunityPostResponse getById(Long id) {
        CommunityPost post = findVisiblePost(id);

        return CommunityPostResponse.from(post);
    }

    @Transactional
    public CommunityPostResponse update(Long id, UpdateCommunityPostRequest request) {
        CommunityPost post = findVisiblePost(id);
        Region region = request.regionId() == null
                ? post.getRegion()
                : regionRepository.findById(request.regionId())
                .orElseThrow(() -> new NotFoundException("지역을 찾을 수 없습니다. id=" + request.regionId()));

        post.update(
                region,
                valueOrCurrent(request.category(), post.getCategory()),
                valueOrCurrent(request.title(), post.getTitle()),
                valueOrCurrent(request.content(), post.getContent())
        );

        return CommunityPostResponse.from(post);
    }

    @Transactional
    public void delete(Long id) {
        CommunityPost post = findVisiblePost(id);
        post.hide();
    }

    private CommunityPost findVisiblePost(Long id) {
        CommunityPost post = communityPostRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("커뮤니티 게시글을 찾을 수 없습니다. id=" + id));
        if (post.isHidden()) {
            throw new NotFoundException("커뮤니티 게시글을 찾을 수 없습니다. id=" + id);
        }
        return post;
    }

    private <T> T valueOrCurrent(T value, T current) {
        return value == null ? current : value;
    }
}
