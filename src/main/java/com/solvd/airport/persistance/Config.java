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
    private static String poolsize;

    public String toString() {
        return ("Settings: \ndriver: " + this.driver + "\nurl: " + this.url + "\nusername: " + this.username + "\npassword: " + this.password + "\npoolsize: " + this.poolsize);
    }

    public void getFromFile(String fileName) {
        ParseJAXB parseJAXB = new ParseJAXB();
        Config config = parseJAXB.fileToObject(fileName);
        this.driver = config.getDriver();
        this.url = config.getUrl();
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.poolsize = config.getPoolsize();
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

    public static String getPoolsize() {
        return poolsize;
    }

    @XmlElement(name = "poolsize")
    public void setPoolsize(String poolsize) {
        this.poolsize = poolsize;
    }
}