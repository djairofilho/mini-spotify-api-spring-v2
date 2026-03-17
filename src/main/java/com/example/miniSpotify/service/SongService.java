package com.example.miniSpotify.service;

import com.example.miniSpotify.dto.TopSongResponse;
import com.example.miniSpotify.exception.BusinessException;
import com.example.miniSpotify.exception.NotFoundException;
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
        song.setAlbum(albumService.getActiveEntity(request.getAlbum().getId()));
        song.setArtista(artistService.getActiveEntity(request.getArtista().getId()));
        return songRepository.save(song);
    }

    public void delete(Long id) {
        Song song = getActiveEntity(id);
        song.setAtivo(false);
        songRepository.save(song);
    }

    public String play(Long songId, Long userId) {
        Song song = getActiveEntity(songId);
        User user = userService.getActiveEntity(userId);
        if (!user.isAtivo()) {
            throw new BusinessException("Usuario inativo nao pode reproduzir musicas");
        }

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

    private void attachRelations(Song song) {
        song.setAlbum(albumService.getActiveEntity(song.getAlbum().getId()));
        song.setArtista(artistService.getActiveEntity(song.getArtista().getId()));
        if (song.getTotalReproducoes() == null) {
            song.setTotalReproducoes(0L);
        }
    }
}
