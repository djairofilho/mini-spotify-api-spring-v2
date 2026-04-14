package com.example.miniSpotify.controller;

import com.example.miniSpotify.model.Artist;
import com.example.miniSpotify.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist create(@Valid @RequestBody Artist artist) {
        return artistService.create(artist);
    }

    @GetMapping
    public List<Artist> findAll() {
        return artistService.findAll();
    }

    @GetMapping("/{id}")
    public Artist findById(@PathVariable Long id) {
        return artistService.findById(id);
    }

    @PutMapping("/{id}")
    public Artist update(@PathVariable Long id, @Valid @RequestBody Artist artist) {
        return artistService.update(id, artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        artistService.delete(id);
    }
}
