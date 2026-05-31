/*
 * String s = "abcaab";
   Find first non-repeting character
 */

package java8.streams;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class firstNonRepetitiveCharacter {

    public static void main(String[] args) {
        String str = "abecabd";

        char result1 = getNonrepeatingCharUsingHashMap(str);
        System.out.println("First Non-repeating character is : " + result1);

        char result2 = getNonrepeatingCharUsingStream(str);
        System.out.println("First Non-repeating character using streams is : " + result2);

        char result3 = getNonrepeatingCharUsingStream1(str);
        System.out.println("First Non-repeating character using streams1 is : " + result3);
    }

    public static char getNonrepeatingCharUsingStream(String str){
        Character ch = str.chars().mapToObj(c -> (char)c)
                        .collect(Collectors.groupingBy(
                                c -> c,
                                LinkedHashMap :: new,
                                Collectors.counting()
                        ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue()==1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        return ch.charValue();
    }

    public static char getNonrepeatingCharUsingStream1(String str){
        Map<Character, Long> lhm = str.chars() // Convert string to an IntStream of characters
                .mapToObj(c -> (char) c) // Convert int to Character objects
                // Step 1: Group and Count occurrences in a LinkedHashMap to preserve order
                .collect(Collectors.groupingBy(
                        java.util.function.Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()
                ));
        char result = lhm.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry :: getKey)
                .findFirst()
                .orElse((char)0);
        return result;
    }

    public static char getNonrepeatingCharUsingHashMap(String str){
        char result = 'a';
        Map<Character, Integer> hm = new LinkedHashMap<>();
        char[] chArr = str.toCharArray();
        System.out.println(chArr);

        for(char ch : chArr){
            if(hm.containsKey(ch)){
                hm.put(ch, hm.get(ch)+1);
            } else{
                hm.put(ch, 1);
            }
        }
        System.out.println(hm);

        Iterator<Map.Entry<Character, Integer>> it = hm.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<Character, Integer> entry = it.next();
            if(entry.getValue()==1){
                result = entry.getKey();
                break;
            }
        }
        return result;
    }
}
