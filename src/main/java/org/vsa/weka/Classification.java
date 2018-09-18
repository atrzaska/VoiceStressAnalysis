package org.vsa.weka;

import org.vsa.WekaConfig;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.lazy.KStar;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;

public class Classification {

    //classify with default option
    public J48 classifyJ48(Instances data) throws Exception {
        J48 tree = new J48();
        tree.buildClassifier(data);
        return tree;

    }

    //clasify with set options
    public J48 classifyJ48(Instances data, String[] options) throws Exception {
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(data);
        return tree;
    }

    public NaiveBayes classifyNaiveBayes(Instances data) throws Exception {
        NaiveBayes bayes = new NaiveBayes();
        bayes.buildClassifier(data);
        return bayes;
    }

    public NaiveBayes classifyNaiveBayes(Instances data, String[] options) throws Exception {
        NaiveBayes bayes = new NaiveBayes();
        bayes.setOptions(options);
        bayes.buildClassifier(data);
        return bayes;
    }

    public IBk classifyIBk(Instances data) throws Exception {
        IBk ibk = new IBk();
        ibk.setKNN(10);
        ibk.buildClassifier(data);
        return ibk;
    }

    public IBk classifyIBk(Instances data, String[] options) throws Exception {
        IBk ibk = new IBk();
        ibk.setOptions(options);
        ibk.buildClassifier(data);
        return ibk;
    }

    public RandomTree classifyRandomTree(Instances data) throws Exception {
        RandomTree rngTree = new RandomTree();
        rngTree.buildClassifier(data);
        return rngTree;
    }

    public RandomTree classifyRandomTree(Instances data, String[] options) throws Exception {
        RandomTree rngTree = new RandomTree();
        rngTree.setOptions(options);
        rngTree.buildClassifier(data);
        return rngTree;
    }

    public SMO classifySMO(Instances data) throws Exception {
        SMO smo = new SMO();
        smo.buildClassifier(data);
        return smo;
    }

    public SMO classifySMO(Instances data, String[] options) throws Exception {
        SMO smo = new SMO();
        smo.setOptions(options);
        smo.buildClassifier(data);
        return smo;
    }

    public PART classifyPART(Instances data) throws Exception {
        PART part = new PART();
        part.buildClassifier(data);
        return part;
    }

    public PART classifyPART(Instances data, String[] options) throws Exception {
        PART part = new PART();
        part.setOptions(options);
        part.buildClassifier(data);
        return part;
    }

    public DecisionTable classifyDecisionTable(Instances data) throws Exception {
        DecisionTable table = new DecisionTable();
        table.buildClassifier(data);
        return table;
    }

    public DecisionTable classifyDecisionTable(Instances data, String[] options) throws Exception {
        DecisionTable table = new DecisionTable();
        table.setOptions(options);
        table.buildClassifier(data);
        return table;
    }

    public MultilayerPerceptron classifyMultiLayer(Instances data) throws Exception {
        MultilayerPerceptron layer = new MultilayerPerceptron();
        layer.buildClassifier(data);
        return layer;
    }

    public MultilayerPerceptron classifyMultiLayer(Instances data, String[] options) throws Exception {
        MultilayerPerceptron layer = new MultilayerPerceptron();
        layer.setOptions(options);
        layer.buildClassifier(data);
        return layer;
    }

    public KStar classifyKStar(Instances data) throws Exception {
        KStar star = new KStar();
        star.buildClassifier(data);
        return star;
    }

    public KStar classifyKStar(Instances data, String[] options) throws Exception {
        KStar star = new KStar();
        star.setOptions(options);
        star.buildClassifier(data);
        return star;
    }

