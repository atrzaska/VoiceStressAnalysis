package org.vsa.util;

import java.nio.ByteBuffer;

/**
 * SoundProcessingUtil
 */
public class SoundUtil {

    /**
     * GetFrame
     * @param data
     * @param frameStart
     * @param frameSize
     * @return
     */
    public static float[] GetFrame(float[] data, int frameStart, int frameSize) {
        float[] frame = new float[frameSize];

        System.arraycopy(data, frameStart, frame, 0, frameSize);

        return frame;
    }

    /**
     * GetFrame
     * 
     * @param data
     * @param frameStart
     * @param frameSize
     * @return
     */
    public static double[] GetFrame(double[] data, int frameStart, int frameSize) {
        double[] frame = new double[frameSize];

        System.arraycopy(data, frameStart, frame, 0, frameSize);

        return frame;
    }

    /**
     * floatMe
     *
     * @param pcms
     * @return
     */
    public static float[] floatMe(short[] pcms) {
        float[] floaters = new float[pcms.length];

        for (int i = 0; i < pcms.length; i++) {
            float val = pcms[i];
            floaters[i] = val / 32768;
        }

        return floaters;
    }

    /**
     * shortMe
     *
     * @param bytes
     * @return
     */
    public static short[] shortMe(byte[] bytes) {
        short[] out = new short[bytes.length / 2]; // will drop last byte if odd number
        ByteBuffer bb = ByteBuffer.wrap(bytes);

        for (int i = 0; i < out.length; i++) {
            out[i] = bb.getShort();
        }

        return out;
    }
}
