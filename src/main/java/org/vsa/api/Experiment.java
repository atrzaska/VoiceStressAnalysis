package org.vsa.api;

import java.io.IOException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.vsa.audio.AudioException;
import org.vsa.util.MathUtil;
import org.vsa.weka.VoiceStressInstance;
import org.vsa.weka.VoiceStressInstanceList;

/**
 * Experiment class.
 */
public class Experiment {
    /**
     * interrogation
     */
    private final Interrogation interrogation;

    /**
     * stressedMean
     */
    private final double stressedMean;

    /**
     * stressedMedian
     */
    private final double stressedMedian;

    /**
     * stressedMin
     */
    private final double stressedMin;

    /**
     * stressedMax
     */
    private final double stressedMax;

    /**
     * stressedStd
     */
    private final double stressedStd;

    /**
     * stressedLowQuantile
     */
    private final double stressedLowQuantile;

    /**
     * stressedHighQuantile
     */
    private final double stressedHighQuantile;

    /**
     * stressedIqr
     */
    private final double stressedIqr;

    /**
     * stressedKurtosis
     */
    private final double stressedKurtosis;

    /**
     * stressedRange
     */
    private final double stressedRange;

    /**
     * unstressedMean
     */
    private final double unstressedMean;

    /**
     * unstressedMedian
     */
    private final double unstressedMedian;

    /**
     * unstressedMin
     */
    private final double unstressedMin;

    /**
     * unstressedMax
     */
    private final double unstressedMax;

    /**
     * unstressedStd
     */
    private final double unstressedStd;

    /**
     * unstressedLowQuantile
     */
    private final double unstressedLowQuantile;

    /**
     * unstressedHighQuantile
     */
    private final double unstressedHighQuantile;

    /**
     * unstressedIqr
     */
    private final double unstressedIqr;

    /**
     * unstressedKurtosis
     */
    private final double unstressedKurtosis;

    /**
     * unstressedRange
     */
    private final double unstressedRange;

    /**
     * numStressedInstances
     */
    private final int numStressedInstances;

    /**
     * numUntressedInstances
     */
    private final int numUntressedInstances;

    /**
     * Experiment constructor.
     * @param interrogation
     * @throws java.io.IOException
     * @throws javax.sound.sampled.UnsupportedAudioFileException
     * @throws org.vsa.audio.AudioException
     */
    public Experiment(Interrogation interrogation) throws IOException, UnsupportedAudioFileException, AudioException {
        this.interrogation = interrogation;

        // get instances
        VoiceStressInstanceList instanceList = interrogation.getInstanceList();

        // get stressed instances
        List<VoiceStressInstance> stressedInstances = instanceList.getStressedInstances();

        // get unstressed instances
        List<VoiceStressInstance> unstressedInstances = instanceList.getUnstressedInstances();

        // save number of stressed instances
        numStressedInstances = stressedInstances.size();

        // save number of stressed instances
        numUntressedInstances = unstressedInstances.size();

        // stressed means
        double[] stressedMeans = new double[stressedInstances.size()];

        // unstressed means
        double[] unstressedMeans = new double[unstressedInstances.size()];

        // copy stressed means
        for(int i = 0; i < stressedInstances.size(); i++) {
            stressedMeans[i] = stressedInstances.get(i).getMean();
        }

        // copy unstressed means
        for(int i = 0; i < unstressedInstances.size(); i++) {
            unstressedMeans[i] = unstressedInstances.get(i).getMean();
        }

        // calculate stressed values
        stressedMean = MathUtil.mean(stressedMeans);
        stressedMedian = MathUtil.median(stressedMeans);
        stressedMin = MathUtil.min(stressedMeans);
        stressedMax = MathUtil.max(stressedMeans);
        stressedStd = MathUtil.std(stressedMeans);
        stressedLowQuantile = MathUtil.lowQuantile(stressedMeans);
        stressedHighQuantile = MathUtil.highQuantile(stressedMeans);
        stressedIqr = MathUtil.iqr(stressedMeans);
        stressedKurtosis = MathUtil.kurtosis(stressedMeans);
        stressedRange = MathUtil.range(stressedMeans);

        // calculate unstressed values
        unstressedMean = MathUtil.mean(unstressedMeans);
        unstressedMedian = MathUtil.median(unstressedMeans);
        unstressedMin = MathUtil.min(unstressedMeans);
        unstressedMax = MathUtil.max(unstressedMeans);
        unstressedStd = MathUtil.std(unstressedMeans);
        unstressedLowQuantile = MathUtil.lowQuantile(unstressedMeans);
        unstressedHighQuantile = MathUtil.highQuantile(unstressedMeans);
        unstressedIqr = MathUtil.iqr(unstressedMeans);
        unstressedKurtosis = MathUtil.kurtosis(unstressedMeans);
        unstressedRange = MathUtil.range(unstressedMeans);
    }

