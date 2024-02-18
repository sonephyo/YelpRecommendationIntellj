import classes.Business;
import classes.Review;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;


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
        List<Review> reviewList = new ArrayList<>();
        while ((lineReview = brReview.readLine()) != null && reviewcount <=1000) {
            Review r1 = gsonReview.fromJson(lineReview, Review.class);
            reviewList.add(r1);
            //System.out.println(r1);
            reviewcount++;

        }
        Gson gsonWrite = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter ("src/database/review1.json"))){
            gsonWrite.toJson(reviewList, writer);
        }
    }
}
