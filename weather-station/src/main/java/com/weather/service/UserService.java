package com.weather.service;

import java.util.HashMap;
import java.util.Map;

import com.weather.common.TokenExpiredException;
import com.weather.model.Token;
import com.weather.model.User;

public interface UserService {
    public static final Map<Long, User> userMap = new HashMap<Long, User>();
    public static final Map<Long, Token> tokenMap = new HashMap<Long, Token>();

    public User registerUser(User user);

    public boolean isValid(User user);

    public String sendLoginEmail(long userId);

    public User openLoginEmail(Token token) throws TokenExpiredException;
}
