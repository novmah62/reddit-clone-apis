package com.novmah.redditcloneapis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String token;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Instant expiryDate;

}
