import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by NMartin11 on 4/5/2016.
 */
public class prepCoeff {

    public List<Object> loadCoefficients()
    {
        List<Object> arr = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String str = "";
        System.out.println("Enter String or Double: type exit to stop");

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

        return arr;
    }

    public HashMap<String, Double> getModel(String fileName)
    {
        HashMap<String, Double> model = new HashMap<String, Double>();
        String csvFile = "C:/NewLCT/" + fileName +".csv";
        BufferedReader br = null;
        String line = "";
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
                System.out.println(data[0] + " " + model.get(data[0]));

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

       model = fixModel(model);

        return model;
    }

    public HashMap<String, Double> fixModel(HashMap<String,Double> model)
    {
        //loop to check for null key values 0 - 60
        for(int i = 0; i < 61; i++)
        {
            //check if i is a used key in model: if not then put key and set value of previous key val
            String key = Integer.toString(i);
            boolean check = model.containsKey(key);
            if(check == false)
            {
                double val = model.get(Integer.toString(i - 1));
                model.put(key, val);
            }

            //Test: check all 60 months are in model
            System.out.println(i + " " + model.get(key));
        }
        return model;
    }


}