    /**
     * getStressedSummaryString
     *
     * @return
     */
    public String getStressedSummaryString() {
        return this.getInterrogation().toSummaryString() + "\n" +
            "Stressed instances count: " + numStressedInstances + "\n" +
            "\n--- Stressed instances evaluation --\n" +
            "Mean: " + String.format("%.2f", getStressedMean()) + " \n" +
            "Median: " + String.format("%.2f", getStressedMedian()) + " \n" +
            "Min: " + String.format("%.2f", getStressedMin()) + " \n" +
            "Max: " + String.format("%.2f", getStressedMax()) + " \n" +
            "Std: " + String.format("%.2f", getStressedStd()) + " \n" +
            "LowQuanTile: " + String.format("%.2f", getStressedLowQuantile()) + " \n" +
            "High Quantile: " + String.format("%.2f", getStressedHighQuantile()) + " \n" +
            "Inter quantile range: " + String.format("%.2f", getStressedIqr()) + " \n" +
            "Kurtosis: " + String.format("%.2f", getStressedKurtosis()) + " \n" +
            "Range: " + String.format("%.2f", getStressedRange());
    }

    /**
     * getUnstressedSummaryString
     *
     * @return
     */
    public String getUnstressedSummaryString() {
        return this.getInterrogation().toSummaryString() + "\n" +
            "Unstressed instances count: " + numUntressedInstances + "\n" +
            "\n--- Unstressed instances evaluation --\n" +
            "Mean: " + String.format("%.2f", getUnstressedMean()) + " \n" +
            "Median: " + String.format("%.2f", getUnstressedMedian()) + " \n" +
            "Min: " + String.format("%.2f", getUnstressedMin()) + " \n" +
            "Max: " + String.format("%.2f", getUnstressedMax()) + " \n" +
            "Std: " + String.format("%.2f", getUnstressedStd()) + " \n" +
            "LowQuanTile: " + String.format("%.2f", getUnstressedLowQuantile()) + " \n" +
            "High Quantile: " + String.format("%.2f", getUnstressedHighQuantile()) + " \n" +
            "Inter quantile range: " + String.format("%.2f", getUnstressedIqr()) + " \n" +
            "Kurtosis: " + String.format("%.2f", getUnstressedKurtosis()) + " \n" +
            "Range: " + String.format("%.2f", getUnstressedRange()) + " \n";
    }
    /**
     * toSummaryString
     *
     * @return
     */
    public String toSummaryString() {
        return this.getInterrogation().toSummaryString() + "\n" +
            "Stressed instances count: " + numStressedInstances + "\n" +
            "Unstressed instances count: " + numUntressedInstances + "\n" +

            "\n--- Stressed instances evaluation --\n" +
            "Mean: " + String.format("%.2f", getStressedMean()) + " \n" +
            "Median: " + String.format("%.2f", getStressedMedian()) + " \n" +
            "Min: " + String.format("%.2f", getStressedMin()) + " \n" +
            "Max: " + String.format("%.2f", getStressedMax()) + " \n" +
            "Std: " + String.format("%.2f", getStressedStd()) + " \n" +
            "LowQuanTile: " + String.format("%.2f", getStressedLowQuantile()) + " \n" +
            "High Quantile: " + String.format("%.2f", getStressedHighQuantile()) + " \n" +
            "Inter quantile range: " + String.format("%.2f", getStressedIqr()) + " \n" +
            "Kurtosis: " + String.format("%.2f", getStressedKurtosis()) + " \n" +
            "Range: " + String.format("%.2f", getStressedRange()) + " \n" +

            "\n--- Unstressed instances evaluation --\n" +
            "Mean: " + String.format("%.2f", getUnstressedMean()) + " \n" +
            "Median: " + String.format("%.2f", getUnstressedMedian()) + " \n" +
            "Min: " + String.format("%.2f", getUnstressedMin()) + " \n" +
            "Max: " + String.format("%.2f", getUnstressedMax()) + " \n" +
            "Std: " + String.format("%.2f", getUnstressedStd()) + " \n" +
            "LowQuanTile: " + String.format("%.2f", getUnstressedLowQuantile()) + " \n" +
            "High Quantile: " + String.format("%.2f", getUnstressedHighQuantile()) + " \n" +
            "Inter quantile range: " + String.format("%.2f", getUnstressedIqr()) + " \n" +
            "Kurtosis: " + String.format("%.2f", getUnstressedKurtosis()) + " \n" +
            "Range: " + String.format("%.2f", getUnstressedRange()) + " \n";
    }

