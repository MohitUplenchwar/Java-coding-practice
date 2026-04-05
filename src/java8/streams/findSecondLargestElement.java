/*
 * Find the Second Largest Distinct Element in an Array using Java Stream API
 * You are given an array of integers. Your task is to find the second largest distinct element in the array using Java 8 Stream API.
 */


package java8.streams;

import java.util.Arrays;
import java.util.stream.Collectors;

public class findSecondLargestElement {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 5};
        System.out.println("Second Largest Element : " + findSecondLargest(arr));
        System.out.println("Second Largest Element via .collect() method: " + findSecondLargestViaCollectMethod(arr));
    }

    public static int findSecondLargest(int[] a){
        return Arrays.stream(a).boxed().distinct().sorted((x,y) -> (y - x)).skip(1).findFirst().orElseThrow();
    }

    public static int findSecondLargestViaCollectMethod(int[] a){
        return Arrays.stream(a).boxed().distinct().sorted((x,y) -> (y - x)).collect(Collectors.toList()).get(1);
    }

    public static int findSecondLargestViaToList(int[] a){
        return Arrays.stream(a).boxed().distinct().sorted((x,y) -> (y - x)).toList().get(1);
    }
}

/*
1. Arrays.stream(a) will return IntStream but .collect only works over Stream<Object>.
2. You need to convert IntStream into Stream<Object> therefore used .boxed()
 */
