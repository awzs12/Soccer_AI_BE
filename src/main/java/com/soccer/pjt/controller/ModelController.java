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
            // Validate and extract 'videoId'
            String videoId = (String) payload.get("videoId");

            if (videoId == null || videoId.isEmpty()) {
                return ResponseEntity.badRequest().body("Missing or empty 'videoId' field in the payload");
            }

            // Validate and extract 'data'
            Object dataObject = payload.get("data");
            if (!(dataObject instanceof List)) {
                return ResponseEntity.badRequest().body("'data' field is not of expected type List");
            }

            @SuppressWarnings("unchecked")
            List<List<Object>> dataList = (List<List<Object>>) dataObject;

            if (dataList.isEmpty()) {
                return ResponseEntity.badRequest().body("'data' field is empty");
            }

            // Process each item in dataList
            List<PlayerDto> playerDtoList = dataList.stream()
                    .map(data -> convertToPlayerDto(data, videoId))
                    .collect(Collectors.toList());

            // Save the list of PlayerDto
            service.savePlayerData(videoId, playerDtoList);
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing the data: " + e.getMessage());
        }
    }

    // Helper method to convert List<Object> to PlayerDto
    private PlayerDto convertToPlayerDto(List<Object> data, String videoId) {
        if (data.size() != 9) {
            throw new IllegalArgumentException("Invalid data format: expected 9 elements, got " + data.size());
        }

        @SuppressWarnings("unchecked")
        List<Integer> playerData = data.subList(0, 8).stream()
                .map(obj -> (Integer) obj)
                .collect(Collectors.toList());

        Integer frameNumber = (Integer) data.get(8);

        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerClass(playerData.get(0));
        playerDto.setX(playerData.get(1));
        playerDto.setY(playerData.get(2));
        playerDto.setWidth(playerData.get(3));
        playerDto.setHeight(playerData.get(4));
        playerDto.setTrackId(playerData.get(5));
        playerDto.setTeam(playerData.get(6));
        playerDto.setJerseyNumber(playerData.get(7));
        playerDto.setFrameNumber(frameNumber);
        playerDto.setVideoId(videoId);

        return playerDto;
    }
}
