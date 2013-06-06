package com.trebogeer.jboost.json;

/**
 * @author dimav
 *         Date: 4/11/13
 *         Time: 7:01 PM
 */
public class JSONToken {

    //
    public final static byte LPAREN = 10; // ("("),
    //
    public final static int RPAREN = 11; // (")"),
    //
    public final static int LBRACE = 12; // ("{"),
    //
    public final static int RBRACE = 13; // ("}"),
    //
    public final static int LBRACKET = 14; // ("["),
    //
    public final static int RBRACKET = 15; // ("]"),
    //
    public final static int COMMA = 16; // (","),
    //
    public final static int COLON = 17; // (":"),
    //
    public final static int IDENTIFIER = 18;
    //
    public final static int FIELD_NAME = 19;

    public final static int EOF = 20;

    public final static int SET = 21;
    public final static int TREE_SET = 22;

    public static String name(int value) {
        switch (value) {
            case LPAREN:
                return "(";
            case RPAREN:
                return ")";
            case LBRACE:
                return "{";
            case RBRACE:
                return "}";
            case LBRACKET:
                return "[";
            case RBRACKET:
                return "]";
            case COMMA:
                return ",";
            case COLON:
                return ":";
            case IDENTIFIER:
                return "ident";
            case FIELD_NAME:
                return "fieldName";
            case EOF:
                return "EOF";
            case SET:
                return "Set";
            case TREE_SET:
                return "TreeSet";
            default:
                return "Unkown";
        }
    }

    public static void main(String... a) {
        for (byte b : "\\{".getBytes()) {
            System.out.print(b);
            System.out.print("|||");
        }
        System.out.println();
        for (byte b : "[".getBytes()) {
            System.out.print(b);
            System.out.print("|||");
        }
        System.out.println();
        for (byte b : "]".getBytes()) {
            System.out.print(b);
            System.out.print("|||");
        }
        System.out.println();
        for (byte b : ",".getBytes()) {
            System.out.print(b);
            System.out.print("|||");
        }
    }
}
