package com.moatbuilders.task.repositories;

import com.moatbuilders.task.domian.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserDetails findByUsername(String userName);
}