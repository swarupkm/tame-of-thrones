package com.swarup.tameofthrones;

import com.swarup.tameofthrones.exceptions.InvalidAllyException;
import com.swarup.tameofthrones.exceptions.InvalidMessageException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Kingdom {

    private final String name;
    private final String emblem;
    private String king;
    private Set<Kingdom> allies = new HashSet<>();

    public Kingdom(String name, String emblem, String king) {
        this.name = name;
        this.emblem = emblem;
        this.king = king;
    }

    public Kingdom(String name, String emblem) {
        this.name = name;
        this.emblem = emblem;
    }

    public static Kingdom nullKingdom() {
        return new Kingdom(null, null, "None");
    }

    public String king() {
        return king;
    }

    public Set<Kingdom> allies() {
        return new HashSet<>(allies);
    }

    public String name() {
        return name;
    }

    public String emblem() {
        return emblem;
    }

    public void addAlly(Kingdom otherKingdom) {
        if (otherKingdom.name() == null || otherKingdom == this) {
            throw new InvalidAllyException("cannot add self has ally");
        }

        if (!hasAlly(otherKingdom)) {
            allies.add(otherKingdom);
            otherKingdom.addAlly(this);
        }
    }

    boolean hasAlly(Kingdom otherKingdom) {
        return allies.contains(otherKingdom);
    }

    public int alliesSize() {
        return allies.size();
    }

    public boolean sendMessageTo(Kingdom otherKingdom, String message) {
        if (this == otherKingdom) {
            throw new InvalidMessageException();
        }

        boolean hasResponded = new MessageVerifier().verify(otherKingdom, message);
        if (hasResponded)
            addAlly(otherKingdom);

        return hasResponded;
    }

    public void clearAllies(){
        this.allies.clear();
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

