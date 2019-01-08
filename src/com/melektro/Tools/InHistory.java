/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

import com.google.gson.GsonBuilder;
import static com.melektro.Tools.ExtAPIs.GetDatePartWithFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import static com.melektro.Tools.ExtAPIs.GetAday;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author marius
 */
class Data {

    public List<String> Events;
    public List<String> Births;
    public List<String> Deaths;
}

class RootObject {

    public String date;
    public Data data;
}

public class InHistory {

    private static final String MONTHTEST = ",january,february,march,april,may,june,july,august,september,october,november,december,";

    private static boolean ValidDate(String ADay) {
        try {
            String month = ADay.substring(0, ADay.indexOf("_"));
            return MONTHTEST.contains("," + month.toLowerCase() + ",");
        } catch (Exception e) {
            return false;
        }
    }

    private static List<String> GetList(Document localDoc, String listString) {
        //String result;
        listString = listString.replaceAll("\\[\\d+\\]", "");
        localDoc = Jsoup.parse(listString);

        Elements lines = localDoc.select("li");

        List<String> ElementsSet = new ArrayList<>();

        lines.forEach(element -> {
            ElementsSet.add(element.text().replace(" – ", " - ").replace("\\\"", "\""));
        });

        return ElementsSet;
    }

    public static String GetHistory(String ADay) throws IOException, InterruptedException {

        if (!ValidDate(ADay)) {
            return ADay + " is not a valid date. The format is month_day. For example, december_25";
        }

        String debugString;

        ADay = StringUtils.capitalize(ADay);

        String result = GetAday(ADay, "", "");
        result = result.substring(1, result.length() - 1);

        result = result.replace(">, <", "> <");
        result = result.replace(">, , <", "> <");
        result = result.replace(">,", ">");

        Document doc = Jsoup.parse(result);
//        doc.select("table").remove();
//        doc.getElementsByClass("mw-empty-elt").remove();
//        doc.getElementsByClass("shortdescription nomobile noexcerpt noprint searchaux").remove();
//        doc.getElementById("toc").remove();
//        doc.getElementById("Holidays_and_observances").remove();
//        doc.getElementsByClass("reflist").remove();
        Elements content = doc.getElementsByClass("mw-parser-output");

        String today = content.toString();

        Elements h2Tags = doc.select("h2");
        String eventString = h2Tags.get(0).toString();
        String birthsString = h2Tags.get(1).toString();
        String deathsString = h2Tags.get(2).toString();
        String holidaysString = h2Tags.get(3).toString();

        Integer eventsIndex = today.indexOf(eventString);
        Integer birthsIndex = today.indexOf(birthsString);
        Integer deathsIndex = today.indexOf(deathsString);
        Integer holidaysIndex = today.indexOf(holidaysString);

        try {
            if (eventsIndex < 0 || birthsIndex < 0) {
                result = "Indexes not calculated: \rbeginIndex=" + eventsIndex + ", endIndex=" + birthsIndex + "\r" + today;
            } else {
                String listEventsString1 = today.substring(eventsIndex + eventString.length(), birthsIndex);
                List<String> listEvents = GetList(doc, listEventsString1);

                String listBirthsString1 = today.substring(birthsIndex + birthsString.length(), deathsIndex);
                List<String> listBirths = GetList(doc, listBirthsString1);

                String listDeathsString1 = today.substring(deathsIndex + deathsString.length(), holidaysIndex);
                List<String> listDeaths = GetList(doc, listDeathsString1);

                RootObject rootObject = new RootObject();
                rootObject.date = ADay;
                rootObject.data = new Data();
                rootObject.data.Births = listBirths;
                rootObject.data.Deaths = listDeaths;
                rootObject.data.Events = listEvents;

                result = new GsonBuilder().setPrettyPrinting().create().toJson(rootObject);
                result = UnEscapeString.Unescape(result);

                /*
                String listEventsString = today.substring(eventsIndex + eventString.length(), birthsIndex);
                listEventsString = GetList(doc, listEventsString);

                String listBirthsString = today.substring(birthsIndex + birthsString.length(), deathsIndex);
                listBirthsString = GetList(doc, listBirthsString);

                String listDeathsString = today.substring(deathsIndex + deathsString.length(), holidaysIndex);
                listDeathsString = GetList(doc, listDeathsString);
                result = listEventsString + listBirthsString + listDeathsString;
                */
            }

        } catch (Exception e) {
            result = "Cannot Parse";
        }

        return result;
    }

    public static String Today() throws IOException, InterruptedException {

        String dateToday = GetDatePartWithFormat("MMMM") + "_" + GetDatePartWithFormat("d");

        return GetHistory(dateToday);
    }

}
