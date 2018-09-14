package com.swarup.tameofthrones.problem2;

import com.swarup.tameofthrones.Kingdom;

public class Message {
    private Kingdom sender;
    private Kingdom reciever;
    private String message;

    public Message(Kingdom sender, Kingdom reciever, String message) {
        this.sender = sender;
        this.reciever = reciever;
        this.message = message;
    }

    public Kingdom getSender() {
        return sender;
    }

    public Kingdom getReciever() {
        return reciever;
    }

    public String getMessage() {
        return message;
    }
}
