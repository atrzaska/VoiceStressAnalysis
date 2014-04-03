package org.vsa.api;

import java.util.List;
import org.vsa.util.FileUtil;

/**
 * Interrogation
 */
public class Interrogation {

    /**
     * path
     */
    private final String path;

    /**
     * files
     */
    private final List<String> files;

    /**
     * Default constructor.
     * 
     * @param path 
     */
    public Interrogation(String path) {
        // set path
        this.path = path;

        // get wav's
        this.files = FileUtil.getWavFiles(path);
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the files
     */
    public List<String> getFiles() {
        return files;
    }
}
