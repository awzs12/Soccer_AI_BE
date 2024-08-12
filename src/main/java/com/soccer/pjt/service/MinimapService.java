package com.soccer.pjt.service;

import com.soccer.pjt.dto.MinimapDto;
import com.soccer.pjt.entity.Minimap;
import com.soccer.pjt.repository.MiniMapDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MinimapService {

    @Autowired
    private MiniMapDataRepository minimapRepository;

    public List<MinimapDto> getMinimapData(String videoId) {
        List<Minimap> minimapEntities = minimapRepository.findByVideoId(videoId); // videoId로 필터링
        return minimapEntities.stream()
                .map(minimap -> new MinimapDto(minimap.getX(), minimap.getY(), minimap.getFrameNumber(), minimap.getVideoId()))
                .collect(Collectors.toList());
    }
}
