package com.soccer.pjt.controller;

import com.soccer.pjt.dto.MinimapBatchDto;
import com.soccer.pjt.dto.MinimapDto;
import com.soccer.pjt.entity.Minimap;
import com.soccer.pjt.repository.MiniMapDataRepository;
import com.soccer.pjt.service.MinimapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MinimapController {

    @Autowired
    private MinimapService minimapService;

    @Autowired
    private MiniMapDataRepository minimapRepository;

    // 미니맵 데이터 저장 (코랩과 통신)
    @PostMapping("/minimap")
    public ResponseEntity<String> saveMinimap(@RequestBody MinimapBatchDto minimapBatchDto) {
        String videoId = minimapBatchDto.getVideoId();
        if (videoId == null || videoId.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid video ID.");
        }

        Map<String, List<MinimapDto>> frames = minimapBatchDto.getFrames();
        List<Minimap> minimapsToSave = new ArrayList<>();

        for (Map.Entry<String, List<MinimapDto>> entry : frames.entrySet()) {
            String frameKey = entry.getKey();
            List<MinimapDto> minimapDtos = entry.getValue();

            for (MinimapDto minimapDto : minimapDtos) {
                Minimap minimap = new Minimap();
                minimap.setX(minimapDto.getX());
                minimap.setY(minimapDto.getY());
                minimap.setFrameNumber(Integer.parseInt(frameKey.replace("Frame_", "")));
                minimap.setVideoId(videoId);
                minimap.setTeam(minimapDto.getTeam());
                minimapsToSave.add(minimap);
            }
        }

        try {
            minimapRepository.saveAll(minimapsToSave);
            return ResponseEntity.ok("Minimap data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving minimap data: " + e.getMessage());
        }
    }

    // 미니맵 데이터 조회
    @GetMapping("/minimap/{videoId}")
    public ResponseEntity<List<MinimapDto>> getMinimapData(@PathVariable String videoId) {
        if (videoId == null || videoId.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            List<MinimapDto> minimapData = minimapService.getMinimapData(videoId);
            if (minimapData.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(minimapData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 클라이언트와의 통신을 위한 미니맵 데이터 저장
    @PostMapping("/client/minimap/{videoId}")
    public ResponseEntity<String> saveMinimapDataForClient(
            @PathVariable String videoId,
            @RequestBody List<MinimapDto> minimapData) {

        if (videoId == null || videoId.isEmpty() || minimapData == null || minimapData.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid input data.");
        }

        try {
            List<Minimap> minimapsToSave = new ArrayList<>();
            for (MinimapDto minimapDto : minimapData) {
                Minimap minimap = new Minimap();
                minimap.setX(minimapDto.getX());
                minimap.setY(minimapDto.getY());
                minimap.setFrameNumber(minimapDto.getFrameNumber());
                minimap.setVideoId(videoId);
                minimap.setTeam(minimapDto.getTeam()); // Fix: Set the correct team value
                minimapsToSave.add(minimap);
            }

            minimapRepository.saveAll(minimapsToSave);
            return ResponseEntity.ok("Minimap data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving minimap data: " + e.getMessage());
        }
    }

    // 클라이언트와의 통신을 위한 미니맵 데이터 조회
    @GetMapping("/client/minimap")
    public ResponseEntity<List<MinimapDto>> getMinimapDataForClient(@RequestParam String videoId) {
        if (videoId == null || videoId.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            List<MinimapDto> minimapData = minimapService.getMinimapData(videoId);

            if (minimapData.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(minimapData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
