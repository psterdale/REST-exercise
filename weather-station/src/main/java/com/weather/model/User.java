package com.weather.model;

public class User {
    private long   userId;
    private String name;
    private String email;
    private int    pinCode;

    public User(String name, String email, int pinCode) {
        this.name = name;
        this.email = email;
        this.pinCode = pinCode;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param l
     *            the userId to set
     */
    public void setUserId(long l) {
        this.userId = l;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the pinCode
     */
    public int getPinCode() {
        return pinCode;
    }

    /**
     * @param pinCode
     *            the pinCode to set
     */
    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }


}
