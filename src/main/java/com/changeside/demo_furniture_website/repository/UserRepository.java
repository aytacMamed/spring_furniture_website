package com.changeside.demo_furniture_website.repository;

import com.changeside.demo_furniture_website.models.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
