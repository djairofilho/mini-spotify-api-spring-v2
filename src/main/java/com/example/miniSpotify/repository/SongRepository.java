package com.example.miniSpotify.repository;

import com.example.miniSpotify.dto.TopSongResponse;
import com.example.miniSpotify.model.Song;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    @EntityGraph(attributePaths = {"album", "artista"})
    List<Song> findAllByAtivoTrue();

    @EntityGraph(attributePaths = {"album", "artista"})
    Optional<Song> findByIdAndAtivoTrue(Long id);

    @Query("""
            select new com.example.miniSpotify.dto.TopSongResponse(
                s.titulo,
                a.nome,
                s.totalReproducoes
            )
            from Song s
            join s.artista a
            where s.ativo = true and a.ativo = true
            order by s.totalReproducoes desc, s.titulo asc
            """)
    List<TopSongResponse> findTopSongs(Pageable pageable);
}
