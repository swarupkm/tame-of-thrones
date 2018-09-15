package com.swarup.tameofthrones;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestMessageVerifier {

    private MessageVerifier verifier;

    @Before
    public void setup() {
        verifier = new MessageVerifier();
    }

    @Test
    public void should_check_message_which_has_emblem_characters_and_be_successful() {
        Kingdom kingdom = new Kingdom("FIRE", "snake", null);

        boolean verified = verifier.verify(kingdom, "snakesnake");

        assertThat(verified).isTrue();

    }

    @Test
    public void should_check_message_which_does_not_have_emblem_characters_and_be_successful() {
        Kingdom kingdom = new Kingdom("FIRE", "snake", null);

        boolean verified = verifier.verify(kingdom, "htgerbx");

        assertThat(verified).isFalse();

    }

}
