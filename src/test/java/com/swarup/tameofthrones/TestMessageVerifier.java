package com.swarup.tameofthrones;

import com.swarup.tameofthrones.exceptions.InvalidMessageException;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestKingdomMessageCombination {
    @Test
    public void should_send_message_to_another_kingdom_which_has_emblem_characters_and_be_successful() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);

        kingdom1.sendMessageTo(kingdom2, "snakensake");

        assertThat(kingdom1.hasAlly(kingdom2)).isTrue();

    }

    @Test
    public void should_recieving_kingdom_should_have_sender_kingdom_as_ally_after_successfull_message() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);

        kingdom1.sendMessageTo(kingdom2, "snakensake");

        assertThat(kingdom2.hasAlly(kingdom1)).isTrue();

    }

    @Test
    public void should_send_message_to_another_kingdom_which_does_not_have_emblem_characters_and_not_be_successful() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);
        Kingdom kingdom2 = new Kingdom("FIRE", "snake", null);

        kingdom1.sendMessageTo(kingdom2, "htgerbx");

        assertThat(kingdom1.hasAlly(kingdom2)).isFalse();

    }

    @Test(expected = InvalidMessageException.class)
    public void should_not_send_message_to_self() {
        Kingdom kingdom1 = new Kingdom("ICE", "Whale", null);

        kingdom1.sendMessageTo(kingdom1, "htgerbx");

    }
}