package com.swarup.tameofthrones.problem1;

import com.swarup.tameofthrones.Kingdom;
import com.swarup.tameofthrones.Southeros;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestIntegration {

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

    }

    @Test
    public void should_pass_for_sample_i_o() {
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom land = southeros.getKingdom("LAND");
        Kingdom air = southeros.getKingdom("AIR");
        Kingdom ice = southeros.getKingdom("ICE");

        space.sendMessageTo(air, "oaaawaala");
        space.sendMessageTo(land, "a1d22n333a4444p");
        space.sendMessageTo(ice, "zmzmzmzaztzozh");

        assertThat(southeros.ruler()).isEqualTo(space.king());
        assertThat(space.allies()).containsExactlyInAnyOrder(land, air, ice);

    }

    @Test
    public void should_pass_for_sample_i_o_2() {
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom land = southeros.getKingdom("LAND");
        Kingdom air = southeros.getKingdom("AIR");
        Kingdom ice = southeros.getKingdom("ICE");
        Kingdom water = southeros.getKingdom("WATER");
        Kingdom fire = southeros.getKingdom("FIRE");

        space.sendMessageTo(air, "Let’s swing the sword together");
        space.sendMessageTo(land, "Die or play the tame of thrones");
        space.sendMessageTo(ice, "Ahoy! Fight for me with men and money");
        space.sendMessageTo(water, "Summer is coming");
        space.sendMessageTo(fire, "Drag on Martin!");

        assertThat(southeros.ruler()).isEqualTo(space.king());
        assertThat(space.allies()).containsExactlyInAnyOrder(land, air, ice, fire);

    }

    @After
    public void tearDown() {
        southeros.clearKingdoms();
    }

}
