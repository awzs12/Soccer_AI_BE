package com.soccer.pjt.dto;

public class MinimapDto {
    private Integer x;
    private Integer y;
    private Integer frameNumber;
    private String videoId;
    private Integer team;

    // Default constructor
//    public MinimapDto() {
//    }

    // Parameterized constructor
    public MinimapDto( Integer x, Integer y, Integer frameNumber, String videoId, Integer team) {

        this.x = x;
        this.y = y;
        this.frameNumber = frameNumber;
        this.videoId = videoId;
        this.team = team;
    }

    // Getters and Setters


    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(Integer frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }
}