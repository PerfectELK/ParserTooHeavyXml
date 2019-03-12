package com.www.pelk.xml.classes;


import com.www.pelk.xml.classes.abs.XmlHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlHandlerRegion extends XmlHandler {

    public String tag;
    public String[] values;

    public XmlHandlerRegion(String filePath) {
        super(filePath);
        this.setTag("Object");
        this.setValues(new String[]{
                "AOID",
                "AOGUID",
                "FORMALNAME",
                "OFFNAME",
                "SHORTNAME",
                "AOLEVEL",
                "REGIONCODE",
                "AREACODE",
                "AUTOCODE",
                "CITYCODE",
                "CTARCODE",
                "PLACECODE",
                "STREETCODE",
                "EXTCODE",
                "SEXTCODE",
                "PLAINCODE",
                "CODE",
                "CURRSTATUS",
                "ACTSTATUS",
                "LIVESTATUS",
                "CENTSTATUS",
                "OPERSTATUS",
                "IFNSFL",
                "IFNSUL",
                "OKATO",
                "POSTALCODE",
                "STARTDATE",
                "ENDDATE",
                "UPDATEDATE",
                "DIVTYPE"});
    }

    @Override
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public void setValues(String [] values) {
        this.values = values;
    }



    @Override
    public ArrayList<String> getTagFromString() throws IOException, URISyntaxException {
        String tags = this.getStringFromXml();

        Pattern pattern = Pattern.compile("<"+this.tag+" ([А-Яа-я\\w=\\-\"\"\\s]+) />");
        Matcher m = pattern.matcher(tags);

        ArrayList<String> result = new ArrayList<String>();
        while(m.find()){
            System.out.println(m.group());
            result.add(m.group());
        }

        return result;
    }

    @Override
    public ArrayList<Map> getValueFromTag() throws IOException, URISyntaxException {
        ArrayList<String> tags = this.getTagFromString();

        ArrayList<Map> mapitems = new ArrayList<Map>();
        for(int i = 0; i < tags.size(); i++){
            String item = tags.get(i);
            Map<String, String> map = new HashMap<String,String>();

            for(int j = 0; j < this.values.length; j++){
                Pattern pattern = Pattern.compile(this.values[j]+"=\"([А-Яа-я\\w=\\-\\s]+)\"");
                Matcher matcher = pattern.matcher(item);
                if(matcher.find()){
                    map.put(this.values[j],matcher.group(1));

                }
            }
            System.out.println(map);
            mapitems.add(map);

        }
        return mapitems;
    }

    @Override
    public boolean saveResult() {
        return false;
    }

}
