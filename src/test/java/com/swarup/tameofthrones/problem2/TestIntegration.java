package com.swarup.tameofthrones.problem2;

import com.swarup.tameofthrones.BallotCompetition;
import com.swarup.tameofthrones.Kingdom;
import com.swarup.tameofthrones.Message;
import com.swarup.tameofthrones.Messenger;
import com.swarup.tameofthrones.Southeros;
import com.swarup.tameofthrones.rulerstratergy.RulerStrategies;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestIntegration {

    private static final String FAILED_MESSAGE = "zzzzzzzzzzzzzzzzzzzzzzzzzz";
    private Southeros southeros;
    private Messenger messenger;
    private BallotCompetition competition;

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

        messenger = mock(Messenger.class);
        competition = new BallotCompetition(southeros, messenger);
    }

    @Test
    public void should_pass_for_sample_i_o_1() {
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom air = southeros.getKingdom("AIR");
        Kingdom land = southeros.getKingdom("LAND");
        Kingdom fire = southeros.getKingdom("FIRE");
        Kingdom ice = southeros.getKingdom("ICE");

        competition.register(ice, space, air);

        when(messenger.generateMessages(refEq(ice), any())).thenReturn(messages(ice, "Panda Dragon"));
        when(messenger.generateMessages(refEq(space), any())).thenReturn(messages(space, "Octopus"));
        when(messenger.generateMessages(refEq(air), any())).thenReturn(messages(air, "Octopus"));

        competition.start();

        assertThat(ice.alliesSize()).isEqualTo(2);
        assertThat(space.alliesSize()).isEqualTo(1);
        assertThat(air.alliesSize()).isEqualTo(1);

        Kingdom rulingKingdom = southeros.rulingKingdomAmong(competition.competingKingdoms());

        assertThat(rulingKingdom).isEqualTo(ice);
        assertThat(rulingKingdom.allies()).containsExactlyInAnyOrder(land, fire);
    }

    @Test
    public void should_pass_for_sample_i_o_2() {
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom air = southeros.getKingdom("AIR");
        Kingdom land = southeros.getKingdom("LAND");
        Kingdom fire = southeros.getKingdom("FIRE");

        competition.register(land, air);

        when(messenger.generateMessages(refEq(land), any())).thenReturn(messages(land, "Octopus"));
        when(messenger.generateMessages(refEq(air), any())).thenReturn(messages(air, "Mammoth"));

        competition.start();

        assertThat(land.alliesSize()).isEqualTo(1);
        assertThat(air.alliesSize()).isEqualTo(1);

        Kingdom rulingKingdom = southeros.rulingKingdomAmong(competition.competingKingdoms());

        assertThat(rulingKingdom).isEqualTo(Kingdom.nullKingdom());

        // NEW ROUND OF VOTING

        competition.resetAllegiances();

        when(messenger.generateMessages(refEq(land), any())).thenReturn(messages(land, "Octopus"));
        when(messenger.generateMessages(refEq(air), any())).thenReturn(messages(air, "Dragon Gorilla"));

        competition.start();

        assertThat(land.alliesSize()).isEqualTo(1);
        assertThat(air.alliesSize()).isEqualTo(2);

        rulingKingdom = southeros.rulingKingdomAmong(competition.competingKingdoms());

        assertThat(rulingKingdom).isEqualTo(air);
        assertThat(rulingKingdom.allies()).containsExactlyInAnyOrder(fire, space);

    }

    @Test
    public void should_pass_for_sample_i_o_3() {
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom ice = southeros.getKingdom("ICE");
        Kingdom land = southeros.getKingdom("LAND");
        Kingdom fire = southeros.getKingdom("FIRE");

        competition.register(fire, space);

        when(messenger.generateMessages(refEq(fire), any())).thenReturn(messages(fire, FAILED_MESSAGE));
        when(messenger.generateMessages(refEq(space), any())).thenReturn(messages(space, FAILED_MESSAGE));

        competition.start();

        assertThat(fire.alliesSize()).isEqualTo(0);
        assertThat(space.alliesSize()).isEqualTo(0);

        Kingdom rulingKingdom = southeros.rulingKingdomAmong(competition.competingKingdoms());

        assertThat(rulingKingdom).isEqualTo(Kingdom.nullKingdom());

        // NEW ROUND OF VOTING

        competition.resetAllegiances();

        when(messenger.generateMessages(refEq(fire), any())).thenReturn(messages(fire, "Octopus"));
        when(messenger.generateMessages(refEq(space), any())).thenReturn(messages(space, "Panda Mammoth"));

        competition.start();

        assertThat(fire.alliesSize()).isEqualTo(1);
        assertThat(space.alliesSize()).isEqualTo(2);

        rulingKingdom = southeros.rulingKingdomAmong(competition.competingKingdoms());

        assertThat(rulingKingdom).isEqualTo(space);
        assertThat(rulingKingdom.allies()).containsExactlyInAnyOrder(land, ice);

    }

    @After
    public void tearDown() throws Exception {
        southeros.clearKingdoms();
    }

    private List<Message> messages(Kingdom senderKingdom, String randomMessage) {
        List<Message> messageList = new ArrayList<>();
        Set<Kingdom> receivingKingdoms = southeros.kingdoms();
        receivingKingdoms.remove(senderKingdom);
        receivingKingdoms.forEach(receivingKingdom -> {
            Message message = new Message(senderKingdom, receivingKingdom, randomMessage);
            messageList.add(message);
        });
        return messageList;
    }
}
