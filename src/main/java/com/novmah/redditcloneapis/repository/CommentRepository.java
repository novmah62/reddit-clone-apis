package com.novmah.redditcloneapis.repository;

import com.novmah.redditcloneapis.model.Comment;
import com.novmah.redditcloneapis.model.Post;
import com.novmah.redditcloneapis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);

}
