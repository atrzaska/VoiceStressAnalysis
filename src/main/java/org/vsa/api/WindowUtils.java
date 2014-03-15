package org.vsa.api;

public class WindowUtils {

    /**
     * hamming window
     * @param L Size
     * @return 
     */
    public double[] hammingWindow(int L) {
        double[] window = new double[L];
        double pi = Math.PI;
        int N = L - 1;

        for(int n = 0; n < L; n++) {
            window[n] = 0.54 - 0.46 * Math.cos(2 * pi * n / N);
        }

        return window;
    }
}
