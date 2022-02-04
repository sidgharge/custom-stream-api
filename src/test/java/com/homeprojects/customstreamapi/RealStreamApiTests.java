package com.homeprojects.customstreamapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class RealStreamApiTests {

    private final List<String> names = Arrays.asList("Sherlock", "John", "Mycroft");

    @Test
    public void foreach() {
        names.stream().forEach(System.out::println);
    }

    @Test
    public void map() {
        names.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    @Test
    public void mapTwice() {
        names.stream()
                .map(String::toUpperCase)
                .map(String::length)
                .forEach(System.out::println);
    }

    @Test
    public void peek() {
        names.stream()
                .peek(name -> System.out.println("Peeking: " + name))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    @Test
    public void skip() {
        names.stream()
                .skip(1)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    @Test
    public void findFirst() {
        Optional<String> firstName = names.stream()
                .filter(name -> name.length() > 4)
                .map(String::toUpperCase)
                .findFirst();

        Assertions.assertEquals(Optional.of("SHERLOCK"), firstName);
    }

    @Test
    public void flatMap() {
        names.stream()
                .flatMap(name -> Stream.of(name, String.valueOf(name.length())))
                .forEach(System.out::println);
    }

    @Test
    public void reduce() {
        Optional<String> reduce = names.stream()
                .filter(name -> name.length() < 5)
                .map(String::toUpperCase)
                .reduce((s1, s2) -> s1 + ", " + s2);

        Assertions.assertEquals(Optional.of("JOHN"), reduce);
    }


    @Test
    public void reduce2() {
        Integer reduce = names.stream()
                .map(String::toUpperCase)
                .reduce(0, (prev, s) -> prev + s.length(), (a, b) -> a + b);

        Assertions.assertEquals(19, reduce);
    }

    @Test
    public void notActivatedStream() {
        names.stream()
                .map(str -> {
                    System.out.println(str);
                    return str.toUpperCase();
                });
    }
}
