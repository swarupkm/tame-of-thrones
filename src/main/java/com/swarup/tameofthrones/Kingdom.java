package com.swarup.tameofthrones;

import com.swarup.tameofthrones.exceptions.InvalidAllyException;
import com.swarup.tameofthrones.exceptions.InvalidMessageException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Kingdom {
    private static final int MIN_ALLIES_FOR_BEING_RULER = 3;
    private final String name;
    private final String emblem;
    private final AllyVerifier allyVerifier = new AllyVerifier();
    private String king;
    private Set<Kingdom> allies = new HashSet<>();

    public Kingdom(String name, String emblem, String king) {
        this.name = name;
        this.emblem = emblem;
        this.king = king;
    }

    public static Kingdom emptyKingdom() {
        return new Kingdom(null, null, "None");
    }

    public void addAlly(Kingdom otherKingdom) {
        if (otherKingdom.name() == null || otherKingdom.name() == this.name()) {
            throw new InvalidAllyException();
        }
        allies.add(otherKingdom);
    }

    boolean hasAlly(Kingdom otherKingdom) {
        return allies.contains(otherKingdom);
    }

    public Set<Kingdom> allies() {
        return allies;
    }

    public int alliesSize() {
        return allies.size();
    }

    public boolean isKingTheRuler() {
        if (allies.size() >= MIN_ALLIES_FOR_BEING_RULER)
            return true;
        return false;
    }

    public String king() {
        return king;
    }

    public String name() {
        return name;
    }

    public boolean sendMessageTo(Kingdom otherKingdom, String message) {
        if (this == otherKingdom) {
            throw new InvalidMessageException();
        }

        boolean hasResponded = allyVerifier.verify(otherKingdom, message);
        if (hasResponded)
            addAlly(otherKingdom);
        return hasResponded;
    }

    public String emblem() {
        return emblem;
    }

    @Override
    public String toString() {
        return "Kingdom{" +
                "name='" + name + '\'' +
                ", emblem='" + emblem + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kingdom kingdom = (Kingdom) o;
        return Objects.equals(name, kingdom.name) &&
                Objects.equals(emblem, kingdom.emblem);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, emblem);
    }
}

