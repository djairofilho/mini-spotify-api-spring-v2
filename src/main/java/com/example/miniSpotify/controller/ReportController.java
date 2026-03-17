package com.example.miniSpotify.controller;

import com.example.miniSpotify.dto.TopSongResponse;
import com.example.miniSpotify.service.SongService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class ReportController {

    private final SongService songService;

    public ReportController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/top-musicas")
    public List<TopSongResponse> topSongs() {
        return songService.getTopSongs();
    }
}
