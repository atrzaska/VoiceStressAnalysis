package org.vsa;

import it.sardegnaricerche.voiceid.utils.wav.WavFile;
import it.sardegnaricerche.voiceid.utils.wav.WavFileException;
import java.io.File;
import java.io.IOException;

public class ReadExample {

    public static void main(String[] args) {
        try {
            // Open the wav file specified as the first argument
            WavFile wavFile = WavFile.openWavFile(new File("wav/yes.wav"));

            // Display information about the wav file
            wavFile.display();

            // Get the number of audio channels in the wav file
            int numChannels = wavFile.getNumChannels();

            // Create a buffer of 100 frames
            double[] buffer = new double[100 * numChannels];

            int framesRead;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;

            do {
                // Read frames into buffer
                framesRead = wavFile.readFrames(buffer, 100);

                // Loop through frames and look for minimum and maximum value
                for (int s = 0; s < framesRead * numChannels; s++) {
                    if (buffer[s] > max) {
                        max = buffer[s];
                    }
                    if (buffer[s] < min) {
                        min = buffer[s];
                    }
                }
            } while (framesRead != 0);

            // Close the wavFile
            wavFile.close();

            // Output the minimum and maximum value
            System.out.printf("Min: %f, Max: %f\n", min, max);
        } catch (IOException | WavFileException e) {
            System.err.println(e);
        }
    }
}
