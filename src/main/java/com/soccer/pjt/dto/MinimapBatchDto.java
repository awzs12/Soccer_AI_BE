package com.soccer.pjt.dto;

import java.util.List;
import java.util.Map;

public class MinimapBatchDto {
    private Map<String, List<MinimapDto>> frames;

    // Getters and Setters

    public Map<String, List<MinimapDto>> getFrames() {
        return frames;
    }

    public void setFrames(Map<String, List<MinimapDto>> frames) {
        this.frames = frames;
    }
}
