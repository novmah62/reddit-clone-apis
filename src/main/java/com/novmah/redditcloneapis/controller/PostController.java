package com.novmah.redditcloneapis.controller;

import com.novmah.redditcloneapis.dto.PostRequest;
import com.novmah.redditcloneapis.dto.PostResponse;
import com.novmah.redditcloneapis.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @GetMapping(params = "subredditId")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@RequestParam Long subredditId) {
        return ResponseEntity.ok(postService.getPostsBySubreddit(subredditId));
    }

    @GetMapping(params = "username")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@RequestParam String username) {
        return ResponseEntity.ok(postService.getPostsByUsername(username));
    }


}
