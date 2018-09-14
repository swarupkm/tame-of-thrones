package com.swarup.tameofthrones.problem2;

import com.swarup.tameofthrones.Kingdom;
import com.swarup.tameofthrones.exceptions.InvalidMessageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Messenger {

    private List<String> messages = new ArrayList<>();

    public List<Message> generateMessages(Kingdom senderKingdom, Set<Kingdom> receivingKingdoms) {
        List<Message> randomMessageList = new ArrayList<>();
        receivingKingdoms.forEach(receivingKingdom -> {
            if (senderKingdom != receivingKingdom) {
                Message message = new Message(senderKingdom, receivingKingdom, randomMessage());
                randomMessageList.add(message);
            }
        });
        return randomMessageList;
    }

    private String randomMessage() {
        int messageSize = messages.size();
        int i = new Random().nextInt(messageSize);
        return messages.get(i);
    }

    public void getMessagesFromFile(String path) {
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine())
                messages.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            throw new InvalidMessageException("Message file not found");
        }
    }

}
