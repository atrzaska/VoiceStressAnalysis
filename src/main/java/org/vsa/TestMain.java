/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vsa;

import org.vsa.weka.Classification;
import org.vsa.weka.DataSet;
import org.vsa.weka.Evaluate;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 *
 * @author mejsl_000
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        DataSet dataSet = new DataSet();
        Classification classi = new Classification();
        Evaluate eval = new Evaluate();
        
        Instances data = dataSet.loadFile("C:/Program Files/Weka-3-6/data/iris.arff");
        J48 tree = classi.classifyJ48(data);
        eval.crossValidation(tree, data, 10);
    }
    
}
