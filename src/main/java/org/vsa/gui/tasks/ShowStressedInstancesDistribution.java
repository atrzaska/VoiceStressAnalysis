package org.vsa.gui.tasks;

import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.api.Experiment;
import org.vsa.api.Interrogation;
import org.vsa.gui.MainWindow;
import org.vsa.gui.ProgressWindow;
import org.vsa.util.PlotUtil;

/**
 * ShowStressedInstancesDistribution
 */
public class ShowStressedInstancesDistribution extends SwingWorker<Void,Void> {
    
    /**
     * window
     */
    private final MainWindow window;

    /**
     * DrawFundamentalFrequencyVectorTask
     * 
     * @param window 
     */
    public ShowStressedInstancesDistribution(MainWindow window) {
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

            // create experiment
            Experiment experiment = new Experiment(interrogation);

            // plot normal distribution
            PlotUtil.drawNormalDistribution(window, experiment.getStressedMean(), experiment.getStressedStd());
        } catch(Exception e) {
            
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
