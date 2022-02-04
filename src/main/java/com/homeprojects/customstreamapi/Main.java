package com.homeprojects.customstreamapi;

import com.homeprojects.customstreamapi.stream.Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

public class Main {

    private static final List<String> names = Arrays.asList("Sam", "Sherlock", "John", "Mycroft", "Julian");

    public static void main(String[] args) {
//        Optional<String> first =
//                Streams.of(names)
//                .peek(name -> System.out.println("Before filter: " + name))
//                .filter(str -> str.length() > 4)
//                .peek(name -> System.out.println("After filter: " + name))
//                .map(String::toUpperCase)
//                .peek(name -> System.out.println("After map: " + name))
//                .skip(1)
//                .peek(name -> System.out.println("After skip: " + name))
//                .findFirst();
//                        .foreach(name -> System.out.println("Final: " + name));

//        System.out.println(first);


//        BiFunction<Integer, String, Integer> fn = (String s1, String s2) -> s1.length() + s2.length();
        Optional<String> reduce = Streams.of(names)
                .filter(s -> s.length() > 4)
                .map(String::toUpperCase)
                .reduce((s1, s2) -> s1.concat(", " + s2));

        Integer reduce2 = Streams.of(names)
//                .filter(s -> s.length() > 10)
                .map(String::toUpperCase)
                .reduce(10, (prev, s) -> prev + s.length());

        System.out.println(reduce2);

    }
}
