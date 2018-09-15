package com.swarup.tameofthrones.rulerstratergy;

import com.swarup.tameofthrones.Kingdom;

import java.util.Set;

@FunctionalInterface
public interface RulerStrategy {
    Kingdom getRuler(Set<Kingdom> kingdoms);
}
