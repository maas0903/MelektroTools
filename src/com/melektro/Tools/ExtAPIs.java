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

    public static String GetToday(String ProxyToUse, String ProxyPortToUse) throws IOException {
        String dateToday = GetDatePartWithFormat("MMMM") + "_" + GetDatePartWithFormat("d");
        return MyWget("https://wikipedia.org/wiki/" + dateToday, ProxyToUse, ProxyPortToUse);

        //return MyWget("https://history.muffinlabs.com/date", ProxyToUse, ProxyPortToUse);
    }

    public static String GetToday_Formatted() throws IOException {
        String eventsString = "<h2><span class=\"mw-headline\" id=\"Events\">Events</span><span class=\"mw-editsection\"><span class=\"mw-editsection-bracket\">[</span><a href=\"/w/index.php?title=December_29&amp;action=edit&amp;section=x\" title=\"Edit section: Events\">edit</a><span class=\"mw-editsection-bracket\">]</span></span></h2>";
        String birthsString = "<h2><span class=\"mw-headline\" id=\"Births\">Births</span><span class=\"mw-editsection\"><span class=\"mw-editsection-bracket\">[</span><a href=\"/w/index.php?title=December_29&amp;action=edit&amp;section=x\" title=\"Edit section: Births\">edit</a><span class=\"mw-editsection-bracket\">]</span></span></h2>";
        String deathsString = "<h2><span class=\"mw-headline\" id=\"Deaths\">Deaths</span><span class=\"mw-editsection\"><span class=\"mw-editsection-bracket\">[</span><a href=\"/w/index.php?title=December_29&amp;action=edit&amp;section=x\" title=\"Edit section: Deaths\">edit</a><span class=\"mw-editsection-bracket\">]</span></span></h2>";

        //var obj = JSON.parse(GetToday("",""));
        String result = GetToday("", "");
        result = result.substring(1, result.length() - 1);

        result = result.replace(">, <", "> <");
        result = result.replace(">, , <", "> <");
        result = result.replace(">,", ">");

        Document doc = Jsoup.parse(result);

//        doc.select("head").remove();
//        doc.getElementById("siteSub").remove();
//        doc.getElementById("mw-fr-revisiondetails").remove();
//        doc.getElementById("contentSub").remove();
//        doc.getElementById("mw-indicator-pp-autoreview").remove();
//        doc.getElementById("siteNotice").remove();
//        doc.getElementById("top").remove();
//        doc.getElementById("firstHeading").remove();
//        doc.getElementsByClass("mw-indicators mw-body-content").remove();
//        result = doc.toString();
        doc.select(
                "table").remove();
        doc.getElementsByClass(
                "mw-empty-elt").remove();
        doc.getElementsByClass(
                "shortdescription nomobile noexcerpt noprint searchaux").remove();
        doc.getElementById(
                "toc").remove();
        doc.getElementById(
                "Holidays_and_observances").remove();
        doc.getElementsByClass(
                "reflist").remove();
        //doc.getElementsByClass("mw-editsection").remove();
        //doc.getElementsByClass("mw-headline").remove();
        Elements content = doc.getElementsByClass("mw-parser-output");
        //result = content.toString();

        String events = content.toString();

        String dateToday = GetDatePartWithFormat("MMMM") + "_" + GetDatePartWithFormat("d");
        birthsString = birthsString.replace("December_29", dateToday);
        eventsString = eventsString.replace("December_29", dateToday);
        events = events.replaceAll("section\\=(?<section>\\d+)", "section=x");

        try {
            Integer beginIndex = events.indexOf(eventsString);
            Integer endIndex = events.indexOf(birthsString);
            if (beginIndex > 0 && endIndex > 0) {
                events = events.substring(beginIndex + eventsString.length(), endIndex).replaceAll("\\[\\d+\\]", "");
                doc = Jsoup.parse(events);

                Elements lines = doc.select("li");

                List<String> ThisSet = new ArrayList<>();

                lines.forEach(element -> {
                    ThisSet.add(element.text().replace(" – ", " - "));
                });

                result = new GsonBuilder().setPrettyPrinting().create().toJson(ThisSet).replace("\\\"", "\"");
                result = UnEscapeString.Unescape(result);
            } else {
                result = "Indexes not calculated";
            }
        } catch (Exception e) {
            result = "Cannot Parse";
        }

        //Remove all HTML tags
        //result = doc.text();
//        Elements toRemove = doc.select("<head>");
//        toRemove.remove();
//        Elements sections = doc.select("section");
//        Element editSection = sections.get(0);
//        Elements toRemove = doc.select("div");
//        toRemove.remove();
//        toRemove = doc.select("table");
//        toRemove.remove();
        /*
        Iterator<Element> iter = divs.iterator();
        
        while(iter.hasNext()){
            Element div = iter.next();
        };
         */
        //List<Element> element = doc.getElementsByAttribute("Events");
        //List<Node> events = element.childNodes();
        //result = editSection.html();
        return result;
    }
}
