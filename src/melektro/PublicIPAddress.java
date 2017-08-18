/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melektro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import static melektro.LogsFormatter.Log;

/**
 *
 * @author Marius
 */

public class PublicIPAddress {
    
    public static String GetPublicIp(String ProxyToUse, String ProxyPortToUse) throws Exception {
        URL whatismyip = new URL("http://checkip.amazonaws.com/");
        URLConnection connection;
        if (ProxyToUse.isEmpty()) {
            connection = whatismyip.openConnection();
        } else {
            Log("  proxy=" + ProxyToUse);
            Log("Using proxy");
            Log("  port=" + ProxyPortToUse);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyToUse, Integer.parseInt(ProxyPortToUse)));
            connection = whatismyip.openConnection(proxy);
        }
        connection.addRequestProperty("Protocol", "Http/1.1");
        connection.addRequestProperty("Connection", "keep-alive");
        connection.addRequestProperty("Keep-Alive", "1000");
        connection.addRequestProperty("User-Agent", "Web-Agent");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String ip = in.readLine();
        return ip;
    }
}
