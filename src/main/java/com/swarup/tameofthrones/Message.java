package com.swarup.tameofthrones;

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
}
