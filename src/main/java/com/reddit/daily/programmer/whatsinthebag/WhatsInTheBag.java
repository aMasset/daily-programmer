package com.reddit.daily.programmer.whatsinthebag;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Idea https://www.reddit.com/r/dailyprogrammer_ideas/comments/4j33t1/easy_whats_in_the_bag/
 *
 * @author masseta
 */
public class WhatsInTheBag {

    public static final int A_UPPERCASE_INDEX = 65;

    public static void main(String[] args) {

        AtomicInteger i = new AtomicInteger(); // Thanks /u/Philboyd_Studge for the idea to initialize, I adapted it to Java 8
        Map<Character, Integer> map = Arrays.asList(9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1)
                .stream()
                .collect(Collectors.toMap(v -> (char) (i.getAndIncrement() + A_UPPERCASE_INDEX), v -> v));
        map.put('_', 2);
        String in = "lqtoonoeffjzt";

        in.chars().mapToObj(c -> (char) c) // decrementing letters picked
                .map(Character::toUpperCase).forEach(c -> map.put(c, map.get(c) - 1));

        map.entrySet().stream() // Checking if input is invalid
                .filter(e -> e.getValue() < 0)
                .peek(e -> System.out.println("Invalid input. More " + e.getKey() + "'s have been taken from the bag than possible."))
                .collect(Collectors.toList())
                .stream().findAny().ifPresent(e -> System.exit(-1));

        map.entrySet().stream() // Printing letters remaining
                .collect(Collectors.groupingBy(e -> e.getValue().toString(), Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(Integer.valueOf(e2.getKey()), Integer.valueOf(e1.getKey())))
                .forEach(e -> System.out.println(("0".equals(e.getKey()) ? "None" : e.getKey()) + ": " + e.getValue().stream().map(Object::toString).sorted().collect(Collectors.joining(", "))));
    }
}
