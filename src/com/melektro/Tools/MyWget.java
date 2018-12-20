/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marius
 */
public class MyWget {
    public static String MyWget(String Url, String ProxyAddress, String ProxyPort) throws MalformedURLException, IOException {
        URL url = new URL(Url);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            List<String> lines = new ArrayList<>();
            for (String line; (line = reader.readLine()) != null;) {
                lines.add(line);
            }
            return lines.toString();
        }
    }
}
