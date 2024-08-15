package com.soccer.pjt.controller;

import com.soccer.pjt.dto.VideoRequestDto;
import com.soccer.pjt.service.VideoForwardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class VideoController {

    @Autowired
    private VideoForwardingService videoForwardingService;

    @PostMapping("/analyze-video")
    public Mono<ResponseEntity<String>> analyzeVideo(@RequestBody VideoRequestDto videoRequest) {
        String url = videoRequest.getUrl();
        String path = videoRequest.getPath();
        String requestId = generateRequestId(url, path);

        return videoForwardingService.forwardToColab(requestId, url, path)
                .map(responseBody -> ResponseEntity.ok("{\"message\": \"Video URL forwarded to Colab\"}"))
                .onErrorResume(e -> {
                    System.err.println("Error processing video: " + e.getMessage());
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.status(500).body("{\"message\": \"Failed to forward video URL\"}"));
                });
    }

    private String generateRequestId(String url, String path) {
        return url + ":" + path; // 요청 ID 생성 로직
    }
}
