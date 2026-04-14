package com.example.miniSpotify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "artists")
public class Artist extends BaseEntity {

    @NotBlank(message = "Nome e obrigatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Genero musical e obrigatorio")
    @Column(nullable = false)
    private String generoMusical;

    @NotBlank(message = "Pais de origem e obrigatorio")
    @Column(nullable = false)
    private String paisOrigem;

    @Column(nullable = false)
    private boolean ativo = true;
}
