package com.example.miniSpotify.controller;

import com.example.miniSpotify.dto.DtoMapper;
import com.example.miniSpotify.dto.PlaybackHistoryRequest;
import com.example.miniSpotify.dto.PlaybackHistoryResponse;
import com.example.miniSpotify.service.PlaybackHistoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historicos-reproducao")
public class PlaybackHistoryController {

    private final PlaybackHistoryService playbackHistoryService;

    public PlaybackHistoryController(PlaybackHistoryService playbackHistoryService) {
        this.playbackHistoryService = playbackHistoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaybackHistoryResponse create(@Valid @RequestBody PlaybackHistoryRequest request) {
        return DtoMapper.toResponse(playbackHistoryService.create(DtoMapper.toEntity(request)));
    }

    @GetMapping
    public List<PlaybackHistoryResponse> findAll() {
        return playbackHistoryService.findAll().stream()
                .map(DtoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public PlaybackHistoryResponse findById(@PathVariable Long id) {
        return DtoMapper.toResponse(playbackHistoryService.findById(id));
    }

    @PutMapping("/{id}")
    public PlaybackHistoryResponse update(@PathVariable Long id, @Valid @RequestBody PlaybackHistoryRequest request) {
        return DtoMapper.toResponse(playbackHistoryService.update(id, DtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playbackHistoryService.delete(id);
    }
}
