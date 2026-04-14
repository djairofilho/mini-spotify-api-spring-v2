package com.example.miniSpotify.dto;

import jakarta.validation.constraints.NotBlank;

public record ArtistRequest(
        @NotBlank(message = "Nome e obrigatorio")
        String nome,

        @NotBlank(message = "Genero musical e obrigatorio")
        String generoMusical,

        @NotBlank(message = "Pais de origem e obrigatorio")
        String paisOrigem,

        Boolean ativo
) {
}
