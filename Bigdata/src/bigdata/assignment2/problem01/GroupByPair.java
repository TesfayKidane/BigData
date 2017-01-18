package bigdata.assignment2.problem01;


import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tesfay
 */
public class GroupByPair<K, V> {
    private final K key;
    private final List<V> values;

    public K getKey() {
        return key;
    }

    public List<V> getValues() {
        return values;
    }

    public GroupByPair(K key, List<V> values) {
        this.key = key;
        this.values = values;
    }

    @Override
    public String toString() {
        return "( " + key + ", " + values + " )";
    }  
}
