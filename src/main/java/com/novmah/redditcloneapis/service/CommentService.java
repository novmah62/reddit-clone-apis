package com.novmah.redditcloneapis.service;

import com.novmah.redditcloneapis.dto.CommentDto;
import com.novmah.redditcloneapis.model.User;

import java.util.List;

public interface CommentService {
    void save(CommentDto commentDto);

    void sendCommentNotification(String message, User user);

    List<CommentDto> getAllCommentsForPost(Long postId);

    List<CommentDto> getAllCommentsForUser(String userName);

    boolean containsSwearWords(String comment);
}
