package com.example.miniSpotify.dto;

public record ArtistResponse(
        Long id,
        String nome,
        String generoMusical,
        String paisOrigem,
        boolean ativo
) {
}
