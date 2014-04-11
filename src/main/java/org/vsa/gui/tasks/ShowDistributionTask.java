package org.vsa.gui.tasks;

import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.gui.InterrogationWindow;
import org.vsa.util.PlotUtil;

/**
 * DrawFundamentalFrequencyVectorTask
 */
public class ShowDistributionTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final InterrogationWindow window;

    /**
     * DrawFundamentalFrequencyVectorTask
     * 
     * @param window 
     */
    public ShowDistributionTask(InterrogationWindow window) {
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
            PlotUtil.drawF0NormalDistribution(window.getSelectedInstance());
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
