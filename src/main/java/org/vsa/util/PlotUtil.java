package org.vsa.util;

import java.io.IOException;
import javax.swing.JFrame;
import org.apache.commons.math3.analysis.function.Gaussian;
import org.math.plot.Plot2DPanel;
import org.vsa.Config;
import org.vsa.weka.VoiceStressInstance;

public class PlotUtil {

    /**
     * createTimeVector
     * 
     * @param y
     * @return 
     */
    private static double[] createTimeVector(double[] y, int sampleRate) {
        double[] vector = new double[y.length];
        double dt = 1.0 / sampleRate;

        for(int i = 0; i < y.length; i++) {
            vector[i] = dt * i;
        }

        return vector;
    }

    /**
     * drawWaveForm
     * 
     * @param signal
     * @param sampleRate
     * @throws IllegalArgumentException
     * @throws IOException 
     */
    public static void drawWaveForm(double[] signal, int sampleRate) throws IllegalArgumentException, IOException {
        // create time vector
        double[] x = createTimeVector(signal, sampleRate);

        // plot
        Plot2DPanel plot = new Plot2DPanel();
//        plot.setLegendOrientation("EAST");
//        plot.addLegend("Nagranie");
        plot.addLinePlot("Nagranie", x, signal);

        // frame
        JFrame frame = new JFrame("Nagranie");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * drawSpectrum
     * 
     * @param signal
     * @param sampleRate
     * @throws IllegalArgumentException
     * @throws IOException 
     */
    public static void drawSpectrum(double[] signal, int sampleRate) throws IllegalArgumentException, IOException {
        // calculate range
        int range = (int)(20000.0 * (double)signal.length / (double)sampleRate);

        // create x array
        double[] x = new double[(int)range];

        // populate x array
        for (int i = 0; i < x.length; i++) {
            x[i] = i * sampleRate / signal.length;
        }

        // calculate fft
        double[] fft = FFTUtil.fft_jtransforms(signal);

        // create spectrum array
        double[] spectrum = new double[range];

        // populate spectrum array
        for (int i = 0; i < spectrum.length; i++) {
            spectrum[i] = MathUtil.calcMagnitudeDB(fft[i*2], fft[i*2+1]);
        }

        // plot
        Plot2DPanel plot = new Plot2DPanel();
//        plot.setLegendOrientation("EAST");
//        plot.addLegend("Spectrum");
        plot.addLinePlot("Spectrum", x, spectrum);

        // frame
        JFrame frame = new JFrame("Spectrum");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * drawCepstrum
     * 
     * @param signal
     * @param sampleRate
     * @param hzStart
     * @param hzWindowSize
     * @throws IllegalArgumentException
     * @throws IOException 
     */
    public static void drawCepstrum(double[] signal, int sampleRate, int hzStart, int hzWindowSize) throws IllegalArgumentException, IOException {
        // create temp samples array
        double[] tmpSignal = new double[signal.length];
        
        // copy signal
        System.arraycopy(signal, 0, tmpSignal, 0, signal.length);
        
        // apply window on temp signal
        SoundWindowUtil.applyHammingWindow(tmpSignal);

        // calculate power cepstrum
        double[] powCeps = CepstrumUtil.powerCepstrum(tmpSignal);

        // create voiceWindow array
        double[] y = new double[hzWindowSize];

        // copy data
        System.arraycopy(powCeps, hzStart - 1, y, 0, hzWindowSize);

        // create x array
        double[] x = new double[y.length];

        // set x values
        for (int i = 0; i < x.length; i++) {
            double start = (double)hzStart / (double)sampleRate;
            double percent = (double)i / (double)x.length;
            double size = (double)hzWindowSize / (double)sampleRate;

            x[i] = start + percent * size;
        }
        
        // plot
        Plot2DPanel plot = new Plot2DPanel();
//        plot.setLegendOrientation("EAST");
//        plot.addLegend("Cepstrum");
        plot.addLinePlot("Cepstrum", x, y);
        plot.setAxisLabel(0, "Quefrency [s]");
        plot.setAxisLabel(1, "Amplituda");

        // frame
        JFrame frame = new JFrame("Cepstrum");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * drawFundamentalFrequencyVector
     * 
     * @param fundamentalFrequencyVector
     */
    public static void drawFundamentalFrequencyVector(double[] fundamentalFrequencyVector) {
        double[] y = fundamentalFrequencyVector;
        double[] x = new double[y.length];

        // populate x axis values
        for (int i = 0; i < x.length; i++) {
            x[i] = i;
        }

        // plot
        Plot2DPanel plot = new Plot2DPanel();
//        plot.setLegendOrientation("EAST");
//        plot.addLegend("Ton Podstawowy");
        plot.addLinePlot("Ton Podstawowy", x, y);
        plot.setAxisLabel(0, "Nr okna");
        plot.setAxisLabel(1, "Częstotliwość [Hz]");

        // frame
        JFrame frame = new JFrame("Ton Podstawowy");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * drawF0NormalDistribution
     * 
     * @param instance 
     */
    public static void drawF0NormalDistribution(VoiceStressInstance instance) {
        // create gaussian function
        Gaussian gaussian = new Gaussian(instance.getMean(), instance.getStd());

        // number of values
        int numValues = Config.plotNumValues;
        
        // create x array
        double[] x = new double[numValues];
        
        // calculate x values
        for(int i = 0; i < numValues; i++) {
            x[i] = (((double)i / (double)numValues) * Config.plotXMax);
        }

        // create y array
        double[] y = new double[numValues];

        // calculate y values
        for(int i = 0; i < y.length; i++) {
            y[i] = gaussian.value(x[i]);
        }

        // plot
        Plot2DPanel plot = new Plot2DPanel();
//        plot.setLegendOrientation("EAST");
//        plot.addLegend("Rozkład normanlny tonu fundamentalnego");
        plot.addLinePlot("Rozkład normanlny tonu fundamentalnego", x, y);
        plot.setAxisLabel(0, "Częstotliwość [Hz]");
        plot.setAxisLabel(1, "Rozkład ");

        // frame
        JFrame frame = new JFrame("Ton Podstawowy");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
