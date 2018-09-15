package com.swarup.tameofthrones;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BallotCompetition {
    private final Southeros universe;
    private final Messenger messenger;
    private Set<Kingdom> competingKingdoms;
    private Set<Kingdom> allegianceProviders;

    public BallotCompetition(Southeros southeros, Messenger messenger) {
        this.universe = southeros;
        this.messenger = messenger;
        this.competingKingdoms = new HashSet<>();
        this.allegianceProviders = new HashSet<>();
    }


    public void register(Kingdom... kingdoms) {
        this.competingKingdoms.addAll(Arrays.asList(kingdoms));
    }

    public void start() {
        for (Kingdom senderKingdom : competingKingdoms) {
            List<Message> messages = messenger.generateMessages(senderKingdom, universe.kingdoms());
            for (Message message : messages) {
                boolean success = false;
                Kingdom receiver = message.getReceiver();
                if (!allegianceProviders.contains(receiver) && !competingKingdoms.contains(receiver)) {
                    success = senderKingdom.sendMessageTo(receiver, message.getMessage());
                }
                if (success) {
                    allegianceProviders.add(receiver);
                }
            }
        }
    }

    public Set<Kingdom> competingKingdoms() {
        return new HashSet<>(competingKingdoms);
    }

    public Set<Kingdom> allegianceProviders() {
        return new HashSet<>(allegianceProviders);
    }

    public void resetAllegiances(){
        allegianceProviders.clear();
        universe.kingdoms().forEach(Kingdom::clearAllies);
    }

}
