package com.krzysiek.social.exceptions;

public class SocialWebsiteException extends RuntimeException {
    public SocialWebsiteException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SocialWebsiteException(String exMessage) {
        super(exMessage);
    }
}