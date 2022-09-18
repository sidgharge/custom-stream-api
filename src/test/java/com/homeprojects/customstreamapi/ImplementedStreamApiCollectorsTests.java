package com.homeprojects.customstreamapi;

import com.homeprojects.customstreamapi.stream.Streams;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ImplementedStreamApiCollectorsTests {

    private final List<String> names = Arrays.asList("Sherlock", "John", "Mycroft");

    @Test
    public void listWithMoreThanOneElement() {
        List<String> result = Streams.of(names)
                .filter(str -> str.length() > 4)
                .map(String::toUpperCase)
                .toList();
        List<String> expected = Arrays.asList("SHERLOCK", "MYCROFT");
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void listWithZeroElements() {
        List<String> result = Streams.of(names)
                .filter(str -> str.length() > 10)
                .map(String::toUpperCase)
                .toList();
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void setWithMoreThanOneElement() {
        Set<String> result = Streams.of(names)
                .filter(str -> str.length() > 4)
                .map(String::toUpperCase)
                .toSet();
        Set<String> expected = Set.of("SHERLOCK", "MYCROFT");
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void setWithZeroElements() {
        Set<String> result = Streams.of(names)
                .filter(str -> str.length() > 10)
                .map(String::toUpperCase)
                .toSet();
        Assertions.assertEquals(0, result.size());
    }
}
