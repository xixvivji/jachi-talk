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
import com.jachitalk.api.room.dto.UpdateRoomHandoverPostRequest;
import com.jachitalk.api.room.dto.UpdateRoomHandoverStatusRequest;
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

    public Page<RoomHandoverPostResponse> findOpenPosts(Long regionId, Long minRent, Long maxRent, Pageable pageable) {
        return roomHandoverPostRepository.findVisiblePosts(RoomHandoverStatus.OPEN, regionId, minRent, maxRent, pageable)
                .map(RoomHandoverPostResponse::from);
    }

    public RoomHandoverPostResponse getById(Long id) {
        RoomHandoverPost post = findVisiblePost(id);

        return RoomHandoverPostResponse.from(post);
    }

    @Transactional
    public RoomHandoverPostResponse update(Long id, UpdateRoomHandoverPostRequest request) {
        RoomHandoverPost post = findVisiblePost(id);
        Region region = request.regionId() == null
                ? post.getRegion()
                : regionRepository.findById(request.regionId())
                .orElseThrow(() -> new NotFoundException("지역을 찾을 수 없습니다. id=" + request.regionId()));

        post.updateBasicInfo(
                region,
                valueOrCurrent(request.title(), post.getTitle()),
                valueOrCurrent(request.deposit(), post.getDeposit()),
                valueOrCurrent(request.monthlyRent(), post.getMonthlyRent()),
                valueOrCurrent(request.roomType(), post.getRoomType()),
                valueOrCurrent(request.description(), post.getDescription())
        );

        post.updateDetails(
                valueOrCurrent(request.maintenanceFee(), post.getMaintenanceFee()),
                valueOrCurrent(request.availableFrom(), post.getAvailableFrom()),
                valueOrCurrent(request.contractUntil(), post.getContractUntil()),
                valueOrCurrent(request.nearestStation(), post.getNearestStation()),
                valueOrCurrent(request.floor(), post.getFloor()),
                valueOrCurrent(request.petAllowed(), post.getPetAllowed()),
                valueOrCurrent(request.landlordConsentStatus(), post.getLandlordConsentStatus())
        );

        return RoomHandoverPostResponse.from(post);
    }

    @Transactional
    public RoomHandoverPostResponse updateStatus(Long id, UpdateRoomHandoverStatusRequest request) {
        RoomHandoverPost post = findVisiblePost(id);
        post.changeStatus(request.status());

        return RoomHandoverPostResponse.from(post);
    }

    @Transactional
    public void delete(Long id) {
        RoomHandoverPost post = findVisiblePost(id);
        post.hide();
    }

    private RoomHandoverPost findVisiblePost(Long id) {
        RoomHandoverPost post = roomHandoverPostRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("방 양도글을 찾을 수 없습니다. id=" + id));
        if (post.isHidden()) {
            throw new NotFoundException("방 양도글을 찾을 수 없습니다. id=" + id);
        }
        return post;
    }

    private <T> T valueOrCurrent(T value, T current) {
        return value == null ? current : value;
    }
}
