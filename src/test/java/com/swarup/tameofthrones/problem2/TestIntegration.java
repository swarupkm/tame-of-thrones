package com.swarup.tameofthrones.problem2;

import com.swarup.tameofthrones.Kingdom;
import com.swarup.tameofthrones.Southeros;
import org.junit.Before;
import org.junit.Test;

public class TestIntegration {

    private Southeros southeros;
    private Messenger messenger = new Messenger();

    @Before
    public void setup() {
        southeros = Southeros.get();
        southeros.register(new Kingdom("LAND", "Panda", null),
                new Kingdom("WATER", "Octopus", null),
                new Kingdom("ICE", "Mammoth", null),
                new Kingdom("AIR", "Owl", null),
                new Kingdom("FIRE", "Dragon", null),
                new Kingdom("SPACE", "Gorilla", "Sham"));

        messenger.getMessagesFromFile("messages.txt");
    }

    @Test
    public void should_pass_for_sample_i_o_1() {
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom air = southeros.getKingdom("AIR");
        Kingdom ice = southeros.getKingdom("ICE");

        Competition competition = new Competition(southeros, messenger);
        competition.register(space, air, ice);

        competition.start();

        System.out.println("Allies of space" + " " + space.allies());
        System.out.println("Allies of air" + " " + air.allies());
        System.out.println("Allies of ice" + " " + ice.allies());

        System.out.println(competition.result());
    }
}
