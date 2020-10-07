package com.dbettkk.compile;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String str;
        CheckString cs = new CheckString();
        while ((str = in.readLine()) != null) {
            String[] strs = str.split("\\s+");
            for (String s : strs) {
                cs.check(s);
            }
        }
    }
}
