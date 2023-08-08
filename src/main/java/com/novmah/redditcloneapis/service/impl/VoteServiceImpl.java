package com.novmah.redditcloneapis.service.impl;

import com.novmah.redditcloneapis.dto.VoteDto;
import com.novmah.redditcloneapis.exception.RedditCloneException;
import com.novmah.redditcloneapis.model.Post;
import com.novmah.redditcloneapis.model.Vote;
import com.novmah.redditcloneapis.repository.PostRepository;
import com.novmah.redditcloneapis.repository.VoteRepository;
import com.novmah.redditcloneapis.service.AuthService;
import com.novmah.redditcloneapis.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.novmah.redditcloneapis.model.VoteType.UPVOTE;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final PostRepository postRepository;

    private final AuthService authService;

    @Override
    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new RedditCloneException("Post Not Found with ID: " + voteDto.getPostId()));

        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new RedditCloneException("You have already " + voteDto.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    public Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
