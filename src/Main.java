import classes.Business;
import classes.Review;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("src/database/businesses.json"));
//        BufferedReader br = new BufferedReader(new FileReader("src/database/oneData.json"));

        // Building Gson

        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        String line;

        // Making the hashtable for the businesses
        int count = 0;
        Hashtable<String, Business> businessHashtable = new Hashtable<>();
        while ((line = br.readLine()) != null && count <=10000) {
            Business b1 = gson.fromJson(line, Business.class);
            businessHashtable.put(b1.getBusiness_id(), b1);
            count++;
        }

        BufferedReader brReview = new BufferedReader(new FileReader("src/database/reviews.json"));

        GsonBuilder gbReview = new GsonBuilder();
        Gson gsonReview = gbReview.create();

        String lineReview;
        int reviewcount = 0;
        while ((lineReview = brReview.readLine()) != null && reviewcount <=1000) {
            Review r1 = gsonReview.fromJson(lineReview, Review.class);
            System.out.println(r1);
            reviewcount++;
            System.out.println("hello world");
        }





    }
}
