package com.swarup.tameofthrones.problem2;

import com.swarup.tameofthrones.Kingdom;
import com.swarup.tameofthrones.Southeros;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Competition {
    private final Southeros universe;
    private final Messenger messenger;
    private Set<Kingdom> competingKingdoms;

    public Competition(Southeros southeros, Messenger messenger) {
        this.universe = southeros;
        this.messenger = messenger;
        competingKingdoms = new HashSet<>();
    }


    public void register(Kingdom... kingdoms) {
        this.competingKingdoms.addAll(Arrays.asList(kingdoms));
    }

    public void start() {
        competingKingdoms.forEach(senderKingdom -> {
            List<Message> messages = messenger.generateMessages(senderKingdom, universe.kingdoms());
            messages.forEach(message -> {
                senderKingdom.sendMessageTo(message.getReciever(), message.getMessage());
            });
        });
    }

    public Kingdom result() {
        int maxAllies = universe.kingdoms().stream().max(Comparator.comparingInt(Kingdom::alliesSize)).orElse(Kingdom.emptyKingdom()).alliesSize();
        Map<Integer, List<Kingdom>> collections = universe.kingdoms().stream().collect(Collectors.groupingBy(Kingdom::alliesSize));
        if (collections.get(maxAllies).size() > 1) {
            return Kingdom.emptyKingdom();
        } else {
            return collections.get(maxAllies).get(0);
        }
    }
}
