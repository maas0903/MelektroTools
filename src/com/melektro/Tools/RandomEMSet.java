/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import jdk.nashorn.internal.objects.NativeArray;

/**
 *
 * @author Marius
 */
public class RandomEMSet {

    private static List<Integer> getUnique(List<Integer> listInts) {
        return listInts.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> makeARandomEMSet() {
        List<Integer> RaList = new ArrayList();
        List<Integer> list;
        List<String> slist = new ArrayList();
        List<String> ASet = new ArrayList();
        for (int i = 1; i < 9; i++) {
            do {
                RaList.add(Randoms.GetRandomNumber(49));
                list = getUnique(RaList);
            } while (list.size() < 5);
            Collections.sort(list);

            List<Integer> RaList2 = new ArrayList();
            List<Integer> list2;
            List<String> slist2 = new ArrayList();
            do {
                RaList2.add(Randoms.GetRandomNumber(11));
                list2 = getUnique(RaList2);
            } while (list2.size() < 2);
            Collections.sort(list2);

            list.forEach(item -> {
                String num = (item < 10) ? ' ' + item.toString() : item.toString();
                slist.add(num);
            });

            list2.forEach(item -> {
                String num = (item < 10) ? ' ' + item.toString() : item.toString();
                slist2.add(num);
            });

            ASet.add(slist + "    " + slist2);
            list.clear();
            slist.clear();
            RaList.clear();
            list2.clear();
            slist2.clear();
            RaList2.clear();
        }
        return ASet;
    }

}
