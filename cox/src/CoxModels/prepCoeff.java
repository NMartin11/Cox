package CoxModels;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by NMartin11 on 4/5/2016.
 */
public class prepCoeff {

    /*  ------Methods------
     *  loadCoefficients    --> user input: first coefficient name then value used with coefficient
     *  Example: agedx: 1   stageiiib: 1--> 1 = coefficient being used or enter number to multiply by value of coefficient in model
     *  setBaseline         --> sets baseline to the array you pass as parameter
     *  calculate           --> takes sum from calcSum method and calculates survival rate
     *  calcSum             --> calculates sum of coefficients with corresponding model
     *
     */
    private List<Object> modelCoeff;
    private List<Double> baseline = new ArrayList<Double>();

    public List<Object> loadCoefficients()
    {
        List<Object> arr = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String str = "";
        System.out.println("Enter Coefficient Name then Enter 1 signifying its use: type exit to stop");

        while(!str.equalsIgnoreCase("exit"))
        {
            double num;
            if(input.hasNextDouble())
            {
                String temp = input.nextLine();
                System.out.println("Is a Double");
                num = Double.parseDouble(temp);
                arr.add(num);
            } else if(input.hasNext())
            {
                str = input.nextLine();
                if(!str.equalsIgnoreCase("exit"))
                {
                    System.out.println("Is a String");
                    arr.add(str);
                }
            }
        }
        //Testing type of object
        for(Object o : arr)
        {
            System.out.println(o.getClass());
        }
       return this.modelCoeff = arr;
    }

    public void setBaseline(ArrayList<Double> base)
    {
        this.baseline = base;
    }

    public Double calcSum(List<Object> p, HashMap<String, Double> m)
    {
        String str = "";
        double val,val2;
        double sum = 0;
        //adds coefficient values being used
        for(int i = 0; i < p.size(); i++)
        {
            str = p.get(i).toString();
            i++;
            val = m.get(str);
            val2 = Double.parseDouble(p.get(i).toString());
            System.out.println("val = " + val + " " + "val2 = " + val2);
            sum += val * val2;
        }

        System.out.println("Sum = " + sum);
        return sum;
    }

    public String calculate(double sum)
    {
        String results = "[";

        for(int i = 0; i < 61; i++)	//iterates 60 times --> each time is a month --> total of 5 years
        {
            double answer = 0;
            answer =  ( Math.exp( -( 1- baseline.get(i)) * Math.exp(sum))); // calculates survival rate --> age multiplied by index value

            //Formats value of answer to 10 decimal places
            DecimalFormat df = new DecimalFormat("#.##########");
            String val = df.format(answer);

            //Creates a string to pass to javascript in tracking.jsp page
            //i being the month and val being survival curve
            if(i < 60)
            {
                results += "[" + i + "," + " " + val + "],";
            }
            else
            {
                results += "[" + i + "," + " " + val + "]]";
            }
        }
        return results;
    }

}
