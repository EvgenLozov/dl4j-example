package com.example;

import java.io.File;
import java.util.Arrays;

public class Util {

    public static long filesCount (String path){
        return Arrays.stream(new File(path).listFiles()).flatMap(f -> Arrays.stream(f.listFiles()))
                .filter(f -> f.isFile())
                .count();


    }
}
