package org.vsa;

/**
 * Config
 */
public class Config {

    /**
     * allowedSampleRate - UNUSED
     *
     * 8000 Hz
     */
    public static final int allowedSampleRate = 8000;

    /**
     * allowedNumChannels
     */
    public static final int allowedNumChannels = 1;

    /**
     * windowSize
     *
     * 40ms
     */
    public static final double windowSize = 0.04;

    /**
     * stepSize
     *
     * 10ms
     */
    public static final double stepSize = 0.01;

    /**
     * startFrequency
     *
     * 330 Hz
     */
    public static final int startFrequency = 330;

    /**
     * endFrequency
     *
     * 50 Hz
     */
    public static final int endFrequency = 50;

    /**
     * threshold
     */
    public static final double cepstrumThreshold = 10.0;

    /**
     * wavPath
     */
    public static final String wavPath = "wav/";

    /**
     * outputPath
     */
    public static final String outputPath = "output/";

    /**
     * silenceThreshold
     */
    public static final double silenceThreshold = 0.05;

    /**
     * testFile
     */
    public static final String testFile = "wav/andrzej/unstressed (1).wav";

    /**
     * plotNumValues
     */
    public static final int plotNumValues = 2000;

    /**
     * plotXMax
     */
    public static final int plotXMax = 500;
}
