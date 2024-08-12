package com.soccer.pjt.controller;

import com.soccer.pjt.dto.VideoRequestDto;
import com.soccer.pjt.service.VideoForwardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VideoController {

    @Autowired
    private VideoForwardingService videoForwardingService;

    @PostMapping("/analyze-video")
    public ResponseEntity<String> analyzeVideo(@RequestBody VideoRequestDto videoRequest) {
        String url = videoRequest.getUrl();
        String path = videoRequest.getPath();

        try {
            // URL과 path를 Google Colab으로 전송
            videoForwardingService.forwardToColab(url, path);
            return ResponseEntity.ok("{\"message\": \"Video URL forwarded to Colab\"}");

        } catch (Exception e) {
            // 예외 처리 및 로그
            System.err.println("Error processing video: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"message\": \"Failed to forward video URL\"}");
        }
    }
}
