package com.soccer.pjt.controller;

import com.soccer.pjt.entity.Player;
import com.soccer.pjt.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "http://localhost:3000") // Cross-Origin 설정
public class PlayerController {

    @Autowired
    private DataService dataService;

    // 모든 플레이어를 조회하는 메서드
    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = dataService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    // 특정 ID로 플레이어를 조회하는 메서드
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = dataService.getPlayerById(id);
        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 비디오 ID로 플레이어 목록을 조회하는 메서드
    @GetMapping("/by-video")
    public ResponseEntity<List<Player>> getPlayersByVideoId(@RequestParam("videoId") String videoId) {
        System.out.println("Received request for videoId: " + videoId); // 요청 확인
        List<Player> players = dataService.getPlayersByVideoId(videoId);
        if (!players.isEmpty()) {
            return ResponseEntity.ok(players);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}