package com.alkemy.pelis.pelis.auth.repository;

import com.alkemy.pelis.pelis.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
