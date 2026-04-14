package com.example.miniSpotify.dto;

public record SongResponse(
        Long id,
        String titulo,
        Integer duracaoSegundos,
        Integer numeroFaixa,
        Long albumId,
        String albumTitulo,
        Long artistaId,
        String artistaNome,
        Long totalReproducoes,
        boolean ativo
) {
}
