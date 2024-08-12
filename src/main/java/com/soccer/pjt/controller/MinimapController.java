package com.soccer.pjt.controller;

import com.soccer.pjt.dto.MinimapBatchDto;
import com.soccer.pjt.dto.MinimapDto;
import com.soccer.pjt.entity.Minimap;
import com.soccer.pjt.repository.MiniMapDataRepository;
import com.soccer.pjt.service.MinimapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MinimapController {

    @Autowired
    private MiniMapDataRepository minimapRepository;

    @Autowired
    private MinimapService minimapService;

    @PostMapping("/minimap")
    public ResponseEntity<Void> saveMinimap(@RequestBody MinimapBatchDto minimapBatchDto) {
        Map<String, List<MinimapDto>> frames = minimapBatchDto.getFrames();

        for (Map.Entry<String, List<MinimapDto>> entry : frames.entrySet()) {
            String frameKey = entry.getKey();
            List<MinimapDto> minimapDtos = entry.getValue();

            for (MinimapDto minimapDto : minimapDtos) {
                Minimap minimap = new Minimap();
                minimap.setX(minimapDto.getX());
                minimap.setY(minimapDto.getY());
                minimap.setFrameNumber(minimapDto.getFrameNumber());
                minimap.setVideoId(minimapDto.getVideoId()); // 비디오 ID 저장
                minimapRepository.save(minimap);
            }
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/minimap/{videoId}")
    public ResponseEntity<List<MinimapDto>> getMinimapData(@PathVariable String videoId) {
        List<MinimapDto> minimapData = minimapService.getMinimapData(videoId);
        return ResponseEntity.ok(minimapData);
    }
}
