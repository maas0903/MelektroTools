/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 *
 * @author marius
 */
public class Nato {

    public static String GetNato(String inputString) {
        String returnStr = "";
        HashMap<String, String> natoMap = new HashMap<>();
        natoMap.put("A", "Alfa     =>	.-   ");
        natoMap.put("B", "Bravo    =>	-... ");
        natoMap.put("C", "Charlie  =>	-.-. ");
        natoMap.put("D", "Delta    =>	-..  ");
        natoMap.put("E", "Echo     =>	.    ");
        natoMap.put("F", "Foxtrot  =>	..-. ");
        natoMap.put("G", "Golf     =>	--.  ");
        natoMap.put("H", "Hotel    =>	.... ");
        natoMap.put("I", "India    =>	..   ");
        natoMap.put("J", "Juliett  =>	.--- ");
        natoMap.put("K", "Kilo     =>	-.-  ");
        natoMap.put("L", "Lima     =>	.-.. ");
        natoMap.put("M", "Mike     =>	--   ");
        natoMap.put("N", "November =>	-.   ");
        natoMap.put("O", "Oscar    =>	---  ");
        natoMap.put("P", "Papa     =>	.--. ");
        natoMap.put("Q", "Quebec   =>	--.- ");
        natoMap.put("R", "Romeo    =>	.-.  ");
        natoMap.put("S", "Sierra   =>	...  ");
        natoMap.put("T", "Tango    =>	-    ");
        natoMap.put("U", "Uniform  =>	..-  ");
        natoMap.put("V", "Victor   =>	...- ");
        natoMap.put("W", "Whiskey  =>	.--  ");
        natoMap.put("X", "X-ray    =>	-..- ");
        natoMap.put("Y", "Yankee   =>	-.-- ");
        natoMap.put("Z", "Zulu     =>	--.. ");
        natoMap.put("a", "Alfa     =>	.-   ");
        natoMap.put("b", "Bravo    =>	-... ");
        natoMap.put("c", "Charlie  =>	-.-. ");
        natoMap.put("d", "Delta    =>	-..  ");
        natoMap.put("e", "Echo     =>	.    ");
        natoMap.put("f", "Foxtrot  =>	..-. ");
        natoMap.put("g", "Golf     =>	--.  ");
        natoMap.put("h", "Hotel    =>	.... ");
        natoMap.put("i", "India    =>	..   ");
        natoMap.put("j", "Juliett  =>	.--- ");
        natoMap.put("k", "Kilo     =>	-.-  ");
        natoMap.put("l", "Lima     =>	.-.. ");
        natoMap.put("m", "Mike     =>	--   ");
        natoMap.put("n", "November =>	-.   ");
        natoMap.put("o", "Oscar    =>	---  ");
        natoMap.put("p", "Papa     =>	.--. ");
        natoMap.put("q", "Quebec   =>	--.- ");
        natoMap.put("r", "Romeo    =>	.-.  ");
        natoMap.put("s", "Sierra   =>	...  ");
        natoMap.put("t", "Tango    =>	-    ");
        natoMap.put("u", "Uniform  =>	..-  ");
        natoMap.put("v", "Victor   =>	...- ");
        natoMap.put("w", "Whiskey  =>	.--  ");
        natoMap.put("x", "X-ray    =>	-..- ");
        natoMap.put("y", "Yankee   =>	-.-- ");
        natoMap.put("z", "Zulu     =>	--.. ");
        natoMap.put("0", "Zero     =>   -----");
        natoMap.put("1", "One      =>   .----");
        natoMap.put("2", "Two      =>   ..---");
        natoMap.put("3", "Three    =>   ...--");
        natoMap.put("4", "Four     =>   ....-");
        natoMap.put("5", "Five     =>   .....");
        natoMap.put("6", "Six      =>   -....");
        natoMap.put("7", "Seven    =>   --...");
        natoMap.put("8", "Eight    =>   ---..");
        natoMap.put("9", "Nine     =>   ----.");
        natoMap.put("-", "Dash     =>   -....-");
        natoMap.put(".", "Period   =>   .-.-.-");
        natoMap.put("Ä", "Ä        =>    .-.-   ");
        natoMap.put("Á", "Á        =>    .--.-  ");
        natoMap.put("Å", "Å        =>    .--.-  ");
        natoMap.put("É", "É        =>    ..-..  ");
        natoMap.put("Ñ", "Ñ        =>    --.--  ");
        natoMap.put("Ö", "Ö        =>    ---.   ");
        natoMap.put("Ü", "Ü        =>    ..--   ");
        natoMap.put("&", "&        =>    .-...  ");
        natoMap.put("'", "'        =>    .----. ");
        natoMap.put("@", "@        =>    .--.-. ");
        natoMap.put(")", ")        =>    -.--.- ");
        natoMap.put("(", "(        =>    -.--.  ");
        natoMap.put(":", ":        =>    ---... ");
        natoMap.put(",", ",        =>    --..-- ");
        natoMap.put("=", "=        =>    -...-  ");
        natoMap.put("!", "!        =>           ");
        natoMap.put("+", "+        =>    .-.-.  ");
        natoMap.put("?", "?        =>    ..--.. ");
        natoMap.put("/", "/        =>    -..-.  ");
        
        String enter = System.getProperty("line.separator");

        for (int i = 0; i < inputString.length(); i++) {
            String natoStr = natoMap.get("" + inputString.charAt(i));
            if (natoStr == null) {
                natoStr = "" + inputString.charAt(i);
            }
            returnStr = MessageFormat.format("{0} {1} - {2}{3}", returnStr, inputString.charAt(i), natoStr, enter);
        }

        returnStr = inputString + enter + returnStr;
        return returnStr.trim();
    }
}
