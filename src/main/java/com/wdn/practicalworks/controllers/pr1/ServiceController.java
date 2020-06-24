package com.wdn.practicalworks.controllers.pr1;

import java.io.File;
import java.util.ArrayList;

public class ServiceController {
    protected static String UPLOADED_FOLDER = "src"+ File.separator+"main"+ File.separator+"resources"+ File.separator+"files"+ File.separator;
    protected static String PROCESSED = "processed" + File.separator + "processed-";

    protected ArrayList<ArrayList> listFilesForFolder(final File folder) {
        ArrayList<ArrayList> returnList = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                ArrayList<String> files = new ArrayList<>();
                files.add(fileEntry.getName());
                File fileResult = new File(UPLOADED_FOLDER + PROCESSED+ fileEntry.getName());
                if (fileResult.exists()) {
                    files.add("processed-"+fileEntry.getName());
                } else {
                    files.add("");
                }
                returnList.add(files);
            }
        }
        return returnList;
    }
}
