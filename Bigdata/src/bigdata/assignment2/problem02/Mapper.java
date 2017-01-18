/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigdata.assignment2.problem02;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author Tesfay
 */
public class Mapper {

    public static List<Integer> map(List<Integer> input) {
        Function<Integer, Integer> mapper = (Integer x) -> {
            if (x == 4) {
                return 4;
            } else if (x == 3) {
                return 3;
            } else {
                return 0;
            }
        };
       return input.stream().map(mapper).collect(Collectors.toList());
    }

    public static boolean fold(List<Integer> input) {

        int sum = 0; // 1 is nice and 0 is not nice

        for (int i : input) {
            sum += i;
        }
        System.err.print(" : sum = " + sum);
        if ((sum % 2 == 0) )
        {
            return true;
        }else if(((sum - 3) >= 0) && ((sum - 3) % 2 == 0)){
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        List<Integer> input1 = Arrays.asList(7, 6, 2, 3, 1);
        List<Integer> input2 = Arrays.asList(7, 6, 2, 4, 1);
        List<Integer> input3 = Arrays.asList(3, 6, 2, 3, 4);
        List<Integer> input4 = Arrays.asList(3, 4, 2, 3, 4, 7, 4);
        List<Integer> input5 = Arrays.asList(1, 6, 2, 8, 2, 9);             
        System.out.println(input1 + " : " + fold(map(input1)));
        System.out.println(input2 + " : " + fold(map(input2)));
        System.out.println(input3 + " : " + fold(map(input3)));
        System.out.println(input4 + " : " + fold(map(input4)));
        System.out.println(input5 + " : " + fold(map(input5)));
    }
}
