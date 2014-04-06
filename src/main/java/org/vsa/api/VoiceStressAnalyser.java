package org.vsa.api;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;
import org.vsa.Config;
import org.vsa.audio.AudioProcessor;
import org.vsa.audio.AudioReader;
import org.vsa.util.CepstrumUtil;
import org.vsa.util.MathUtil;
import org.vsa.util.PlotUtil;
import org.vsa.util.SoundWindowUtil;

/**
 * VoiceStressAnalyser
 */
public final class VoiceStressAnalyser {

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
     * 
     * @param file
     * @throws IOException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
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
//        this.numSteps = (int)Math.floor(numSamples / framesPerStep);
        this.numSteps = this.calulateNumSteps();

        // create sound window
        this.soundWindow = SoundWindowUtil.hammingWindow(framesPerWindow);

        // calculate start duration
        this.hzStart = (int)Math.floor(sampleRate / Config.startFrequency);

        // calculate end duration
        this.hzEnd = (int)Math.floor(sampleRate / Config.endFrequency);

        // calculate frequency window size
        this.hzWindowSize = hzEnd - hzStart + 1;

        // read signal
        double[] mSignal = audioReader.getChannelSamples(0);

        AudioProcessor audioProcessor = new AudioProcessor(mSignal);
        audioProcessor.normalizeSignal();
        audioProcessor.removeStartSilence();
        audioProcessor.removeEndSilence();
        this.signal = audioProcessor.getProcessedSignal();

//        PlotUtil.drawSpectrum(signal, sampleRate);
        PlotUtil.drawWaveForm(signal, sampleRate);
//        PlotUtil.drawCepstrum(signal, sampleRate, hzStart, hzWindowSize);

        // apply window function
//        SoundWindowUtil.applyWindow(signal, SoundWindowUtil.hammingWindow(signal.length));

//        audioReader.drawWaveForm();
//        audioReader.drawSpectrum();

        // calculate F0 for whole signal
//        double F0_all = calculateFundamentalFrequencyWithCepstrum(signal);

        this.drawFundamentalFrequencyVector();
    }

    public double[] createIndexVector(int size) {
        double[] indexes = new double[size];

        for (int i = 0; i < size; i++) {
            indexes[i] = i;
        }

        return indexes;
    }

    public int calulateNumSteps() {
        // calculate number of steps per window
        int stepsPerWindow = (int)Math.ceil(framesPerWindow / framesPerStep);

        // calculate number of steps
        int totalNumSteps = (int)Math.floor(numSamples / framesPerStep);

        //return value
        return totalNumSteps - stepsPerWindow;
    }

    /**
     * getFundamentalFrequencyVector
     *
     * @return
     */
    public double[] getFundamentalFrequencyVector() {
        // create F0 vector
        double[] fundamentalFrequencyVector = new double[numSteps];

        int offset = 0;
        int numSteps = 0;

        // get F0 for each window
        for (int i = 0; i < numSteps - 4; i++) {
            int start = i * framesPerStep;
            int end = start + framesPerWindow;
            int size = framesPerWindow;

            if(end > this.numSamples) {
                continue;
            }

            // create window
            double[] window = new double[size];

            // copy signal part to window
            System.arraycopy(signal, start, window, 0, size);

            // apply window function
            SoundWindowUtil.applyWindow(window, SoundWindowUtil.hammingWindow(window.length));

            // calculate F0
            double F0 = calculateFundamentalFrequencyWithCepstrum(window);

            // save F0 in arr
            fundamentalFrequencyVector[i] = F0;
//            if(F0 == 0) {
//                fundamentalFrequencyVector[i] = fundamentalFrequencyVector[i-1];
//            } else {
//                fundamentalFrequencyVector[i] = F0;
//            }
        }

        // return value
        return fundamentalFrequencyVector;
    }

    /**
     * drawFundamentalFrequencyVector
     */
    public void drawFundamentalFrequencyVector() {
        double[] y = this.getFundamentalFrequencyVector();
        double[] x = this.createIndexVector(y.length);

        // plot
        Plot2DPanel plot = new Plot2DPanel();
        plot.setLegendOrientation("EAST");
        plot.addLegend("Ton Podstawowy");
        plot.addLinePlot("Ton Podstawowy", x, y);
        plot.setAxisLabel(0, "Nr okna");
        plot.setAxisLabel(1, "Częstotliwość [Hz]");

        // frame
        JFrame frame = new JFrame("Ton Podstawowy");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
        double[] voiceWindow = new double[this.hzWindowSize];

        // copy data
        System.arraycopy(powCeps, this.hzStart - 1, voiceWindow, 0, this.hzWindowSize);

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
        double F0 = 1 / (sampleTime * ((maxIndex + hzStart) - 1));

        // return value
        return F0;
    }
}
