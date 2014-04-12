package org.vsa.weka;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.vsa.api.VoiceStressAnalyser;
import org.vsa.audio.AudioException;
import org.vsa.util.MathUtil;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

/**
 * VoiceStressInstance class.
 */
public class VoiceStressInstance {
    
    /**
     * name
     */
    private String name;

    /**
     * path
     */
    private String path;

    /**
     * totalF0
     */
    private double totalF0;
    
    /**
     * mean
     */
    private double mean;
    
    /**
     * min
     */
    private double min;
    
    /**
     * max
     */
    private double max;
    
    /**
     * std
     */
    private double std;
    
    /**
     * lowQuantil
     */
    private double lowQuantile;
    
    /**
     * highQuantil
     */
    private double highQuantile;
    
    /**
     * iqr
     */
    private double iqr;
    
    /**
     * kurtosis
     */
    private double kurtosis;

    /**
     * stressed
     */
    private boolean stressed;
    
    /**
     * median
     */
    private double median;
    
    /**
     * power
     */
    private double power;
    
    /**
     * range
     */
    private double range;

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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getPath
     * 
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * setPath
     * 
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * getTotalF0
     * 
     * @return the totalF0
     */
    public double getTotalF0() {
        return totalF0;
    }

    /**
     * setTotalF0
     * 
     * @param totalF0 the totalF0 to set
     */
    public void setTotalF0(double totalF0) {
        this.totalF0 = totalF0;
    }

    /**
     * getMean
     * 
     * @return the mean
     */
    public double getMean() {
        return mean;
    }

    /**
     * setMean
     * 
     * @param mean the mean to set
     */
    public void setMean(double mean) {
        this.mean = mean;
    }

    /**
     * getMin
     * 
     * @return the min
     */
    public double getMin() {
        return min;
    }

    /**
     * setMin
     * 
     * @param min the min to set
     */
    public void setMin(double min) {
        this.min = min;
    }

    /**
     * getMax
     * 
     * @return the max
     */
    public double getMax() {
        return max;
    }

    /**
     * setMax
     * 
     * @param max the max to set
     */
    public void setMax(double max) {
        this.max = max;
    }

    /**
     * getStd
     * 
     * @return the std
     */
    public double getStd() {
        return std;
    }

    /**
     * setStd
     * 
     * @param std the std to set
     */
    public void setStd(double std) {
        this.std = std;
    }

    /**
     * getLowQuantile
     * 
     * @return the lowQuantile
     */
    public double getLowQuantile() {
        return lowQuantile;
    }

    /**
     * setLowQuantile
     * 
     * @param lowQuantile the lowQuantile to set
     */
    public void setLowQuantile(double lowQuantile) {
        this.lowQuantile = lowQuantile;
    }

    /**
     * getHighQuantile
     * 
     * @return the highQuantile
     */
    public double getHighQuantile() {
        return highQuantile;
    }

    /**
     * setHighQuantile
     * 
     * @param highQuantile the highQuantile to set
     */
    public void setHighQuantile(double highQuantile) {
        this.highQuantile = highQuantile;
    }

    /**
     * getIqr
     * 
     * @return the iqr
     */
    public double getIqr() {
        return iqr;
    }

    /**
     * setIqr
     * 
     * @param iqr the iqr to set
     */
    public void setIqr(double iqr) {
        this.iqr = iqr;
    }

    /**
     * getKurtosis
     * 
     * @return the kurtosis
     */
    public double getKurtosis() {
        return kurtosis;
    }

    /**
     * setKurtosis
     * 
     * @param kurtosis the kurtosis to set
     */
    public void setKurtosis(double kurtosis) {
        this.kurtosis = kurtosis;
    }

    /**
     * isStressed
     * 
     * @return the stressed
     */
    public boolean isStressed() {
        return stressed;
    }

    /**
     * setStressed
     * 
     * @param stressed the stressed to set
     */
    public void setStressed(boolean stressed) {
        this.stressed = stressed;
    }

    /**
     * getMedian
     * 
     * @return the median
     */
    public double getMedian() {
        return median;
    }

    /**
     * setMedian
     * 
     * @param median the median to set
     */
    public void setMedian(double median) {
        this.median = median;
    }

    /**
     * @return the power
     */
    public double getPower() {
        return power;
    }

    /**
     * setPower
     * 
     * @param power the power to set
     */
    public void setPower(double power) {
        this.power = power;
    }

    /**
     * getRange
     * 
     * @return the range
     */
    public double getRange() {
        return range;
    }

    /**
     * setRange
     * 
     * @param range the range to set
     */
    public void setRange(double range) {
        this.range = range;
    }

