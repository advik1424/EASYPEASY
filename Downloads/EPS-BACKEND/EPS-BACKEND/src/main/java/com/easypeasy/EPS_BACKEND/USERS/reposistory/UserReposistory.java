package com.easypeasy.EPS_BACKEND.USERS.reposistory;

import com.easypeasy.EPS_BACKEND.USERS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserReposistory extends JpaRepository<User,Long> {



    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);




}
