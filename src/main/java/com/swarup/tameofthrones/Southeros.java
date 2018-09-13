package com.swarup.tameofthrones;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Southeros {

    private static Southeros southeros = new Southeros();

    private Set<Kingdom> kingdoms = new HashSet<>();

    //Singleton , eager initialization since there is no cost / dependency involved during initialization
    //Southeros can be converted to a Universe class if situation demands in the future
    public static Southeros get() {
        return southeros;
    }

    public String ruler() {
        Kingdom kingdom = kingdoms().stream().filter(Kingdom::isKingTheRuler).findFirst().orElse(Kingdom.emptyKingdom());
        return kingdom.king();

    }

    public Set<Kingdom> kingdoms() {
        return kingdoms;
    }

    public Kingdom getKingdom(String name) {
        return kingdoms().stream().filter(kingdom -> kingdom.name().equalsIgnoreCase(name)).findFirst().orElse(Kingdom.emptyKingdom());
    }

    public void register(Kingdom... kingdoms) {
        this.kingdoms.addAll(Arrays.asList(kingdoms));
    }

    public void clearKingdoms() {
        kingdoms.clear();
    }
}

