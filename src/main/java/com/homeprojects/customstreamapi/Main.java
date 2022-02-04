package com.homeprojects.customstreamapi;

import com.homeprojects.customstreamapi.stream.Streams;

import java.util.Arrays;
import java.util.List;

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


        Streams.of(names)
                .flatMap(name -> {
                    List<String> strings = Arrays.asList(name, String.valueOf(name.length()));
                    return Streams.of(strings);
                })
                .skip(1)
                .forEach(System.out::println);
    }
}
