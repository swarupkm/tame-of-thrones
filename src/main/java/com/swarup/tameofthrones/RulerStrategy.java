package com.swarup.tameofthrones;

import java.util.Set;

@FunctionalInterface
public interface RulerStrategy {
    Kingdom getRuler(Set<Kingdom> kingdoms);
}
