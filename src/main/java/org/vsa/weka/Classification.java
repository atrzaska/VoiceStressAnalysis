/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vsa.weka;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;

/**
 *
 * @author mejsl_000
 */
public class Classification {

    public Classification() { }
    
    //classify with default option
    public J48 classifyJ48(Instances data) throws Exception{
        J48 tree = new J48();
        tree.buildClassifier(data);
        return tree;
        
    }
    //clasify with set options
    public J48 classifyJ48(Instances data,String[] options) throws Exception{
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(data);
        return tree;
    }
    
    public NaiveBayes classifyNaiveBayes(Instances data) throws Exception{
        NaiveBayes bayes = new NaiveBayes();
        bayes.buildClassifier(data);
        return bayes;
    }
    
    public IBk classifyIBk(Instances data) throws Exception{
        IBk ibk = new IBk();
        ibk.buildClassifier(data);
        return ibk;
    }
    
    public RandomTree classifyRandomTree(Instances data) throws Exception{
        RandomTree rngTree = new RandomTree();
        rngTree.buildClassifier(data);
        return rngTree;
    }
    
    public SMO classifySMO(Instances data) throws Exception{
        SMO smo = new SMO();
        smo.buildClassifier(data);
        return smo;
    }
    
    public PART classifyPART(Instances data) throws Exception{
        PART part = new PART();
        part.buildClassifier(data);
        return part;
    }
    
    public void classify1(){
        //to do
    }
    
    public void classify2(){
        //to do
    }
    
    public void classify3(){
        //to do
    }
    
    public void classifyNewFromFile(Classifier train, Instances unlabeled) throws Exception{
        
        Instances labeled = new Instances(unlabeled);
        
        for (int i = 0; i< labeled.numInstances(); i++){
            double clsLabel = train.classifyInstance(unlabeled.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
        }
        
        System.out.println(labeled);
    }
    
    public void classifyNewFromFileUsingModel(String path, Instances unlabeled) throws Exception{
        
        Classifier train = (Classifier) weka.core.SerializationHelper.read(path);
        
        Instances labeled = new Instances(unlabeled);
        
        for (int i = 0; i< labeled.numInstances(); i++){
            double clsLabel = train.classifyInstance(unlabeled.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
        }
        
        System.out.println(labeled);
    }
    
    public void classifyFromVector(){
        //to do
    }
    
    public void classifySingleInstance(){
        
    }
    
    public void classifyInstanceList(){
        
    }
}
