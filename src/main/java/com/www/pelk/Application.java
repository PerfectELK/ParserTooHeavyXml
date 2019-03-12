package com.www.pelk;

import com.www.pelk.xml.classes.XmlHandlerRegion;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Application {

    public static void main(String[] args) throws URISyntaxException {
        XmlHandlerRegion Region = new XmlHandlerRegion("/xml/respublic.XML");
        try {
            //System.out.println(Region.getStringFromXml());
            Region.getValueFromTag();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
