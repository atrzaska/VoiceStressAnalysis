package org.vsa.gui.tasks;

import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.vsa.api.Interrogation;
import org.vsa.gui.MainWindow;
import org.vsa.gui.ProgressWindow;
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
    private final MainWindow window;

    /**
     * ClassifyTask
     * 
     * @param window 
     */
    public ClassifyTask(MainWindow window) {
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
            // get interrogation from list
            Interrogation interrogation = (Interrogation)window.getSelectedInterrogation();

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
            SummaryWindow summaryWindow = new SummaryWindow(window);
            summaryWindow.setVisible(true);

            // set text
            summaryWindow.setTextSummary(evaluation.toSummaryString());
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
