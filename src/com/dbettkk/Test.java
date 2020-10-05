package com.dbettkk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static List<Character> l = new ArrayList();
    public static void init(){
        for (int i = 0; i < 10 ; i++){
            l.add((char) ('0' + i));
        }
        for (int i = 0; i < 26 ; i++){
            l.add((char) ('a' + i));
            l.add((char) ('A' + i));
        }
    }
    public static void checkFirst(String s){
        switch (s){
            case "BEGIN":
                System.out.println("Begin");
                break;
            case "END":
                System.out.println("End");
                break;
            case "FOR":
                System.out.println("For");
                break;
            case "IF":
                System.out.println("if");
                break;
            case "THEN":
                System.out.println("Then");
                break;
            case "ELSE":
                System.out.println("Else");
                break;
            case ":":
                System.out.println("Colon");
                break;
            case "+":
                System.out.println("Plus");
                break;
            case "*":
                System.out.println("Star");
                break;
            case ",":
                System.out.println("Comma");
                break;
            case "(":
                System.out.println("LParenthesis");
                break;
            case ")":
                System.out.println("RParenthesis");
                break;
            case ":=":
                System.out.println("Assign");
                break;
            default:
                checkSecond(s);
        }
    }
    public static void checkSecond(String s){
        try {
            int num = Integer.parseInt(s);
            System.out.println("Int(" + num + ")");
        } catch (Exception e){
            for (int i = 0; i < s.length(); i++){
                char tmp = s.charAt(i);
                if (!l.contains(tmp)){
                    System.out.println("Unknown");
                    System.exit(0);
                }
            }
            if (s.charAt(0) >= '0' && s.charAt(0) <= '9'){
                String left = s;
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < s.length(); i++){
                    char tmp = s.charAt(i);
                    if(tmp >= '0' && tmp <= '9'){
                        sb.append(tmp);
                    }
                    else{
                        while (true){
                            if (sb.toString().equals("0")){
                                break;
                            }
                            if(sb.charAt(0) == '0'){
                                sb.deleteCharAt(0);
                            }
                            else {
                                break;
                            }
                        }
                        System.out.println("Int(" + sb + ")");
                        left = s.substring(i);
                        // System.out.println(left);
                        break;
                    }
                }
                checkFirst(left);
            } else {
                System.out.println("Ident(" + s + ")");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String str;
        init();
        while ((str = in.readLine()) != null) {
            String[] strs = str.split("\\s");
            for (String s : strs) {
                checkFirst(s);
            }
        }
    }
}
