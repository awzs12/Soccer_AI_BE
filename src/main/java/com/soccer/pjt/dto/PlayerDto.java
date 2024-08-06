package com.soccer.pjt.dto;

public class PlayerDto {
    private Integer playerClass;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private Integer trackId;
    private Integer team;
    private Integer jerseyNumber;
    private Integer frameNumber; // Add this field
    private String videoId;


    // Getters and Setters
    public Integer getPlayerClass() { return playerClass; }
    public void setPlayerClass(Integer playerClass) { this.playerClass = playerClass; }

    public Integer getX() { return x; }
    public void setX(Integer x) { this.x = x; }

    public Integer getY() { return y; }
    public void setY(Integer y) { this.y = y; }

    public Integer getWidth() { return width; }
    public void setWidth(Integer width) { this.width = width; }

    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }

    public Integer getTrackId() { return trackId; }
    public void setTrackId(Integer trackId) { this.trackId = trackId; }

    public Integer getTeam() { return team; }
    public void setTeam(Integer team) { this.team = team; }

    public Integer getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(Integer jerseyNumber) { this.jerseyNumber = jerseyNumber; }

    public Integer getFrameNumber() { return frameNumber; }
    public void setFrameNumber(Integer frameNumber) { this.frameNumber = frameNumber; }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
