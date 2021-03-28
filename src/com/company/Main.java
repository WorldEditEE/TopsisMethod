package com.company;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class Main {

    public static void main(String[] args) {

        double[] minValue = {1,1,1,1,1};
        double[] maxValue = {0,0,0,0,0};
        DecimalFormat df = new DecimalFormat("#.###");
        String [] objectNames = {"Unity","UE4","Godot","CryEngine","GameMaker"};

        double[] EasyOfUse = { 0.9 , 0.7 , 1 , 0.5 , 1,}; //вес == 0,6
        double[] mobility = { 0.8 , 0.8 , 0.9 , 0.6 , 1,}; //вес == 0,5
        double[] effciency = { 0.9 , 1 , 0.6 , 0.8 , 0.5,}; //вес == 0,8
        double[] functionally = { 0.9 , 1 , 0.7 , 0.8 , 0.4,}; //вес == 1
        double[] practicality = { 0.9 , 0.7 , 0.9 , 0.5 , 1,}; //вес == 0,6
        double[] finallySort = {0 , 0 , 0, 0 ,0 };

        double[] weightage = {0.2,0.1,0.3,0.3,0.1};

        NormalView(EasyOfUse,mobility,effciency,functionally,practicality,weightage,objectNames);

        CalculateNormalisedMatrix(EasyOfUse,mobility,effciency,functionally,practicality,weightage,objectNames);

        CalculateNormalisedMatrixWeightage(EasyOfUse,mobility,effciency,functionally,practicality,weightage,objectNames);

        CalculateIdealAndWorst(EasyOfUse,mobility,effciency,functionally,practicality,weightage,objectNames,finallySort);


    }


    // Нормализованный вид
    public static void NormalView(double[] EasyOfUse,double[] mobility,double[] effciency,double[] functionally,double[] practicality,double[] weightage,String [] objectNames){

        System.out.println(Arrays.toString(weightage)+"\n Удб  Моб  Эфф  Фун  Прк");
        for(int i = 0 ; i < 5 ; i++){
            System.out.println(" " + EasyOfUse[i] + "  " + mobility[i] + "  " + effciency[i] + "  " + functionally[i] + "  " + practicality[i] + "  " + objectNames[i]);
        }

        System.out.println("_________________________________________________________________________________");

    }


    private static void CalculateNormalisedMatrix(double[] easyOfUse, double[] mobility, double[] effciency, double[] functionally, double[] practicality, double[] weightage, String[] objectNames) {

        DecimalFormat df = new DecimalFormat("#.##");
        double r =  pow((((pow(easyOfUse[0],2)) + (pow(easyOfUse[1],2)) + (pow(easyOfUse[2],2)) + (pow(easyOfUse[3],2)) + (pow(easyOfUse[4],2)))),0.5);
        for(int i = 0; i < 5 ; i++){
            easyOfUse[i] = easyOfUse[i] / r;
        }

        r = pow((((pow(mobility[0],2)) + (pow(mobility[1],2)) + (pow(mobility[2],2)) + (pow(mobility[3],2)) + (pow(mobility[4],2)))),0.5);
        for(int i = 0; i < 5 ; i++){
            mobility[i] = mobility[i] / r;
        }

        r = pow((((pow(effciency[0],2)) + (pow(effciency[1],2)) + (pow(effciency[2],2)) + (pow(effciency[3],2)) + (pow(effciency[4],2)))),0.5);
        for(int i = 0; i < 5 ; i++){
            effciency[i] = effciency[i] / r;
        }

        r = pow((((pow(functionally[0],2)) + (pow(functionally[1],2)) + (pow(functionally[2],2)) + (pow(functionally[3],2)) + (pow(functionally[4],2)))),0.5);
        for(int i = 0; i < 5 ; i++){
            functionally[i] = functionally[i] / r;
        }

        r = pow((((pow(practicality[0],2)) + (pow(practicality[1],2)) + (pow(practicality[2],2)) + (pow(practicality[3],2)) + (pow(practicality[4],2)))),0.5);
        for(int i = 0; i < 5 ; i++){
            practicality[i] = practicality[i] / r;
        }


        System.out.println("Нормализованная матрица : \n" + "\n Удб   Моб    Эфф   Фун   Прк");
        for(int i = 0 ; i < 5 ; i++){
            System.out.println(" " + df.format(easyOfUse[i]) + "  " + df.format(mobility[i]) + "   " + df.format(effciency[i]) + "  " + df.format(functionally[i]) + "  " + df.format(practicality[i]) + "  " + objectNames[i]);
        }

        System.out.println("_________________________________________________________________________________");

    }


    private static void CalculateNormalisedMatrixWeightage(double[] easyOfUse, double[] mobility, double[] effciency, double[] functionally, double[] practicality, double[] weightage, String[] objectNames) {

        DecimalFormat df = new DecimalFormat("#.##");

        for(int i = 0 ; i < 5 ; i++){
            easyOfUse[i] = easyOfUse[i] * weightage[0];
            mobility[i] = mobility[i] * weightage[1];
            effciency[i] = effciency[i] * weightage[2];
            functionally[i] = functionally[i] * weightage[3];
            practicality[i] = practicality[i] * weightage[4];
        }

        System.out.println("Взвешенная Нормализованная матрица : \n" +"\n Удб   Моб    Эфф   Фун   Прк");
        for(int i = 0 ; i < 5 ; i++){
            System.out.println(" " + df.format(easyOfUse[i]) + "  " + df.format(mobility[i]) + "   " + df.format(effciency[i]) + "  " + df.format(functionally[i]) + "  " + df.format(practicality[i]) + "  " + objectNames[i]);
        }

        System.out.println("_________________________________________________________________________________");


    }

    private static void CalculateIdealAndWorst(double[] easyOfUse, double[] mobility, double[] effciency, double[] functionally, double[] practicality, double[] weightage, String[] objectNames,double[] finnalySort) {

        Double [] Engines = new Double[objectNames.length];
        DecimalFormat df = new DecimalFormat("#.##");

        double[] minValue = {1,1,1,1,1};
        double[] maxValue = {0,0,0,0,0};


        for(int i = 0 ; i < 5 ; i++){
            // худшая альтернатива
            if(easyOfUse[i] < minValue[0]){
                minValue[0] = easyOfUse[i];
            }
            if(mobility[i] < minValue[1]){
                minValue[1] = mobility[i];
            }
            if(effciency[i] < minValue[2]){
                minValue[2] = effciency[i];
            }
            if(functionally[i] < minValue[3]){
                minValue[3] = functionally[i];
            }
            if(practicality[i] < minValue[4]){
                minValue[4] = practicality[i];
            }

        }

        for(int i = 0 ; i < 5 ; i++){
            // лучшая альтернатива
            if(easyOfUse[i] > maxValue[0]){
                maxValue[0] = easyOfUse[i];
            }
            if(mobility[i] > maxValue[1]){
                maxValue[1] = mobility[i];
            }
            if(effciency[i] > maxValue[2]){
                maxValue[2] = effciency[i];
            }
            if(functionally[i] > maxValue[3]){
                maxValue[3] = functionally[i];
            }
            if(practicality[i] > maxValue[4]){
                maxValue[4] = practicality[i];
            }

        }

        System.out.printf("%42sУдобство%14sМобильность%10sЭффективность%7sФункциональность%4sПрактичность\n"," "," "," "," "," ");
        System.out.println("Лучшие альтернативы в каждом из столбцов :"+Arrays.toString(maxValue));
        System.out.println("Худшие альтернативы в каждом из столбцов :"+Arrays.toString(minValue));

        System.out.println("_________________________________________________________________________________");

        double[] sPlus  = new double[objectNames.length];
        double[] sMinus = new double[objectNames.length];

        for(int i = 0 ; i < 5 ; i++){

             sPlus[i] = pow((pow((easyOfUse[i] - maxValue[0]),2) + pow((mobility[i] - maxValue[1]),2) + pow((effciency[i] - maxValue[2]),2) + pow((functionally[i] - maxValue[3]),2) + pow((practicality[i] - maxValue[4]),2)),0.5);
             sMinus[i] = pow((pow((easyOfUse[i] - minValue[0]),2) + pow((mobility[i] - minValue[1]),2) + pow((effciency[i] - minValue[2]),2) + pow((functionally[i] - minValue[3]),2) + pow((practicality[i] - minValue[4]),2)),0.5);

        }

        double[] Ci = new double[objectNames.length];
        String[] place = new String[objectNames.length];

        for(int i = 0 ; i < 5; i++){


            Ci[i] = sMinus[i] / (sPlus[i] + sMinus[i]);
            Engines[i] = Ci[i];
        }

        boolean sorted = false;
        Double temp;
        String placeIn;


        System.out.println(Arrays.toString(Engines));





        System.out.printf("Конечная матрица : \n" +"\n Удб   Моб    Эфф   Фун   Прк%15sS+%25sS-%25sCi\n"," "," "," ");
        for(int i = 0 ; i < 5 ; i++){
            System.out.printf(" " + df.format(easyOfUse[i]) + "  " + df.format(mobility[i]) + "   " + df.format(effciency[i]) + "  " + df.format(functionally[i]) + "  " + df.format(practicality[i]) + "  " + objectNames[i] + " %7s" + (sPlus[i]) + " %7s" + (sMinus[i])+" %7s "+Ci[i] +"\n"," "," "," ");
        }

        System.out.println("_________________________________________________________________________________");

        String finalString = "";

        for(int i = 0 ; i < 5 ; i++){
            System.out.println(objectNames[i] = objectNames[i] +" - Эффективность :  "+ Ci[i]+ "\n");

        }
















    }









}
