/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melektro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import static melektro.LogsFormatter.Log;

/**
 *
 * @author Marius
 */
public class MyWget {

    /*
    public static final String NEWLINE = System.getProperty("line.separator");

    public String readContents(File f) throws IOException {
        StringBuilder builder = new StringBuilder(1024);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line).append(NEWLINE);
            }
        } finally {
            closeQuietly(br);
        }
        return builder.toString();
    }
    */
    /*
    private void closeQuietly(BufferedReader br) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

    public static String MyWget(String Url, String ProxyAddress, String ProxyPort) throws MalformedURLException, IOException {
        URL url = new URL(Url);
        URLConnection connection;
        if (ProxyAddress.isEmpty()) {
            connection = url.openConnection();
        } else {
            Log("  proxy=" + ProxyAddress);
            Log("Using proxy");
            Log("  port=" + ProxyPort);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyAddress, Integer.parseInt(ProxyPort)));
            connection = url.openConnection(proxy);
        }
        connection.addRequestProperty("Protocol", "Http/1.1");
        connection.addRequestProperty("Connection", "keep-alive");
        connection.addRequestProperty("Keep-Alive", "1000");
        connection.addRequestProperty("User-Agent", "Web-Agent");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        //ToDo - read into StringBuilder like above commented method. 
        CharBuffer cbuf = CharBuffer.allocate(5000);
        int read = in.read(cbuf);
        cbuf.flip();

        //String ret = in.readLine();
        return cbuf.toString();

        //String ret = in.readLine();
        //return ret;
    }
}
