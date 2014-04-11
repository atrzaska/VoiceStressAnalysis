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
import org.vsa.weka.Classification;
import org.vsa.weka.Evaluate;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 * ClassifyTask
 */
public class ClassifyTask extends SwingWorker<Void,Void> {
    
    /**
     * interrogation
     */
    private final MainWindow mainWindow;

    /**
     * ClassifyTask
     * 
     * @param mainWindow 
     */
    public ClassifyTask(MainWindow mainWindow) {
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
            // get interrogation from list
            Interrogation interrogation = (Interrogation)mainWindow.getSelectedInterrogation();

            // get instances
            Instances instances = interrogation.toWekaInstances();

            // create classification
            Classification classification = new Classification();

            // create desired classifier
            J48 classifier = classification.classifyJ48(instances);
            
            // create evaluation
            Evaluate evaluate = new Evaluate();
            
            // perform cross validation on 6 folds
            Evaluation evaluation = evaluate.crossValidation(classifier, instances, 6);

            // create summary window
            SummaryWindow window = new SummaryWindow();
            window.setVisible(true);

            // set text
            window.setTextSummary(evaluation.toSummaryString());
        } catch(Exception e) {
            
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
