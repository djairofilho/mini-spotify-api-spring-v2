package com.example.miniSpotify.service;

import com.example.miniSpotify.exception.NotFoundException;
import com.example.miniSpotify.model.PlaybackHistory;
import com.example.miniSpotify.repository.PlaybackHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaybackHistoryService {

    private final PlaybackHistoryRepository playbackHistoryRepository;
    private final UserService userService;
    private final SongService songService;

    public PlaybackHistoryService(
            PlaybackHistoryRepository playbackHistoryRepository,
            UserService userService,
            SongService songService
    ) {
        this.playbackHistoryRepository = playbackHistoryRepository;
        this.userService = userService;
        this.songService = songService;
    }

    public PlaybackHistory create(PlaybackHistory history) {
        attachRelations(history);
        return playbackHistoryRepository.save(history);
    }

    public List<PlaybackHistory> findAll() {
        return playbackHistoryRepository.findAllByAtivoTrue();
    }

    public PlaybackHistory findById(Long id) {
        return getActiveEntity(id);
    }

    public PlaybackHistory update(Long id, PlaybackHistory request) {
        PlaybackHistory history = getActiveEntity(id);
        history.setUsuario(userService.getUsableEntity(request.getUsuario().getId()));
        history.setMusica(songService.getActiveEntity(request.getMusica().getId()));
        history.setAtivo(request.isAtivo());
        return playbackHistoryRepository.save(history);
    }

    public void delete(Long id) {
        PlaybackHistory history = getActiveEntity(id);
        history.setAtivo(false);
        playbackHistoryRepository.save(history);
    }

    private PlaybackHistory getActiveEntity(Long id) {
        return playbackHistoryRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new NotFoundException("Historico de reproducao nao encontrado"));
    }

    private void attachRelations(PlaybackHistory history) {
        history.setUsuario(userService.getUsableEntity(history.getUsuario().getId()));
        history.setMusica(songService.getActiveEntity(history.getMusica().getId()));
    }
}
