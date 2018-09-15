package com.swarup.tameofthrones;

import java.util.Objects;

public class Message {
    private Kingdom sender;
    private Kingdom receiver;
    private String message;

    public Message(Kingdom sender, Kingdom receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public Kingdom getSender() {
        return sender;
    }

    public Kingdom getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(sender, message1.sender) &&
                Objects.equals(receiver, message1.receiver) &&
                Objects.equals(message, message1.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sender, receiver, message);
    }
}
