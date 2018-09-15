package com.swarup.tameofthrones;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BallotCompetition {
    private final Southeros universe;
    private final Messenger messenger;
    private Set<Kingdom> competingKingdoms;

    public BallotCompetition(Southeros southeros, Messenger messenger) {
        this.universe = southeros;
        this.messenger = messenger;
        this.competingKingdoms = new HashSet<>();
    }


    public void register(Kingdom... kingdoms) {
        this.competingKingdoms.addAll(Arrays.asList(kingdoms));
    }

    public void start() {
        for (Kingdom senderKingdom : competingKingdoms) {
            List<Message> messages = messenger.generateMessages(senderKingdom, universe.kingdoms());
            for (Message message : messages) {
                Kingdom receiver = message.getReceiver();
                if (!competingKingdoms.contains(receiver)) {
                    senderKingdom.sendMessageTo(receiver, message.getMessage());
                }
            }
        }
    }

    public Set<Kingdom> competingKingdoms() {
        return new HashSet<>(competingKingdoms);
    }

    public void resetAllegiances() {
        universe.kingdoms().forEach(Kingdom::clearAllies);
    }

}
