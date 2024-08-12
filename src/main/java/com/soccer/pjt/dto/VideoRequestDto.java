package com.soccer.pjt.dto;

public class VideoRequestDto {

    private String url;
    private String path;

    // 기본 생성자
    public VideoRequestDto() {
    }

    // 매개변수 생성자
    public VideoRequestDto(String url, String path) {
        this.url = url;
        this.path = path;
    }

    // Getter 및 Setter
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "VideoDto{" +
                "url='" + url + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
