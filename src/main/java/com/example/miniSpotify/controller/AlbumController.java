package com.example.miniSpotify.controller;

import com.example.miniSpotify.model.Album;
import com.example.miniSpotify.service.AlbumService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album create(@Valid @RequestBody Album album) {
        return albumService.create(album);
    }

    @GetMapping
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/{id}")
    public Album findById(@PathVariable Long id) {
        return albumService.findById(id);
    }

    @PutMapping("/{id}")
    public Album update(@PathVariable Long id, @Valid @RequestBody Album album) {
        return albumService.update(id, album);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        albumService.delete(id);
    }
}
