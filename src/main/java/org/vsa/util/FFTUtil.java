package org.vsa.util;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

/**
 * FFTUtil
 */
public class FFTUtil {

    /**
     * fft_jtransforms
     * 
     * @param src
     * @return 
     */
    public static double[] fft_jtransforms(double[] src) {
        double[] out = new double[src.length * 2];

        System.arraycopy(src, 0, out, 0, src.length);

        DoubleFFT_1D fft = new DoubleFFT_1D(src.length);
        fft.realForwardFull(out);

        return out;
    }
}
