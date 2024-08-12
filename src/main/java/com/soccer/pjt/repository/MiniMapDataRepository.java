package com.soccer.pjt.repository;

import com.soccer.pjt.entity.Minimap;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MiniMapDataRepository extends JpaRepository<Minimap, Long> {
    List<Minimap> findByVideoId(String videoId); // videoId로 필터링
}
