package com.example.miniSpotify.dto;

public record SongSummaryResponse(
        Long id,
        String titulo,
        String artistaNome
) {
}
