package org.vsa.util;

/**
 * SoundWindowUtil
 */
public class SoundWindowUtil {

    /**
     * applyWindow
     * 
     * @param signal
     * @param window 
     */
    public static void applyWindow(double[] signal, double[] window) {
        for (int i = 0; i < signal.length; i++) {
            signal[i] = signal[i]* window[i];
        }
    }

    /**
     * hamming window
     * 
     * @param L Size
     * @return
     */
    public static double[] hammingWindow(int L) {
        double[] window = new double[L];

        for(int n = 0; n < L; n++) {
            window[n] = 0.54 - 0.46 * Math.cos(2 * Math.PI * n / (L - 1));
        }

        return window;
    }

    /**
     * Make a blackman window
     * 
     * w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
     * @param N size
     * @return
     */
    public static double[] blackmanWindow(int N) {
        double[] window = new double[N];

        for (int n = 0; n < window.length; n++) {
            window[n] = 0.42 - 0.5 * Math.cos(2 * Math.PI * n / (N - 1))
                    + 0.08 * Math.cos(4 * Math.PI * n / (N - 1));
        }

        return window;
    }
}
