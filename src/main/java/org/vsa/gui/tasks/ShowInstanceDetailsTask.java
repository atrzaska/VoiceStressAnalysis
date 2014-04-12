package org.vsa.gui.tasks;

import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.gui.InterrogationWindow;
import org.vsa.gui.SummaryWindow;
import org.vsa.weka.VoiceStressInstance;

/**
 * ShowInstanceDetailsTask
 */
public class ShowInstanceDetailsTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final InterrogationWindow window;

    /**
     * ShowInstanceDetailsTask
     * 
     * @param window 
     */
    public ShowInstanceDetailsTask(InterrogationWindow window) {
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

        try {
            // get instance
            VoiceStressInstance instance = window.getSelectedInstance();

            // create summary window
            SummaryWindow summaryWindow = new SummaryWindow(window);

            // set text
            summaryWindow.setTextSummary(instance.toSummaryString());

            // show window
            summaryWindow.setVisible(true);
        } catch(Exception e) {
            
            // show exception message
            JOptionPane.showMessageDialog(window, e.getLocalizedMessage());
        }

        // set default cursor
        window.setCursor(null);

        // return
        return null;
    }
}
