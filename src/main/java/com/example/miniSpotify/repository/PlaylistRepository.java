package com.example.miniSpotify.repository;

import com.example.miniSpotify.model.Playlist;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @EntityGraph(attributePaths = {"usuario", "musicas", "musicas.artista", "musicas.album"})
    List<Playlist> findAllByAtivoTrue();

    @EntityGraph(attributePaths = {"usuario", "musicas", "musicas.artista", "musicas.album"})
    Optional<Playlist> findByIdAndAtivoTrue(Long id);
}
