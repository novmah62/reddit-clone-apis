package com.novmah.redditcloneapis.service;

import com.novmah.redditcloneapis.dto.SubredditDto;

import java.util.List;

public interface SubredditService {

    SubredditDto save(SubredditDto subredditDto);

    List<SubredditDto> getALl();

    SubredditDto getSubreddit(Long id);

    void deleteSubreddit(Long id);

}
