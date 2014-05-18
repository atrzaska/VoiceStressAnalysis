package org.vsa.gui.tasks;

import java.awt.Cursor;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.vsa.Config;
import org.vsa.api.Interrogation;
import org.vsa.gui.MainWindow;
import org.vsa.gui.ProgressWindow;
import org.vsa.util.FileUtil;
import org.vsa.weka.Classification;
import org.vsa.weka.DataSet;
import weka.classifiers.Classifier;

/**
 * SaveModelTask
 */
public class SaveModelTask extends SwingWorker<Void,Void> {

    /**
     * window
     */
    private final MainWindow window;

    /**
     * SaveModelTask
     * 
     * @param window 
     */
    public SaveModelTask(MainWindow window) {
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
            fileChooser.setSelectedFile(new File(FileUtil.generateModelFileName(interrogation.getName())));

            // set filter
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Model", "model");
            fileChooser.setFileFilter(filter);

            // show dialog
            int result = fileChooser.showSaveDialog(window);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                // set wait cursor
                window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                // create progress window
                ProgressWindow progressWindow = new ProgressWindow(window);
                progressWindow.setIndeterminate(true);
                progressWindow.setVisible(true);

                // create dataset
                DataSet dataSet = new DataSet();

                // create classification
                Classification classification = new Classification();

                // create classifier
                Classifier classifier = classification.classifyForModel(interrogation.toWekaInstances());
                
                // save model
                dataSet.saveModel(file.getPath(), classifier);
                
                // set default cursor
                window.setCursor(null);

                // close progress window
                progressWindow.dispose();
            }
        } catch(Exception e) {
            // show exception message
            JOptionPane.showMessageDialog(window, e.getLocalizedMessage());
        }

        return null;
    }

}
