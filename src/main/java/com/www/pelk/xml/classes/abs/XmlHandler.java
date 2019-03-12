package com.www.pelk.xml.classes.abs;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public abstract class XmlHandler {

    String filePath;
    int pointer = 0;

    protected XmlHandler(String filePath){
        this.filePath = filePath;
    }

    public  File getXmlFile() throws URISyntaxException {
        return new File(this.getClass().getResource(this.filePath).toURI());
    }

    public BufferedInputStream getBufferedInputStream() throws FileNotFoundException, URISyntaxException {
        return new BufferedInputStream(new FileInputStream(this.getXmlFile()));
    }

    public String getStringFromXml() throws IOException, URISyntaxException {
        ArrayList<Byte> bytes = new ArrayList<Byte>();
        BufferedInputStream bi = this.getBufferedInputStream();
        bi.skip(this.pointer);

        int c;
        int counter = 0;
        byte by = -1;
        boolean thisClousureTag = false;

        while((c = bi.read()) != -1){
            bytes.add((byte)c);

            if(by == 47 && (byte)c == 62 ){
                thisClousureTag = true;
            }else{
                thisClousureTag = false;
            }

            if(counter > 500000 && thisClousureTag){
                this.pointer++;
                break;
            }

            counter++;
            this.pointer++;
            by = (byte) c;
        }

        int bytesLength = bytes.size();
        byte[] b = new byte[bytesLength];

        for(int i = 0; i < bytesLength; i++){
            b[i] = bytes.get(i);
        }
        return new String(b,StandardCharsets.UTF_8);

    }

    public abstract void setTag(String tag);

    public abstract void setValues(String [] values);


    public abstract ArrayList<String> getTagFromString() throws IOException, URISyntaxException;

    public abstract ArrayList<Map> getValueFromTag() throws IOException, URISyntaxException;

    public abstract boolean saveResult();

}
