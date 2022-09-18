package com.solvd.airport.service;

import com.solvd.airport.Main;
import com.solvd.airport.persistance.Settings;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.Charset;

public class ParseJAXB implements IParse {

    @Override
    public Settings fileToObject(String fileName) {
        URL resource = Main.class.getClassLoader().getResource(fileName);
        File file = new File(resource.getFile());
        /** use JAXB parser from XML to JavaObject */
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Settings.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        Settings object = null;
        Unmarshaller um = null;
        try {
            um = context.createUnmarshaller();
            object = (Settings) um.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    @Override
    public void objectToFile(Settings settings, File file) {
        /** use JAXB parser from Object to XML */
        StringWriter writer = new StringWriter();
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Settings.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(settings, writer);
            String result = writer.toString();
            System.out.println(result);
            FileUtils.writeStringToFile(file, result, Charset.defaultCharset());
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}