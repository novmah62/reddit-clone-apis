package com.novmah.redditcloneapis.repository;

import com.novmah.redditcloneapis.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    Optional<Subreddit> findBySubredditName(String subredditName);

}
