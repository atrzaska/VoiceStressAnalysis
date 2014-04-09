package org.vsa;

import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.vsa.api.VoiceStressAnalyser;
import org.vsa.api.VsaSystem;
import org.vsa.audio.AudioException;
import org.vsa.util.PlotUtil;

/**
 * Main
 */
public class Main {

    /**
     * main
     * 
     * @param args 
     */
    public static void main(String args[]) throws Exception {
        System.out.println("start");
        System.out.println("----------------------------------------------------------------------------------");

//        // create voice stress analyser for test file
//        VoiceStressAnalyser vsa = new VoiceStressAnalyser("wav/andrzej/nagranie2.wav");
//
//        // draw f0 vector
//        PlotUtil.drawFundamentalFrequencyVector(vsa.getFundamentalFrequencyVector());

        // create vsa system
        VsaSystem vsaSystem = new VsaSystem();
            
        // generate arff files
        vsaSystem.generateArffFiles();

        System.out.println("koniec");
        System.out.println("----------------------------------------------------------------------------------");
    }
}