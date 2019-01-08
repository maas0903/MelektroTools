/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

import com.google.gson.Gson;
import java.io.IOException;
import static com.melektro.Tools.MyWget.MyWget;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.time.Instant;
import java.util.ArrayList;
import java.util.function.Consumer;

/*

/**
 *
 * @author Marius
 */

class Request
{
    public double altitude;
    public int datetime;
    public double latitude;
    public double longitude;
    public int passes;
}

class RequestStr
{
    public double altitude;
    public String datetime;
    public double latitude;
    public double longitude;
    public int passes;
}

class Response
{
    public int duration;
    public int risetime;
}

class Responsestr
{
    public int duration;
    public String risetime;
}

class IssRootObject
{
    public String message;
    public Request request;
    public List<Response> response;
}

class IssRootObjectStr
{
    public String message;
    public RequestStr request;
    public List<Responsestr> response;
}

public class ExtAPIs {

    public static String GetPublicIp(String ProxyToUse, String ProxyPortToUse) throws Exception {
        return MyWget("http://checkip.amazonaws.com/", ProxyToUse, ProxyPortToUse);
    }

    public static String GetIss(String ProxyToUse, String ProxyPortToUse) throws IOException {
        //return MyWget("http://api.open-notify.org/iss-now.json?callback=?");
        return MyWget("http://api.open-notify.org/iss-now.json", ProxyToUse, ProxyPortToUse);
    }

    public static String GetIssWhen(String ProxyToUse, String ProxyPortToUse, String lat, String lon, String count, Integer gmt) throws IOException {
        String url = "http://api.open-notify.org/iss-pass.json?lat=" + lat + "&lon=" + lon + "&alt=20&n=" + count;
        String result = MyWget(url, ProxyToUse, ProxyPortToUse)
                .replace(" ", "")
                .replace(",,", ",")
                .replace("{,\"", "{\"")
                .replace("[,{", "[{")
                .replace(",},],}]", "}]}]")
                .replace(",}", "}")
                //.replace("},\"", "}\"")
                ;
        Gson gson = new Gson();
        result = result.substring(1, result.length()-1);
        
        IssRootObject fromJson = gson.fromJson(result, IssRootObject.class);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        IssRootObjectStr rootObjStr = new IssRootObjectStr();
        rootObjStr.message = fromJson.message;
        rootObjStr.request = new RequestStr();
        rootObjStr.request.altitude = fromJson.request.altitude;
        rootObjStr.request.latitude = fromJson.request.latitude;
        rootObjStr.request.longitude = fromJson.request.longitude;
        rootObjStr.request.passes = fromJson.request.passes;
        String gmtStr;
        if (gmt > 0) {
            gmtStr = "GMT+"+gmt.toString();
        }
        else
        {
            gmtStr = "GMT"+gmt.toString();
        }
        rootObjStr.request.datetime = Instant
                        .ofEpochSecond((Integer)fromJson.request.datetime)
                        .atZone(ZoneId.of(gmtStr))
                        .format(formatter);        
        
        rootObjStr.response = new ArrayList<Responsestr>();

        fromJson.response.forEach(new Consumer<Response>() {
            @Override
            public void accept(Response e) {
                Integer iTmp = e.duration;
                Responsestr responsestr = new Responsestr();
                responsestr.duration = e.duration;
                responsestr.risetime = Instant
                        .ofEpochSecond((Integer)e.risetime)
                        .atZone(ZoneId.of(gmtStr))
                        .format(formatter);
                rootObjStr.response.add(responsestr);
            }
        });
        
        result = gson.toJson(rootObjStr);
                
        return result;
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
