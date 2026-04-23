package org.example;

public class Calc {
    private static String[] tokens;
    private static int pos;

    public static int run(String expression) {
        String replace = expression.replace("(", " ( ").replace(")", " ) ");
        tokens = replace.trim().split("\\s+");
        pos = 0;
        return parseExpr();
    }

    private static int parseExpr() {
        int result = parseFirst();

        while (pos < tokens.length) {
            String op =  tokens[pos];
            if (!op.equals("+") && !op.equals("-")) break;
            pos++;
            int right = parseFirst();
            if (op.equals("+")) {
                result +=  right;
            } else {
                result -= right;
            }
        }
        return result;
    }

    private static int parseFirst() {
        int result = parseNumber();

        while (pos < tokens.length) {
            String op = tokens[pos];
            if (!op.equals("*")) break;
            pos++;
            int right = parseNumber();
            result *= right;
        }
        return result;
    }

    private static int parseNumber() {
        if (tokens[pos].equals("(")) {
            pos++;
            int result = parseExpr();
            if (tokens[pos].equals(")")) {
                pos++;
                return result;
            }
        }
        return Integer.parseInt(tokens[pos++]);
    }
}
