package org.vsa.weka;

import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

/**
 * Evaluate
 */
public class Evaluate {
    
    /**
     * crossValidation
     * 
     * @param classifier
     * @param instances
     * @param folds
     * @return 
     * @throws Exception 
     */
    public Evaluation crossValidation(Classifier classifier, Instances instances, int folds) throws Exception {
        
        // create evaluation
        Evaluation evaluation = new Evaluation(instances);

        // cross validate model
        evaluation.crossValidateModel(classifier, instances, folds, new Random());

        // return
        return evaluation;
    }
    
    /**
     * trainTestSet
     * 
     * @param classifier
     * @param train
     * @param test
     * @return 
     * @throws Exception 
     */
    public Evaluation trainTestSet(Classifier classifier, Instances train, Instances test) throws Exception {
        
        // create evaluation
        Evaluation evaluation = new Evaluation(train);

        // evaluate model
        evaluation.evaluateModel(classifier, test);

        // return
        return evaluation;
    }
}
