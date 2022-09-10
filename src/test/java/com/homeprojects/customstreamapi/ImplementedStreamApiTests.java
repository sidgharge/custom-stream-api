package com.homeprojects.customstreamapi;

import com.homeprojects.customstreamapi.stream.Streams;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImplementedStreamApiTests {

    private final List<String> names = Arrays.asList("Sherlock", "John", "Mycroft");

    @Test
    public void foreach() {
        List<String> output = new ArrayList<>();
        Streams.of(names).forEach(output::add);
        assertEquals(names, output);
    }

    @Test
    public void map() {
        List<String> output = new ArrayList<>();
        Streams.of(names).map(String::toUpperCase).forEach(output::add);
        List<String> expected = Arrays.asList("SHERLOCK", "JOHN", "MYCROFT");
        assertEquals(expected, output);
    }

    @Test
    public void mapTwice() {
        List<Integer> output = new ArrayList<>();
        Streams.of(names)
                .map(String::toUpperCase)
                .map(String::length)
                .forEach(output::add);
        List<Integer> expected = Arrays.asList(8, 4, 7);
        assertEquals(expected, output);
    }

    @Test
    public void peek() {
        List<String> peekOutput = new ArrayList<>();
        List<String> finalOutput = new ArrayList<>();
        Streams.of(names)
                .peek(name -> peekOutput.add("Peeking: " + name))
                .map(String::toUpperCase)
                .forEach(finalOutput::add);
        List<String> peekExpected = Arrays.asList("Peeking: Sherlock", "Peeking: John", "Peeking: Mycroft");
        List<String> finalExpected = Arrays.asList("SHERLOCK", "JOHN", "MYCROFT");
        assertEquals(peekExpected, peekOutput);
        assertEquals(finalExpected, finalOutput);
    }

    @Test
    public void skip() {
        List<String> output = new ArrayList<>();
        Streams.of(names)
                .skip(1)
                .map(String::toUpperCase)
                .forEach(output::add);
        List<String> finalExpected = Arrays.asList("JOHN", "MYCROFT");
        assertEquals(finalExpected, output);
    }

    @Test
    public void findFirst() {
        Optional<String> firstName = Streams.of(names)
                .filter(name -> name.length() > 4)
                .map(String::toUpperCase)
                .findFirst();

        assertEquals(Optional.of("SHERLOCK"), firstName);
    }

    @Test
    public void flatMap() {
        List<String> output = new ArrayList<>();
        Streams.of(names)
                .flatMap(name -> Streams.of(Arrays.asList(name, String.valueOf(name.length()))))
                .forEach(output::add);
        List<String> expected = Arrays.asList("Sherlock", "8", "John", "4", "Mycroft", "7");
        assertEquals(expected, output);
    }

    @Test
    public void reduce() {
        Optional<String> reduce = Streams.of(names)
                .filter(name -> name.length() < 8)
                .map(String::toUpperCase)
                .reduce((s1, s2) -> s1 + ", " + s2);

        assertEquals(Optional.of("JOHN, MYCROFT"), reduce);
    }


    // Have not implemented
//    @Test
//    public void reduce2() {
//        Integer reduce = Streams.of(names)
//                .map(String::toUpperCase)
//                .reduce(0, (prev, s) -> prev + s.length(), (a, b) -> a + b);
//
//        assertEquals(19, reduce);
//    }

    @Test
    public void notActivatedStream() {
        List<String> output = new ArrayList<>();
        Streams.of(names)
                .map(str -> {
                    output.add(str);
                    return str.toUpperCase();
                });
        assertEquals(0, output.size());
    }
}
