package com.dbettkk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static List<Character> l1 = new ArrayList();
    private static List<Character> l2 = new ArrayList();
    public static void init(){
        for (int i = 0; i < 10 ; i++){
            l1.add((char) ('0' + i));
            l2.add((char) ('0' + i));
        }
        for (int i = 0; i < 26 ; i++){
            l1.add((char) ('a' + i));
            l2.add((char) ('a' + i));
            l1.add((char) ('A' + i));
            l2.add((char) ('A' + i));
        }
        l2.add(':');
        l2.add('+');
        l2.add('*');
        l2.add(',');
        l2.add('(');
        l2.add(')');
        l2.add('=');
    }
    public static boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }

    public static boolean isAlpha(char c){
        if (c >= 'a' && c <= 'z')
            return true;
        return c >= 'A' && c <= 'Z';
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
                checkThird(s);
        }
    }
    public static void checkSecond(String s){
        try {
            // 首先判断是不是数字
            int num = Integer.parseInt(s);
            System.out.println("Int(" + num + ")");
        } catch (Exception e){

            for (int i = 0; i < s.length(); i++){
                char tmp = s.charAt(i);
                if (!l1.contains(tmp)){
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
                        System.out.println("Int(" + Integer.parseInt(sb.toString()) + ")");
                        left = s.substring(i);
                        break;
                    }
                }
                checkFirst(left);
            } else {
                System.out.println("Ident(" + s + ")");
            }
        }
    }
    public static void checkThird(String s){
        int cnt = 0;
        while (true) {
            char tmp = s.charAt(cnt);
            if(!l2.contains(tmp)){
                System.out.println("Unknown");
                System.exit(0);
            }
            if (isNumber(tmp)){
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(s.charAt(cnt));
                    cnt++;
                }while(s.length()>cnt&&isNumber(s.charAt(cnt)));
                if (s.length() > cnt){
                    tmp = s.charAt(cnt);
                }
                System.out.println("Int(" + Integer.parseInt(sb.toString()) + ")");

            }
            if (isAlpha(tmp)){
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(s.charAt(cnt));
                    cnt++;
                }while(s.length()>cnt&&l1.contains(s.charAt(cnt)));
                if (s.length() > cnt){
                    tmp = s.charAt(cnt);
                }
                switch (sb.toString()){
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
                    default:
                        System.out.println("Ident(" + sb + ")");
                }
            }

            switch (tmp){
                case ':':
                    if (s.length() > cnt+1 && s.charAt(cnt+1) == '='){
                        System.out.println("Assign");
                        cnt++;
                    }
                    else System.out.println("Colon");
                    break;
                case '+':
                    System.out.println("Plus");

                    break;
                case '*':
                    System.out.println("Star");
                    break;
                case ',':
                    System.out.println("Comma");
                    break;
                case '(':
                    System.out.println("LParenthesis");
                    break;
                case ')':
                    System.out.println("RParenthesis");
                    break;
                default:
                    cnt--;
            }
            cnt++;
            if (s.length() <= cnt){
                break;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String str;
        init();
        while ((str = in.readLine()) != null) {
            String[] strs = str.split("\\s+");
            for (String s : strs) {
                checkFirst(s);
            }
        }
    }
}
