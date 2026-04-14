package com.example.miniSpotify.controller;

import com.example.miniSpotify.model.Song;
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
    public Song create(@Valid @RequestBody Song song) {
        return songService.create(song);
    }

    @GetMapping
    public List<Song> findAll() {
        return songService.findAll();
    }

    @GetMapping("/{id}")
    public Song findById(@PathVariable Long id) {
        return songService.findById(id);
    }

    @PutMapping("/{id}")
    public Song update(@PathVariable Long id, @Valid @RequestBody Song song) {
        return songService.update(id, song);
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
