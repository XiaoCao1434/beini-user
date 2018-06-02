package com.beini.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beini.user.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
