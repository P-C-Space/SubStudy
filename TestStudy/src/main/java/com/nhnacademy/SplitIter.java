package com.nhnacademy;

import com.google.common.base.Splitter;
import java.util.Iterator;

public class SplitIter {
    public static void main(String[] args) {
        String fruits = "사과포토멜론레몬";
        Iterable<String> split = Splitter.fixedLength(2).split(fruits);

        Iterator<String> iterator = split.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
