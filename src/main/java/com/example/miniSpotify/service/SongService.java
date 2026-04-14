package com.example.miniSpotify.service;

import com.example.miniSpotify.dto.TopSongResponse;
import com.example.miniSpotify.exception.BusinessException;
import com.example.miniSpotify.exception.NotFoundException;
import com.example.miniSpotify.model.Album;
import com.example.miniSpotify.model.Artist;
import com.example.miniSpotify.model.PlaybackHistory;
import com.example.miniSpotify.model.Song;
import com.example.miniSpotify.model.User;
import com.example.miniSpotify.repository.PlaybackHistoryRepository;
import com.example.miniSpotify.repository.SongRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumService albumService;
    private final ArtistService artistService;
    private final UserService userService;
    private final PlaybackHistoryRepository playbackHistoryRepository;

    public SongService(
            SongRepository songRepository,
            AlbumService albumService,
            ArtistService artistService,
            UserService userService,
            PlaybackHistoryRepository playbackHistoryRepository
    ) {
        this.songRepository = songRepository;
        this.albumService = albumService;
        this.artistService = artistService;
        this.userService = userService;
            this.playbackHistoryRepository = playbackHistoryRepository;
    }

    public Song create(Song song) {
        attachRelations(song);
        return songRepository.save(song);
    }

    public List<Song> findAll() {
        return songRepository.findAllByAtivoTrue();
    }

    public Song findById(Long id) {
        return getActiveEntity(id);
    }

    public Song update(Long id, Song request) {
        Song song = getActiveEntity(id);
        song.setTitulo(request.getTitulo());
        song.setDuracaoSegundos(request.getDuracaoSegundos());
        song.setNumeroFaixa(request.getNumeroFaixa());
        song.setTotalReproducoes(request.getTotalReproducoes());
        song.setAtivo(request.isAtivo());
        song.setAlbum(albumService.getUsableEntity(getAlbumId(request.getAlbum())));
        song.setArtista(artistService.getUsableEntity(getArtistId(request.getArtista())));
        return songRepository.save(song);
    }

    public void delete(Long id) {
        Song song = getActiveEntity(id);
        song.setAtivo(false);
        songRepository.save(song);
    }

    public String play(Long songId, Long userId) {
        Song song = getUsableEntity(songId);
        User user = userService.getUsableEntity(userId);

        song.setTotalReproducoes(song.getTotalReproducoes() + 1);
        songRepository.save(song);

        PlaybackHistory playbackHistory = new PlaybackHistory();
        playbackHistory.setUsuario(user);
        playbackHistory.setMusica(song);
        playbackHistoryRepository.save(playbackHistory);

        return "Musica reproduzida com sucesso";
    }

    public List<TopSongResponse> getTopSongs() {
        return songRepository.findTopSongs(PageRequest.of(0, 10));
    }

    public Song getActiveEntity(Long id) {
        return songRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new NotFoundException("Musica nao encontrada"));
    }

    public Song getUsableEntity(Long id) {
        Song song = getActiveEntity(id);
        albumService.getUsableEntity(song.getAlbum().getId());
        artistService.getUsableEntity(song.getArtista().getId());
        return song;
    }

    private void attachRelations(Song song) {
        song.setAlbum(albumService.getUsableEntity(getAlbumId(song.getAlbum())));
        song.setArtista(artistService.getUsableEntity(getArtistId(song.getArtista())));
        if (song.getTotalReproducoes() == null) {
            song.setTotalReproducoes(0L);
        }
    }

    private Long getAlbumId(Album album) {
        if (album == null || album.getId() == null) {
            throw new BusinessException("Id do album e obrigatorio");
        }
        return album.getId();
    }

    private Long getArtistId(Artist artist) {
        if (artist == null || artist.getId() == null) {
            throw new BusinessException("Id do artista e obrigatorio");
        }
        return artist.getId();
    }
}
