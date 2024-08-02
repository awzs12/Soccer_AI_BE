package com.soccer.pjt.service;

import com.soccer.pjt.entity.VideoData;
import com.soccer.pjt.repository.VideoDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoDataService {

    @Autowired
    private VideoDataRepository repository;

    public VideoData saveVideoData(VideoData videoData){
        return repository.save(videoData);
    }


    public List<VideoData> getAllVideoData(){
        return repository.findAll();
    }
}
