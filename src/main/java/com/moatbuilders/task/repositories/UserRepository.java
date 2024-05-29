package com.moatbuilders.task.repositories;

import com.moatbuilders.task.domian.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByUsername(String userName);
}