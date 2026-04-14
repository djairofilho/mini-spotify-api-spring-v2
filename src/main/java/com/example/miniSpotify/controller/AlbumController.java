package com.example.miniSpotify.controller;

import com.example.miniSpotify.dto.AlbumRequest;
import com.example.miniSpotify.dto.AlbumResponse;
import com.example.miniSpotify.dto.DtoMapper;
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
    public AlbumResponse create(@Valid @RequestBody AlbumRequest request) {
        return DtoMapper.toResponse(albumService.create(DtoMapper.toEntity(request)));
    }

    @GetMapping
    public List<AlbumResponse> findAll() {
        return albumService.findAll().stream()
                .map(DtoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public AlbumResponse findById(@PathVariable Long id) {
        return DtoMapper.toResponse(albumService.findById(id));
    }

    @PutMapping("/{id}")
    public AlbumResponse update(@PathVariable Long id, @Valid @RequestBody AlbumRequest request) {
        return DtoMapper.toResponse(albumService.update(id, DtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        albumService.delete(id);
    }
}
