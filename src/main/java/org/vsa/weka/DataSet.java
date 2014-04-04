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
import weka.classifiers.Classifier;
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
    
}