    public double classifyNewFromFile(Classifier train, Instances unlabeled) throws Exception {

        Instances labeled = new Instances(unlabeled);
        double clsLabel = 3;
        for (int i = 0; i < labeled.numInstances(); i++) {
            clsLabel = train.classifyInstance(unlabeled.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
        }

        return clsLabel;
    }

    public void classifyNewFromFileUsingModel(String path, Instances unlabeled) throws Exception {

        Classifier train = (Classifier) weka.core.SerializationHelper.read(path);

        Instances labeled = new Instances(unlabeled);
        double clsLabel = 0;
        for (int i = 0; i < labeled.numInstances(); i++) {
            clsLabel = train.classifyInstance(unlabeled.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
        }

        System.out.println("Wyswietl instancje " + clsLabel);
    }

    /*
     CSF greedy
     CSF best first
     Filtered CSF greedy
     Filtered CSF best first
     Consistency greedy
     Consistency best first
     */
    public Classifier classifyForModel(Instances inst) throws Exception {

        Instances data = inst;
        String summary = "";
        WekaConfig conf = WekaConfig.getInstance();
        String algorithm = conf.getAlgorithm();
        Classifier clas = null;

        switch (algorithm) {
            case "J48":
                summary += "J48 \n";
                clas = classifyJ48(data);
                break;
            case "Naive Bayes":
                summary += "Naive Bayes \n";
                clas = classifyNaiveBayes(data);
                break;
            case "Lazy IBk":
                summary += "Lazy IBk \n";
                clas = classifyIBk(data);
                break;
            case "Random Tree":
                summary += "Random Tree \n";
                clas = classifyRandomTree(data);
                break;
            case "SMO":
                summary += "SMO \n";
                clas = classifySMO(data);
                break;
            case "PART":
                summary += "PART \n";
                clas = classifyPART(data);
                break;
            case "Decision Table":
                summary += "Decision Table \n";
                clas = classifyDecisionTable(data);
                break;
            case "Multi Layer":
                summary += "Multi Layer \n";
                clas = classifyMultiLayer(data);
                break;
            case "Kstar":
                summary += "Kstar \n";
                clas = classifyKStar(data);
                break;
        }
        return clas;
    }

    public String classifyMyInstances(Instances inst) throws Exception {
        Instances data = inst;
        String summary = "";
        WekaConfig conf = WekaConfig.getInstance();
        String algorithm = conf.getAlgorithm();
        Classifier clas = null;

        if (conf.isFilterBool()) {
            FilterSet filtr = new FilterSet();
            switch (conf.getFilter()) {
                case "CSF greedy":
                    data = filtr.filterCFS_Greedy(inst);
                    break;
                case "CSF best first":
                    data = filtr.filterCFS_BestFirst(inst);
                    break;
                case "Filtered CSF greedy":
                    data = filtr.filterFilteredCSF_Greedy(inst);
                    break;
                case "Filtered CSF best first":
                    data = filtr.filterFilteredCSF_BestFirst(inst);
                    break;
                case "Consistency greedy":
                    data = filtr.filterConsinstency_Greedy(inst);
                    break;
                case "Consistency best first":
                    data = filtr.filterConsinstency_BestFirst(inst);
                    break;
            }
        }

        switch (algorithm) {
            case "J48":
                summary += "J48 \n";
                clas = classifyJ48(data);
                break;
            case "Naive Bayes":
                summary += "Naive Bayes \n";
                clas = classifyNaiveBayes(data);
                break;
            case "Lazy IBk":
                summary += "Lazy IBk \n";
                clas = classifyIBk(data);
                break;
            case "Random Tree":
                summary += "Random Tree \n";
                clas = classifyRandomTree(data);
                break;
            case "SMO":
                summary += "SMO \n";
                clas = classifySMO(data);
                break;
            case "PART":
                summary += "PART \n";
                clas = classifyPART(data);
                break;
            case "Decision Table":
                summary += "Decision Table \n";
                clas = classifyDecisionTable(data);
                break;
            case "Multi Layer":
                summary += "Multi Layer \n";
                clas = classifyMultiLayer(data);
                break;
            case "Kstar":
                summary += "Kstar \n";
                clas = classifyKStar(data);
                break;
        }

        summary += "\n";
        summary += "---------Classification-------------- \n";
        summary += clas.toString();
        Evaluate eval = new Evaluate();
        Evaluation evalu = eval.crossValidation(clas, data, conf.getFolds());
        summary += "----------Evaluation---------------- \n";
        summary += evalu.toSummaryString();
        summary += evalu.toMatrixString();

        return summary;
    }
}