    /**
     * toWekaInstance
     * 
     * @param dataset
     * @return 
     */
    public Instance toWekaInstance(Instances dataset) {
        // create instance
        DenseInstance instance = new DenseInstance(13);
        instance.setDataset(dataset);

        // set values
        instance.setValue(0, this.getMean());
        instance.setValue(1, this.getMedian());
        instance.setValue(2, this.getMin());
        instance.setValue(3, this.getMax());
        instance.setValue(4, this.getStd());
        instance.setValue(5, this.getLowQuantile());
        instance.setValue(6, this.getHighQuantile());
        instance.setValue(7, this.getIqr());
        instance.setValue(8, this.getKurtosis());
        instance.setValue(9, this.getRange());
        instance.setValue(10, this.getPower());
        instance.setValue(11, this.getTotalF0());
        instance.setValue(12, stressed ? "stressed" : "unstressed");

        // return instance
        return instance;
    }
    
    /**
     * getFundamentalFrequencyVector
     * 
     * @return 
     * @throws java.io.IOException 
     * @throws javax.sound.sampled.UnsupportedAudioFileException 
     * @throws org.vsa.audio.AudioException 
     */
    public double[] getFundamentalFrequencyVector() throws IOException, UnsupportedAudioFileException, AudioException {
        // create voice stress analyser
        VoiceStressAnalyser voiceStressAnalyser = new VoiceStressAnalyser(path);
        
        // get f0 vector
        double[] f0vector = voiceStressAnalyser.getFundamentalFrequencyVector();

        // return
        return f0vector;
    }
    /**
     * fromSoundFile
     * 
     * @param path
     * @return 
     * @throws java.io.IOException 
     * @throws javax.sound.sampled.UnsupportedAudioFileException 
     * @throws org.vsa.audio.AudioException 
     */
    public static VoiceStressInstance fromSoundFile(String path) throws IOException, UnsupportedAudioFileException, AudioException {
        // create voice stress analyser
        VoiceStressAnalyser voiceStressAnalyser = new VoiceStressAnalyser(path);
        
        // get f0 vector
        double[] f0vector = voiceStressAnalyser.getFundamentalFrequencyVector();

        // create file
        File file = new File(path);
        
        // get instance name
        String name = file.getName();

        // check if stressed
        boolean stressed = !(name.contains("unstressed"));

        // get f0 total
        double f0total = voiceStressAnalyser.getTotalFundamentalFrequency();
        
        // set parameters
        VoiceStressInstance instance = new VoiceStressInstance();
        instance.setMean(MathUtil.mean(f0vector));
        instance.setMedian(MathUtil.median(f0vector));
        instance.setMin(MathUtil.min(f0vector));
        instance.setMax(MathUtil.max(f0vector));
        instance.setStd(MathUtil.std(f0vector));
        instance.setLowQuantile(MathUtil.lowQuantile(f0vector));
        instance.setHighQuantile(MathUtil.highQuantile(f0vector));
        instance.setIqr(MathUtil.iqr(f0vector));
        instance.setKurtosis(MathUtil.kurtosis(f0vector));
        instance.setRange(MathUtil.range(f0vector));
        instance.setPower(MathUtil.absMean(voiceStressAnalyser.getSignal()));
        instance.setTotalF0(f0total);
        instance.setStressed(stressed);
        instance.setName(name);
        instance.setPath(path);

        // return instance
        return instance;
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

    public String toSummaryString() {
        return "Instance name: " + this.getName() + " \n" +
                "Path: " + this.getPath() + " \n" +
                "Mean: " + String.format("%.2f", this.getMean()) + " \n" +
                "Median: " + String.format("%.2f", this.getMedian()) + " \n" +
                "Min: " + String.format("%.2f", this.getMin()) + " \n" +
                "Max: " + String.format("%.2f", this.getMax()) + " \n" +
                "Std: " + String.format("%.2f", this.getStd()) + " \n" +
                "LowQuanTile: " + String.format("%.2f", this.getLowQuantile()) + " \n" +
                "High Quantile: " + String.format("%.2f", this.getHighQuantile()) + " \n" +
                "Inter quantile range: " + String.format("%.2f", this.getIqr()) + " \n" +
                "Kurtosis: " + String.format("%.2f", this.getKurtosis()) + " \n" +
                "Range: " + String.format("%.2f", this.getRange()) + " \n" +
                "Power: " + String.format("%.2f", this.getPower()) + " \n" +
                "Total F0: " + String.format("%.2f", this.getTotalF0()) + " \n" +
                "Stressed: " + this.isStressed();
    }
}
