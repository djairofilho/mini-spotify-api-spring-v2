package com.example.miniSpotify.controller;

import com.example.miniSpotify.model.Playlist;
import com.example.miniSpotify.service.PlaylistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Playlist create(@Valid @RequestBody Playlist playlist) {
        return playlistService.create(playlist);
    }

    @GetMapping
    public List<Playlist> findAll() {
        return playlistService.findAll();
    }

    @GetMapping("/{id}")
    public Playlist findById(@PathVariable Long id) {
        return playlistService.findById(id);
    }

    @PutMapping("/{id}")
    public Playlist update(@PathVariable Long id, @Valid @RequestBody Playlist playlist) {
        return playlistService.update(id, playlist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playlistService.delete(id);
    }

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public Playlist addSong(
            @PathVariable Long playlistId,
            @PathVariable Long musicaId,
            @RequestHeader("X-USER-ID") Long userId
    ) {
        return playlistService.addSong(playlistId, musicaId, userId);
    }
}
