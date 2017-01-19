/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdata.assignment3.problem01;

import myutils.IO;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import myutils.Database;

/**
 *
 * @author Tesfay
 */
public class WordCount {

    private final int numberOfMappers;
    private final int numberOfReducers;

    LinkedList<Mapper> mappers;
    LinkedList<Reducer> reducers;

    public WordCount(int numberOfMappers, int numberOfReducers) {
        this.numberOfMappers = numberOfMappers;
        this.numberOfReducers = numberOfReducers;

        mappers = new LinkedList<>();
        reducers = new LinkedList<>();

        // create m mappers and r reducers
        for (int i = 0; i < numberOfMappers; i++) {
            mappers.add(new Mapper());
        }
        for (int j = 0; j < numberOfReducers; j++) {
            reducers.add(new Reducer());
        }
    }

    public int getNumberOfMappers() {
        return numberOfMappers;
    }

    public int getNumberOfReducers() {
        return numberOfReducers;
    }

    public LinkedList<Mapper> getMappers() {
        return mappers;
    }

    public LinkedList<Reducer> getReducers() {
        return reducers;
    }

    public static int getPartition(String key, int r) {
        return (int) Math.abs(key.hashCode()) % r;
    }

    public static void main(String[] args) {
        File file1 = new File("src\\bigdata\\assignment3\\problem01\\testDataForW1D1.txt");
        List<String> allWords = IO.fileToList(file1);
//        List<String> allWords = Database.getStringArrayData(10, 1);
//        System.out.println("Input Words : ");
//        allWords.stream().forEach(x -> System.out.print(x + ", "));

        int numberOfInputSplit = 3;
        int numberOfMappers = numberOfInputSplit;
        int numberOfReducers = 3;

        List<List<String>> inputSplitted = splitInput(allWords, numberOfInputSplit);

        // send splitted files to mappers
        WordCount wordCount = new WordCount(numberOfMappers, numberOfReducers);

        for (int i = 0; i < inputSplitted.size(); i++) {
            System.out.println("\nMapper " + i);
            wordCount.mappers.get(i).map(inputSplitted.get(i)).stream().forEach(x -> System.out.println(x.toString()));
        }

        // find partition of words in mappers and send them to reducer
        for (int m = 0; m < wordCount.mappers.size(); m++) {
            for (int i = 0; i < numberOfReducers; i++) {
                System.out.println("Pairs sent from Mapper " + m + " to Reducer " + i);
                for (Pair p : wordCount.mappers.get(m).getMapResult()) {
                    int partion = getPartition((String) p.getKey(), numberOfReducers);
                    if (partion == i) {
                        wordCount.getReducers().get(i).getUnShuffledUnsorted().add(p);
                        System.out.println(p.toString());
                    }
                }
            }
        }

        // group pairs based on their keys for each reducer
        for (int r = 0; r < wordCount.reducers.size(); r++) {
            System.out.println("Reducer " + r + " input");
            wordCount.getReducers().get(r).shuffleSort().stream().forEach(x -> System.out.println(x));
        }

        // reduce the grouped pairs
        for (int r = 0; r < wordCount.reducers.size(); r++) {
            System.out.println("Reducer " + r + " output");
            wordCount.getReducers().get(r).reduce().stream().forEach(x -> System.out.println(x));
        }

    }

    public static List<List<String>> splitInput(List<String> allWords, int numberOfInputSplit) {
        List<List<String>> splittedList = new ArrayList<>();
        for (int i = 0; i < numberOfInputSplit; i++) {
            splittedList.add(new ArrayList<>());
        }

        for (int j = 0; j < allWords.size(); j++) {
            splittedList.get(j % numberOfInputSplit).add(allWords.get(j));
        }

        return splittedList;
    }
}
