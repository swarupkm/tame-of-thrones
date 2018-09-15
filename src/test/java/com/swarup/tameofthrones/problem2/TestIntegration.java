package com.swarup.tameofthrones.problem2;

import com.swarup.tameofthrones.BallotCompetition;
import com.swarup.tameofthrones.Kingdom;
import com.swarup.tameofthrones.Messenger;
import com.swarup.tameofthrones.Southeros;
import com.swarup.tameofthrones.rulerstratergy.RulerStrategies;
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

        southeros.setRulerFindingStratergy(RulerStrategies::highPriestRulerStrategy);

        messenger.prepareMessagesFromFile("messages.txt");
    }

    @Test
    public void should_pass_for_sample_i_o_1() {
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom air = southeros.getKingdom("AIR");
        Kingdom ice = southeros.getKingdom("ICE");

        BallotCompetition competition = new BallotCompetition(southeros, messenger);
        competition.register(space, air, ice);

        competition.start();

        System.out.println("Allies of space" + " " + space.allies());
        System.out.println("Allies of air" + " " + air.allies());
        System.out.println("Allies of ice" + " " + ice.allies());

        System.out.println(southeros.rulingKingdomAmong(competition.competingKingdoms()));
    }
}
