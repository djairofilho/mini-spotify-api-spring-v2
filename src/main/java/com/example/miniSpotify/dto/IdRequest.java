package com.example.miniSpotify.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IdRequest(
        @NotNull(message = "Id e obrigatorio")
        @Positive(message = "Id deve ser maior que zero")
        Long id
) {
}
