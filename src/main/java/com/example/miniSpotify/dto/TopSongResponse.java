package com.example.miniSpotify.dto;

public record TopSongResponse(
        String tituloMusica,
        String nomeArtista,
        Long totalReproducoes
) {
}
