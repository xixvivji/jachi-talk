package com.jachitalk.api.room.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jachitalk.api.common.error.NotFoundException;
import com.jachitalk.api.region.domain.Region;
import com.jachitalk.api.region.repository.RegionRepository;
import com.jachitalk.api.room.domain.LandlordConsentStatus;
import com.jachitalk.api.room.domain.RoomHandoverPost;
import com.jachitalk.api.room.domain.RoomHandoverStatus;
import com.jachitalk.api.room.dto.CreateRoomHandoverPostRequest;
import com.jachitalk.api.room.dto.RoomHandoverPostResponse;
import com.jachitalk.api.room.repository.RoomHandoverPostRepository;
import com.jachitalk.api.user.domain.User;
import com.jachitalk.api.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class RoomHandoverPostService {

    private final RoomHandoverPostRepository roomHandoverPostRepository;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    public RoomHandoverPostService(RoomHandoverPostRepository roomHandoverPostRepository,
                                   UserRepository userRepository,
                                   RegionRepository regionRepository) {
        this.roomHandoverPostRepository = roomHandoverPostRepository;
        this.userRepository = userRepository;
        this.regionRepository = regionRepository;
    }

    @Transactional
    public RoomHandoverPostResponse create(CreateRoomHandoverPostRequest request) {
        User author = userRepository.findById(request.authorId())
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다. id=" + request.authorId()));
        Region region = regionRepository.findById(request.regionId())
                .orElseThrow(() -> new NotFoundException("지역을 찾을 수 없습니다. id=" + request.regionId()));

        RoomHandoverPost post = new RoomHandoverPost(
                author,
                region,
                request.title(),
                request.deposit(),
                request.monthlyRent(),
                request.roomType(),
                request.description()
        );

        post.updateDetails(
                request.maintenanceFee(),
                request.availableFrom(),
                request.contractUntil(),
                request.nearestStation(),
                request.floor(),
                request.petAllowed(),
                request.landlordConsentStatus() == null ? LandlordConsentStatus.NOT_CHECKED : request.landlordConsentStatus()
        );

        return RoomHandoverPostResponse.from(roomHandoverPostRepository.save(post));
    }

    public Page<RoomHandoverPostResponse> findOpenPosts(Pageable pageable) {
        return roomHandoverPostRepository.findByStatus(RoomHandoverStatus.OPEN, pageable)
                .map(RoomHandoverPostResponse::from);
    }

    public RoomHandoverPostResponse getById(Long id) {
        RoomHandoverPost post = roomHandoverPostRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("방 양도글을 찾을 수 없습니다. id=" + id));

        return RoomHandoverPostResponse.from(post);
    }
}

