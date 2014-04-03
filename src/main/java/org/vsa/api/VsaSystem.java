package org.vsa.api;

import org.vsa.Config;

/**
 * VsaSystem
 */
public class VsaSystem {

    /**
     * interrogationList
     */
    private final InterrogationList interrogationList;
    
    /**
     * Default constructor.
     */
    public VsaSystem() {
        // get wav folder path
        String wavPath = Config.wavPath;

        interrogationList = new InterrogationList(wavPath);
    }
}
