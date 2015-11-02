package com.reddit.daily.programmer.gameofthrees;

import java.util.Scanner;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/3r7wxz/20151102_challenge_239_easy_a_game_of_threes/
 *
 * @author axel
 */
public class GameOfThrees {

    public static void main(String[] args) {
        System.out.println("Enter a number: ");
        Integer input = new Scanner(System.in).nextInt();

        while (input > 1) {
            Integer n = input % 3 == 0 ? 0 : input % 3 == 1 ? -1 : 1;
            System.out.println(input + " " + n);
            input = (input + n) / 3;
        }
        System.out.println(input);
    }
}
