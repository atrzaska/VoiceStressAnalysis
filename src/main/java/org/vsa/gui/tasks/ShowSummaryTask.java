package org.vsa.gui.tasks;

import java.awt.Cursor;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.api.Interrogation;
import org.vsa.audio.AudioException;
import org.vsa.gui.MainWindow;
import org.vsa.gui.ProgressWindow;
import org.vsa.gui.SummaryWindow;
import weka.core.Instances;

/**
 * ShowSummaryTask
 */
public class ShowSummaryTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final MainWindow window;

    /**
     * SummaryTask
     * 
     * @param window 
     */
    public ShowSummaryTask(MainWindow window) {
        this.window = window;
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
        window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        // create progress window
        ProgressWindow progressWindow = new ProgressWindow(window);
        progressWindow.setVisible(true);
        progressWindow.setIndeterminate(true);

        try {
            // get interrogation
            Interrogation interrogation = window.getSelectedInterrogation();

            // get instances
            Instances instances = interrogation.toWekaInstances();

            // create summary window
            SummaryWindow summaryWindow = new SummaryWindow(window);
            summaryWindow.setVisible(true);

            // set text
            summaryWindow.setTextSummary(instances.toSummaryString());
        } catch(IOException | UnsupportedAudioFileException | AudioException e) {
            
            // show exception message
            JOptionPane.showMessageDialog(window, e.getLocalizedMessage());
        }

        // set default cursor
        window.setCursor(null);

        // close progress window
        progressWindow.dispose();

        // return
        return null;
    }
}
