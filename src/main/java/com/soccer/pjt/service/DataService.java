package com.soccer.pjt.service;

import com.soccer.pjt.dto.PlayerDto;
import com.soccer.pjt.entity.Player;
import com.soccer.pjt.repository.PlayerDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    private final PlayerDataRepository playerDataRepository;
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);

    @Autowired
    public DataService(PlayerDataRepository playerDataRepository) {
        this.playerDataRepository = playerDataRepository;
    }

    public void savePlayerData(String videoId, List<PlayerDto> dtos) {
        List<Player> players = dtos.stream()
                .map(dto -> {
                    Player player = dtoToEntity(dto);
                    player.setVideoId(videoId); // 비디오 ID 설정
                    return player;
                })
                .collect(Collectors.toList());
        playerDataRepository.saveAll(players);
    }

    private Player dtoToEntity(PlayerDto dto) {
        Player player = new Player();

        player.setPlayerClass(dto.getPlayerClass());
        player.setX(dto.getX());
        player.setY(dto.getY());
        player.setWidth(dto.getWidth());
        player.setHeight(dto.getHeight());
        player.setTrackId(dto.getTrackId());
        player.setTeam(dto.getTeam());
        player.setJerseyNumber(dto.getJerseyNumber());
        player.setVideoId(dto.getVideoId()); // 비디오 ID 설정
        player.setFrameNumber(dto.getFrameNumber()); // 프레임 넘버 설정
        return player;
    }

    public List<Player> getAllPlayers() {
        return playerDataRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerDataRepository.findById(id).orElse(null);
    }

    public List<Player> getPlayersByVideoId(String videoId) {
        return playerDataRepository.findByVideoId(videoId);
    }
}
