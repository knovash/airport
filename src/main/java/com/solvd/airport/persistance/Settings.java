package com.solvd.airport.persistance;

import com.solvd.airport.service.ParseJAXB;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"driver", "url", "username", "password", "poolsize"})
public class Settings {
    private String driver;
    private String url;
    private String username;
    private String password;
    private String poolsize;

    public String toString() {
        return ("Settings: \ndriver: " + this.driver + "\nurl: " + this.url + "\nusername: " + this.username + "\npassword: " + this.password + "\npoolsize: " + this.poolsize);
    }

    public void getFromFile(String fileName) {
        ParseJAXB parseJAXB = new ParseJAXB();
        Settings settings = parseJAXB.fileToObject(fileName);
        this.driver = settings.getDriver();
        this.url = settings.getUrl();
        this.username = settings.getUsername();
        this.password = settings.getPassword();
        this.poolsize = settings.getPoolsize();
    }

    public String getDriver() {
        return driver;
    }

    @XmlElement(name = "driver")
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    @XmlElement(name = "url")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    @XmlElement(name = "username")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @XmlElement(name = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoolsize() {
        return poolsize;
    }

    @XmlElement(name = "poolsize")
    public void setPoolsize(String poolsize) {
        this.poolsize = poolsize;
    }
}