    /**
     * @return the interrogation
     */
    public Interrogation getInterrogation() {
        return interrogation;
    }

    /**
     * @return the stressedMean
     */
    public double getStressedMean() {
        return stressedMean;
    }

    /**
     * @return the stressedMedian
     */
    public double getStressedMedian() {
        return stressedMedian;
    }

    /**
     * @return the stressedMin
     */
    public double getStressedMin() {
        return stressedMin;
    }

    /**
     * @return the stressedMax
     */
    public double getStressedMax() {
        return stressedMax;
    }

    /**
     * @return the stressedStd
     */
    public double getStressedStd() {
        return stressedStd;
    }

    /**
     * @return the stressedLowQuantile
     */
    public double getStressedLowQuantile() {
        return stressedLowQuantile;
    }

    /**
     * @return the stressedHighQuantile
     */
    public double getStressedHighQuantile() {
        return stressedHighQuantile;
    }


    /**
     * @return the stressedIqr
     */
    public double getStressedIqr() {
        return stressedIqr;
    }

    /**
     * @return the stressedKurtosis
     */
    public double getStressedKurtosis() {
        return stressedKurtosis;
    }

    /**
     * @return the stressedRange
     */
    public double getStressedRange() {
        return stressedRange;
    }

    /**
     * @return the unstressedMean
     */
    public double getUnstressedMean() {
        return unstressedMean;
    }

    /**
     * @return the unstressedMedian
     */
    public double getUnstressedMedian() {
        return unstressedMedian;
    }

    /**
     * @return the unstressedMin
     */
    public double getUnstressedMin() {
        return unstressedMin;
    }

    /**
     * @return the unstressedMax
     */
    public double getUnstressedMax() {
        return unstressedMax;
    }

    /**
     * @return the unstressedStd
     */
    public double getUnstressedStd() {
        return unstressedStd;
    }

    /**
     * @return the unstressedLowQuantile
     */
    public double getUnstressedLowQuantile() {
        return unstressedLowQuantile;
    }

    /**
     * @return the unstressedHighQuantile
     */
    public double getUnstressedHighQuantile() {
        return unstressedHighQuantile;
    }

    /**
     * @return the unstressedIqr
     */
    public double getUnstressedIqr() {
        return unstressedIqr;
    }

    /**
     * @return the unstressedKurtosis
     */
    public double getUnstressedKurtosis() {
        return unstressedKurtosis;
    }

    /**
     * @return the unstressedRange
     */
    public double getUnstressedRange() {
        return unstressedRange;
    }
}
