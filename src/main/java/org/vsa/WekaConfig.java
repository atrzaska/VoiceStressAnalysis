package org.vsa;

/**
 * WekaConfig
 */
public final class WekaConfig {

    /**
     * algorithm
     */
    private String algorithm;
    
    /**
     * filter
     */
    private String filter;
    
    /**
     * filterBool
     */
    private boolean filterBool;
    
    /**
     * folds
     */
    private int folds;

    /**
     * instance
     */
    private static WekaConfig instance = new WekaConfig();

    /**
     * getInstance
     * 
     * @return 
     */
    public static WekaConfig getInstance() {
        return instance;
    }

    /**
     * setInstance
     * 
     * @param aInstance the instance to set
     */
    public static void setInstance(WekaConfig aInstance) {
        instance = aInstance;
    }

    /**
     * Default constructor.
     */
    private WekaConfig() {
        filterBool = false;
        algorithm = "J48";
        filter = "CFS_BestFirst";
        folds = 10;
    }

    /**
     * getAlgorithm
     * 
     * @return 
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * setAlgorythm
     * 
     * @param algorithm the algorithm to set J48 Naive Bayes Lazy IBk Random
 Tree SMO PART Decision Table Multi Layer Kstar
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * getFilter
     * 
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * setFilter
     * 
     * @param filter the filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * isFilterBool
     * 
     * @return the filterBool
     */
    public boolean isFilterBool() {
        return filterBool;
    }

    /**
     * setFilterBool
     * 
     * @param filterBool the filterBool to set
     */
    public void setFilterBool(boolean filterBool) {
        this.filterBool = filterBool;
    }

    /**
     * getFolds
     * 
     * @return the folds
     */
    public int getFolds() {
        return folds;
    }

    /**
     * setFolds
     * 
     * @param folds the folds to set
     */
    public void setFolds(int folds) {
        this.folds = folds;
    }
}
