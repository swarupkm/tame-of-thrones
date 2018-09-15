package com.swarup.tameofthrones;

import com.swarup.tameofthrones.rulerstratergy.RulerStrategies;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSoutheros {

    private Southeros southeros;

    @Before
    public void setup() {
        southeros = Southeros.get();
        southeros.register(new Kingdom("LAND", "Panda", null),
                new Kingdom("WATER", "Octopus", null),
                new Kingdom("ICE", "Mammoth", null),
                new Kingdom("AIR", "Owl", null),
                new Kingdom("FIRE", "Dragon", null),
                new Kingdom("SPACE", "Gorilla", "Sham"));

        southeros.setRulerFindingStratergy(RulerStrategies::atleast3AlliesRulerStrategy);

    }

    @Test
    public void should_have_6_kingdoms() {
        assertThat(southeros.kingdoms()).hasSize(6);
    }

    @Test
    public void should_have_no_rulers_when_no_kingdom_has_at_least_3_allies() {
        assertThat(southeros.ruler()).isEqualTo("None");
    }

    @Test
    public void should_have_ruler_when_one_kingdom_has_atleast_3_allies() {
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom water = southeros.getKingdom("WATER");
        Kingdom ice = southeros.getKingdom("ICE");
        Kingdom air = southeros.getKingdom("AIR");

        space.addAlly(water);
        space.addAlly(ice);
        space.addAlly(air);

        assertThat(southeros.ruler()).isEqualTo(space.king());

    }

    @After
    public void tearDown() {
        southeros.clearKingdoms();
    }
}
