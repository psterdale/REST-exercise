package com.weather.model;

public class Temperature {
    private int pinCode;
    private int temperature;

    /**
     * @return the pinCode
     */
    public int getPinCode() {
        return pinCode;
    }

    /**
     * @return the temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * @param pinCode
     *            the pinCode to set
     */
    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * @param temperature
     *            the temperature to set
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

}
