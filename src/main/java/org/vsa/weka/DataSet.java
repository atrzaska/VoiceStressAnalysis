package org.vsa.weka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

/**
 * DataSet
 */
public class DataSet {

    /**
     * loadFile
     * 
     * @param path
     * @return
     * @throws Exception 
     */
    public Instances loadFile(String path) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        Instances data = new Instances(reader);
        reader.close();
        data.setClassIndex(data.numAttributes()-1);
        return data;
    }
    
    /**
     * saveModel
     * 
     * @param path
     * @param classifier
     * @throws Exception 
     */
    public void saveModel(String path, Classifier classifier) throws Exception {
        weka.core.SerializationHelper.write(path, classifier);
    }

    /**
     * createInstances
     * 
     * @param instanceList
     * @return 
     */
    public Instances createInstances(List<Instance> instanceList) {

        // create attributes list
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("mean"));
        attributes.add(new Attribute("min"));
        attributes.add(new Attribute("max"));
        attributes.add(new Attribute("std"));
        attributes.add(new Attribute("lowQuantile"));
        attributes.add(new Attribute("highQuantile"));
        attributes.add(new Attribute("iqr"));
        attributes.add(new Attribute("kurtosis"));
        attributes.add(new Attribute("kurtosis"));
        attributes.add(new Attribute("f0total"));
        attributes.add(new Attribute("stressed"));
        
        // create instances array
        Instances instances = new Instances("voice",  attributes, 0);

        // add instances
        for(Instance i : instanceList){
            instances.add(i);
        }

        // return object
        return instances;
    }
    
}
