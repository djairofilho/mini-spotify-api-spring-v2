package com.example.miniSpotify.repository;

import com.example.miniSpotify.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findAllByAtivoTrue();

    Optional<Artist> findByIdAndAtivoTrue(Long id);
}
