package com.novmah.redditcloneapis.service;

import com.novmah.redditcloneapis.dto.PostRequest;
import com.novmah.redditcloneapis.dto.PostResponse;

import java.util.List;

public interface PostService {

    void save(PostRequest postRequest);

    PostResponse getPost(Long id);

    List<PostResponse> getAllPosts();

    List<PostResponse> getPostsBySubreddit(Long subredditId);

    List<PostResponse> getPostsByUsername(String username);

}
