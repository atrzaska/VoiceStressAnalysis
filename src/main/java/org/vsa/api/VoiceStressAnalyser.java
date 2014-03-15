package org.vsa.api;

import it.sardegnaricerche.voiceid.utils.wav.WavFile;
import it.sardegnaricerche.voiceid.utils.wav.WavFileException;
import java.io.File;
import java.io.IOException;
import org.vsa.Config;
import org.vsa.api.windows.HammingWindow;
import org.vsa.api.windows.SoundWindow;

/**
 * VoiceStressAnalyser
 */
public class VoiceStressAnalyser {
    
    /**
     * wavFile
     */
    private final WavFile wavFile;
    
    /**
     * sampleRate
     */
    private final int sampleRate;
    
    /**
     * sampleTime (seconds)
     */
    private final int sampleTime;
    
    /**
     * numFrames
     */
    private final int numFrames;

    /**
     * framesPerWindow
     */
    private final int framesPerWindow;

    /**
     * numWindows
     */
    private final int numWindows;

    /**
     * framesPerStep
     */
    private final int framesPerStep;
    
    /**
     * numSteps
     */
    private final int numSteps;

    /**
     * soundWindow
     */
    private final SoundWindow soundWindow;
    
    /**
     * hzStart
     */
    private final int hzStart;
    
    /**
     * hzEnd
     */
    private final int hzEnd;
    
    /**
     * Default constructor
     * @param file
     * @throws IOException
     * @throws WavFileException 
     */
    public VoiceStressAnalyser(String file) throws IOException, WavFileException {
        // read wav file
        this.wavFile = WavFile.openWavFile(new File(file));

        // set sample rate
        this.sampleRate = (int)wavFile.getSampleRate();
        
        // set sample time
        this.sampleTime = 1 / sampleRate;

        // read number of frames
        this.numFrames = (int)wavFile.getNumFrames();

        // calculate number of frames per window
        this.framesPerWindow = (int)Math.floor(Config.windowSize * sampleRate);

        // calculate number of windows
        this.numWindows = (int)Math.floor(numFrames / framesPerWindow);

        // calculate number of frames per step
        this.framesPerStep = (int)Math.floor(Config.stepSize * sampleRate);

        // calculate number of steps
        this.numSteps = (int)Math.floor(numFrames / framesPerStep);

        // create sound window
        this.soundWindow = new HammingWindow(framesPerWindow);

        // calculate start duration
        this.hzStart = (int)Math.floor(sampleRate / Config.startFrequency);

        // calculate end duration
        this.hzEnd = (int)Math.floor(sampleRate / Config.endFrequency);
    }

    public double calculateFundamentalFrequencyWithCepstrum(double[] signal) {
        
        return 0;
    }
}
