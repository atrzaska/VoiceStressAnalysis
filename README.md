# VoiceStressAnalysis
Welcome to my IT masters thesis project. The goal of this project was to detect stress in human voice using techniques like:

- sound processing
- machine learning

## Idea

The general idea is as follows:

First you generate a trained model with the algorithm used in options (Options -> Options).

Next you need to a find model which performs the best on training data.

Then you can save the trained model as a weka model file on a disk (Action -> Generate Weka model).

Later you can use trained model to classify some new wav file (File -> Classify file).

You can provide a pretrained weka model and a sample wav file to classify.

You can also save weka `ARFF` file to perform experiments using different algorithms in Weka GUI.

## Dependencies

- Java
- Maven

## Compilation

    mvn clean
    mvn package

## Running the application

To start the application please run the following command:

    java -jar bin/vsa-1.0.jar

