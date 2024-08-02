package com.soccer.pjt.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Integer playerClass;
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private Integer trackId;;
    private Integer team;
    private Integer jerseyNumber;



    public Player() {
    }

    // Getters and Setters


    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public Integer getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(Integer playerClass) {
        this.playerClass = playerClass;
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

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String toString() {
        return "Player{" +
                ",playerClass=" + playerClass +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", trackId=" + trackId +
                ", team=" + team +
                ", jerseyNumber=" + jerseyNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(idx, player.idx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx);
    }
}
