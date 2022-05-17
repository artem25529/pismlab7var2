package com.es.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@MessageDriven(mappedName = "jms/dest", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class Consumer implements MessageListener {
    private final List<String> messages = new ArrayList<>();
    private final Path PATH = Paths.get("E:\\Programs\\glassfish5\\file.txt");

    @EJB
    private MsgService msgService;

    @Override
    public synchronized void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            if (messages.size() < 10) {
                String text = textMessage.getText();
                messages.add(text);
            } else {
                Files.write(PATH, findMinVowelsQty().getBytes());
                Files.write(PATH, findMaxVowelsQty().getBytes());
                messages.clear();
            }
        } catch (JMSException | IOException e) {
            e.printStackTrace();
        }
    }

    private String findMinVowelsQty() {
        return messages.stream()
                .min(Comparator.comparing(this::getVowelQty))
                .orElse(null);
    }

    private String findMaxVowelsQty() {
        return messages.stream()
                .max(Comparator.comparing(this::getVowelQty))
                .orElse(null);
    }

    private int getVowelQty(String s) {
        int res = 0;
        for (char c : s.toCharArray()) {
            if (Character.toString(c)
                    .matches("[aoiuye]")) {
                res++;
            }
        }

        return res;
    }
}
