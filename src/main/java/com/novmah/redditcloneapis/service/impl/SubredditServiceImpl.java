package com.novmah.redditcloneapis.service.impl;

import com.novmah.redditcloneapis.dto.SubredditDto;
import com.novmah.redditcloneapis.exception.RedditCloneException;
import com.novmah.redditcloneapis.mapper.SubredditMapper;
import com.novmah.redditcloneapis.model.Subreddit;
import com.novmah.redditcloneapis.repository.SubredditRepository;
import com.novmah.redditcloneapis.service.SubredditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubredditServiceImpl implements SubredditService {

    private final SubredditMapper subredditMapper;

    private final SubredditRepository subredditRepository;

    @Override
    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setSubId(subreddit.getSubId());
        return subredditDto;
    }

    @Override
    @Transactional
    public List<SubredditDto> getALl() {
        List<Subreddit> subreddits = subredditRepository.findAll();
        return subreddits.stream().map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new RedditCloneException("No subreddit found with ID: " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }

    @Override
    public void deleteSubreddit(Long id) {
        subredditRepository.deleteById(id);
    }
}
