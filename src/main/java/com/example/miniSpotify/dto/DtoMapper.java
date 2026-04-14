package com.example.miniSpotify.dto;

import com.example.miniSpotify.model.Album;
import com.example.miniSpotify.model.Artist;
import com.example.miniSpotify.model.PlaybackHistory;
import com.example.miniSpotify.model.Playlist;
import com.example.miniSpotify.model.Song;
import com.example.miniSpotify.model.User;

public final class DtoMapper {

    private DtoMapper() {
    }

    public static User toEntity(UserRequest request) {
        User user = new User();
        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setTipoPlano(request.tipoPlano());
        user.setAtivo(activeOrDefault(request.ativo()));
        return user;
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getTipoPlano(),
                user.isAtivo(),
                user.getDataCriacao()
        );
    }

    public static Artist toEntity(ArtistRequest request) {
        Artist artist = new Artist();
        artist.setNome(request.nome());
        artist.setGeneroMusical(request.generoMusical());
        artist.setPaisOrigem(request.paisOrigem());
        artist.setAtivo(activeOrDefault(request.ativo()));
        return artist;
    }

    public static ArtistResponse toResponse(Artist artist) {
        return new ArtistResponse(
                artist.getId(),
                artist.getNome(),
                artist.getGeneroMusical(),
                artist.getPaisOrigem(),
                artist.isAtivo()
        );
    }

    public static Album toEntity(AlbumRequest request) {
        Album album = new Album();
        album.setTitulo(request.titulo());
        album.setDataLancamento(request.dataLancamento());
        album.setArtista(artistReference(request.artista().id()));
        album.setAtivo(activeOrDefault(request.ativo()));
        return album;
    }

    public static AlbumResponse toResponse(Album album) {
        Artist artist = album.getArtista();
        return new AlbumResponse(
                album.getId(),
                album.getTitulo(),
                album.getDataLancamento(),
                artist.getId(),
                artist.getNome(),
                album.isAtivo()
        );
    }

    public static Song toEntity(SongRequest request) {
        Song song = new Song();
        song.setTitulo(request.titulo());
        song.setDuracaoSegundos(request.duracaoSegundos());
        song.setNumeroFaixa(request.numeroFaixa());
        song.setAlbum(albumReference(request.album().id()));
        song.setArtista(artistReference(request.artista().id()));
        song.setTotalReproducoes(request.totalReproducoes());
        song.setAtivo(activeOrDefault(request.ativo()));
        return song;
    }

    public static SongResponse toResponse(Song song) {
        Album album = song.getAlbum();
        Artist artist = song.getArtista();
        return new SongResponse(
                song.getId(),
                song.getTitulo(),
                song.getDuracaoSegundos(),
                song.getNumeroFaixa(),
                album.getId(),
                album.getTitulo(),
                artist.getId(),
                artist.getNome(),
                song.getTotalReproducoes(),
                song.isAtivo()
        );
    }

    public static SongSummaryResponse toSummaryResponse(Song song) {
        return new SongSummaryResponse(
                song.getId(),
                song.getTitulo(),
                song.getArtista().getNome()
        );
    }

    public static Playlist toEntity(PlaylistRequest request) {
        Playlist playlist = new Playlist();
        playlist.setNome(request.nome());
        playlist.setPublica(Boolean.TRUE.equals(request.publica()));
        playlist.setUsuario(userReference(request.usuario().id()));
        playlist.setAtivo(activeOrDefault(request.ativo()));
        return playlist;
    }

    public static PlaylistResponse toResponse(Playlist playlist) {
        User user = playlist.getUsuario();
        return new PlaylistResponse(
                playlist.getId(),
                playlist.getNome(),
                playlist.isPublica(),
                playlist.getDataCriacao(),
                user.getId(),
                user.getNome(),
                playlist.getMusicas().stream()
                        .map(DtoMapper::toSummaryResponse)
                        .toList(),
                playlist.isAtivo()
        );
    }

    public static PlaybackHistory toEntity(PlaybackHistoryRequest request) {
        PlaybackHistory history = new PlaybackHistory();
        history.setUsuario(userReference(request.usuario().id()));
        history.setMusica(songReference(request.musica().id()));
        history.setAtivo(activeOrDefault(request.ativo()));
        return history;
    }

    public static PlaybackHistoryResponse toResponse(PlaybackHistory history) {
        User user = history.getUsuario();
        Song song = history.getMusica();
        return new PlaybackHistoryResponse(
                history.getId(),
                user.getId(),
                user.getNome(),
                song.getId(),
                song.getTitulo(),
                history.getDataReproducao(),
                history.isAtivo()
        );
    }

    private static boolean activeOrDefault(Boolean active) {
        return active == null || active;
    }

    private static User userReference(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    private static Artist artistReference(Long id) {
        Artist artist = new Artist();
        artist.setId(id);
        return artist;
    }

    private static Album albumReference(Long id) {
        Album album = new Album();
        album.setId(id);
        return album;
    }

    private static Song songReference(Long id) {
        Song song = new Song();
        song.setId(id);
        return song;
    }
}
