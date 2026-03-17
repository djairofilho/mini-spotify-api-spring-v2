package com.example.miniSpotify.repository;

import com.example.miniSpotify.model.Album;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @EntityGraph(attributePaths = {"artista"})
    List<Album> findAllByAtivoTrue();

    @EntityGraph(attributePaths = {"artista"})
    Optional<Album> findByIdAndAtivoTrue(Long id);
}
