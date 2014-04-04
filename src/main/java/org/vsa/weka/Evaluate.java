/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vsa.weka;

import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author mejsl_000
 */
public class Evaluate {

    public Evaluate() {
    }
    
    public void crossValidation(Classifier classifire,Instances data, int folds ) 
            throws Exception{
        Evaluation eval = new Evaluation(data);
        eval.crossValidateModel(classifire, data, folds, new Random(1));
        System.out.println(eval.toSummaryString());
    }
    
    public void trainTestSet(Classifier classifier, Instances train, Instances test) 
            throws Exception{
        Evaluation eval = new Evaluation(train);
        eval.evaluateModel(classifier, test);
        System.out.println(eval.toSummaryString());
    }
    
}
