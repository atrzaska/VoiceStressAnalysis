/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vsa.weka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author mejsl_000
 */
public class DataSet {

    public DataSet() {
    }
    
    public Instances loadFile(String path) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(path));
        Instances data = new Instances(reader);
        reader.close();
        data.setClassIndex(data.numAttributes()-1);
        return data;
    }
    
    public void saveModel(String path, Classifier classifier) throws Exception{
        
        weka.core.SerializationHelper.write(path, classifier);
    }
    //double absMax, double maxIndex, double max, double min,
    //        double mean, double median, double std, double lowQuantil, 
    //       double highQuantil, double iqr, double kurtosis
    public Instances createInstances(List<Instance> instance){
        ArrayList<Attribute> voice = new ArrayList<Attribute>();
        voice.add(new Attribute("absMax"));
        voice.add(new Attribute("maxIndex"));
        voice.add(new Attribute("max"));
        voice.add(new Attribute("min"));
        voice.add(new Attribute("mean"));
        voice.add(new Attribute("median"));
        voice.add(new Attribute("std"));
        voice.add(new Attribute("lowQuantil"));
        voice.add(new Attribute("highQuantil"));
        voice.add(new Attribute("iqr"));
        voice.add(new Attribute("kurtosis"));
        
        Instances inst = new Instances("voice",  voice, 0);
        for(Instance i : instance){
            inst.add(i);
        }
        return inst;
    }
    
}
