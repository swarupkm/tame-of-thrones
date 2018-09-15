package com.swarup.tameofthrones;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMessenger {
    private static final String MESSAGE = "\"Summer is coming\"";
    private Messenger messenger;


    @Before
    public void setup() {
        messenger = new Messenger();
        messenger.prepareMessagesFromFile("src/test/resources/messages.txt");
    }

    @Test
    public void should_generate_message_on_behalf_of_sender_kingdom_for_receiver_kingdoms() {
        Kingdom sender = new Kingdom("K1", "E1", null);
        Kingdom receiver1 = new Kingdom("K2", "E3", null);
        Kingdom receiver2 = new Kingdom("K3", "E3", null);

        List<Message> messages = messenger.generateMessages(sender, kingdomSet(receiver1, receiver2));

        assertThat(messages).containsExactlyInAnyOrder(new Message(sender, receiver1, MESSAGE), new Message(sender, receiver2, MESSAGE));
    }

    @Test
    public void should_not_generate_message_if_receiving_kingdoms_contains_self() {
        Kingdom sender = new Kingdom("K1", "E1", null);
        Kingdom receiver = sender;

        List<Message> messages = messenger.generateMessages(sender, kingdomSet(receiver));

        assertThat(messages).isEmpty();
    }

    @Test
    public void should_retrieve_message_from_message_files() {
        assertThat(messenger.messages()).contains(MESSAGE);
    }

    private Set<Kingdom> kingdomSet(Kingdom... receivers) {
        Set<Kingdom> kingdoms = new HashSet<>();
        Stream.of(receivers).forEach(kingdom -> kingdoms.add(kingdom));
        return kingdoms;
    }
}