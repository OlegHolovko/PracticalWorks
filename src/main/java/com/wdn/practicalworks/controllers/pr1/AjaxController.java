package com.wdn.practicalworks.controllers.pr1;


import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@RestController
public class AjaxController extends ServiceController {

    @RequestMapping(value = "/pr1/listing", method = { RequestMethod.POST })
    public String listing(@RequestParam("xml-action") String action, @RequestParam("filename") String filename) throws IOException, SAXException, ParserConfigurationException {
        String xmlAsString;
        if(action.equals("show-original")) {
            Reader fileReader = new FileReader(UPLOADED_FOLDER + filename);
            BufferedReader bufReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = bufReader.readLine();
            while( line != null){
                sb.append(line).append("\n");
                line = bufReader.readLine();
            }
            xmlAsString = sb.toString();
            bufReader.close();
            return xmlAsString;
        }
        if(action.equals("show-result")) {
            Reader fileReader = new FileReader(UPLOADED_FOLDER + PROCESSED + filename);
            BufferedReader bufReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line = bufReader.readLine();
            while( line != null){
                sb.append(line).append("\n");
                line = bufReader.readLine();
            }
            xmlAsString = sb.toString();
            bufReader.close();
            return xmlAsString;
        }
        return null;
    }
}
