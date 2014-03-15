/* VisNow
   Copyright (C) 2006-2013 University of Warsaw, ICM

This file is part of GNU Classpath.

GNU Classpath is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

GNU Classpath is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with GNU Classpath; see the file COPYING.  If not, write to the 
University of Warsaw, Interdisciplinary Centre for Mathematical and 
Computational Modelling, Pawinskiego 5a, 02-106 Warsaw, Poland. 

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version. */

package pl.edu.icm.visnow.lib.experimental.creed.Cepstrum;

import edu.emory.mathcs.jtransforms.dct.FloatDCT_1D;
import edu.emory.mathcs.jtransforms.fft.FloatFFT_1D;

/**
 *
 * @author creed
 */
public class Cepstrum {
    
    int frameSize = 40;
    int melBanks = 6;
    
    private float[] GetFrame(float[] data, int frameStart, int frameSize) {
        float[] frame = new float[frameSize];
        System.arraycopy(data, frameStart, frame, 0, frameSize);
        return frame;
    }

    private float[] PowerSpectrum(float[] frame) {
        int length = frame.length;
        float[] a = new float[2*length];
        System.arraycopy(frame, 0, a, 0, length);
        FloatFFT_1D fft1 = new FloatFFT_1D(length);
        fft1.realForwardFull(a);
        
        float[] output = new float[length];
        
        for (int i = 0; i < output.length; i++) {
            output[i] = a[2*i]*a[2*i]+a[2*i+1]*a[2*i+1];
        }
        
        return output;
    }

    private float[] Cepstrum(float[] spectrum) {
        int length = spectrum.length;
        
        float[] a = new float[length];
        System.arraycopy(spectrum, 0, a, 0, length);
        FloatDCT_1D dct = new FloatDCT_1D(length);
        dct.forward(a, true);
        
        return a;
        
//        float[] output = new float[length];
//        for (int i = 0; i < output.length; i++) {
//            output[i] = a[2*i]*a[2*i]+a[2*i+1]*a[2*i+1];
//        }
//        
//        return output;
        
    }

    private float[] MelSpectrum(float[] framePowerSpectrum) {
        float[] melSpectrum = new float[melBanks];
        
        for (int i = 0; i < melSpectrum.length; i++) {
            float integral = 0;
            
            float[] filter = GetFilter( i, melBanks );
            
            for (int j = 0; j < filter.length; j++) {
                integral += filter[j]*framePowerSpectrum[j];
            }
            
            melSpectrum[i] = integral;
        }            
        
        return melSpectrum;
    }

    private float[] GetFilter(int i, int melBanks) {
        int preKnot = GetKnot( i-1, melBanks );
        int midKnot = GetKnot( i, melBanks );
        int postKnot = GetKnot( i+1, melBanks );
        
        float[] filter = new float[frameSize];
        
        for (int j = 0; j < filter.length; j++) {
            if( j < preKnot )
                filter[j] = 0;
            else if ( j < midKnot )
                filter[j] = ((float)j - preKnot) / (midKnot - preKnot);
            else if ( j < postKnot )
                filter[j] = 1 - ((float)j - midKnot) / ( postKnot - preKnot);
            else 
                filter[j] = 0;
        }
        
        return filter;
    }

    private int GetKnot(int i, int melBanks) {
        float mel = 402.15f + (2834.99f - 402.15f) / melBanks * i; // secret formula for transforming i to mel; idea: indices [0;melBanks] correspond to mels: [402.15;2834.99]
        double freq = 700 * ( Math.exp(mel / 1125 ) - 1 ); // mels -> frequency
        double index = ((freq-300)/(8000-300)*frameSize); // frequncy -> index; [300Hz;8000Hz] correspond to [0;frameSize]
        return (int) index;
    }
}
