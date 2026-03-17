package com.example.miniSpotify.repository;

import com.example.miniSpotify.model.PlaybackHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaybackHistoryRepository extends JpaRepository<PlaybackHistory, Long> {

    @EntityGraph(attributePaths = {"usuario", "musica", "musica.artista"})
    List<PlaybackHistory> findAllByAtivoTrue();

    @EntityGraph(attributePaths = {"usuario", "musica", "musica.artista"})
    Optional<PlaybackHistory> findByIdAndAtivoTrue(Long id);
}
