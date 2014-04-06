/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vsa.weka;

import weka.core.DenseInstance;
import weka.core.Instance;

/**
 *
 * @author mejsl_000
 */
public class Voice {

    private double[] instance = new double[11];
    
    public Voice(double absMax, double maxIndex, double max, double min,
            double mean, double median, double std, double lowQuantil, 
            double highQuantil, double iqr, double kurtosis) {
        
        instance[0] = absMax;
        instance[1] = maxIndex;
        instance[2] = max;
        instance[3] = min;
        instance[4] = mean;
        instance[5] = median;
        instance[6] = std;
        instance[7] = lowQuantil;
        instance[8] = highQuantil;
        instance[9] = iqr;
        instance[10] = kurtosis;
    }
    
    public Instance toInstance(){
        return new DenseInstance(1.0, instance);
    }
    
}
