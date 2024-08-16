package com.soccer.pjt.controller;

import com.soccer.pjt.dto.VideoRequestDto;
import com.soccer.pjt.service.VideoForwardingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoForwardingService videoForwardingService;

    @PostMapping("/analyze-video")
    public Mono<ResponseEntity<String>> analyzeVideo(@RequestBody VideoRequestDto videoRequest) {
        String url = videoRequest.getUrl();
        String path = videoRequest.getPath();

        // Request ID 생성
        String requestId = generateRequestId(url, path);

        // 비디오 URL을 Colab으로 전달
        return videoForwardingService.forwardToColab(requestId, url, path)
                .map(responseBody -> ResponseEntity.ok("{\"message\": \"Video URL forwarded to Colab\"}"))
                .onErrorResume(e -> {
                    logger.error("Error processing video: {}", e.getMessage(), e);
                    return Mono.just(ResponseEntity.status(500).body("{\"message\": \"Failed to forward video URL\"}"));
                });
    }

    private String generateRequestId(String url, String path) {
        return url + ":" + path; // 요청 ID 생성 로직
    }
}
