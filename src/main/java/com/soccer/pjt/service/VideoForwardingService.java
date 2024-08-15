package com.soccer.pjt.service;

import com.soccer.pjt.dto.VideoRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VideoForwardingService {

    private final WebClient webClient = WebClient.create("https://a395-220-120-170-131.ngrok-free.app");
    private final Set<String> processedRequests = ConcurrentHashMap.newKeySet(); // 요청 ID를 저장할 Set

    public Mono<String> forwardToColab(String requestId, String url, String path) {
        // 중복 요청 방지
        if (processedRequests.contains(requestId)) {
            return Mono.just("Request already processed");
        }

        processedRequests.add(requestId);

        VideoRequestDto requestDto = new VideoRequestDto(url, path);

        return webClient.post()
                .uri("/api/analyze-video")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(String.class)
                .doFinally(signalType -> processedRequests.remove(requestId)); // 요청 완료 후 요청 ID 제거
    }
}
