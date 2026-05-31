/*
 * Given an arraylist, find first non-repetitive element.
 */

package java8.streams;

import java.util.*;
import java.util.stream.Collectors;

public class findFirstNonRepetitiveNumber {

    public static void main(String[] args) {

        List<Integer> lst = Arrays.asList(1, 2, 3, 5, 1);

        int result = 0;

        Map<Integer, Integer> map = new LinkedHashMap<>();
        for(int i : lst){
            if(map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }
            else{
                map.put(i, 1);
            }
        }
        Iterator<Map.Entry<Integer,Integer>> itr = map.entrySet().iterator();

        while(itr.hasNext()){
            Map.Entry<Integer,Integer> entry = itr.next();
            if(entry.getValue()==1){
                result = entry.getKey();
                break;
            }
        }

        System.out.println("First non-repetitive element is : " + result);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        List<Integer> lst2 = List.of(1, 2, 3, 5, 1, 2, 3, 5, 5, 6, 7);
        Map<Integer, Long> map1 = lst2.stream().collect(Collectors.groupingBy(
                ele -> ele,
                LinkedHashMap::new,
                Collectors.counting()
        ));

        Iterator<Map.Entry<Integer,Long>> itr1 = map1.entrySet().iterator();

        while(itr1.hasNext()){
            Map.Entry<Integer,Long> entry = itr1.next();
            if(entry.getValue()==1){
                System.out.println("First Non-Repetitive element in ls2 is : " + entry.getKey());
                break;
            }
        }

        map1.entrySet().stream()
                .filter(entry -> entry.getValue()==1)
                .findFirst()
                .ifPresent(entry -> System.out.println("First Non-Repetitive element in ls2 by stream is : " + entry.getKey()));








    }

}
