package org.vsa.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/**
 * FileUtil
 */
public class FileUtil {

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
        List<String> files = new ArrayList<>();

        // create file object
        File folder = new File(folderPath);

        // iterate folder files
        for (final File file : folder.listFiles()) {
            // get file extension
            String fileExtension = FilenameUtils.getExtension(file.getAbsolutePath());

            // check if it's a file
            if(fileExtension.equalsIgnoreCase("wav")) {
                files.add(file.getAbsolutePath());
            }
        }

        // return list
        return files;
        
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
