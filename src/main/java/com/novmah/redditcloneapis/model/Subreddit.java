package com.novmah.redditcloneapis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subreddit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long subId;

    private String subredditName;

    private String description;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "postId")
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private User user;

}
