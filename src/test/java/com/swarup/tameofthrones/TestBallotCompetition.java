package com.swarup.tameofthrones;

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

public class TestBallotCompetition {

    private static final String SUCCESS_MESSAGE_FOR_LAND_AND_WATER = "PandaOctopus";
    private Southeros southeros;

    private BallotCompetition competition;

    private Messenger messenger;

    @Before
    public void setup() {
        southeros = Southeros.get();
        southeros.register(new Kingdom("LAND", "Panda"),
                new Kingdom("WATER", "Octopus"),
                new Kingdom("ICE", "Mammoth"),
                new Kingdom("AIR", "Owl"),
                new Kingdom("FIRE", "Dragon"),
                new Kingdom("SPACE", "Gorilla"));
        messenger = mock(Messenger.class);
        competition = new BallotCompetition(southeros, messenger);
    }

    @Test
    public void should_have_allegience_list_and_not_allow_them_to_participate_once_they_give_allegiance() {
        Kingdom fire = southeros.getKingdom("FIRE");
        Kingdom space = southeros.getKingdom("SPACE");
        Kingdom land = southeros.getKingdom("LAND");
        Kingdom water = southeros.getKingdom("WATER");

        competition.register(fire, space);

        when(messenger.generateMessages(refEq(fire), any())).thenReturn(messages(fire));
        when(messenger.generateMessages(refEq(space), any())).thenReturn(messages(space));

        competition.start();

        assertThat(competition.allegianceProviders()).containsExactlyInAnyOrder(land, water);

        assertThat(fire.alliesSize()).isEqualTo(2);
        assertThat(space.alliesSize()).isEqualTo(0);
        assertThat(land.alliesSize()).isEqualTo(1);
        assertThat(water.alliesSize()).isEqualTo(1);
    }

    @Test
    public void should_not_allow_compeititors_to_get_allied_even_if_message_is_verified() {
        Kingdom land = southeros.getKingdom("LAND");
        Kingdom water = southeros.getKingdom("WATER");

        competition.register(land, water);

        when(messenger.generateMessages(refEq(land), any())).thenReturn(messages(land));
        when(messenger.generateMessages(refEq(water), any())).thenReturn(messages(water));

        assertThat(land.alliesSize()).isEqualTo(0);
        assertThat(water.alliesSize()).isEqualTo(0);
        assertThat(competition.allegianceProviders()).size().isEqualTo(0);


    }

    @Test
    public void should_clear_allies_and_allegiance_upon_reset_of_allegiances() {
        Kingdom fire = southeros.getKingdom("FIRE");
        Kingdom space = southeros.getKingdom("SPACE");

        competition.register(fire, space);

        when(messenger.generateMessages(refEq(fire), any())).thenReturn(messages(fire));
        when(messenger.generateMessages(refEq(space), any())).thenReturn(messages(space));

        competition.start();
        competition.resetAllegiances();

        assertThat(competition.allegianceProviders()).size().isEqualTo(0);
        southeros.kingdoms().forEach(kingdom -> assertThat(kingdom.alliesSize()).isEqualTo(0));


    }

    private List<Message> messages(Kingdom senderKingdom) {
        List<Message> messageList = new ArrayList<>();
        Set<Kingdom> receivingKingdoms = southeros.kingdoms();
        receivingKingdoms.remove(senderKingdom);
        receivingKingdoms.forEach(receivingKingdom -> {
            Message message = new Message(senderKingdom, receivingKingdom, SUCCESS_MESSAGE_FOR_LAND_AND_WATER);
            messageList.add(message);
        });
        return messageList;
    }

    @After
    public void tearDown() throws Exception {
        southeros.clearKingdoms();
    }

}