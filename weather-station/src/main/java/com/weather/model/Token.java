package com.weather.model;

public class Token {
    private long   userId;
    private String token;
    private long   createdTime;

    public Token(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Token(long userId) {
        this.userId = userId;

    }

    public Token(long userId, String token, long createdTime) {
        this.userId = userId;
        this.token = token;
        this.createdTime = createdTime;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @return the createdTime
     */
    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @param createdTime
     *            the createdTime to set
     */
    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

}
