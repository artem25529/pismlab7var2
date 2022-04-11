package com.es.service;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.*;

@Singleton
public class Producer {
    @Resource(mappedName = "jms/queue")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/dest")
    private Queue queue;

    public void sendJmsMessage(String message) {
        try (Connection connection = connectionFactory.createConnection()) {
            Session session = connection.createSession();
            MessageProducer producer = session.createProducer(queue);
            producer.send(createJmsMessage(session, message));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public TextMessage createJmsMessage(Session session, String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(message);
        return textMessage;
    }
}
