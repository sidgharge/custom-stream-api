package com.homeprojects.customstreamapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public void notActivatedStream() {
        names.stream()
                .map(str -> {
                    System.out.println(str);
                    return str.toUpperCase();
                });
    }
}
