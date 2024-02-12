package classes;

public class Review {

    private String business_id;

    private double stars;
    private String text;

    public String getBusiness_id() {;
        return business_id;
    }

    public double getStars() {
        return stars;
    }

    public String getReview_text() {
        return text;
    }

    @Override
    public String toString() {
        return "Review{" +
                "business_id='" + business_id + '\'' +
                ", stars=" + stars +
                ", review_text='" + text + '\'' +
                '}';
    }
}
