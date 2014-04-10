package org.vsa.api;

import java.util.ArrayList;
import java.util.List;
import org.vsa.util.FileUtil;

/**
 * InterrogationList
 */
public class InterrogationList {

    /**
     * interrogations
     */
    private final List<Interrogation> interrogations = new ArrayList<>();

    /**
     * Default constructor.
     * 
     * @param wavPath
     */
    public InterrogationList(String wavPath) {
        List<String> folderNames = FileUtil.getFolderNames(wavPath);

        // get interrogations
        for (String name : folderNames) {
            // create new Interrogation
            Interrogation interrogation = new Interrogation(name);

            // add it to the list
            interrogations.add(interrogation);
        }
    }

    /**
     * getInterrogations
     * 
     * @return the interrogations
     */
    public List<Interrogation> getInterrogations() {
        return interrogations;
    }

    /**
     * numInterrogations
     * 
     * @return 
     */
    public int numInterrogations() {
        return interrogations.size();
    }

    /**
     * getInterrogation
     * 
     * @param index
     * @return 
     */
    public Interrogation getInterrogation(int index) {
        return interrogations.get(index);
    }

    /**
     * getInterrogation
     * 
     * @param name
     * @return 
     */
    public Interrogation getInterrogation(String name) {
        for(Interrogation interrogation : interrogations) {
            if(interrogation.getName().equalsIgnoreCase(name)) {
                return interrogation;
            }
        }

        return null;
    }
}
