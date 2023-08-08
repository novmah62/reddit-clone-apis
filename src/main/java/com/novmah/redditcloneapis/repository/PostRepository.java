package com.novmah.redditcloneapis.repository;

import com.novmah.redditcloneapis.model.Post;
import com.novmah.redditcloneapis.model.Subreddit;
import com.novmah.redditcloneapis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);

}
