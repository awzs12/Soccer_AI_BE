package com.soccer.pjt.repository;


import com.soccer.pjt.entity.VideoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDataRepository extends JpaRepository<VideoData, Long> {
}
