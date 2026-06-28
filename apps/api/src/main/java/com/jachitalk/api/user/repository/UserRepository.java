package com.jachitalk.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jachitalk.api.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

