package com.roy.landcomparator.datasource;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CharacterFilter {

    public static String getCorrectCidnum(double CIDNUM) {

        Double dCidnum = CIDNUM;
        Integer iCidnum = (int) CIDNUM;

        String temp = dCidnum.toString();

        List<String> tokens = new ArrayList<>();

        StringTokenizer stringtokenizer = new StringTokenizer(temp, ".");

        while (stringtokenizer.hasMoreElements()) {
            tokens.add(stringtokenizer.nextToken());
        }

        if (Integer.parseInt(tokens.get(1)) > 0) {
            return dCidnum.toString();
        } else {
            return iCidnum.toString();
        }
    }
}
