package com.example.miniSpotify.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AlbumRequest(
        @NotBlank(message = "Titulo e obrigatorio")
        String titulo,

        @NotNull(message = "Data de lancamento e obrigatoria")
        @PastOrPresent(message = "Data de lancamento nao pode ser futura")
        LocalDate dataLancamento,

        @Valid
        @NotNull(message = "Artista e obrigatorio")
        IdRequest artista,

        Boolean ativo
) {
}
