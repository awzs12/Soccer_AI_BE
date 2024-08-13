package com.soccer.pjt.dto;

import java.util.List;
import java.util.Map;

public class MinimapBatchDto {
    private String videoId;
    private Map<String, List<MinimapDto>> frames;

    // Getters and Setters


    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Map<String, List<MinimapDto>> getFrames() {
        return frames;
    }

    public void setFrames(Map<String, List<MinimapDto>> frames) {
        this.frames = frames;
    }
}
