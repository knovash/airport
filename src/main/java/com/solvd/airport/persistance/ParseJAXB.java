package com.solvd.airport.persistance;

import com.solvd.airport.Main;
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

    @Override
    public void objectToFile(Config config, File file) {
        /** use JAXB parser from Object to XML */
        StringWriter writer = new StringWriter();
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Config.class);
            Marshaller marshaller = null;
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(config, writer);
            String result = writer.toString();
            System.out.println(result);
            FileUtils.writeStringToFile(file, result, Charset.defaultCharset());
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}