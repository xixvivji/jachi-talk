package com.jachitalk.api.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jachitalk.api.region.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}

