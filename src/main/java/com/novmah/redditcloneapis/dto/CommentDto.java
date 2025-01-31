package com.novmah.redditcloneapis.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long cmtId;

    private Long postId;

    private Instant createdDate;

    @NotBlank
    private String text;

    private String userName;

}
