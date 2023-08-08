package com.novmah.redditcloneapis.service;

import com.novmah.redditcloneapis.dto.AuthenticationResponse;
import com.novmah.redditcloneapis.dto.LoginRequest;
import com.novmah.redditcloneapis.dto.RefreshTokenRequest;
import com.novmah.redditcloneapis.dto.RegisterRequest;
import com.novmah.redditcloneapis.model.User;
import com.novmah.redditcloneapis.model.VerificationToken;

public interface AuthService {

    void signup(RegisterRequest registerRequest);

    User getCurrentUser();

    void fetchUserAndEnable(VerificationToken verificationToken);

    String generateVerificationToken(User user);

    void verifyAccount(String token);

    AuthenticationResponse login(LoginRequest loginRequest);

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    boolean isLoggedIn();

}
