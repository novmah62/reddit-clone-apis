package com.novmah.redditcloneapis.controller;

import com.novmah.redditcloneapis.dto.SubredditDto;
import com.novmah.redditcloneapis.service.SubredditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        SubredditDto createSubreddit = subredditService.save(subredditDto);
        return new ResponseEntity<>(createSubreddit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        return ResponseEntity.ok(subredditService.getALl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable Long id) {
        return ResponseEntity.ok(subredditService.getSubreddit(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubreddit(@PathVariable Long id) {
        subredditService.deleteSubreddit(id);
        return ResponseEntity.ok("Subreddit Deleted Successfully!!!");
    }

}
