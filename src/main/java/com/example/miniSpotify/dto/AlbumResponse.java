package com.example.miniSpotify.dto;

import java.time.LocalDate;

public record AlbumResponse(
        Long id,
        String titulo,
        LocalDate dataLancamento,
        Long artistaId,
        String artistaNome,
        boolean ativo
) {
}
