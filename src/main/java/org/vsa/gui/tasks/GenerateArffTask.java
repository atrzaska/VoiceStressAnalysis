package org.vsa.gui.tasks;

import java.awt.Cursor;
import java.io.IOException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.Config;
import org.vsa.api.Interrogation;
import org.vsa.audio.AudioException;
import org.vsa.gui.MainWindow;
import org.vsa.gui.ProgressWindow;
import org.vsa.util.FileUtil;

/**
 * GenerateArffTask
 */
public class GenerateArffTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final MainWindow window;

    /**
     * GenerateArffTask
     * 
     * @param window 
     */
    public GenerateArffTask(MainWindow window) {
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

        // get selections
        List<Object> selections = window.getSelectedInterrogations();

        // create progress window
        ProgressWindow progressWindow = new ProgressWindow(window);
        progressWindow.setVisible(true);

        // set max
        progressWindow.setMaxValue(selections.size());

        try {
            // iterate selections
            for(int i = 0; i < selections.size(); i++) {
                
                // cast object
                Interrogation interrogation = (Interrogation)selections.get(i);

                // generate output path
                String outputPath = Config.outputPath + FileUtil.generateArffFileName(interrogation.getName());

                // save arff
                interrogation.generateArff(outputPath);

                // update progress
                progressWindow.setValue(i+1);
            }

            // show ok dialog
            JOptionPane.showMessageDialog(window, "OK");
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
