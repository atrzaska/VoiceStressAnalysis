package org.vsa.audio;

import org.vsa.Config;
import org.vsa.util.MathUtil;

/**
 * AudioProcessor
 */
public class AudioProcessor {
    
    /**
     * signal
     */
    private double[] signal;

    /**
     * Default constructor.
     * 
     * @param signal 
     */
    public AudioProcessor(double[] signal) {
        this.signal = signal;
    }

    /**
     * removeStartSilence
     */
    public void removeStartSilence() {
        this.signal = removeStartSilence(this.signal);
    }

    /**
     * removeEndSilence
     */
    public void removeEndSilence() {
        this.signal = removeEndSilence(this.signal);
    }

    /**
     * normalizeSignal
     */
    public void normalizeSignal() {
        normalizeSignal(this.signal);
    }

    /**
     * removeStartSilence
     * 
     * @param signal
     * @return 
     */
    public static  double[] removeStartSilence(double[] signal) {
        // start index
        int startIndex = 0;

        // get silence threshold
        double threshold = Config.silenceThreshold;
        
        // find start index
        for (int i = 0; i < signal.length; i++) {
            // get abs value
            double val = Math.abs(signal[i]);
            
            // check if threshold is exceded
            if(val > threshold) {
                startIndex = i;
                break;
            }
        }
    
        // dont copy if startIndex is 0
        if(startIndex == 0) {
            return signal;
        } else {
            int cutSignalLengh = signal.length - startIndex;
            double[] cutSignal = new double[cutSignalLengh];

            System.arraycopy(signal, startIndex, cutSignal, 0, cutSignalLengh);
            return cutSignal;
        }
    }

    /**
     * removeEndSilence
     * 
     * @param signal
     * @return 
     */
    public static  double[] removeEndSilence(double[] signal) {
        // start index
        int endIndex = 0;

        // get silence threshold
        double threshold = Config.silenceThreshold;
        
        // find start index
        for (int i = signal.length - 1; i >= 0; i--) {
            // get abs value
            double val = Math.abs(signal[i]);
            
            // check if threshold is exceded
            if(val > threshold) {
                endIndex = i;
                break;
            }
        }
    
        // dont copy if endIndex is at the end of the signal
        if(endIndex == signal.length - 1) {
            return signal;
        } else {
            int cutSignalLengh = signal.length - endIndex;
            double[] cutSignal = new double[cutSignalLengh];

            System.arraycopy(signal, 0, cutSignal, 0, cutSignalLengh);
            return cutSignal;
        }
    }

    /**
     * normalizeSignal
     * 
     * @param signal
     */
    public static void normalizeSignal(double[] signal) {
        // find max
        double max = MathUtil.absMax(signal);

        // calculate factor
        double factor = 1.0 / max;

        // multiply signal by factor
        for (int i = 0; i < signal.length; i++) {
            signal[i] = signal[i] * factor;
        }
    }

    /**
     * getProcessedSignal
     * 
     * @return 
     */
    public double[] getProcessedSignal() {
        return signal;
    }
}
