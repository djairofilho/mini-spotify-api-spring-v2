package com.example.miniSpotify.controller;

import com.example.miniSpotify.model.PlaybackHistory;
import com.example.miniSpotify.service.PlaybackHistoryService;
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
    public PlaybackHistory create(@RequestBody PlaybackHistory playbackHistory) {
        return playbackHistoryService.create(playbackHistory);
    }

    @GetMapping
    public List<PlaybackHistory> findAll() {
        return playbackHistoryService.findAll();
    }

    @GetMapping("/{id}")
    public PlaybackHistory findById(@PathVariable Long id) {
        return playbackHistoryService.findById(id);
    }

    @PutMapping("/{id}")
    public PlaybackHistory update(@PathVariable Long id, @RequestBody PlaybackHistory playbackHistory) {
        return playbackHistoryService.update(id, playbackHistory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        playbackHistoryService.delete(id);
    }
}
