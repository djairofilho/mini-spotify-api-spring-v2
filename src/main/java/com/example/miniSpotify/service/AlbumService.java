package com.example.miniSpotify.service;

import com.example.miniSpotify.exception.BusinessException;
import com.example.miniSpotify.exception.NotFoundException;
import com.example.miniSpotify.model.Album;
import com.example.miniSpotify.model.Artist;
import com.example.miniSpotify.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistService artistService;

    public AlbumService(AlbumRepository albumRepository, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.artistService = artistService;
    }

    public Album create(Album album) {
        album.setArtista(artistService.getUsableEntity(getArtistId(album.getArtista())));
        return albumRepository.save(album);
    }

    public List<Album> findAll() {
        return albumRepository.findAllByAtivoTrue();
    }

    public Album findById(Long id) {
        return getActiveEntity(id);
    }

    public Album update(Long id, Album request) {
        Album album = getActiveEntity(id);
        album.setTitulo(request.getTitulo());
        album.setDataLancamento(request.getDataLancamento());
        album.setArtista(artistService.getUsableEntity(getArtistId(request.getArtista())));
        album.setAtivo(request.isAtivo());
        return albumRepository.save(album);
    }

    public void delete(Long id) {
        Album album = getActiveEntity(id);
        album.setAtivo(false);
        albumRepository.save(album);
    }

    public Album getActiveEntity(Long id) {
        return albumRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new NotFoundException("Album nao encontrado"));
    }

    public Album getUsableEntity(Long id) {
        Album album = getActiveEntity(id);
        artistService.getUsableEntity(album.getArtista().getId());
        return album;
    }

    private Long getArtistId(Artist artist) {
        if (artist == null || artist.getId() == null) {
            throw new BusinessException("Id do artista e obrigatorio");
        }
        return artist.getId();
    }
}
