import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

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
            //System.out.println(i + " " + model.get(key));
        }
        System.out.println("Fixed Model");
        return model;
    }

    //TODO: SUB HASHMAP WITH BASELINE VALUES FROM MODEL
    public String calculate(HashMap<String, Double> m, double sum)
    {
        String results = "[";

        for(int i = 0; i < 61; i++)	//iterates 60 times --> each time is a month --> total of 5 years
        {
            String key = Integer.toString(i);

            //---Test loop: checks if file has 60 months
            System.out.println("key: " + key);

            //TODO; get baseline value in array corresponding to months in order
            //TODO; USE FOR EACH LOOP ON BASELINE ARRAY
            double monthval = m.get(key);
            double answer = 0;
            answer =  ( Math.exp( -( 1- monthval) * Math.exp(sum))); // calculates survival rate --> age multiplied by index value

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


    public final boolean containsDigit(String s)
    {
        boolean containsDigit = false;
        if(s != null && !s.isEmpty()){
            for(char c : s.toCharArray()){
                if(containsDigit = Character.isDigit(c)){
                    return containsDigit = true;
                }
            }
        }
        return containsDigit;
    }


}
