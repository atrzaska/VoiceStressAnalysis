package org.vsa.audio;

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
import org.vsa.util.CepstrumUtil;
import org.vsa.util.FFTUtil;
import org.vsa.util.MathUtil;

/**
 * AudioReader
 */
public final class AudioReader {

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
}
