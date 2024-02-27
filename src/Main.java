import classes.Business;
import classes.Review;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Locale;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import javax.swing.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("src/database/businesses.json"));

        // Building Gson
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        String line;


        // Making the hashtable for the businesses
        Hashtable<String, Business> businessHashtable = new Hashtable<>();
        while ((line = br.readLine()) != null) {
            Business b1 = gson.fromJson(line, Business.class);
            businessHashtable.put(b1.getBusiness_id(), b1);
        }

        // For review
        BufferedReader brReview = new BufferedReader(new FileReader("src/database/reviews.json"));

        GsonBuilder gbReview = new GsonBuilder();
        Gson gsonReview = gbReview.create();

        String lineReview;
        int reviewcount = 0;
        int reviewLengthToParse = 10000;

        Review[] reviewList = new Review[reviewLengthToParse];

        while ((lineReview = brReview.readLine()) != null && reviewcount < reviewLengthToParse) {
            Review r1 = gsonReview.fromJson(lineReview, Review.class);
            r1.setBusiness_name(businessHashtable.get(r1.getBusiness_id()).getName());
            reviewList[reviewcount] = r1;
            reviewcount++;
        }

        String userInput = "LEX Nightclub";
        String userInputStoreReview = searchForStore(reviewList, userInput);

//        System.out.println(userInputStoreReview);

        reviewList = Arrays.stream(reviewList)
                .filter(s -> !(s.getBusiness_name().equalsIgnoreCase(userInput)))
                .toArray(Review[]::new);

        String[] inputSplit = cleanString(userInputStoreReview);
        System.out.println(Arrays.toString(inputSplit));

        int[] dfCount = new int[inputSplit.length];
        for(Review review: reviewList) {
            review.init_FreqTableForEachReview(inputSplit);
            String[] cleanReviewData = cleanString(review.getReview_text());


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
        }

        for(Review r:reviewList) {
            r.setTotalWeight(calculateWeight(r.getCountOfEachWord(),dfCount,reviewLengthToParse));
            System.out.println(Arrays.toString(r.getCountOfEachWord()));
            System.out.println(Arrays.toString(justTesting(r.getCountOfEachWord(), dfCount, reviewLengthToParse)));
        }

        Arrays.sort(reviewList, new Comparator<Review>() {
            @Override
            public int compare(Review r1, Review r2) {
                return Double.compare(r2.getTotalWeight(), r1.getTotalWeight());
            }
        });


        // Output Number
        int outputNumber = 3;
        for (int i = 0; i < outputNumber; i++) {
            System.out.println("__________");
            System.out.println(reviewList[i].getTotalWeight());
            System.out.println(Arrays.toString(reviewList[i].getContainsWord()));
            System.out.println(Arrays.toString(reviewList[i].getCountOfEachWord()));
            Business businessOutput = businessHashtable.get(reviewList[i].getBusiness_id());
            System.out.println(businessOutput.getName());
        }

    }

    private static double calculateWeight(int[] tfData,int[] dfData, int totalReview) {
        double total = 0;
        for (int i = 0; i < tfData.length; i++) {
            total += Math.log10(1+tfData[i])*((double) totalReview /(dfData[i]+1));
        }
        return total;
    }

    private static double[] justTesting(int[] tfData,int[] dfData, int totalReview) {
        double[] total = new double[tfData.length];
        for (int i = 0; i < tfData.length; i++) {
            total[i] =  Math.log10(1+tfData[i])*((double) totalReview /(dfData[i]+1));
        }
        return total;
    }




    private static String[] cleanString(String rawString) {
//        System.out.println("______Raw String________");
//
//        System.out.println(rawString);
        rawString = rawString.replaceAll("[^a-zA-Z']", " ");


        //rawString = rawString.replaceAll("\\b(to|a|the|and|there|in|is|are|for|I|we|on|would|have)\\b", " ");
        rawString = rawString.toLowerCase();
//        System.out.println("--------To lower case------");
//
//        System.out.println(rawString);
        try
            {
                String wordTxt = Files.readString(Paths.get("Library/eng.txt"), Charset.defaultCharset());
                String[] words = wordTxt.split("\\s");
                for (String word : words){
                    word = word.toLowerCase();
                    rawString = rawString.replaceAll("\\b" + word +"\\b", "");
                }
//                System.out.println("--Modified string----");
//
//                System.out.println(rawString);
//                System.out.println("----");
            } catch (IOException e){
            e.printStackTrace();
        }
        return Arrays.stream(rawString.split("\\s+")).filter(s -> !s.isEmpty()).toArray(String[]::new);
    }

    private static String searchForStore(Review[] reviewList, String userInput) {
        for (Review r: reviewList) {
            if (r.getBusiness_name().equalsIgnoreCase(userInput)) {
                return r.getReview_text();
            }
        }
        return null;
    }

}