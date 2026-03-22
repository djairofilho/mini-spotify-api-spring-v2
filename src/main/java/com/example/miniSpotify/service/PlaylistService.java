package com.example.miniSpotify.service;

import com.example.miniSpotify.exception.BusinessException;
import com.example.miniSpotify.exception.NotFoundException;
import com.example.miniSpotify.model.Playlist;
import com.example.miniSpotify.model.Song;
import com.example.miniSpotify.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserService userService;
    private final SongService songService;

    public PlaylistService(PlaylistRepository playlistRepository, UserService userService, SongService songService) {
        this.playlistRepository = playlistRepository;
        this.userService = userService;
        this.songService = songService;
    }

    public Playlist create(Playlist playlist) {
        playlist.setUsuario(userService.getUsableEntity(playlist.getUsuario().getId()));
        return playlistRepository.save(playlist);
    }

    public List<Playlist> findAll() {
        return playlistRepository.findAllByAtivoTrue();
    }

    public Playlist findById(Long id) {
        return getActiveEntity(id);
    }

    public Playlist update(Long id, Playlist request) {
        Playlist playlist = getActiveEntity(id);
        playlist.setNome(request.getNome());
        playlist.setPublica(request.isPublica());
        playlist.setUsuario(userService.getUsableEntity(request.getUsuario().getId()));
        playlist.setAtivo(request.isAtivo());
        return playlistRepository.save(playlist);
    }

    public void delete(Long id) {
        Playlist playlist = getActiveEntity(id);
        playlist.setAtivo(false);
        playlistRepository.save(playlist);
    }

    public Playlist addSong(Long playlistId, Long songId, Long userId) {
        Playlist playlist = getActiveEntity(playlistId);
        Song song = songService.getUsableEntity(songId);
        userService.getUsableEntity(userId);

        if (!playlist.getUsuario().getId().equals(userId)) {
            throw new BusinessException("Apenas o dono da playlist pode adicionar musicas");
        }

        boolean alreadyExists = playlist.getMusicas().stream()
                .anyMatch(existingSong -> existingSong.getId().equals(songId));
        if (alreadyExists) {
            throw new BusinessException("A musica ja esta na playlist");
        }

        playlist.getMusicas().add(song);
        return playlistRepository.save(playlist);
    }

    public Playlist getActiveEntity(Long id) {
        return playlistRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new NotFoundException("Playlist nao encontrada"));
    }
}
