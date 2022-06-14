package com.example.practiceleastsquares;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.math3.linear.RealVector;
//import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import org.apache.commons.math3.stat.regression.AbstractMultipleLinearRegression;



import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    double[] y;
    double[][] x;
    public int dimension_one;
    public int dimension_two;
    PersonData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        user = new PersonData(new ArrayList<String>(Arrays.asList("Stretch", "Eat Healthy", "Meditate")));
        user.addData(4.0, new ArrayList<Double>(Arrays.asList(0.0, 0.0, 1.0)), new Date());
        user.addData(3.0, new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0)), new Date());
        user.addData(2.0, new ArrayList<Double>(Arrays.asList(0.0, 0.0, 1.0)), new Date());
        user.addData(4.0, new ArrayList<Double>(Arrays.asList(1.0, 1.0, 0.0)), new Date());
        user.addData(5.0, new ArrayList<Double>(Arrays.asList(1.0, 0.0, 0.0)), new Date());
        user.addData(5.0, new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0)), new Date());
        user.addData(4.0, new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0)), new Date());
        user.addData(4.0, new ArrayList<Double>(Arrays.asList(1.0, 0.0, 1.0)), new Date());
        user.addData(1.0, new ArrayList<Double>(Arrays.asList(0.0, 1.0, 1.0)), new Date());
        user.addData(5.0, new ArrayList<Double>(Arrays.asList(1.0, 1.0, 1.0)), new Date());
        user.addData(2.0, new ArrayList<Double>(Arrays.asList(0.0, 1.0, 0.0)), new Date());

        dimension_one = user.moods.size();
        dimension_two = user.habitTracker.get(0).size();


        y = new double[dimension_one];
        for (int a=0; a<dimension_one; a++){
            y[a]=user.moods.get(a);
        }

        x = new double[dimension_one][dimension_two]; //first is data, second is predictors
        for(int i=0; i<dimension_one; i++){
            for (int j=0; j<dimension_two; j++){
                x[i][j]=user.habitTracker.get(i).get(j);
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
        System.out.println(user.habits.get(getIndexOfLargest(resultsArray)));
        System.out.println(user.habits.get(getIndexOfSecondLargest(resultsArray)));
        System.out.println(user.habits.get(getIndexOfThirdLargest(resultsArray)));





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

