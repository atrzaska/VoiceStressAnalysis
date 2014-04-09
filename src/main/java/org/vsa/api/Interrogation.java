package org.vsa.api;

import java.io.IOException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.vsa.Config;
import org.vsa.audio.AudioException;
import org.vsa.util.FileUtil;
import org.vsa.weka.VoiceStressInstance;
import org.vsa.weka.VoiceStressInstanceList;

/**
 * Interrogation
 */
public class Interrogation {

    /**
     * name
     */
    private final String name;

    /**
     * files
     */
    private final List<String> files;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the files
     */
    public List<String> setName() {
        return files;
    }

    /**
     * Default constructor.
     * 
     * @param name 
     */
    public Interrogation(String name) {
        // set name
        this.name = name;

        String path = Config.wavPath + name + "/";

        // get wav's
        this.files = FileUtil.getWavFiles(path);
    }

    /**
     * processFiles
     * 
     * @return 
     * @throws java.io.IOException 
     * @throws javax.sound.sampled.UnsupportedAudioFileException 
     * @throws org.vsa.audio.AudioException 
     */
    public VoiceStressInstanceList processFiles() throws IOException, UnsupportedAudioFileException, AudioException {
        // create output list
        VoiceStressInstanceList instanceList = new VoiceStressInstanceList();

        // set name
        instanceList.setName(name);

        // iterate all files
        for (String file : files) {
            // process sound file
            VoiceStressInstance instance = VoiceStressInstance.fromSoundFile(file);
            
            // add instance to array
            instanceList.addInstance(instance);
        }

        return instanceList;
    }
}
