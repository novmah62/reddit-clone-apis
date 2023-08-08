package com.novmah.redditcloneapis.controller;

import com.novmah.redditcloneapis.dto.CommentDto;
import com.novmah.redditcloneapis.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(params = "postId")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@RequestParam Long postId) {
        return ResponseEntity.ok(commentService.getAllCommentsForPost(postId));
    }

    @GetMapping(params = "userName")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser(@RequestParam String userName) {
        return ResponseEntity.ok(commentService.getAllCommentsForUser(userName));
    }

}
