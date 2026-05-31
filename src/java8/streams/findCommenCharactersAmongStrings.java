/*
 * Find commen character among all strings.
 * Ex : List<String> ls = Arrays.asList("Mohit","Sumit","Lalit", "Rohit", "Tilak", "Tagore");
 * Ans : t
 * Reason : t occour in every String.
 */

package java8.streams;

import java.util.*;
import java.util.stream.Collectors;

public class findCommenCharactersAmongStrings {

    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Mohit","Sumit","Lalit", "Rohit", "Tilak", "Tagore");

        List<Character> result = findCommenCharacter(ls);
        result.stream().forEach(c -> System.out.println(c));

        System.out.println("------------------------------------------------------------------------------------------------");

        List<Character> result1 = findCommenCharacterUsingStreams(ls);
        result1.stream().forEach(c -> System.out.println(c));

    }

    public static List<Character> findCommenCharacter(List<String> list){
        int n = list.size();
        Set<Character> commenChars = new HashSet<>();
        List<Set<Character>> set = new ArrayList<>();

        //Step 1 : Convert each string to a Set of lowercase characters
        for(String str : list){
            Set<Character> charSet = str.chars().mapToObj(c -> (char)Character.toLowerCase(c)).collect(Collectors.toSet());
            set.add(charSet);
        }
        System.out.println(set);

        int i = 0;
        commenChars = set.get(0);
        while(i < n-1){
            Set<Character> set2 = set.get(i + 1);
            commenChars.retainAll(set2);
            i++;
        }
        System.out.println("Result : " + commenChars);
        return commenChars.stream().toList();
    }

    public static List<Character> findCommenCharacterUsingStreams(List<String> list){

        Set<Character> set = list.stream()
                .map(
                str -> str.chars().mapToObj(ch -> (char)Character.toLowerCase(ch)).collect(Collectors.toSet()))
                .reduce(
                        (set1, set2) -> {set1.retainAll(set2);
                                                                        return set1;}
                )
                .orElse(Collections.emptySet());

        return new ArrayList<>(set);
    }

    public static List<Character> findCommenCharacterUsingProblemSolving(List<String> list){
        List<Set<Character>> lst = new ArrayList<>();

        for(String str : list){
            char[] chArr = str.toCharArray();
            Set<Character> characterSet = new HashSet<>();
            for(char ch : chArr){
                characterSet.add(ch);
            }
            lst.add(characterSet);
        }

        System.out.println(lst);

        Set<Character> commenCharacters =  lst.get(0);
        for(int i =0; i < lst.size(); i++){
            Set<Character> newSet = lst.get(i+1);
            commenCharacters.retainAll(newSet);
        }
        return commenCharacters.stream().toList();
    }
}
