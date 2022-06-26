package com.ali.mangareader.utils;

import java.util.List;

public class JoinList {

    public String join(List<String> list) {
        String names = "";
        int j = 1;
        for (String n: list) {
            if (j == 1) {
                names += n;
                j++;
                continue;
            }
            names = names + ", " + n;
            j++;
        }
        return names;
    }

}
