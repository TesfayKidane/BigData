/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdata.assignment2.problem01;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Tesfay
 */
public class Reducer {
    
    public static List<Pair> reduce(Set<GroupByPair> gp){
        return gp.stream().map(t -> new Pair(t.getKey(), t.getValues().stream().mapToInt(x -> (int)x).sum())).collect(Collectors.toList());
    }
}
