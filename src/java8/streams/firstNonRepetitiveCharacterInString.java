package java8.streams;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class firstNonRepetitiveCharacterInString {

    public static void main(String[] args) {
        String str1 = "MohitMohritee";
        printFirstNonRepetitiveCharacterInString(str1);
        System.out.println("First Non-repetitive character in " + str1 + " : " + findFirstNonRepetitiveCharacterInString(str1));

        String str2 = "ShrirangShircbhatengbte";
        printFirstNonRepetitiveCharacterInString(str2);
        System.out.println("First Non-repetitive character in " + str2 + " : " + findFirstNonRepetitiveCharacterInString(str2));
    }

    public static void printFirstNonRepetitiveCharacterInString(String str){

        //Step 1 : Calculate frequency for each character
        Map<Character, Long> charCount = str.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        LinkedHashMap :: new,
                        Collectors.counting()
                    ));

        //Step 2 : Find first non-repetitive character from frequency map
        charCount.entrySet().stream()
                .filter(entry -> entry.getValue()==1)
                .findFirst()
                .ifPresent(entry -> System.out.println("First Non-repetitive character in " + str + " : " + entry.getKey()));
    }

    public static char findFirstNonRepetitiveCharacterInString(String str){

        //Step 1 : Calculate frequency for each character
        Map<Character, Long> charCount = str.chars().mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        LinkedHashMap :: new,
                        Collectors.counting()
                ));

        //Step 2 : Find first non-repetitive character from frequency map
        return charCount.entrySet().stream()
                .filter(entry -> entry.getValue()==1)
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse('_');
    }
}
