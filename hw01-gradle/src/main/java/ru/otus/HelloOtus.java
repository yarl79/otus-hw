package ru.otus;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelloOtus {
    public static void main(String[] args) {
        List<Integer> sequence = Stream.generate(
                () -> (new Random()).nextInt(10))
                .limit(10)
                .collect(Collectors.toList());
        List<List<Integer>> parts = Lists.partition(sequence, 2); // {{1, 2}, {3, 4}, {5}}
        System.out.println(parts);
    }
}
