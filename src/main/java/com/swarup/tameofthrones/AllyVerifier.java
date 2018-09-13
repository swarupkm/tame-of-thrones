package com.swarup.tameofthrones;

import java.util.HashMap;

public class AllyVerifier {
    public AllyVerifier() {
    }

    boolean verify(Kingdom prospectKingdom, String message) {
        HashMap<String, Integer> messageMap = Utils.characterIntegerHashMap(message);
        HashMap<String, Integer> emblemMap = Utils.characterIntegerHashMap(prospectKingdom.emblem());
        for (String emblemChar : emblemMap.keySet()) {
            if (!messageMap.containsKey(emblemChar) || messageMap.get(emblemChar) < emblemMap.get(emblemChar)) {
                return false;
            }
        }
        return true;
    }
}