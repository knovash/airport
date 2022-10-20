package com.solvd.airport.persistence;

import com.solvd.airport.Main;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

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

    public void getFromFile() {

        Properties props = new Properties();
        URL resource = Main.class.getClassLoader().getResource("config.properties");
        File file = new File(Objects.requireNonNull(resource).getFile());
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            props.load(in);
            in.close();
            driver = props.getProperty("driver");
            if (driver != null) {
                Class.forName(driver);
            }
            url = props.getProperty("url");
            username = props.getProperty("username");
            password = props.getProperty("password");
            poolsize = Integer.valueOf(props.getProperty("poolsize"));

            Connection con = DriverManager.getConnection(url, username, password);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        Config.driver = driver;
    }

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        Config.url = url;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        Config.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Config.password = password;
    }

    public static Integer getPoolsize() {
        return poolsize;
    }

    public void setPoolsize(Integer poolsize) {
        Config.poolsize = poolsize;
    }
}
