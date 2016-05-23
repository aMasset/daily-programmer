package com.reddit.daily.programmer.dogplacement;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/4jom3a/20160516_challenge_267_easy_all_the_places_your/
 *
 * @author masseta
 */
public class DogPlacement {
    public static void main(String[] args) {
        System.out.println("Enter your dog placement : ");
        Integer place = new Scanner(System.in).nextInt();

        System.out.println(IntStream.rangeClosed(1, 100)
                .filter(n -> n != place).mapToObj(DogPlacement::format)
                .collect(Collectors.joining(", ")));
    }

    public static String format(Integer number) {
        if ((number % 10) == 1 && (number % 100) != 11) return number + "st";
        if ((number % 10) == 2 && (number % 100) != 12) return number + "nd";
        if ((number % 10) == 3 && (number % 100) != 13) return number + "rd";
        return number + "th";
    }
}