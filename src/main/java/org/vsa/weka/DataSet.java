package org.vsa.weka;

import java.io.BufferedReader;
import java.io.FileReader;
import weka.classifiers.Classifier;
import weka.core.Instances;

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
