package com.unknown.authenticatedbackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unknown.authenticatedbackend.models.ApplicationUser;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Integer>{

    Optional<ApplicationUser> findByUsername(String username);
}
