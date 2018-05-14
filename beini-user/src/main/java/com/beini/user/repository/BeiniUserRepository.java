package com.beini.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beini.user.entity.BeiniUser;

public interface BeiniUserRepository extends JpaRepository<BeiniUser, String> {

}
