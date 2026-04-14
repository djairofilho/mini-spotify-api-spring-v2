package com.example.miniSpotify.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlaylistRequest(
        @NotBlank(message = "Nome e obrigatorio")
        String nome,

        Boolean publica,

        @Valid
        @NotNull(message = "Usuario e obrigatorio")
        IdRequest usuario,

        Boolean ativo
) {
}
