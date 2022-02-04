package com.homeprojects.customstreamapi;

import com.homeprojects.customstreamapi.stream.Streams;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final List<String> names = Arrays.asList("Sam", "Sherlock", "John", "Mycroft", "Julian");

    public static void main(String[] args) {
        Optional<String> first =
                Streams.of(names)
                .peek(name -> System.out.println("Before filter: " + name))
                .filter(str -> str.length() > 4)
                .peek(name -> System.out.println("After filter: " + name))
                .map(String::toUpperCase)
                .peek(name -> System.out.println("After map: " + name))
                .skip(1)
                .peek(name -> System.out.println("After skip: " + name))
                .findFirst();
//                        .foreach(name -> System.out.println("Final: " + name));

        System.out.println(first);
    }
}
