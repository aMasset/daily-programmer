package com.reddit.daily.programmer.abundantanddeficient;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author masseta on 30/11/2015.
 */
    public class AbundantAndDeficient {

        public static void main(String[] args) {

            for (Integer number : Arrays.asList(6, 18, 21, 9, 111, 112, 220, 69, 134, 85)) {
                Integer factorsSum = IntStream.rangeClosed(1, number)
                        .filter(n -> (number % n) == 0)
                        .sum();

                if (factorsSum > number * 2) {
                    System.out.println(number + " abundant by " + (factorsSum - number * 2));
                } else if (factorsSum < number * 2) {
                    System.out.println(number + " deficient");
                } else {
                    System.out.println(number + " neither");
                }
            }
        }
    }
