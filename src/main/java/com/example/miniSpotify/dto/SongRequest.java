package com.example.miniSpotify.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record SongRequest(
        @NotBlank(message = "Titulo e obrigatorio")
        String titulo,

        @NotNull(message = "Duracao em segundos e obrigatoria")
        @Positive(message = "Duracao em segundos deve ser maior que zero")
        Integer duracaoSegundos,

        @NotNull(message = "Numero da faixa e obrigatorio")
        @Positive(message = "Numero da faixa deve ser maior que zero")
        Integer numeroFaixa,

        @Valid
        @NotNull(message = "Album e obrigatorio")
        IdRequest album,

        @Valid
        @NotNull(message = "Artista e obrigatorio")
        IdRequest artista,

        @PositiveOrZero(message = "Total de reproducoes nao pode ser negativo")
        Long totalReproducoes,

        Boolean ativo
) {
}
