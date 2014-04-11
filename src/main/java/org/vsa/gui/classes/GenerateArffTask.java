package org.vsa.gui.classes;

import java.awt.Cursor;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.Config;
import org.vsa.api.Interrogation;
import org.vsa.audio.AudioException;
import org.vsa.gui.MainWindow;
import org.vsa.util.FileUtil;

/**
 * GenerateArffTask
 */
public class GenerateArffTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final MainWindow mainWindow;

    /**
     * GenerateArffTask
     * 
     * @param mainWindow 
     */
    public GenerateArffTask(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * doInBackground
     * 
     * @return
     * @throws Exception 
     */
    @Override
    protected Void doInBackground() throws Exception {
        // set wait cursor
        mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            // iterate selections
            for(Object o : mainWindow.getSelectedInterrogations()) {
                // cast object
                Interrogation interrogation = (Interrogation)o;

                // generate output path
                String outputPath = Config.outputPath + FileUtil.generateArffFileName(interrogation.getName());

                // save arff
                interrogation.generateArff(outputPath);
            }

            // show ok dialog
            JOptionPane.showMessageDialog(mainWindow, "OK");
        } catch(IOException | UnsupportedAudioFileException | AudioException e) {
            
            // show exception message
            JOptionPane.showMessageDialog(mainWindow, e.getLocalizedMessage());
        }

        // return
        return null;
    }

    @Override
    public void done() {
        // set default cursor
        mainWindow.setCursor(null);
    }
}
