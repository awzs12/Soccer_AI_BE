package com.soccer.pjt.service;

import com.soccer.pjt.dto.VideoRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VideoForwardingService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String COLAB_ENDPOINT = "https://1e36-112-170-16-151.ngrok-free.app/api/analyze-video"; // 실제 코랩 서버 URL로 대체

    public void forwardToColab(String url, String path) {
        // VideoRequestDto 객체 생성
        VideoRequestDto requestDto = new VideoRequestDto(url, path);

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // HTTP 요청 객체 생성
        HttpEntity<VideoRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);

        try {
            // POST 요청 전송
            ResponseEntity<String> response = restTemplate.exchange(
                    COLAB_ENDPOINT,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // 응답 상태 코드와 본문 로그
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());

        } catch (Exception e) {
            // 예외 처리 및 로그
            System.err.println("Failed to forward request to Colab: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
