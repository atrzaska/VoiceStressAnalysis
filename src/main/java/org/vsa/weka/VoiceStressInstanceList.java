package org.vsa.weka;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

/**
 * VoiceStressInstanceList
 */
public class VoiceStressInstanceList {
    
    /**
     * instances
     */
    private final List<VoiceStressInstance> instances = new ArrayList<>();

    /**
     * name
     */
    private String name;

    /**
     * numInstances
     * 
     * @return
     */
    public int numInstances() {
        return instances.size();
    }

    /**
     * getInstance
     * 
     * @param index
     * @return 
     */
    public VoiceStressInstance getInstance(int index) {
        return instances.get(index);
    }

    /**
     * addInstance
     * 
     * @param instance 
     */
    public void addInstance(VoiceStressInstance instance) {
        this.instances.add(instance);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * toWekaInstances
     * 
     * @return 
     */
    public Instances toWekaInstances() {

        // create class attribute values list
        List<String> classAttributeValues = new ArrayList<>();
        classAttributeValues.add("stressed");
        classAttributeValues.add("unstressed");

        // create attributes
        Attribute a1 = new Attribute("mean");
        Attribute a2 = new Attribute("min");
        Attribute a3 = new Attribute("max");
        Attribute a4 = new Attribute("std");
        Attribute a5 = new Attribute("lowQuantile");
        Attribute a6 = new Attribute("highQuantile");
        Attribute a7 = new Attribute("iqr");
        Attribute a8 = new Attribute("kurtosis");
        Attribute a9 = new Attribute("f0total");
        Attribute classAttr = new Attribute("stressed", classAttributeValues);

        // create attributes list
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(a1);
        attributes.add(a2);
        attributes.add(a3);
        attributes.add(a4);
        attributes.add(a5);
        attributes.add(a6);
        attributes.add(a7);
        attributes.add(a8);
        attributes.add(a9);
        attributes.add(classAttr);

        // create instances array
        Instances wekaInstances = new Instances(name,  attributes, 0);
        wekaInstances.setClassIndex(wekaInstances.numAttributes() - 1);

        // add instances
        for(VoiceStressInstance instance : instances){
            wekaInstances.add(instance.toWekaInstance(wekaInstances));
        }

        // return object
        return wekaInstances;
    }

    /**
     * toArffFile
     * 
     * @param path 
     * @throws java.io.IOException 
     */
    public void toArffFile(String path) throws IOException {
        // get instances
        Instances instances = this.toWekaInstances();

        // create file object
        File outputFile = new File(path);

        // save arff
        ArffSaver saver = new ArffSaver();
        saver.setInstances(instances);
        saver.setFile(outputFile);
        saver.writeBatch();
    }
}
