package com.swarup.tameofthrones;

import java.util.HashMap;

public class AllyVerifier {

    boolean verify(Kingdom prospectKingdom, String message) {
        HashMap<String, Integer> messageMap = characterIntegerHashMap(message);
        HashMap<String, Integer> emblemMap = characterIntegerHashMap(prospectKingdom.emblem());
        for (String emblemChar : emblemMap.keySet()) {
            if (!messageMap.containsKey(emblemChar) || messageMap.get(emblemChar) < emblemMap.get(emblemChar)) {
                return false;
            }
        }
        return true;
    }

    private HashMap<String, Integer> characterIntegerHashMap(String message) {
        char[] chars = message.toLowerCase().toCharArray();
        HashMap<String, Integer> messageMap = new HashMap<>();
        for (char c : chars) {
            String key = String.valueOf(c);
            messageMap.putIfAbsent(key, 0);
            Integer incrementedCount = messageMap.get(key) + 1;
            messageMap.put(key, incrementedCount);
        }
        return messageMap;
    }
}