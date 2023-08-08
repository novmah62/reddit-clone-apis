package com.novmah.redditcloneapis.exception;

public class SubredditNotFoundException extends RuntimeException{

    public SubredditNotFoundException(String message) {
        super(message);
    }

}
