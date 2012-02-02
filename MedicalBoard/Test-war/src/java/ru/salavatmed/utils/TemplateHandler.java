package ru.salavatmed.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;


/**
 *
 *  @author Alexey Khudyakov
 *  ICQ: 164777039
 *  @date: 15.01.2011
 */
public class TemplateHandler {

    private String filename;
    private HashMap replaceValues;

    public TemplateHandler(String filename, HashMap replaceValues) {
        this.filename = filename;
        this.replaceValues = replaceValues;
    }

    public String templateProcessing() throws IOException {
        String content = IOUtils.toString(new FileInputStream(filename), "utf-8");

        Set entries = replaceValues.entrySet();
        Iterator it = entries.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if(entry.getValue() == null){
                entry.setValue("");
            }
            content = content.replace((String) entry.getKey(), (String) entry.getValue());
        }
        return content;
    }
}
