package com.example.miniSpotify.controller;

import com.example.miniSpotify.dto.DtoMapper;
import com.example.miniSpotify.dto.SongRequest;
import com.example.miniSpotify.dto.SongResponse;
import com.example.miniSpotify.service.SongService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musicas")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SongResponse create(@Valid @RequestBody SongRequest request) {
        return DtoMapper.toResponse(songService.create(DtoMapper.toEntity(request)));
    }

    @GetMapping
    public List<SongResponse> findAll() {
        return songService.findAll().stream()
                .map(DtoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public SongResponse findById(@PathVariable Long id) {
        return DtoMapper.toResponse(songService.findById(id));
    }

    @PutMapping("/{id}")
    public SongResponse update(@PathVariable Long id, @Valid @RequestBody SongRequest request) {
        return DtoMapper.toResponse(songService.update(id, DtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        songService.delete(id);
    }

    @PostMapping("/{id}/reproduzir")
    public String play(@PathVariable Long id, @RequestHeader("X-USER-ID") Long userId) {
        return songService.play(id, userId);
    }
}
