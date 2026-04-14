package com.example.miniSpotify.dto;

import com.example.miniSpotify.model.PlanType;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String nome,
        String email,
        PlanType tipoPlano,
        boolean ativo,
        LocalDateTime dataCriacao
) {
}
