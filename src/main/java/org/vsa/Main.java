package org.vsa;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.vsa.gui.MainWindow;

/**
 * Main
 */
public class Main {

    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * main
     *
     * @param args
     */
    public static void main(String[] args) {
        // log
        logger.log(Level.INFO, "start");

        // log
        logger.log(Level.INFO, "creating GUI");

        // set look and feel
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }

        // display form
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });

        // log
        logger.log(Level.INFO, "done");
    }
}
