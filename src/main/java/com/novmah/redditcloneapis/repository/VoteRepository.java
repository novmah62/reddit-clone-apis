package com.novmah.redditcloneapis.repository;

import com.novmah.redditcloneapis.model.Post;
import com.novmah.redditcloneapis.model.User;
import com.novmah.redditcloneapis.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
