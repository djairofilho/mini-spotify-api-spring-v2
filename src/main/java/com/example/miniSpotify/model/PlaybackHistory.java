package com.example.miniSpotify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "playback_history")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlaybackHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

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

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Song getMusica() {
        return musica;
    }

    public void setMusica(Song musica) {
        this.musica = musica;
    }

    public LocalDateTime getDataReproducao() {
        return dataReproducao;
    }

    public void setDataReproducao(LocalDateTime dataReproducao) {
        this.dataReproducao = dataReproducao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
