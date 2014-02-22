package org.vsa;

import java.util.logging.Logger;

public class VsaProgram implements Runnable {
    private final Logger logger = Logger.getAnonymousLogger();

    public VsaProgram() {
    }

    @Override
    public void run() {
        logger.info("555");
    }
}
