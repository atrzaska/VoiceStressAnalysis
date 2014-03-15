package org.vsa.api;

import it.sardegnaricerche.voiceid.utils.wav.WavFile;
import java.io.File;
import org.vsa.Config;

public class VsaProgram {
    
    
    public VsaProgram() throws Exception {
        
        // Open the wav file
        WavFile wavFile = WavFile.openWavFile(new File("wav/nie2.wav"));
        // Display information about the wav file
        wavFile.display();
        
        if(wavFile.getNumChannels() != Config.allowedNumChannels) {
            throw new Exception("Mono input only");
        }

        double max = 0;

        double[] frameBuffer = new double[(int)wavFile.getNumFrames()];
        wavFile.readFrames(frameBuffer, (int)wavFile.getNumFrames());

        
        
        for(int i = 0; i < frameBuffer.length; i++) {
            double val = frameBuffer[i];
            
            if(val > max) {
                max = val;
            }
        }

        System.out.printf("buffer size: %d\n", frameBuffer.length);
        System.out.printf("numFrames: %d\n", wavFile.getNumFrames());
        System.out.printf("max: %.2f\n", max);
    }
}
