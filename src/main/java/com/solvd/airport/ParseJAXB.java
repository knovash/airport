package com.solvd.airport;

import com.solvd.airport.Main;
import com.solvd.airport.persistance.Config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;

public class ParseJAXB {

    public Config fileToObject(String fileName) {
        URL resource = Main.class.getClassLoader().getResource(fileName);
        File file = new File(resource.getFile());
        /** use JAXB parser from XML to JavaObject */
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Config.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        Config object = null;
        Unmarshaller um = null;
        try {
            um = context.createUnmarshaller();
            object = (Config) um.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
}