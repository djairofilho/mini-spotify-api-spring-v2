package com.example.miniSpotify.controller;

import com.example.miniSpotify.dto.DtoMapper;
import com.example.miniSpotify.dto.PlaylistRequest;
import com.example.miniSpotify.dto.PlaylistResponse;
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
    public PlaylistResponse create(@Valid @RequestBody PlaylistRequest request) {
        return DtoMapper.toResponse(playlistService.create(DtoMapper.toEntity(request)));
    }

    @GetMapping
    public List<PlaylistResponse> findAll() {
        return playlistService.findAll().stream()
                .map(DtoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public PlaylistResponse findById(@PathVariable Long id) {
        return DtoMapper.toResponse(playlistService.findById(id));
    }

    @PutMapping("/{id}")
    public PlaylistResponse update(@PathVariable Long id, @Valid @RequestBody PlaylistRequest request) {
        return DtoMapper.toResponse(playlistService.update(id, DtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playlistService.delete(id);
    }

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public PlaylistResponse addSong(
            @PathVariable Long playlistId,
            @PathVariable Long musicaId,
            @RequestHeader("X-USER-ID") Long userId
    ) {
        return DtoMapper.toResponse(playlistService.addSong(playlistId, musicaId, userId));
    }
}
