package com.example.miniSpotify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "songs")
public class Song extends BaseEntity {

    @NotBlank(message = "Titulo e obrigatorio")
    @Column(nullable = false)
    private String titulo;

    @NotNull(message = "Duracao em segundos e obrigatoria")
    @Positive(message = "Duracao em segundos deve ser maior que zero")
    @Column(nullable = false)
    private Integer duracaoSegundos;

    @NotNull(message = "Numero da faixa e obrigatorio")
    @Positive(message = "Numero da faixa deve ser maior que zero")
    @Column(nullable = false)
    private Integer numeroFaixa;

    @NotNull(message = "Album e obrigatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @NotNull(message = "Artista e obrigatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artista;

    @PositiveOrZero(message = "Total de reproducoes nao pode ser negativo")
    @Column(nullable = false)
    private Long totalReproducoes = 0L;

    @Column(nullable = false)
    private boolean ativo = true;
}
