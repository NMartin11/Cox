import java.util.ArrayList;
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
        System.out.println("Enter String or Double");

        while(!str.equalsIgnoreCase("exit"))
        {
            double num;
            str = input.nextLine();
            if(str.matches(".*\\d.*"))
            {
                num = Double.parseDouble(str);
                arr.add(num);
            } else if(!str.equalsIgnoreCase("exit"))
            {
                arr.add(str);
            }
        }

        //Testing type of object
        for(Object o : arr)
        {
            System.out.println(o.getClass());
        }

        return arr;
    }
}
