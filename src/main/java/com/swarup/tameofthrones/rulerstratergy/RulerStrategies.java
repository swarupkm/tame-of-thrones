package com.swarup.tameofthrones.rulerstratergy;

import com.swarup.tameofthrones.Kingdom;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RulerStrategies {
    public static Kingdom atleast3AlliesRulerStrategy(Set<Kingdom> kingdoms) {
        for (Kingdom kingdom : kingdoms)
            if (kingdom.alliesSize() >= 3) return kingdom;
        return Kingdom.nullKingdom();
    }

    public static Kingdom highPriestRulerStrategy(Set<Kingdom> kingdoms) {
        int maxAllies = kingdoms.stream().max(Comparator.comparingInt(Kingdom::alliesSize)).orElse(Kingdom.nullKingdom()).alliesSize();
        Map<Integer, List<Kingdom>> collections = kingdoms.stream().collect(Collectors.groupingBy(Kingdom::alliesSize));
        if (collections.get(maxAllies).size() > 1) {
            return Kingdom.nullKingdom();
        } else {
            return collections.get(maxAllies).get(0);
        }
    }
}
