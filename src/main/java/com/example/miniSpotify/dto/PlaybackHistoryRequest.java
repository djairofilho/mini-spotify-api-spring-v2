package com.example.miniSpotify.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PlaybackHistoryRequest(
        @Valid
        @NotNull(message = "Usuario e obrigatorio")
        IdRequest usuario,

        @Valid
        @NotNull(message = "Musica e obrigatoria")
        IdRequest musica,

        Boolean ativo
) {
}
