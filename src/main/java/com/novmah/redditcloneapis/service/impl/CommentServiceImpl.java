package com.novmah.redditcloneapis.service.impl;

import com.novmah.redditcloneapis.dto.CommentDto;
import com.novmah.redditcloneapis.exception.PostNotFoundException;
import com.novmah.redditcloneapis.exception.RedditCloneException;
import com.novmah.redditcloneapis.mapper.CommentMapper;
import com.novmah.redditcloneapis.model.Comment;
import com.novmah.redditcloneapis.model.NotificationEmail;
import com.novmah.redditcloneapis.model.Post;
import com.novmah.redditcloneapis.model.User;
import com.novmah.redditcloneapis.repository.CommentRepository;
import com.novmah.redditcloneapis.repository.PostRepository;
import com.novmah.redditcloneapis.repository.UserRepository;
import com.novmah.redditcloneapis.service.AuthService;
import com.novmah.redditcloneapis.service.CommentService;
import com.novmah.redditcloneapis.service.MailContentBuilderService;
import com.novmah.redditcloneapis.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final String POST_URL = "";

    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final MailService mailService;

    private final MailContentBuilderService mailContentBuilderService;

    @Override
    public void save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilderService.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    @Override
    public void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    @Override
    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));
        List<Comment> comments = commentRepository.findByPost(post);
        return comments.stream().map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<CommentDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        List<Comment> comments = commentRepository.findAllByUser(user);
        return comments.stream().map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new RedditCloneException("Comments contains unacceptable language");
        }
        return false;
    }
}
