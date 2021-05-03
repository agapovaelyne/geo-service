package ru.netology;

import java.util.Random;

public class Helpers {
    public static final String RU = "172";
    public static final String US = "96";
    public static final String RAND = "rand";

    public static String getIpForTest(String locale){
        Random rand = new Random();
        if (locale.equals(RAND)) {
            int i = 96;
            while (i != 96 && i != 172) {
                i = rand.nextInt(255);
            }
            locale = String.valueOf(i);
        }
        return locale +
                "." + String.valueOf(rand.nextInt(255)) +
                "." + String.valueOf(rand.nextInt(255)) +
                "." + String.valueOf(rand.nextInt(255));
    }
}
