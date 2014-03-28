package org.vsa.util;

public class MathUtil {
    public static double max(double[] arr) {
        double max = arr[0];

        for(int i = 0; i < arr.length; i++) {
            double val = arr[i];

            if (val > max) {
                max = val;
            }
        }

        return max;
    }

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

    public static double calcMagnitudeDB(double re, double im) {
        return 20 * Math.log10(Math.sqrt(re * re + im * im));
    }

    public static double calcMagnitudeDB2(double re, double im) {
        return 10 * Math.log10(re * re + im * im);
    }
}
