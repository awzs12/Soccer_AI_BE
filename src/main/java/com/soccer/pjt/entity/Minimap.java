package com.soccer.pjt.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "minimap")
public class Minimap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Integer x;
    private Integer y;
    private Integer frameNumber;
    private String videoId; // videoId 필드 추가
    private Integer team;

    // Default constructor
    public Minimap() {
    }

    // Parameterized constructor
    public Minimap(Long idx, Integer x, Integer y, Integer frameNumber, String videoId, Integer team) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.frameNumber = frameNumber;
        this.videoId = videoId;
        this.team = team;
    }

    // Getter and Setter methods
    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

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

    @Override
    public String toString() {
        return "Minimap{" +
                "idx=" + idx +
                ", x=" + x +
                ", y=" + y +
                ", frameNumber=" + frameNumber +
                ", videoId='" + videoId + '\'' +
                ", team=" + team +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minimap minimap = (Minimap) o;
        return idx.equals(minimap.idx);
    }

    @Override
    public int hashCode() {
        return idx.hashCode();
    }
}
