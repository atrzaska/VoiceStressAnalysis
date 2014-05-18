package org.vsa.gui.tasks;

import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;
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
        try {

            // get selections
            Interrogation interrogation = window.getSelectedInterrogation();

            // show save file dialog
            JFileChooser fileChooser = new JFileChooser(Config.outputPath);

            // suggest file name
            fileChooser.setSelectedFile(new File(FileUtil.generateArffFileName(interrogation.getName())));

            // show dialog
            int result = fileChooser.showSaveDialog(window);

            if(result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                // set wait cursor
                window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                // create progress window
                ProgressWindow progressWindow = new ProgressWindow(window);
                progressWindow.setIndeterminate(true);
                progressWindow.setVisible(true);

                // save arff
                interrogation.generateArff(file.getPath());

                // set default cursor
                window.setCursor(null);

                // close progress window
                progressWindow.dispose();
            }

        } catch(IOException | UnsupportedAudioFileException | AudioException e) {
            
            // show exception message
            JOptionPane.showMessageDialog(window, e.getLocalizedMessage());
        }

        // return
        return null;
    }
}
