package com.example.miniSpotify.dto;

import com.example.miniSpotify.model.PlanType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank(message = "Nome e obrigatorio")
        String nome,

        @NotBlank(message = "Email e obrigatorio")
        @Email(message = "Email deve ser valido")
        String email,

        @NotNull(message = "Tipo de plano e obrigatorio")
        PlanType tipoPlano,

        Boolean ativo
) {
}
