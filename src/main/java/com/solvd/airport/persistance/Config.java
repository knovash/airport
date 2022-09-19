package com.solvd.airport.persistance;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"driver", "url", "username", "password", "poolsize"})
public class Config {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static Integer poolsize;

    public String toString() {
        return ("Settings: \ndriver: " + driver + "\nurl: " + url + "\nusername: " + username + "\npassword: " + password + "\npoolsize: " + poolsize);
    }

    public void getFromFile(String fileName) {
        ParseJAXB parseJAXB = new ParseJAXB();
        Config config = parseJAXB.fileToObject(fileName);
        driver = getDriver();
        url = getUrl();
        username = getUsername();
        password = getPassword();
        poolsize = getPoolsize();
    }

    public static String getDriver() {
        return driver;
    }

    @XmlElement(name = "driver")
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public static String getUrl() {
        return url;
    }

    @XmlElement(name = "url")
    public void setUrl(String url) {
        this.url = url;
    }

    public static String getUsername() {
        return username;
    }

    @XmlElement(name = "username")
    public void setUsername(String username) {
        this.username = username;
    }

    public static String getPassword() {
        return password;
    }

    @XmlElement(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    public static Integer getPoolsize() {
        return poolsize;
    }

    @XmlElement(name = "poolsize")
    public void setPoolsize(Integer poolsize) {
        this.poolsize = poolsize;
    }
}