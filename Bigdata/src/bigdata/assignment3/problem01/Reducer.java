/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdata.assignment3.problem01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Tesfay
 */
public class Reducer {
    
    private List<Pair> unShuffledUnsorted = new ArrayList<>();
    private Set<GroupByPair> groupedPair = new HashSet<>();
    public List<Pair> getUnShuffledUnsorted() {
        return unShuffledUnsorted;
    }
    public List<Pair> reduce(Set<GroupByPair> gp){
        return gp.stream().map(t -> new Pair(t.getKey(), t.getValues().stream().mapToInt(x -> (int)x).sum())).collect(Collectors.toList());
    }

    public Set<GroupByPair> getGp() {
        return groupedPair;
    }
    
    public Set<GroupByPair> shuffleSort(){
      groupedPair = ShuffleSort.shuffle(unShuffledUnsorted);
      return groupedPair;
    }
    
    public List<Pair> reduce(){
        return groupedPair.stream().map(t -> new Pair(t.getKey(), t.getValues().stream().mapToInt(x -> (int)x).sum())).collect(Collectors.toList());
    }
}
