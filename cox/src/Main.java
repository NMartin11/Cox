

import CoxModels.Model;
import CoxModels.prepCoeff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by NMartin11 on 4/4/2016.
 */
public class Main {

    public static void backgroundSurvivalRate()
    {
        Model background = new Model();
        background.setModel("background");
        background.setBaseline();
        HashMap<String,Double> model = background.getModel();
        ArrayList<Double> baseline = background.getBaseline();
        prepCoeff prep = new prepCoeff();
        prep.setBaseline(baseline);
        List<Object> coeff = prep.loadCoefficients();
        String results = prep.calculate(prep.calcSum(coeff,model));

        System.out.println("Results " + results);
    }

    public static void limitedSurvivalRate()
    {
        Model limited = new Model();
        limited.setModel("limited");
        limited.setBaseline();
        HashMap<String,Double> model = limited.getModel();
        ArrayList<Double> baseline = limited.getBaseline();
        prepCoeff prep = new prepCoeff();
        prep.setBaseline(baseline);
        List<Object> coeff = prep.loadCoefficients();
        String results = prep.calculate(prep.calcSum(coeff,model));

        System.out.println("Results " + results);
    }

    public static void extensiveSurvivalRate()
    {
        Model extensive = new Model();
        extensive.setModel("extensive");
        extensive.setBaseline();
        HashMap<String,Double> model = extensive.getModel();
        ArrayList<Double> baseline = extensive.getBaseline();
        prepCoeff prep = new prepCoeff();
        prep.setBaseline(baseline);
        List<Object> coeff = prep.loadCoefficients();
        String results = prep.calculate(prep.calcSum(coeff,model));

        System.out.println("Results " + results);
    }



    public static void main(String[] args) {

        //backgroundSurvivalRate();
        //limitedSurvivalRate();
        extensiveSurvivalRate();
    }
}


