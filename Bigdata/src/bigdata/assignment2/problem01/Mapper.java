/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdata.assignment2.problem01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Tesfay
 */
public class Mapper {

    public static List<String> fileToList(File file) {
        List<String> rawWords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                List<String> s = Arrays.asList(line.split("[\\s*\\-\\s*|\\s+|\\s*\\,\\s*]")).stream().filter(x -> !x.contains(" ")).collect(Collectors.toList());
                rawWords.addAll(s);
                line = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(Mapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rawWords;
    }

    public static List<Pair> getListOfWords(File file) {
        List<String> rawWords = fileToList(file);

        Predicate<String> isAlphabet = (String t) -> {
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if (!(c >= 65 && c <= 90) && !(c >= 97 && c <= 122)) {
                    return false;
                }
            }
            return true;
        };

        Function<String, Pair> createPair = (String t) -> new Pair(t, 1);

        Comparator<Pair> comparator = (Pair o1, Pair o2) -> {
            String s1 = (String) o1.getKey();
            String s2 = (String) o2.getKey();
            return s1.compareTo(s2);
        };
        return rawWords.stream().filter(isAlphabet).map(x -> x.toLowerCase()).map(createPair).sorted(comparator).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        File file = new File("src\\bigdata\\problem02\\testDataForW1D1.txt");
        List<Pair> pairs = getListOfWords(file).stream().collect(Collectors.toList());
        System.out.println("Raw Word List");
        pairs.forEach(x -> System.out.println(x.toString()));
        
        System.out.println("Grouped Word List, Shuffled");
        Set<GroupByPair> groupByPairs = ShuffleSort.shuffle(pairs);
        groupByPairs.forEach(x -> System.out.println(x.toString()));
        
        System.out.println("Reduced");
        List<Pair> reduced = Reducer.reduce(groupByPairs);
        reduced.forEach(x -> System.out.println(x.toString()));
    }
}
