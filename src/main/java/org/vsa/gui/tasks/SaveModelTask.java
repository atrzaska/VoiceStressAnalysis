/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vsa.gui.tasks;

import java.awt.Cursor;
import java.io.File;
import javax.swing.JFileChooser;
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
 *
 * @author mejsl_000
 */
public class SaveModelTask {

    private MainWindow window;

    public SaveModelTask(MainWindow window) {
        this.window = window;
    }

    public void execute() throws Exception {
        Interrogation interrogation = window.getSelectedInterrogation();

        JFileChooser fileChooser = new JFileChooser(Config.outputPath);

        //fileChooser.setSelectedFile(new File(FileUtil.generateArffFileName(interrogation.getName())));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Model", "model");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(window);

        if (result == JFileChooser.APPROVE_OPTION) {
            window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            ProgressWindow progressWindow = new ProgressWindow(window);
            progressWindow.setVisible(true);
            progressWindow.setIndeterminate(true);
            DataSet data = new DataSet();
            Classification classifi = new Classification();
            Classifier klasyfikator = classifi.classifyForModel(interrogation.toWekaInstances());
            System.out.println(klasyfikator.toString());
            data.saveModel(fileChooser.getSelectedFile().getAbsolutePath(), klasyfikator);
            System.out.println(data.loadModel(fileChooser.getSelectedFile().getAbsolutePath()).toString());
            // set default cursor
            window.setCursor(null);

            // close progress window
            progressWindow.dispose();
        }
    }

}
