package org.vsa.api;

import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.vsa.Config;
import org.vsa.audio.AudioException;
import org.vsa.util.FileUtil;

/**
 * VsaSystem
 */
public class VsaSystem {

    /**
     * interrogations
     */
    private final InterrogationList interrogationList;
    
    /**
     * Default constructor.
     */
    public VsaSystem() {
        // get wav folder path
        String wavPath = Config.wavPath;

        // init list
        interrogationList = new InterrogationList(wavPath);
    }

    /**
     * getInterrogationList
     * 
     * @return the interrogations
     */
    public InterrogationList getInterrogationList() {
        return interrogationList;
    }

    /**
     * generateArffFiles
     * 
     * @throws java.io.IOException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     * @throws org.vsa.audio.AudioException
     */
    public void generateArffFiles() throws IOException, UnsupportedAudioFileException, AudioException {
        // iterate interrogations
        for(Interrogation interrogation : interrogationList.getInterrogations()) {

            // generate output path
            String outputPath = Config.outputPath + FileUtil.generateArffFileName(interrogation.getName());

            // save arff
            interrogation.generateArff(outputPath);
        }
    }
}
