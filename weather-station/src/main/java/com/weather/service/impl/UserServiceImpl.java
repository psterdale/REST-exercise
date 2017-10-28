package com.weather.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.weather.common.TokenExpiredException;
import com.weather.model.Token;
import com.weather.model.User;
import com.weather.service.UserService;

public class UserServiceImpl implements UserService {
    public static AtomicLong idCounter = new AtomicLong(0);
    private SecureRandom     random    = new SecureRandom();
    @Override
    public User registerUser(User user) {
        user.setUserId(idCounter.incrementAndGet());
        userMap.put(user.getUserId(), user);
        return user;
    }

    @Override
    public boolean isValid(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() || user.getName() == null || user.getName().isEmpty()
                || user.getPinCode() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public String sendLoginEmail(long userId) {
        User user = userMap.get(userId);
        Properties props = new Properties();

        try {
            props.load(new FileReader(new File("email.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            return "Unable to load email properrties file.";
        }
        System.out.println("Login User name:" + props.getProperty("username"));
        System.out.println("Passowrd:" + props.getProperty("password"));
        // Properties prop = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
            }
        });

        try {
            char[] a2z = user.getName().toCharArray();
            List<Character> list = new ArrayList<Character>();
            for (int i = 0; i < a2z.length; i++) {
                list.add(a2z[i]);
            }
            Collections.shuffle(list, random);
            String tokenStr = list.toArray() + "_" + System.currentTimeMillis();
            Token tokenObj = new Token(user.getUserId(), tokenStr, System.currentTimeMillis());
            tokenMap.put(tokenObj.getUserId(), tokenObj);

            String link = "http://localhost:8080/login?userId=" + user.getUserId() + "&token=" + tokenStr;
            System.out.println(link);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Login to weather-station");
            message.setText("Dear ," + user.getName() + "\n\n Please login to weather-station using below link\n"
                    + link);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return "Login link emailed successfully";
    }

    @Override
    public User openLoginEmail(Token token) throws TokenExpiredException {
        Token tokenObj = tokenMap.get(token.getUserId());
        if (tokenObj == null || (System.currentTimeMillis() - tokenObj.getCreatedTime()) > (15 * 60 * 1000)) {
            throw new TokenExpiredException("Login link expired...");
        }
        return userMap.get(token.getUserId());
    }

    public static void main(String[] args) throws TokenExpiredException {
        UserServiceImpl service = new UserServiceImpl();
        service.registerUser(new User("test", "test@gmail.com", 456789));
        try {
            service.sendLoginEmail(1);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        service.openLoginEmail(new Token(1, "test"));
    }
}
