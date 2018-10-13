/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.melektro.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
/**
 *
 * @author Marius
 */
public class makeARandomEMSet {
    private static List<Integer> getUnique(List<Integer> listInts) {
        return listInts.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> makeARandomEMSet() {
        List<Integer> RaList = new ArrayList();
        List<Integer> list;
        List<String> ASet = new ArrayList();
        for (int i = 1; i < 9; i++) {
            do {
                RaList.add(Randoms.GetRandomNumber(49));
                list = getUnique(RaList);
            } while (list.size() < 5);
            Collections.sort(list);

            List<Integer> RaList2 = new ArrayList();
            List<Integer> list2;
            do {
                RaList2.add(Randoms.GetRandomNumber(11));
                list2 = getUnique(RaList2);
            } while (list2.size() < 2);
            Collections.sort(list2);

            ASet.add(list.toString() + "    " + list2.toString());
            list.clear();
            RaList.clear();
            list2.clear();
            RaList2.clear();
        }
        return ASet;
    }
    
}
