package org.vsa.api.windows;

public class HammingWindow implements SoundWindow {

    /**
     * window
     */
    private final double[] window;
    
    /**
     * default constructor
     * @param L 
     */
    public HammingWindow(int L) {
        this.window = hammingWindow(L);
    }

    /**
     * toArray
     * @return 
     */
    @Override
    public double[] toArray() {
        return window;
    }

    /**
     * hamming window
     * @param L Size
     * @return 
     */
    public static double[] hammingWindow(int L) {
        double[] window = new double[L];
        double pi = Math.PI;
        int N = L - 1;

        for(int n = 0; n < L; n++) {
            window[n] = 0.54 - 0.46 * Math.cos(2 * pi * n / N);
        }

        return window;
    }
}
