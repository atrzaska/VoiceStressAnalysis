package org.vsa.gui.tasks;

import java.awt.Cursor;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.audio.AudioException;
import org.vsa.gui.InterrogationWindow;
import org.vsa.util.PlotUtil;

/**
 * DrawFundamentalFrequencyVectorTask
 */
public class DrawFundamentalFrequencyVectorTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final InterrogationWindow window;

    /**
     * DrawFundamentalFrequencyVectorTask
     * 
     * @param window 
     */
    public DrawFundamentalFrequencyVectorTask(InterrogationWindow window) {
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
            // get f0 vector
            double[] f0vector = window.getSelectedInstance().getFundamentalFrequencyVector();
            
            // draw it!
            PlotUtil.drawFundamentalFrequencyVector(f0vector);
        } catch(IOException | UnsupportedAudioFileException | AudioException e) {
            
            // show exception message
            JOptionPane.showMessageDialog(window, e.getLocalizedMessage());
        }
        
        // set default cursor
        window.setCursor(null);

        // return
        return null;
    }
}
