package com.soccer.pjt.controller;

import com.soccer.pjt.dto.PlayerDto;
import com.soccer.pjt.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ModelController {

    @Autowired
    private DataService service;

    @PostMapping("/receive_list")
    public ResponseEntity<?> receiveList(@RequestBody Map<String, Object> payload) {
        try {
            String videoId = (String) payload.get("videoId");
            List<List<Integer>> playerDtos = (List<List<Integer>>) payload.get("data");

            if (videoId == null || playerDtos == null) {
                return ResponseEntity.badRequest().body("Missing 'videoId' or 'data' field in the payload");
            }

            // Convert List<List<Integer>> to List<PlayerDto>
            List<PlayerDto> playerDtoList = playerDtos.stream()
                    .map(this::convertToPlayerDto)
                    .collect(Collectors.toList());

            // Save the list of PlayerDto
            service.savePlayerData(videoId, playerDtoList);
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing the data: " + e.getMessage());
        }
    }

    // Helper method to convert List<Integer> to PlayerDto
    private PlayerDto convertToPlayerDto(List<Integer> data) {
        if (data.size() != 8) {
            throw new IllegalArgumentException("Invalid data format: expected 8 elements, got " + data.size());
        }
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerClass(data.get(0));
        playerDto.setX(data.get(1));
        playerDto.setY(data.get(2));
        playerDto.setWidth(data.get(3));
        playerDto.setHeight(data.get(4));
        playerDto.setTrackId(data.get(5));
        playerDto.setTeam(data.get(6));
        playerDto.setJerseyNumber(data.get(7));
        return playerDto;
    }
}
