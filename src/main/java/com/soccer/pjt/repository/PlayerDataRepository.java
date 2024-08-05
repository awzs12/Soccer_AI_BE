package com.soccer.pjt.repository;

import com.soccer.pjt.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDataRepository extends JpaRepository<Player, Long> {
    List<Player> findByVideoId(String videoId);

}
