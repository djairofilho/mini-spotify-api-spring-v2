package com.example.miniSpotify.service;

import com.example.miniSpotify.exception.NotFoundException;
import com.example.miniSpotify.model.Artist;
import com.example.miniSpotify.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist create(Artist artist) {
        return artistRepository.save(artist);
    }

    public List<Artist> findAll() {
        return artistRepository.findAllByAtivoTrue();
    }

    public Artist findById(Long id) {
        return getActiveEntity(id);
    }

    public Artist update(Long id, Artist request) {
        Artist artist = getActiveEntity(id);
        artist.setNome(request.getNome());
        artist.setGeneroMusical(request.getGeneroMusical());
        artist.setPaisOrigem(request.getPaisOrigem());
        artist.setAtivo(request.isAtivo());
        return artistRepository.save(artist);
    }

    public void delete(Long id) {
        Artist artist = getActiveEntity(id);
        artist.setAtivo(false);
        artistRepository.save(artist);
    }

    public Artist getActiveEntity(Long id) {
        return artistRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new NotFoundException("Artista nao encontrado"));
    }

    public Artist getUsableEntity(Long id) {
        return getActiveEntity(id);
    }
}
