package com.dbettkk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    private static List<Character> l = new ArrayList();
    private static Map<String, String> m1 = new HashMap<>();
    private static Map<String, String> m2 = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String str;
        init();
        while ((str = in.readLine()) != null) {
            String[] strs = str.split("\\s+");
            for (String s : strs) {
                check(s);
            }
        }
    }

    public static void init(){
        for (int i = 0; i < 10 ; i++){
            l.add((char) ('0' + i));
        }
        for (int i = 0; i < 26 ; i++){
            l.add((char) ('a' + i));
            l.add((char) ('A' + i));
        }
        l.add(':');
        l.add('+');
        l.add('*');
        l.add(',');
        l.add('(');
        l.add(')');
        m1.put("BEGIN", "Begin");
        m1.put("END", "End");
        m1.put("FOR", "For");
        m1.put("IF", "If");
        m1.put("THEN", "Then");
        m1.put("ELSE", "Else");
        m2.put(":", "Colon");
        m2.put("+", "Plus");
        m2.put("*", "Star");
        m2.put(",", "Comma");
        m2.put("(", "LParenthesis");
        m2.put(")", "RParenthesis");
        m2.put(":=", "Assign");
    }
    public static boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }

    public static boolean isAlpha(char c){
        if (c >= 'a' && c <= 'z') return true;
        return c >= 'A' && c <= 'Z';
    }
    public static void check(String s){
        if (s == null || s.equals("")) return;
        int cnt = 0;
        do {
            char tmp = s.charAt(cnt);
            if (!l.contains(tmp)) {
                System.out.println("Unknown");
                System.exit(0);
            }
            if (isNumber(tmp)) {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(s.charAt(cnt));
                    cnt++;
                } while (s.length() > cnt && isNumber(s.charAt(cnt)));
                if (s.length() > cnt) {
                    tmp = s.charAt(cnt);
                }
                System.out.println("Int(" + Integer.parseInt(sb.toString()) + ")");
            }
            if (isAlpha(tmp)) {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(s.charAt(cnt));
                    cnt++;
                } while (s.length() > cnt && (isAlpha(s.charAt(cnt)) || isNumber(s.charAt(cnt))));
                if (s.length() > cnt) {
                    tmp = s.charAt(cnt);
                }
                if (m1.containsKey(sb.toString()))
                    System.out.println(m1.get(sb.toString()));
                else System.out.println("Ident(" + sb + ")");
            }
            if (m2.containsKey(tmp + "")){
                if (tmp == ':'){
                    if (s.length() > cnt + 1 && s.charAt(cnt + 1) == '=') {
                        System.out.println("Assign");
                        cnt++;
                    }
                    else System.out.println(m2.get(":"));
                }
                else System.out.println(m2.get(tmp + ""));
                cnt++;
            } else {
                cnt++;
            }
        } while (s.length() > cnt);
    }

}
