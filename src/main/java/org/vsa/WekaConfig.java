/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vsa;

import org.vsa.weka.Classification;
import weka.classifiers.Classifier;

/**
 *
 * @author mejsl_000
 */
public final class WekaConfig {

    private static WekaConfig instance = new WekaConfig();

    public static WekaConfig getInstance() {
        return instance;
    }

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(WekaConfig aInstance) {
        instance = aInstance;
    }

    private WekaConfig() {
        filterBool = false;
        algorythm = "J48";
        filter = "CFS_BestFirst";
        folds = 10;
    }

    private String algorythm;
    private String filter;
    private boolean filterBool;
    private int folds;

    public String getAlgorythm() {
        return algorythm;
    }

    /**
     * @param algorythm the algorythm to set J48 Naive Bayes Lazy IBk Random
     * Tree SMO PART Decision Table Multi Layer Kstar
     */
    public void setAlgorythm(String algorythm) {
        this.algorythm = algorythm;
    }

    /**
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param filter the filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * @return the filterBool
     */
    public boolean isFilterBool() {
        return filterBool;
    }

    /**
     * @param filterBool the filterBool to set
     */
    public void setFilterBool(boolean filterBool) {
        this.filterBool = filterBool;
    }

    /**
     * @return the folds
     */
    public int getFolds() {
        return folds;
    }

    /**
     * @param folds the folds to set
     */
    public void setFolds(int folds) {
        this.folds = folds;
    }

    /**
     * @return the classification
     */
}
