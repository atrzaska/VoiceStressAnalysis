package org.vsa.util;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * MathUtil
 */
public class MathUtil {
    
    /**
     * find absolute max value in array
     * 
     * @param arr
     * @return 
     */
    public static double absMax(double[] arr) {
        double max = Math.abs(arr[0]);

        for(int i = 0; i < arr.length; i++) {
            double val = Math.abs(arr[i]);

            if (val > max) {
                max = val;
            }
        }

        return max;
    }

    /**
     * max index in array
     * 
     * @param arr
     * @return 
     */
    public static int maxIndex(double[] arr) {
        double max = arr[0];
        int index = 0;

//        for (double val : arr) {
        for(int i = 0; i < arr.length; i++) {
            double val = arr[i];

            if (val > max) {
                max = val;
                index = i;
            }
        }

        return index;
    }

    /**
     * find max value in array
     * 
     * @param arr
     * @return 
     */
    public static double max(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getMax();
    }

    /**
     * min
     * 
     * @param arr
     * @return 
     */
    public static double min(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getMin();
    }
    
    /**
     * mean value of array
     * 
     * @param arr
     * @return 
     */
    public static double mean(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getMean();
    }

    /**
     * median
     * 
     * @param arr
     * @return 
     */
    public static double median(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getPercentile(0.50);
    }
    
    /**
     * std
     * 
     * @param arr
     * @return 
     */
    public static double std(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getStandardDeviation();
    }

    /**
     * lowQuantile
     * 
     * @param arr
     * @return 
     */
    public static double lowQuantile(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getPercentile(0.25);
    }

    /**
     * highQuantile
     * 
     * @param arr
     * @return 
     */
    public static double highQuantile(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getPercentile(0.75);
    }

    /**
     * iqr
     * 
     * @param arr
     * @return 
     */
    public static double iqr(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getPercentile(0.75) - ds.getPercentile(0.25);
    }

    /**
     * kurtosis
     * 
     * @param arr
     * @return 
     */
    public static double kurtosis(double[] arr) {
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        return ds.getKurtosis();
    }

    /**
     * calcMagnitudeDB
     * 
     * @param re
     * @param im
     * @return 
     */
    public static double calcMagnitudeDB(double re, double im) {
        return 20 * Math.log10(Math.hypot(re, im));
    }

    /**
     * calcMagnitudeDB2
     * 
     * @param re
     * @param im
     * @return 
     */
    public static double calcMagnitudeDB2(double re, double im) {
        return 10 * Math.log10(re * re + im * im);
    }
}
