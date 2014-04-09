package org.vsa.weka;

import java.io.IOException;
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
     * toWekaInstance
     * 
     * @param dataset
     * @return 
     */
    public Instance toWekaInstance(Instances dataset) {
        // create instance
        DenseInstance instance = new DenseInstance(10);
        instance.setDataset(dataset);

        // set values
        instance.setValue(0, mean);
        instance.setValue(1, min);
        instance.setValue(2, max);
        instance.setValue(3, std);
        instance.setValue(4, lowQuantile);
        instance.setValue(5, highQuantile);
        instance.setValue(6, iqr);
        instance.setValue(7, kurtosis);
        instance.setValue(8, totalF0);

        // set class
        if(this.isStressed()) {
            instance.setValue(9, "stressed");
        } else {
            instance.setValue(9, "unstressed");
        }

        // return instance
        return instance;
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

        // get f0 total
        double f0total = voiceStressAnalyser.getTotalFundamentalFrequency();
        
        // set parameters
        VoiceStressInstance instance = new VoiceStressInstance();
        instance.setMean(MathUtil.mean(f0vector));
        instance.setMin(MathUtil.min(f0vector));
        instance.setStd(MathUtil.std(f0vector));
        instance.setLowQuantile(MathUtil.lowQuantile(f0vector));
        instance.setHighQuantile(MathUtil.highQuantile(f0vector));
        instance.setIqr(MathUtil.iqr(f0vector));
        instance.setKurtosis(MathUtil.kurtosis(f0vector));
        instance.setTotalF0(f0total);

        // return instance
        return instance;
    }
}
