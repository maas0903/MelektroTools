/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

import com.google.gson.GsonBuilder;
import java.io.IOException;
import static com.melektro.Tools.MyWget.MyWget;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdk.nashorn.internal.objects.Global;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*

/**
 *
 * @author Marius
 */
public class ExtAPIs {

    public static String GetPublicIp(String ProxyToUse, String ProxyPortToUse) throws Exception {
        return MyWget("http://checkip.amazonaws.com/", ProxyToUse, ProxyPortToUse);
    }

    public static String GetIss(String ProxyToUse, String ProxyPortToUse) throws IOException {
        //return MyWget("http://api.open-notify.org/iss-now.json?callback=?");
        return MyWget("http://api.open-notify.org/iss-now.json", ProxyToUse, ProxyPortToUse);
    }

    public static String GetIssWhen(String ProxyToUse, String ProxyPortToUse, String lat, String lon, String count) throws IOException {
        //String url = "http://api.open-notify.org/iss-pass.json?lat="+lat+"&lon="+lon+"&alt=20&n="+count+"&callback=?";
        String url = "http://api.open-notify.org/iss-pass.json?lat=" + lat + "&lon=" + lon + "&alt=20&n=" + count;
        return MyWget(url, ProxyToUse, ProxyPortToUse);
    }

    public static String GetDatePartWithFormat(String format) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String GetAday(String ADay, String ProxyToUse, String ProxyPortToUse) throws IOException, InterruptedException {
        String result = MyWget("https://wikipedia.org/wiki/" + ADay, ProxyToUse, ProxyPortToUse);
        return result;
    }

    public static String GetToday_Formatted() throws IOException, InterruptedException {
        return InHistory.Today();
    }
    
    public static String GetADay_Formatted(String ADay) throws IOException, InterruptedException {
        return InHistory.GetHistory(ADay);
    }
}
