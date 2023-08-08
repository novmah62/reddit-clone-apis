package com.novmah.redditcloneapis.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubredditDto {

    private Long subId;

    @NotBlank(message = "Community name is required")
    private String subredditName;

    @NotBlank(message = "Description is required")
    private String description;

    private Integer numberOfPosts;


}
