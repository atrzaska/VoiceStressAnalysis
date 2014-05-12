/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vsa.weka;

/**
 *
 * @author mejsl_000
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        DataSet sata = new DataSet();
        sata.loadFile("output/andrzej-20140505071222.arff");
    }
    
}
