package com.example.miniSpotify.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PlaylistResponse(
        Long id,
        String nome,
        boolean publica,
        LocalDateTime dataCriacao,
        Long usuarioId,
        String usuarioNome,
        List<SongSummaryResponse> musicas,
        boolean ativo
) {
}
