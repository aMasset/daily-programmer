package com.reddit.daily.programmer.typoglycemia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author masseta on 9/11/2015.
 */
public class Typoglycemia {
    public static void main(String[] args) {
        String text = "According to a research team at Cambridge University, it doesn't matter in what order the letters in a word are,\n" +
                "the only important thing is that the first and last letter be in the right place.\n" +
                "The rest can be a total mess and you can still read it without a problem.\n" +
                "This is because the human mind does not read every letter by itself, but the word as a whole.\n" +
                "Such a condition is appropriately called Typoglycemia.";

        text = Arrays.asList(text.split(" ")).stream().map(Typoglycemia::scramble).collect(Collectors.joining(" "));
        System.out.println(text);
    }

    public static String scramble(String word) {
        List<List<Character>> decomposedWord = new ArrayList<>();
        List<Character> cs = new ArrayList<>();
        for (Character c : word.toCharArray()) {
            if (c.toString().matches("[a-zA-Z]")) {
                cs.add(c);
            } else {
                decomposedWord.add(new ArrayList<>(cs));
                cs = new ArrayList<>();
                cs.add(c);
                decomposedWord.add(new ArrayList<>(cs));
                cs = new ArrayList<>();
            }
        }
        decomposedWord.add(cs);

        StringBuilder sb = new StringBuilder();
        decomposedWord.stream()
                .filter(characters -> !characters.isEmpty())
                .forEach(characters -> {
                    if (characters.size() > 3) {
                        characters = shuffleInside(characters);
                    }
                    characters.forEach(sb::append);
                });
        return sb.toString();
    }

    public static List<Character> shuffleInside(List<Character> characters) {
        Character first = characters.get(0);
        Character last = characters.get(characters.size() - 1);
        characters.remove(characters.size() - 1);
        characters.remove(0);
        Collections.shuffle(characters);
        characters.add(0, first);
        characters.add(last);
        return characters;
    }
}
