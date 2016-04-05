import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by NMartin11 on 4/4/2016.
 */
public class Main {

    public static void main(String[] args)
    {
        prepCoeff prep = new prepCoeff();
        List<Object> coeff = new ArrayList<>();
        HashMap<String,Double> model = new HashMap<String,Double>();

        model = prep.getModel("recurrence");
    }

}
