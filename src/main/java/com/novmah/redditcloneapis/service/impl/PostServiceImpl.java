package com.novmah.redditcloneapis.service.impl;

import com.novmah.redditcloneapis.dto.PostRequest;
import com.novmah.redditcloneapis.dto.PostResponse;
import com.novmah.redditcloneapis.exception.PostNotFoundException;
import com.novmah.redditcloneapis.exception.SubredditNotFoundException;
import com.novmah.redditcloneapis.mapper.PostMapper;
import com.novmah.redditcloneapis.model.Post;
import com.novmah.redditcloneapis.model.Subreddit;
import com.novmah.redditcloneapis.model.User;
import com.novmah.redditcloneapis.repository.PostRepository;
import com.novmah.redditcloneapis.repository.SubredditRepository;
import com.novmah.redditcloneapis.repository.UserRepository;
import com.novmah.redditcloneapis.service.AuthService;
import com.novmah.redditcloneapis.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    private final PostRepository postRepository;

    private final SubredditRepository subredditRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    @Override
    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findBySubredditName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        List<Post> post = postRepository.findAll();
        return post.stream().map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
