package com.reddit.daily.programmer.gameofthrees;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/3r7wxz/20151102_challenge_239_easy_a_game_of_threes/
 *
 * @author axel
 */
public class GameOfThrees {
    public static void main(String[] args) {
        System.out.println("Enter a number: ");
        Integer n = new Scanner(System.in).nextInt();

        while (n > 1) {
            final Integer f = n;
            n = Arrays.asList(n, n + 1, n - 1).stream()
                    .filter(nb -> nb % 3 == 0)
                    .peek(nb -> System.out.println(f + " " + (nb - f)))
                    .findFirst().map(nb -> nb / 3).get();
        }
        System.out.println(n);
    }
}
