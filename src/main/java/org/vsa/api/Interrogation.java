package org.vsa.api;

import java.io.IOException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.vsa.Config;
import org.vsa.audio.AudioException;
import org.vsa.util.FileUtil;
import org.vsa.weka.VoiceStressInstance;
import org.vsa.weka.VoiceStressInstanceList;
import weka.core.Instances;

/**
 * Interrogation
 */
public class Interrogation {

    /**
     * name
     */
    private String name;

    /**
     * files
     */
    private final List<String> files;

    /**
     * instanceList
     */
    private VoiceStressInstanceList instanceList;

    /**
     * getName
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * numFiles
     * 
     * @return 
     */
    public int numFiles() {
        return files.size();
    }

    /**
     * getFiles
     * 
     * @return the files
     */
    public List<String> getFiles() {
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

        // return
        return instanceList;
    }

    /**
     * toWekaInstances
     * 
     * @return
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws AudioException
     */
    public Instances toWekaInstances() throws IOException, UnsupportedAudioFileException, AudioException {
        // convert to weka instances
        Instances instances = getInstanceList().toWekaInstances();

        // return instances
        return instances;
    }

    /**
     * generateArff
     * 
     * @param outputPath
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws AudioException
     */
    public void generateArff(String outputPath) throws IOException, UnsupportedAudioFileException, AudioException {
        // write instances to arff file
        getInstanceList().toArffFile(outputPath);
    }

    /**
     * toSummaryString
     * 
     * @return 
     */
    public String toSummaryString() {
        return "Nazwa przesłuchania: " + this.getName() + "\n" +
                "Liczba plików dźwiękowych" + this.numFiles();
    }

    /**
     * toString
     * 
     * @return 
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * getInstanceList
     * 
     * @return the instanceList
     * @throws java.io.IOException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     * @throws org.vsa.audio.AudioException
     */
    public VoiceStressInstanceList getInstanceList() throws IOException, UnsupportedAudioFileException, AudioException {
        // process files if null
        if(instanceList == null) {
            instanceList = this.processFiles();
        }

        return instanceList;
    }
}
