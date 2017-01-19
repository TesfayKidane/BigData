/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdata.assignment3.problem01;

import bigdata.assignment2.problem01.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Tesfay
 */
public class ShuffleSort {

    public static Set<GroupByPair> shuffle(List<Pair> pairs) {
        Map<Object, Long> gp = pairs.stream().collect(Collectors.groupingBy(Pair::getKey, Collectors.counting()));

        Set<GroupByPair> shuffled = new HashSet<>();
        for (Map.Entry<Object, Long> e : gp.entrySet()) {
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < e.getValue(); i++) {
                values.add(1);
            }
            shuffled.add(new GroupByPair((String) e.getKey(), values));
        }

        return shuffled;
    }
}
