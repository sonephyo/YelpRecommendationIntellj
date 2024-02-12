package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Business {

    private String business_id;
    private String name;
    private String address;
    private String state;
    private String postal_code;

    private double stars;

    public double getStars() {
        return stars;
    }

    private ArrayList<String> reviews;

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    @Override
    public String toString() {
        return "Business{" +
                "business_id='" + business_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", stars=" + stars +
                ", reviews=" + reviews +
                '}';
    }
}

