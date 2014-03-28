package org.vsa.api.audio;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;
import org.vsa.util.FFTUtil;
import org.vsa.util.MathUtil;

/**
 * AudioReader
 */
public class AudioReader {

    /**
     * audioInputStream
     */
    private final AudioInputStream audioInputStream;

    /**
     * audioFormat
     */
    private final AudioFormat audioFormat;

    /**
     * audioData
     */
    private final double[] audioData;

    /**
     * Default constructor
     *
     * @param file
     * @throws UnsupportedAudioFileException
     * @throws IOException
     */
    public AudioReader(File file) throws UnsupportedAudioFileException, IOException {
        // open stream
        audioInputStream = AudioSystem.getAudioInputStream(file);

        // get audio format
        audioFormat = audioInputStream.getFormat();

        // number of frames
        int numFrames = this.numFrames();

        // frame size
        int frameSize = this.getFrameSize();

        // create array
        byte[] byteData = new byte[numFrames * frameSize];

        // read data
        audioInputStream.read(byteData);

        // init array
        audioData = new double[this.numSamples() * this.numChannels()];

        // decode bytes
        decodeBytes(byteData, audioData);

        // close stream
        audioInputStream.close();
    }

    /**
     * getAudioFormat
     *
     * @return audioFormat
     */
    public AudioFormat getAudioFormat() {
        return audioFormat;
    }

    /**
     * numChannels
     *
     * @return
     */
    public int numChannels() {
        return audioFormat.getChannels();
    }

    /**
     * numFrames
     *
     * @return
     */
    public int numFrames() {
        return this.numSamples() * this.numChannels();
    }

    /**
     * numFramesPerChannel
     *
     * @return
     */
    public int numFramesPerChannel() {
        return this.numSamples();
    }

    /**
     * Return the number of samples in a channel
     *
     * @return number of samples in a channel
     */
    public int numSamplesPerChannel() {
        return this.numFrames();
    }

    /**
     * Return the number of samples in a channel
     *
     * @return number of samples in a channel
     */
    public int numSamples() {
        return (int) audioInputStream.getFrameLength() * this.numChannels();
    }

    /**
     * getFrameSize
     *
     * @return
     */
    public int getFrameSize() {
        return audioFormat.getFrameSize();
    }

    /**
     * getSampleSizeInBits
     *
     * @return
     */
    public int getSampleSizeInBits() {
        return audioFormat.getSampleSizeInBits();
    }

    /**
     * getSampleRate
     *
     * @return
     */
    public int getSampleRate() {
        return (int)audioFormat.getSampleRate();
    }

    /**
     * getSampleSizeInBytes
     *
     * @return
     */
    public int getSampleSizeInBytes() {
        return audioFormat.getSampleSizeInBits() / 8;
    }

    /**
     * getChannelSamplesF
     *
     * @param channel
     * @return
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public float[] getChannelSamplesF(int channel) throws IllegalArgumentException, IOException {
        int numSamples = this.numSamples();
        int numChannels = this.numChannels();

        float[] channelSamples = new float[numSamples];

        for (int i = 0; i < channelSamples.length; i++) {
            channelSamples[i] = (float)audioData[i * numChannels + channel];
        }

        return channelSamples;
    }

    /**
     * getChannelSamplesD
     *
     * @param channel
     * @return
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public double[] getChannelSamples(int channel) throws IllegalArgumentException, IOException {
        int numSamples = this.numSamplesPerChannel();
        int numChannels = this.numChannels();

        double[] channelSamples = new double[numSamples];

        for (int i = 0; i < channelSamples.length; i++) {
            channelSamples[i] = audioData[i * numChannels + channel];
        }

        return channelSamples;
    }

    /**
     * Decode bytes of audioBytes into audioSamples
     *
     * @param audioBytes
     * @param audioSamples
     */
    private void decodeBytes(byte[] audioBytes, double[] audioSamples) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(audioBytes);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        for(int i = 0; i < audioSamples.length; i++) {
            short s = byteBuffer.getShort();

            // read short
            double tmp = s;

            // normalize this
            tmp = tmp / 32768.0;

            audioSamples[i] = tmp;
        }
    }

    private double[] createTimeVector(double[] y) {
//        int numSamples = this.numSamples();
        int numSamples = y.length;

        double[] vector = new double[numSamples];
        double dt = 1.0 / this.getSampleRate();

        for(int i = 0; i < numSamples; i++) {
            vector[i] = dt * i;
        }

        return vector;
    }

    public void drawCepstrum() throws IllegalArgumentException, IOException {
    }

    public void drawWaveForm() throws IllegalArgumentException, IOException {
        Plot2DPanel plot = new Plot2DPanel();
        plot.setLegendOrientation("EAST");
        plot.addLegend("Nagranie");

        double[] samples = this.getChannelSamples(0);

        double[] y = new double[2000];
        double[] x = this.createTimeVector(y);

        int factor = samples.length / y.length;
        int index = 0;

        // x
        for (int i = 0; i < y.length; i++) {
            double max = samples[index];

            for (int j = 0; j < factor;j++) {
                double val = Math.abs(samples[index]);

                if(val > max) {
                    max = val;
                }

                index++;
            }
        }

        plot.addLinePlot("Nagranie", x, y);

        JFrame frame = new JFrame("Nagranie");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void drawSpectrum() throws IllegalArgumentException, IOException {
        Plot2DPanel plot = new Plot2DPanel();
        plot.setLegendOrientation("EAST");
        plot.addLegend("Spectrum");

        int range = 20000 * this.numSamples() / this.getSampleRate();

        double[] x = new double[range];

        for (int i = 0; i < x.length; i++) {
            x[i] = i * this.getSampleRate() / this.numSamples();
        }

        double[] y = this.getChannelSamples(0);
        double[] fft = FFTUtil.fft_jtransforms(y);
        double[] spectrum = new double[range];

        for (int i = 0; i < spectrum.length; i++) {
            spectrum[i] = MathUtil.calcMagnitudeDB(fft[i*2], fft[i*2+1]);
        }

        plot.addLinePlot("Spectrum", x, spectrum);

        JFrame frame = new JFrame("Spectrum");
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
