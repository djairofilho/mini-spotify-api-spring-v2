package com.example.miniSpotify.controller;

import com.example.miniSpotify.dto.ArtistRequest;
import com.example.miniSpotify.dto.ArtistResponse;
import com.example.miniSpotify.dto.DtoMapper;
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
    public ArtistResponse create(@Valid @RequestBody ArtistRequest request) {
        return DtoMapper.toResponse(artistService.create(DtoMapper.toEntity(request)));
    }

    @GetMapping
    public List<ArtistResponse> findAll() {
        return artistService.findAll().stream()
                .map(DtoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ArtistResponse findById(@PathVariable Long id) {
        return DtoMapper.toResponse(artistService.findById(id));
    }

    @PutMapping("/{id}")
    public ArtistResponse update(@PathVariable Long id, @Valid @RequestBody ArtistRequest request) {
        return DtoMapper.toResponse(artistService.update(id, DtoMapper.toEntity(request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        artistService.delete(id);
    }
}
