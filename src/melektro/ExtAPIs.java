/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melektro;

import java.io.IOException;
import static melektro.MyWget.MyWget;

/**
 *
 * @author Marius
 */
public class ExtAPIs {
    public static String GetPublicIp(String ProxyToUse, String ProxyPortToUse) throws Exception {
        return MyWget("http://checkip.amazonaws.com/", ProxyToUse, ProxyPortToUse);
    }
    
    public static String GetIss(String ProxyToUse, String ProxyPortToUse) throws IOException{
        //return MyWget("http://api.open-notify.org/iss-now.json?callback=?");
        return MyWget("http://api.open-notify.org/iss-now.json", ProxyToUse, ProxyPortToUse);
    }

    public static String GetIssWhen(String ProxyToUse, String ProxyPortToUse, String lat, String lon, String count) throws IOException{
        //String url = "http://api.open-notify.org/iss-pass.json?lat="+lat+"&lon="+lon+"&alt=20&n="+count+"&callback=?";
        String url = "http://api.open-notify.org/iss-pass.json?lat="+lat+"&lon="+lon+"&alt=20&n="+count;
        return MyWget(url, ProxyToUse, ProxyPortToUse);
    }

    
}
