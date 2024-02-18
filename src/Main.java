import classes.Business;
import classes.Review;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Locale;
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
        int reviewLengthToParse = 10000;

        Review[] reviewList = new Review[reviewLengthToParse];

        while ((lineReview = brReview.readLine()) != null && reviewcount < reviewLengthToParse) {
            Review r1 = gsonReview.fromJson(lineReview, Review.class);
            reviewList[reviewcount] = r1;
            reviewcount++;
        }

        long startTime = System.nanoTime();

        String userInput = "asian food";
        String[] inputSplit = cleanString(userInput);
//        System.out.println(Arrays.toString(inputSplit));


        int[] dfCount = new int[inputSplit.length];
        for(Review review: reviewList) {
            review.init_FreqTableForEachReview(inputSplit);
            String[] cleanReviewData = cleanString(review.getReview_text());
//            System.out.println(Arrays.toString(cleanReviewData));


            for(String i: cleanReviewData) {
                for(int j = 0; j < inputSplit.length; j++) {
                    if (inputSplit[j].equalsIgnoreCase(i)){
                        review.incrementCountOfEach(j);
                        review.setTrueContainsWord(j);
                    }
                }
            }
            for(int i = 0; i < review.getContainsWord().length; i++) {
                if(review.getContainsWord()[i]) {
                    dfCount[i]++;
                }
            }

//            System.out.println(Arrays.toString(review.getContainsWord()));
//            System.out.println(Arrays.toString(review.getCountOfEachWord()));
        }
//        System.out.println(Arrays.toString(dfCount));

        for(Review r:reviewList) {
            r.setTotalWeight(calculateWeight(r.getCountOfEachWord(),dfCount,reviewLengthToParse));
//            System.out.println(r.getTotalWeight());
        }

        Arrays.sort(reviewList, new Comparator<Review>() {
            @Override
            public int compare(Review r1, Review r2) {
                return Double.compare(r2.getTotalWeight(), r1.getTotalWeight());
            }
        });

        long endTime = System.nanoTime();

        long totalTime = endTime - startTime;
        double milliseconds = totalTime / 1e6;
        System.out.println("--------------Elapsed time: " + milliseconds + " milliseconds");

        for (int i = 0; i < 3; i++) {
            System.out.println(reviewList[i].getTotalWeight());
            System.out.println(reviewList[i]);
            System.out.println(Arrays.toString(reviewList[i].getContainsWord()));
            System.out.println(Arrays.toString(reviewList[i].getCountOfEachWord()));

        }







    }

    private static double calculateWeight(int[] tfData,int[] dfData, int totalReview) {
        double total = 0;
        for (int i = 0; i < tfData.length; i++) {
            total += Math.log10(1+tfData[i])*((double) totalReview /dfData[i]);
        }
        return total;
    }



    private static String[] cleanString(String rawString) {
        rawString = rawString.replaceAll("[^a-zA-Z]", " ");
        rawString = rawString.replaceAll("\\b(to|a|the|and|there|in|is|are|for|I|we|on|would|have)\\b", " ");
        rawString = rawString.toLowerCase(Locale.ROOT);


        return Arrays.stream(rawString.split("\\s+")).filter(s -> !s.isEmpty()).toArray(String[]::new);

    }
}
