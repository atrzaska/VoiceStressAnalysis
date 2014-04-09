package org.vsa.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/**
 * FileUtil
 */
public class FileUtil {

    /**
     * generateArffFileName
     * 
     * @param name
     * @return 
     */
    public static String generateArffFileName(String name) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return name + "-" + simpleDateFormat.format(new Date()) + ".arff";
    }

    /**
     * listFilesForFolder
     * 
     * @param folderPath
     * @return 
     */
    public static List<File> getFileList(String folderPath) {
        // create output array
        List<File> files = new ArrayList<>();

        // create file object
        File folder = new File(folderPath);

        // iterate folder files
        for (final File fileEntry : folder.listFiles()) {

            // check if it's a file
            if(fileEntry.isFile()) {   
                // save file paths in array
                files.add(fileEntry);
            }
        }

        // return list
        return files;
    }

    /**
     * getWavFiles
     * 
     * @param folderPath
     * @return 
     */
    public static List<String> getWavFiles(String folderPath) {
        // create output array
        List<String> wavFiles = new ArrayList<>();

        // create file object
        File folder = new File(folderPath);

        // get files from folder
        File[] allFiles = folder.listFiles();

        // check for nulls
        if(allFiles == null) {
            return wavFiles;
        }

        // iterate over files
        for (File file : allFiles) {
            // get file extension
            String fileExtension = FilenameUtils.getExtension(file.getAbsolutePath());

            // check if it's a wav file
            if(fileExtension.equalsIgnoreCase("wav")) {
                wavFiles.add(file.getAbsolutePath());
            }
        }

        // return list
        return wavFiles;
        
    }
    /**
     * Get folder names
     * 
     * @param folderPath
     * @return 
     */
    public static List<String> getFolderNames(String folderPath) {
        // create output array
        List<String> folders = new ArrayList<>();

        // create file object
        File folder = new File(folderPath);

        // iterate folder files
        for (final File fileEntry : folder.listFiles()) {

            // check if it's a file
            if(fileEntry.isDirectory()) {   
                // save file paths in array
                folders.add(fileEntry.getName());
            }
        }

        // return list
        return folders;
    }
}
