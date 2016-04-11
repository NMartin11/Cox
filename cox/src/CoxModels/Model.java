package CoxModels;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NMartin11 on 4/7/2016.
 */
public class Model{

    /*      ----Methods-----
     *  setModel        --> takes fileName of csv as string and puts it into hashmap
     *  getModel        --> returns model
     *  getBaseLine     --> returns baseline
     *  setBaseLine     --> gets value from model and puts them into ArrayList
     *  fixModel        --> takes model and replicates missing data from previous entry
     */
    private HashMap<String, Double> model = new HashMap<>();
    private ArrayList<Double> baseline = new ArrayList<>();


    public void setModel(String csvFileName)
    {
        HashMap<String, Double> model = new HashMap<String, Double>();
        String csvFile = "C:/NewLCT/" + csvFileName +".csv";
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        try
        {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] data = line.split(cvsSplitBy);

                double value = Double.parseDouble(data[1]);
                model.put(data[0], value);
                //testing
                //System.out.println(data[0] + " " + model.get(data[0]));
            }
        }	catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        //Finished going through the file
        System.out.println("Done Reading file");

        this.model = fixModel(model);
    }


    public HashMap<String,Double> getModel()
    {
        return this.model;
    }


    public ArrayList<Double> getBaseline()
    {
        return this.baseline;
    }


    public void setBaseline()
    {
        for(int i = 0; i < 61; i++)
        {
            baseline.add(this.model.get(Integer.toString(i)));
        }
    }


    public HashMap<String, Double> fixModel(HashMap<String,Double> model)
    {
        //loop to check for null key values 0 - 60
        for(int i = 0; i < 61; i++)
        {
            //check if i is a used key in model: if not then put key and set value of previous key val
            String key = Integer.toString(i);
            boolean check = model.containsKey(key);
            if(!check)
            {
                double val = model.get(Integer.toString(i - 1));
                model.put(key, val);
            }

            //Test: check all 60 months are in model
            System.out.println(i + " " + model.get(key));
        }
        System.out.println("Fixed Model");
        return model;
    }
}
