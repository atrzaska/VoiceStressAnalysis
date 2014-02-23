package org.vsa;

import it.sardegnaricerche.voiceid.utils.wav.WavFile;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
//        VsaProgram program = new VsaProgram();
//        program.run();

        // Open the wav file
        WavFile wavFile = WavFile.openWavFile(new File("wav/yes.wav"));
        // Display information about the wav file
        wavFile.display();
    }
}
