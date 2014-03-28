package org.vsa.api;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.vsa.Config;
import org.vsa.api.audio.AudioReader;
import org.vsa.util.CepstrumUtil;
import org.vsa.util.MathUtil;
import org.vsa.util.WindowUtil;

/**
 * VoiceStressAnalyser
 */
public class VoiceStressAnalyser {

    /**
     * sampleRate
     */
    private final int sampleRate;

    /**
     * sampleTime (seconds)
     */
    private final double sampleTime;

    /**
     * numSamples
     */
    private final int numSamples;

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
    private final double[] soundWindow;

    /**
     * hzStart
     */
    private final int hzStart;

    /**
     * hzEnd
     */
    private final int hzEnd;

    /**
     * hzWindowSize
     */
    private final int hzWindowSize;

    /**
     * signal
     */
    private final double[] signal;

    /**
     * Default constructor
     * @param file
     * @throws IOException
     * @throws WavFileException
     */
    public VoiceStressAnalyser(String file) throws IOException, UnsupportedAudioFileException {
        // read wav file
        AudioReader audioReader = new AudioReader(new File(file));

        // set sample rate
        this.sampleRate = audioReader.getSampleRate();

        // set sample time
        this.sampleTime = 1.0 / sampleRate;

        // read number of frames
        this.numSamples = audioReader.numSamples();

        // calculate number of frames per window
        this.framesPerWindow = (int)Math.floor(Config.windowSize * sampleRate);

        // calculate number of windows
        this.numWindows = (int)Math.floor(numSamples / framesPerWindow);

        // calculate number of frames per step
        this.framesPerStep = (int)Math.floor(Config.stepSize * sampleRate);

        // calculate number of steps
        this.numSteps = (int)Math.floor(numSamples / framesPerStep);

        // create sound window
        this.soundWindow = WindowUtil.hammingWindow(framesPerWindow);

        // calculate start duration
        this.hzStart = (int)Math.floor(sampleRate / Config.startFrequency);

        // calculate end duration
        this.hzEnd = (int)Math.floor(sampleRate / Config.endFrequency);

        // calculate frequency window size
        this.hzWindowSize = hzEnd - hzStart + 1;

        // read signal
        signal = audioReader.getChannelSamples(0);

        // apply window function
        WindowUtil.applyWindow(signal, WindowUtil.hammingWindow(signal.length));

//        audioReader.drawWaveForm();
//        audioReader.drawSpectrum();

        // calculate F0 for whole signal
        double F0 = calculateFundamentalFrequencyWithCepstrum(signal);

        for (int i = 0; i < numSteps; i++) {

        }
    }

    public double calculateFundamentalFrequencyWithCepstrum(double[] signal) {
        double[] powCeps = CepstrumUtil.powerCepstrum(signal);

        double[] voiceWindow = new double[this.hzWindowSize];

        System.arraycopy(powCeps, this.hzStart - 1, voiceWindow, 0, this.hzWindowSize);
        double maxVal  = MathUtil.max(voiceWindow);
        int maxIndex = MathUtil.maxIndex(voiceWindow);

        double F0 = 1 / (sampleTime * ((maxIndex + hzStart) - 1));

        return F0;
    }
}
