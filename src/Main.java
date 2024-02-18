import classes.Business;
import classes.Review;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        int reviewLengthToParse = 100;

        Review[] reviewList = new Review[reviewLengthToParse];
        while ((lineReview = brReview.readLine()) != null && reviewcount < reviewLengthToParse) {
            Review r1 = gsonReview.fromJson(lineReview, Review.class);
//            System.out.println(r1);
            reviewList[reviewcount] = r1;
            reviewcount++;
        }

        String userInput = "a";
        String[] inputSplit = cleanString(userInput);

//        System.out.println(reviewList[1].getReview_text());

//        for (Review r: reviewList) {
//            System.out.println(r.getReview_text());
//        }


        String reviewRaw = reviewList[1].getReview_text();

        //*** Data Cleaning ***//
        String[] clearString = cleanString(reviewRaw);

        int sameCount = 0;
        for(String i: clearString) {
            for (String j: inputSplit) {
                if (j.equalsIgnoreCase(i)) {
                    sameCount++;
                }
            }
        }


    }

    private static String[] cleanString(String rawString) {
        rawString = rawString.replaceAll("[^a-zA-Z0-9]", " ");

        return rawString.split("\\s+");
    }
}
