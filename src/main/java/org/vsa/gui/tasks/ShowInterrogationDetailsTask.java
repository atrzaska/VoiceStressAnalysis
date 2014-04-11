package org.vsa.gui.tasks;

import java.awt.Cursor;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.audio.AudioException;
import org.vsa.gui.InterrogationWindow;
import org.vsa.gui.MainWindow;
import org.vsa.gui.ProgressWindow;

/**
 * ShowInterrogationDetailsTask
 */
public class ShowInterrogationDetailsTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final MainWindow window;

    /**
     * SummaryTask
     * 
     * @param window 
     */
    public ShowInterrogationDetailsTask(MainWindow window) {
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
            // create window
            InterrogationWindow interrogationWindow = new InterrogationWindow(window);
            interrogationWindow.setVisible(true);
        } catch(UnsupportedAudioFileException | AudioException e) {
            
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
