package com.example.miniSpotify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @NotBlank(message = "Nome e obrigatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email deve ser valido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Tipo de plano e obrigatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanType tipoPlano;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prePersist() {
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
    }
}
