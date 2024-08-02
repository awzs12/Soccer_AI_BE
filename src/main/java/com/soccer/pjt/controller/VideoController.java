package com.soccer.pjt.controller;


import com.soccer.pjt.entity.VideoData;
import com.soccer.pjt.service.VideoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoDataService service;

    @PostMapping
    public VideoData uploadVideoData(@RequestBody VideoData videoData){
        return service.saveVideoData(videoData);
    }

    @GetMapping
    public List<VideoData> getAllVideoData() {
        return service.getAllVideoData();
    }
}
