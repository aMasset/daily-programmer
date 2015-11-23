package com.reddit.daily.programmer.funnyplant;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/3twuwf/20151123_challenge_242_easy_funny_plant/
 *
 * @author masseta on 23/11/2015.
 */
public class FunnyPlant {

    public static void main(String[] args) {
        System.out.println("Enter people to feed and starting fruits: ");
        String[] infos = new Scanner(System.in).nextLine().split(" ");
        final Integer people = Integer.valueOf(infos[0]);
        Integer production = Integer.valueOf(infos[1]);

        List<Plant> plants = IntStream.rangeClosed(1, production)
                .mapToObj(value -> new Plant()).collect(Collectors.toList());

        while (production < people) {
            plants.forEach(Plant::addWeek);
            production = plants.stream().mapToInt(Plant::getFruits).sum();
            IntStream.rangeClosed(1, production).forEach(v -> plants.add(new Plant()));
        }

        System.out.println(plants.stream().mapToInt(Plant::getWeek).max().getAsInt());
    }
}

class Plant {
    private Integer week;

    Plant() {
        week = 1;
    }

    public void addWeek() {
        week++;
    }

    public Integer getWeek() {
        return week;
    }

    public Integer getFruits() {
        return week - 1;
    }
}
