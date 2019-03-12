package com.www.pelk.xml.classes.saveResult.As;

import com.www.pelk.xml.interfaces.ResultSaver;

import java.util.ArrayList;
import java.util.Map;

public class AsFiles implements ResultSaver {

    ArrayList<Map> arr;

    AsFiles(ArrayList<Map> arr){
        this.arr = arr;
    }

    @Override
    public boolean save() {
        return false;
    }
}
