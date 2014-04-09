package org.vsa.api;

import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.vsa.Config;
import org.vsa.audio.AudioException;
import org.vsa.util.FileUtil;
import org.vsa.weka.VoiceStressInstanceList;
import weka.core.Instances;

/**
 * VsaSystem
 */
public class VsaSystem {

    /**
     * interrogationList
     */
    private final InterrogationList interrogationList;
    
    /**
     * Default constructor.
     */
    public VsaSystem() {
        // get wav folder path
        String wavPath = Config.wavPath;

        interrogationList = new InterrogationList(wavPath);

//        PlotUtil.drawSpectrum(signal, sampleRate);
//        PlotUtil.drawWaveForm(signal, sampleRate);
//        PlotUtil.drawCepstrum(signal, sampleRate, hzStart, hzWindowSize);
        // apply window function
//        SoundWindowUtil.applyWindow(signal, SoundWindowUtil.hammingWindow(signal.length));
//        audioReader.drawWaveForm();
//        audioReader.drawSpectrum();
        // calculate F0 for whole signal
//        double F0_all = calculateFundamentalFrequencyWithCepstrum(signal);
//        this.drawFundamentalFrequencyVector();
    }

    /**
     * getInterrogationList
     * 
     * @return the interrogationList
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
        for (int i = 0; i < interrogationList.numInterrogations(); i++) {
            // get interrogation
            Interrogation interrogation = interrogationList.getInterrogation(i);

            // process files
            VoiceStressInstanceList voiceStressInstanceList = interrogation.processFiles();

            // convert to weka instances
            Instances instances = voiceStressInstanceList.toWekaInstances();

            // generate output path
            String outputPath = Config.outputPath + FileUtil.generateArffFileName(voiceStressInstanceList.getName());

            // write instances to arff file
            voiceStressInstanceList.toArffFile(outputPath);
        }
    }
}
