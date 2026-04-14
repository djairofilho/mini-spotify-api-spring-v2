package com.example.miniSpotify.dto;

import java.time.LocalDateTime;

public record PlaybackHistoryResponse(
        Long id,
        Long usuarioId,
        String usuarioNome,
        Long musicaId,
        String musicaTitulo,
        LocalDateTime dataReproducao,
        boolean ativo
) {
}
