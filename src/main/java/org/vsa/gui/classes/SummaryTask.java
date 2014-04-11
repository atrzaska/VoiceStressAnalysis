package org.vsa.gui.classes;

import java.awt.Cursor;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.api.Interrogation;
import org.vsa.audio.AudioException;
import org.vsa.gui.MainWindow;
import org.vsa.gui.SummaryWindow;
import weka.core.Instances;

/**
 * SummaryTask
 */
public class SummaryTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final MainWindow mainWindow;

    /**
     * SummaryTask
     * 
     * @param mainWindow 
     */
    public SummaryTask(MainWindow mainWindow) {
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
            // get interrogation
            Interrogation interrogation = mainWindow.getSelectedInterrogation();

            // get instances
            Instances instances = interrogation.toWekaInstances();

            // create summary window
            SummaryWindow window = new SummaryWindow();
            window.setVisible(true);

            // set text
            window.setTextSummary(instances.toSummaryString());
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
