package com.example.practiceleastsquares;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.math3.linear.RealVector;
//import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression;



import android.os.Bundle;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity{

    double[] y;
    double[][] x;
    public static final int DIMENSION_ONE = 8;
    public static final int DIMENSION_TWO = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        y = new double[DIMENSION_ONE];
        for (int a=0; a<DIMENSION_ONE; a++){
            y[a]=Math.ceil(Math.random()*5);
        }

        x = new double[DIMENSION_ONE][DIMENSION_TWO]; //first is data, second is predictors
        for(int i=0; i<DIMENSION_ONE; i++){
            for (int j=0; j<DIMENSION_TWO; j++){
                x[i][j]=Math.round(Math.random());
            }
        }
        OLSMultipleLinearRegression myMLR = new OLSMultipleLinearRegression();
        myMLR.newSampleData(y,x);
        RealVector resultsVector = myMLR.calculateBeta();
        System.out.println(Arrays.toString(y));
        System.out.println(Arrays.deepToString(x));
        System.out.println(resultsVector);
        double[] tempArray = resultsVector.toArray();
        double[] resultsArray = new double[tempArray.length-1];
        for (int i=1; i<tempArray.length; i++){
            resultsArray[i-1]=tempArray[i];
        }
        System.out.println(Arrays.toString(resultsArray));
        System.out.println(getIndexOfLargest(resultsArray));
        System.out.println(getIndexOfSecondLargest(resultsArray));
        System.out.println(getIndexOfThirdLargest(resultsArray));




    }

    public int getIndexOfLargest(double[] array){
        if ( array == null || array.length == 0 ){
            return -1; // null or empty
        }
        int largest = 0;
        for ( int i = 1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest; // position of the first largest found
    }

    public int getIndexOfSecondLargest(double[] array){
        int largest;
        if ( array == null || array.length == 0 ){
            return -1; // null or empty
        }
        int max = getIndexOfLargest(array);
        if (max == 0){
            largest = 1;
        } else {
            largest = 0;
        }
        for ( int i = largest+1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] && array[i]<array[max]){
                largest = i;
            }
        }
        return largest; // position of the first largest found
    }

    public int getIndexOfThirdLargest(double[] array){
        int largest;
        if ( array == null || array.length == 0 ){
            return -1; // null or empty
        }
        int max = getIndexOfLargest(array);
        int secondMax = getIndexOfSecondLargest(array);
        if(max==0 || secondMax==0){
            if(max==1 || secondMax==1){
                largest=2;
            } else {
                largest=1;
            }
        } else {
            largest = 0;
        }

        for ( int i = largest+1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] && array[i]<array[secondMax]){
                largest = i;
            }
        }
        return largest; // position of the first largest found
    }

}