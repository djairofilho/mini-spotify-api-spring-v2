package com.example.miniSpotify.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "playback_history")
public class PlaybackHistory extends BaseEntity {

    @NotNull(message = "Usuario e obrigatorio")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    @NotNull(message = "Musica e obrigatoria")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_id", nullable = false)
    private Song musica;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataReproducao;

    @Column(nullable = false)
    private boolean ativo = true;

    @PrePersist
    public void prePersist() {
        if (dataReproducao == null) {
            dataReproducao = LocalDateTime.now();
        }
    }
}
