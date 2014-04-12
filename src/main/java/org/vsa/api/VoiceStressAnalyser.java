package org.vsa.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.vsa.Config;
import org.vsa.audio.AudioException;
import org.vsa.audio.AudioProcessor;
import org.vsa.audio.AudioReader;
import org.vsa.util.ArrayUtil;
import org.vsa.util.CepstrumUtil;
import org.vsa.util.MathUtil;
import org.vsa.util.SoundWindowUtil;

/**
 * VoiceStressAnalyser
 */
public final class VoiceStressAnalyser {
    /**
     * audioReader
     */
    private final AudioReader audioReader;

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
     * getSignal
     * 
     * @return the signal
     */
    public double[] getSignal() {
        return signal;
    }

    /**
     * getAudioReader
     * 
     * @return the audioReader
     */
    public AudioReader getAudioReader() {
        return audioReader;
    }

    /**
     * getSampleRate
     * 
     * @return the sampleRate
     */
    public int getSampleRate() {
        return sampleRate;
    }

    /**
     * getSampleTime
     * 
     * @return the sampleTime
     */
    public double getSampleTime() {
        return sampleTime;
    }

    /**
     * getNumSamples
     * 
     * @return the numSamples
     */
    public int getNumSamples() {
        return numSamples;
    }

    /**
     * getFramesPerWindow
     * 
     * @return the framesPerWindow
     */
    public int getFramesPerWindow() {
        return framesPerWindow;
    }

    /**
     * getNumWindows
     * 
     * @return the numWindows
     */
    public int getNumWindows() {
        return numWindows;
    }

    /**
     * getFramesPerStep
     * 
     * @return the framesPerStep
     */
    public int getFramesPerStep() {
        return framesPerStep;
    }

    /**
     * getNumSteps
     * 
     * @return the numSteps
     */
    public int getNumSteps() {
        return numSteps;
    }

    /**
     * getHzStart
     * 
     * @return the hzStart
     */
    public int getHzStart() {
        return hzStart;
    }

    /**
     * getHzEnd
     * 
     * @return the hzEnd
     */
    public int getHzEnd() {
        return hzEnd;
    }

    /**
     * getHzWindowSize
     * 
     * @return the hzWindowSize
     */
    public int getHzWindowSize() {
        return hzWindowSize;
    }

    /**
     * Default constructor
     * 
     * @param file
     * @throws IOException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     * @throws AudioException
     */
    public VoiceStressAnalyser(String file) throws IOException, UnsupportedAudioFileException, AudioException {
        // read wav file
        audioReader = new AudioReader(new File(file));

        // check number of channels
        if(audioReader.numChannels() != Config.allowedNumChannels) {
            throw new AudioException("Mono sound only.");
        }
        
        // set sample rate
        this.sampleRate = audioReader.getSampleRate();

        // set sample time
        this.sampleTime = 1.0 / sampleRate;

        // read number of frames
        this.numSamples = audioReader.numSamplesPerChannel();

        // calculate number of frames per window
        this.framesPerWindow = (int)Math.floor(Config.windowSize * sampleRate);

        // calculate number of windows
        this.numWindows = (int)Math.floor(numSamples / framesPerWindow);

        // calculate number of frames per step
        this.framesPerStep = (int)Math.floor(Config.stepSize * sampleRate);

        // calculate number of steps
        this.numSteps = (int)Math.floor(numSamples / framesPerStep);

        // calculate start duration
        this.hzStart = (int)Math.floor(sampleRate / Config.startFrequency);

        // calculate end duration
        this.hzEnd = (int)Math.floor(sampleRate / Config.endFrequency);

        // calculate frequency window size
        this.hzWindowSize = hzEnd - hzStart + 1;

        // read signal
        double[] mSignal = audioReader.getChannelSamples(0);

        // process sound
        AudioProcessor audioProcessor = new AudioProcessor(mSignal);
        audioProcessor.normalizeSignal();
        audioProcessor.removeStartSilence();
        audioProcessor.removeEndSilence();
//        audioProcessor.reduceNoise();
        this.signal = audioProcessor.getProcessedSignal();
    }

    /**
     * getFundamentalFrequencyVector
     *
     * @return
     */
    public double[] getFundamentalFrequencyVector() {
        // create F0 array
        List<Double> output = new ArrayList<>();

        // get F0 for each window
        for (int i = 0; i < getNumSteps(); i++) {
            int start = i * getFramesPerStep();
            int end = start + getFramesPerWindow();
            int size = getFramesPerWindow();

            if(end >= getSignal().length) {
                break;
            }

            // create window
            double[] window = new double[size];

            // copy signal part to window
            System.arraycopy(getSignal(), start, window, 0, size);

            // apply window function
            SoundWindowUtil.applyHammingWindow(window);

            // calculate F0
            double F0 = calculateFundamentalFrequencyWithCepstrum(window);

            // save F0 in arr
            if(F0 > 0) {
                output.add(F0);
            }
        }

        // return value
        return ArrayUtil.toPrimitiveArray(output);
    }

    /**
     * calculateFundamentalFrequencyWithCepstrum
     *
     * @param signal Signal
     * @return F0
     */
    public double calculateFundamentalFrequencyWithCepstrum(double[] signal) {
        // calculate power cepstrum
        double[] powCeps = CepstrumUtil.powerCepstrum(signal);

        // create voiceWindow array
        double[] voiceWindow = new double[this.getHzWindowSize()];

        // copy data
        System.arraycopy(powCeps, this.getHzStart() - 1, voiceWindow, 0, this.getHzWindowSize());

        // find max value
        double maxVal  = MathUtil.max(voiceWindow);

        // find average value
        double avgVal = MathUtil.mean(voiceWindow);

        // calculate max/avg ratio
        double maxAvgRatio = maxVal / avgVal;

        // check threshold
        if(maxAvgRatio < Config.cepstrumThreshold) {
            return 0;
        }

        // find max index
        int maxIndex = MathUtil.maxIndex(voiceWindow);

        // calculate F0
        double F0 = 1 / (getSampleTime() * ((maxIndex + getHzStart()) - 1));

        // return value
        return F0;
    }

    /**
     * getTotalFundamentalFrequency
     * 
     * @return 
     */
    public double getTotalFundamentalFrequency() {
        // create temp array
        double[] tmp = new double[getSignal().length];
        
        // copy signal
        System.arraycopy(getSignal(), 0, tmp, 0, getSignal().length);

        // apply hamming window
        SoundWindowUtil.applyHammingWindow(tmp);

        // get f0
        double f0 = this.calculateFundamentalFrequencyWithCepstrum(tmp);

        // return value
        return f0;
    }
}
