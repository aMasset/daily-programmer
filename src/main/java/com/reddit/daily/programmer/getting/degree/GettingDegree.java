package com.reddit.daily.programmer.getting.degree;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * https://www.reddit.com/r/dailyprogrammer/comments/4q35ip/20160627_challenge_273_easy_getting_a_degree/
 *
 * @author masseta
 */
public class GettingDegree {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter a conversion command");
        String sc = new Scanner(System.in).next();
        final Double in = Double.valueOf(sc.substring(0, sc.length() - 2));
        final String cmd = sc.substring(sc.length() - 2, sc.length());
        Map<String, Callable<Double>> f = new HashMap<>();
        f.put("rd", () -> in * 180 / Math.PI);
        f.put("dr", () -> in * Math.PI / 180);
        f.put("kc", () -> in - 273.15);
        f.put("kf", () -> in * 9 / 5 - 459.67);
        f.put("ck", () -> in + 273.15);
        f.put("fk", () -> (in + 459.67) * 5 / 9);
        f.put("cf", () -> in * 9 / 5 + 32);
        f.put("fc", () -> (in - 32) * 5 / 9);

        System.out.println(f.containsKey(cmd)
                ? new DecimalFormat("0.##").format(f.get(cmd).call()) + cmd.charAt(1)
                : "No candidate for conversion");
    }
}
