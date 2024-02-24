import java.util.Arrays;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {

        int[] list = new int[]{1,32,5,2,3};

//        int[] newList = IntStream.range(0, list.length)
//                .filter(i -> i != 0)
//                .map(i -> list[i])
//                .toArray();

//        int[] newList = Arrays.stream(list)
//                .filter(i -> i != 32)
//                .toArray();

        String[] listofString = {"apple", "banana", "orange", "grape", "kiwi"};

        String[] newlist = IntStream.range(0, listofString.length)
                .filter(i -> i != 2)
                .mapToObj(i -> listofString[i])
                .toArray(String[]::new);

        System.out.println(Arrays.toString(newlist));
    }
}
