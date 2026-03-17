package com.example.miniSpotify.repository;

import com.example.miniSpotify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByAtivoTrue();

    Optional<User> findByIdAndAtivoTrue(Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByEmail(String email);
}
