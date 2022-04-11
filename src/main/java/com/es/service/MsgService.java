package com.es.service;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class MsgService {
    private final List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        if (messages.contains(message)) {
            System.out.println(message);
            messages.clear();
        } else {
            messages.add(message);
        }

    }
}
