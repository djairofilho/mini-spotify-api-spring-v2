package com.example.miniSpotify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "albums")
public class Album extends BaseEntity {

    @NotBlank(message = "Titulo e obrigatorio")
    @Column(nullable = false)
    private String titulo;

    @NotNull(message = "Data de lancamento e obrigatoria")
    @PastOrPresent(message = "Data de lancamento nao pode ser futura")
    @Column(nullable = false)
    private LocalDate dataLancamento;

    @NotNull(message = "Artista e obrigatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artista;

    @Column(nullable = false)
    private boolean ativo = true;
}
