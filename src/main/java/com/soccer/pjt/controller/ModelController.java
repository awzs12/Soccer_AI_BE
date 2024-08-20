package com.soccer.pjt.controller;

import com.soccer.pjt.dto.PlayerDto;
import com.soccer.pjt.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ModelController {

    @Autowired
    private DataService service;

    @PostMapping(value = "/receive_list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> receiveList(@RequestBody Map<String, Object> payload) {
        try {
            String videoId = (String) payload.get("videoId");
            if (videoId == null || videoId.isEmpty()) {
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\": \"Missing or empty 'videoId' field in the payload\"}");
            }

            Object dataObject = payload.get("data");
            if (!(dataObject instanceof List)) {
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\": \"'data' field is not of expected type List\"}");
            }

            @SuppressWarnings("unchecked")
            List<List<Object>> dataList = (List<List<Object>>) dataObject;
            if (dataList.isEmpty()) {
                return ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"error\": \"'data' field is empty\"}");
            }

            // Process and save player data
            List<PlayerDto> playerDtoList = dataList.stream()
                    .map(data -> convertToPlayerDto(data, videoId))
                    .collect(Collectors.toList());
            service.savePlayerData(videoId, playerDtoList);

            // Update status to completed
            service.updateStatus(videoId, "completed");

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\": \"Data saved successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"Error processing the data: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping(value = "/status/{videoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStatus(@PathVariable String videoId) {
        String status = service.getStatus(videoId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"status\": \"" + status + "\"}");
    }

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