package com.reddit.daily.tests;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by axel on 30/10/2015.
 */
public class Main {

    public static void main(String[] args) {
        Arrays.asList("Hello World", "fun", "etc..")
                .stream()
                .filter(w -> w.length() > 3)
                .forEach(System.out::println);

        List<Person> persons = Arrays.asList(new Person("00000001", "Axel"),
                new Person("00000002", "Anthony"),
                new Person("00000001", "Dounlon"),
                new Person("00000004", "Doublon2")
        );

        List<String> duplicates = persons.stream()
                .collect(Collectors.groupingBy(Person::getMatricFgs, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(p -> p.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (!duplicates.isEmpty()) {
            System.out.println("DUPLICATES = " + duplicates);
        } else {
            System.out.println("NO DUPLICATES");
        }
    }
}


class Person {

    private String matricFgs;
    private String nom;

    public Person() {

    }

    public Person(String matricFgs, String nom) {
        this.matricFgs = matricFgs;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMatricFgs() {
        return matricFgs;
    }

    public void setMatricFgs(String matricFgs) {
        this.matricFgs = matricFgs;
    }
}