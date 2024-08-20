package com.soccer.pjt.controller;

import com.soccer.pjt.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class StatusController {

    @Autowired
    private DataService dataService;

    @GetMapping("/task-status/{taskId}")
    public ResponseEntity<?> getTaskStatus(@PathVariable String videoId) {
        String status = dataService.getStatus(videoId);
        return ResponseEntity.ok(Map.of("status", status));
    }
}
