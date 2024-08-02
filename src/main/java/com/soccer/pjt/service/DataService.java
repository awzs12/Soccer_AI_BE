package com.soccer.pjt.service;

import com.soccer.pjt.dto.PlayerDto;
import com.soccer.pjt.entity.Player;
import com.soccer.pjt.repository.PlayerDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    private final RestTemplate restTemplate;
    private final PlayerDataRepository playerDataRepository;
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);

    @Autowired
    public DataService(RestTemplate restTemplate, PlayerDataRepository playerDataRepository) {
        this.restTemplate = restTemplate;
        this.playerDataRepository = playerDataRepository;
    }

    public List<Player> save(List<PlayerDto> dtos) {
        List<Player> players = dtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
        return playerDataRepository.saveAll(players);
    }

    /**
     * Fetches player data from an external API, saves it to the database,
     * and logs the result.
     */

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
        return player;
    }

    /**
     * Retrieves all players from the database.
     *
     * @return List of Player entities
     */
    public List<Player> getAllPlayers() {
        return playerDataRepository.findAll();
    }

    /**
     * Retrieves a player by its ID from the database.
     *
     * @param id the ID of the player
     * @return the Player entity, or null if not found
     */
    public Player getPlayerById(Long id) {
        return playerDataRepository.findById(id).orElse(null);
    }
}