package com.novmah.redditcloneapis.service;

import com.novmah.redditcloneapis.model.RefreshToken;

public interface RefreshTokenService {

    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);

}
