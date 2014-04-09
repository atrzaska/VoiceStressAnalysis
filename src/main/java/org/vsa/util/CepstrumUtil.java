package org.vsa.util;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;

/**
 * CepstrumUtil
 */
public class CepstrumUtil {

    /**
     * powerCepstrum
     * 
     * @param signal
     * @return
     */
    public static double[] powerCepstrum(double[] signal) {
        // create temp array
        double[] tmp = new double[signal.length * 2];

        // copy signal
        System.arraycopy(signal, 0, tmp, 0, signal.length);

        // create output arr
        double[] output = new double[signal.length];

        // fft magic
        DoubleFFT_1D fft = new DoubleFFT_1D(signal.length);
        fft.realForwardFull(tmp);

        // calculate abs value
        for (int i = 0; i < output.length; i++) {
            // get real value
            double re = tmp[2*i];

            // get imaginary value
            double im = tmp[2*i+1];

            // calculate absolute value of complex number
            double absVal = Math.hypot(re, im);

            // calculate absVal^2
            double pow1 = Math.pow(absVal, 2);

            // calculate log10
            double log1 = Math.log10(pow1);

            // save result
            output[i] = log1;
        }

        // clear tmp array
        tmp = new double[signal.length * 2];

        // copy output
        System.arraycopy(output, 0, tmp, 0, output.length);

        // fft magic
        fft.realForwardFull(tmp);

        // calculate abs value
        for (int i = 0; i < output.length; i++) {
            // get real value
            double re = tmp[2*i];

            // get imaginary value
            double im = tmp[2*i+1];

            // calculate absolute value of complex number
            double absVal = Math.hypot(re, im);

            // calculate absVal^2
            double pow1 = Math.pow(absVal, 2);

            // save result
            output[i] = pow1;
        }

        return output;
    }
}
