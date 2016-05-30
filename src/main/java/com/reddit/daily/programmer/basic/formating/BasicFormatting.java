package com.reddit.daily.programmer.basic.formating;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/4lpygb/20160530_challenge_269_easy_basic_formatting/
 *
 * @author masseta
 */
public class BasicFormatting {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/java/com/reddit/daily/programmer/basic/formating/input.txt"));
        AtomicInteger level = new AtomicInteger();
        lines.stream()
                .skip(2)
                .map(l -> l.replace("»", "").replace("·", ""))
                .map(l -> {
                    if (l.startsWith("IF") || l.startsWith("FOR")) {
                        return (Stream.generate(() -> lines.get(1)).limit(level.getAndAdd(1))).collect(Collectors.joining("")) + l;
                    } else if (l.startsWith("NEXT") || l.startsWith("ENDIF")) {
                        return (Stream.generate(() -> lines.get(1)).limit(level.addAndGet(-1))).collect(Collectors.joining("")) + l;
                    }
                    return (Stream.generate(() -> lines.get(1)).limit(level.get())).collect(Collectors.joining("")) + l;
                })
                .forEach(System.out::println);
    }
}
